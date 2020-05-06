package controller;

import gui.GameFrame;

import gui.MyAudioPlayer;
import gui.myComponents.MyCardButton;
import models.Cards.Card;
import models.Cards.Spell;
import models.board.InfoPassive;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;

public class BoardController {

    private File eventsLog;
    private FileWriter fileWriter;
    private Random random ;
    private CardController  cardController;
    private int Mana ;
    private ArrayList<Card> DeckCardsInGame;
    private MyAudioPlayer audioPlayer;


    public BoardController(){
        DeckCardsInGame = Controller.getInstance().getCurrentPlayer().getDeckCardsInGame();
        random = new Random();
        cardController = new CardController();
        makeEvents();
        audioPlayer = MyAudioPlayer.getInstance();
        if(Controller.getInstance().getCurrentPlayer().getInfoPassive()== InfoPassive.ManaJump) Controller.getInstance().getCurrentPlayer().setCurrentMana(1);
        Mana = Controller.getInstance().getCurrentPlayer().getCurrentMana();
        if(Controller.getInstance().getCurrentPlayer().getInfoPassive()== InfoPassive.PotionOfVitality) Controller.getInstance().getCurrentPlayer().getPlayersChoosedHero().setHP(60);
    }

    public void makeEvents(){
        eventsLog = new File("./src/main/java/logs/events/eventsLog");
        try {
            fileWriter = new FileWriter(eventsLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setVisible(JLabel[] labels, int i){
        for (int j = 0; j < i; j++) {
            labels[j].setVisible(true);
        }
        for (int j = i; j < labels.length ; j++) {
            labels[j].setVisible(false);
        }
    }


    public ArrayList<Card> getPlayersHandCards(){
        return Controller.getInstance().getCurrentPlayer().getHandsCards();
    }

    public void drawCard(Boolean firstDraw) throws IOException {
        System.out.println(DeckCardsInGame.size());

        int i = random.nextInt(DeckCardsInGame.size());
        if(Controller.getInstance().getCurrentPlayer().getHandsCards().size()!=12) {
            fileWriter.append("draw card "+DeckCardsInGame.get(i).getName()+" from deck to hand cards\n");
            fileWriter.flush();
            Controller.getInstance().getCurrentPlayer().getHandsCards().add(DeckCardsInGame.get(i));
            DeckCardsInGame.remove(i);
        }
        if(DeckCardsInGame.size()!=0 && (Controller.getInstance().getCurrentPlayer().getInfoPassive()==InfoPassive.TwiceDraw || firstDraw)){
            i = random.nextInt(DeckCardsInGame.size());
            fileWriter.append("draw card "+DeckCardsInGame.get(i).getName()+" from deck to hand cards\n");
            fileWriter.flush();
            Controller.getInstance().getCurrentPlayer().getHandsCards().add(DeckCardsInGame.get(i));
            DeckCardsInGame.remove(i);
        }
        if(firstDraw){
//            for (Card card : DeckCardsInGame){
//                if(card.getType()== Card.type.SPELL&& ((Spell)card).getQuest().length()>6){
//                    fileWriter.append("draw card "+DeckCardsInGame.get(i).getName()+" from deck to hand cards\n");
//                    fileWriter.flush();
//                    Controller.getInstance().getCurrentPlayer().getHandsCards().add(DeckCardsInGame.get(i));
//                    DeckCardsInGame.remove(i);
//                    return;
//                }
//            }
            i = random.nextInt(DeckCardsInGame.size());
            fileWriter.append("draw card "+DeckCardsInGame.get(i).getName()+" from deck to hand cards\n");
            fileWriter.flush();
            Controller.getInstance().getCurrentPlayer().getHandsCards().add(DeckCardsInGame.get(i));
            DeckCardsInGame.remove(i);
        }
        Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO,"draw card - PlayPanel\n");

    }

    public void endTurn(){
        if(Mana<10) Mana ++;
         Controller.getInstance().getCurrentPlayer().setCurrentMana(Mana);
    }

    public String setHeroHealth() {
       return Controller.getInstance().getCurrentPlayer().getPlayersChoosedHero().getHP() + "";
    }

    public void playCard(String card) throws IOException {
        if(Controller.getInstance().getCurrentPlayer().getInfoPassive()== InfoPassive.OffCards) Controller.getInstance().getCurrentPlayer().setCurrentMana(Controller.getInstance().getCurrentPlayer().getCurrentMana()-cardController.creatCard(card).getManaCost() + 1);
        else Controller.getInstance().getCurrentPlayer().setCurrentMana(Controller.getInstance().getCurrentPlayer().getCurrentMana()-cardController.creatCard(card).getManaCost());
        Controller.getInstance().getCurrentPlayer().getHandsCards().remove(cardController.creatCard(card));
        if(cardController.creatCard(card).getType()== Card.type.MINION) playMinion(card);
        if(cardController.creatCard(card).getType()== Card.type.SPELL) playSpell(card);
        if(cardController.creatCard(card).getType()== Card.type.WEAPON) playWeapon(card);
        Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO,"played card - PlayPanel");
    }

//    public void playHeroPower(){
//        if(Controller.getInstance().getCurrentPlayer().getInfoPassive()==InfoPassive.FreePower) Controller.getInstance().getCurrentPlayer().setCurrentMana(Controller.getInstance().getCurrentPlayer().getCurrentMana()-cardController.creatCard(card).getManaCost() + 1);
//    }

    private void playWeapon(String name) throws IOException {
//        new MyCardButton(name,100,GameFrame.getInstance().getPlayPanel().getFieldCards());
        Controller.getInstance().getCurrentPlayer().getFieldCardsInGame().add(cardController.creatCard(name));
        fileWriter.append("Player " + Controller.getInstance().getCurrentPlayer().getPlayerID()+ " played a Weapon card : "+name +"\n");
        fileWriter.flush();
    }

    private void playSpell(String name) throws IOException {
        audioPlayer.playQuick("spell.wav");
        fileWriter.append("Player " + Controller.getInstance().getCurrentPlayer().getPlayerID()+ " played a Spell card : "+name+"\n");
        fileWriter.flush();
    }

    private void playMinion(String name) throws IOException {
        audioPlayer.playQuick("Whip Crack-SoundBible.com-330576409.wav");
//     new MyCardButton(name,100,GameFrame.getInstance().getPlayPanel().getFieldCards());
        Controller.getInstance().getCurrentPlayer().getFieldCardsInGame().add(cardController.creatCard(name));
        fileWriter.append("Player " + Controller.getInstance().getCurrentPlayer().getPlayerID()+ " played a Minion card : "+name+"\n");
        fileWriter.flush();
    }

    public File getEventsLog() {
        return eventsLog;
    }


    public boolean hasEnoughMana(String selectedCard) {
        if(Controller.getInstance().getCurrentPlayer().getInfoPassive()== InfoPassive.OffCards)
            return Controller.getInstance().getCurrentPlayer().getCurrentMana() +1 >= cardController.creatCard(selectedCard).getManaCost();
        else return Controller.getInstance().getCurrentPlayer().getCurrentMana() >= cardController.creatCard(selectedCard).getManaCost();

    }

    public ArrayList<Card> getPlayerFieldCards() {
        return Controller.getInstance().getCurrentPlayer().getFieldCardsInGame();
    }


//    public ArrayList<Card> drawCard(Boolean firstDraw) throws IOException {
//        ArrayList<Card> result = new ArrayList<>();
//        int i = random.nextInt(Controller.getInstance().getCurrentPlayer().getPlayersDeck().getCards().size());
//        if(Controller.getInstance().getCurrentPlayer().getHandsCards().size()!=12) {
//            result.add(Controller.getInstance().getCurrentPlayer().getPlayersDeck().getCards().get(i));
//            fileWriter.append("draw card "+Controller.getInstance().getCurrentPlayer().getPlayersDeck().getCards().get(i).getName()+" from deck to hand cards");
//            fileWriter.flush();
//            Controller.getInstance().getCurrentPlayer().getHandsCards().add()
//            Controller.getInstance().getCurrentPlayer().getPlayersDeck().getCards().remove(i);
//        }
//        if(Controller.getInstance().getCurrentPlayer().getInfoPassive()==InfoPassive.TwiceDraw || firstDraw){
//            i = random.nextInt(Controller.getInstance().getCurrentPlayer().getPlayersDeck().getCards().size());
//            result.add(Controller.getInstance().getCurrentPlayer().getPlayersDeck().getCards().get(i));
//            fileWriter.append("draw card "+Controller.getInstance().getCurrentPlayer().getPlayersDeck().getCards().get(i).getName()+" from deck to hand cards\n");
//            fileWriter.flush();
//            Controller.getInstance().getCurrentPlayer().getPlayersDeck().getCards().remove(i);
//        }
//        if(firstDraw){
//            i = random.nextInt(Controller.getInstance().getCurrentPlayer().getPlayersDeck().getCards().size());
//            result.add(Controller.getInstance().getCurrentPlayer().getPlayersDeck().getCards().get(i));
//            fileWriter.append("draw card "+Controller.getInstance().getCurrentPlayer().getPlayersDeck().getCards().get(i).getName()+" from deck to hand cards\n");
//            fileWriter.flush();
//            Controller.getInstance().getCurrentPlayer().getPlayersDeck().getCards().remove(i);
//        }
//        Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO,"draw card - PlayPanel\n");
//        return result;
//    }
}
