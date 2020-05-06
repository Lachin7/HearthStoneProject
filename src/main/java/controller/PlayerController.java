package controller;

import models.Cards.Card;
import gui.GameFrame;
import models.Deck;
import models.Player;
import models.board.InfoPassive;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;

import static JSON.jsonForCards.jsonForCards.creatCardFromjson;
import static JSON.jsonForPlayers.jsonForPlayers.*;

public class PlayerController {

    private Logger PlayerLOGGER = Logger.getLogger("PlayerLog");
    private ArrayList<InfoPassive> passives;
    private String[] InfoPassives;

    public String SignIn(String userName, String pass) {
        String message = "";
        if (getPlayerFiles(userName) == null) message = "Error! There isn't an account in this name , Try again..";
                else {
            try {
                if(!jsonFileReader(userName).getPlayerPassword().equals(new Player().getHashedPassword(pass)))
                    message = "Error! Wrong password , Try again..";
                else {
            Player player = null;
            try {
                player = jsonFileReader(userName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Controller.getInstance().setCurrentPlayer(player);
            player.setSignedUp(true);
                    LogManager.getLogManager().reset();
            FileHandler fileHandler = null;
            try {
                fileHandler = new FileHandler("./src/main/java/logs/" + player.getPlayerName() + "-" + player.getPlayerID() + ".log", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileHandler.setFormatter(new SimpleFormatter());
                    PlayerLOGGER.addHandler(fileHandler);
                    PlayerLOGGER.info("USER  : " + player.getPlayerName() + "\nSigned_In AT :" +
                            new SimpleDateFormat(" yyyy/MM/dd HH:mm:ss").format(new Date()) + "\nPASSWORD : " + player.getPlayerPassword() + "\n");
                    GameFrame.getInstance().goToPanel("mainMenu");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    public String SignUp(String CreatedName, String CreatedPass){
        String message = " ";

        if(getPlayerFiles(CreatedName)!=null) message ="Error! Sorry this user name is taken, Try something else..";
        else if(CreatedName.length()<3) message = "Error! your username should contain more than 2 Characters , try again";
        else if(CreatedPass.length()<3) message = "Error! your password should contain more than 2 Characters , try again";
        else {
            Player SignedUpPlayer = new Player();
            SignedUpPlayer.setPlayerName(CreatedName);
            SignedUpPlayer.setPlayerPassword(SignedUpPlayer.getHashedPassword(CreatedPass));
            SignedUpPlayer.setPlayerCoins(50);
            SignedUpPlayer.setPlayerID(System.currentTimeMillis());
            SignedUpPlayer.setPlayersChoosedHero(SignedUpPlayer.getPlayersMage());
            ArrayList<Card> optionalMageDeck = null;
            optionalMageDeck = (new ArrayList<Card>(Arrays.asList(creatCardFromjson("Polymorph"),creatCardFromjson("RollingFireball"),creatCardFromjson("MurlocRaider"),creatCardFromjson("MalygossExplosion"),creatCardFromjson("MalygossNova"),creatCardFromjson("Backstab"),creatCardFromjson("GoblinBomb"),creatCardFromjson("LostSpirit"),creatCardFromjson("SerratedTooth"),creatCardFromjson("MagmaRager"))));
            Deck deck = new Deck("optional Mage deck",optionalMageDeck);
            deck.setHero(Card.HeroClass.MAGE);
            SignedUpPlayer.setPlayersDeck(deck);
            SignedUpPlayer.setDecks(new ArrayList<>()) ;
            SignedUpPlayer.getDecks().add(SignedUpPlayer.getPlayersDeck());
            ArrayList<Card> optionalAllCards = (new ArrayList<Card>(Arrays.asList(creatCardFromjson("Polymorph"),creatCardFromjson("RollingFireball"),creatCardFromjson("MurlocRaider"),creatCardFromjson("MalygossExplosion"),creatCardFromjson("MalygossNova"),creatCardFromjson("Backstab"),creatCardFromjson("GoblinBomb"),creatCardFromjson("LostSpirit"),creatCardFromjson("SerratedTooth"),creatCardFromjson("MagmaRager"))));
            optionalAllCards.add(creatCardFromjson("TimeRip"));
            optionalAllCards.add(creatCardFromjson("BlinkFox"));
            optionalAllCards.add(creatCardFromjson("HungryCrab"));
            SignedUpPlayer.setALLPlayersCards(optionalAllCards);
            SignedUpPlayer.getPlayersUnlockedHeroes().add(SignedUpPlayer.getPlayersMage());
            try {
                jsonTofilePlayer(SignedUpPlayer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Controller.getInstance().setCurrentPlayer(SignedUpPlayer);
            LogManager.getLogManager().reset();
            FileHandler fileHandler = null;
            try {
                fileHandler = new FileHandler("./src/main/java/logs/"+ SignedUpPlayer.getPlayerName()+"-"+SignedUpPlayer.getPlayerID()+".log",true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileHandler.setFormatter(new SimpleFormatter());
            PlayerLOGGER.addHandler(fileHandler);
            PlayerLOGGER.info("USER  : " + SignedUpPlayer.getPlayerName() + "\nCREATED AT :" +
                    new SimpleDateFormat(" yyyy/MM/dd HH:mm:ss").format(new Date()) + "\nPASSWORD : " + SignedUpPlayer.getPlayerPassword() + "\n");
            message = "you are signed up successfully! BEGIN YOUR JOURNEY IN HEARTH STONE!!";
            GameFrame.getInstance().goToPanel("mainMenu");
        }
        return message;
    }

    public Logger getPlayerLOGGER() {
        return PlayerLOGGER;
    }


    public void checkForNewGame() {
        if(Controller.getInstance().getCurrentPlayer().getMakeNewGame()==null || Controller.getInstance().getCurrentPlayer().getMakeNewGame()){
            Controller.getInstance().getCurrentPlayer().setCurrentMana(0);
            Controller.getInstance().getCurrentPlayer().setHandsCards(new ArrayList<>()) ;
            Controller.getInstance().getCurrentPlayer().setFieldCardsInGame(new ArrayList<>());
            for(Card card : Controller.getInstance().getCurrentPlayer().getPlayersDeck().getCards()){
                Controller.getInstance().getCurrentPlayer().getDeckCardsInGame().add(card);
            }
        }
    }

    public void setPassives(){
        passives = InfoPassive.getRandomPassives(3);
        InfoPassives = new String[]{passives.get(0).getName(),passives.get(1).getName(),passives.get(2).getName()};
        int response = JOptionPane.showOptionDialog(null,passives.get(0).getName() + " : " + passives.get(0).getExplanation()+
                        "\n" + passives.get(1).getName() + " : " + passives.get(1).getExplanation()+ "\n" +passives.get(2).getName() + " : " + passives.get(2).getExplanation(),
                "choose your Info Passive", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, InfoPassives, InfoPassives[0]);
        Controller.getInstance().getCurrentPlayer().setInfoPassive(passives.get(response));
        Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO,"passive "+ passives.get(response)+  " selected - PlayPanel");
    }
}
