package gui.panels;

import controller.BoardController;
import controller.Controller;
import controller.modes.AI;
import controller.modes.DeckReader;
import controller.modes.Online;
import controller.modes.Practice;
import gui.GameFrame;
import gui.myComponents.MyButton;
import gui.myComponents.MyCardButton;
import gui.myComponents.MyPanel;
import models.Deck;
import models.Player;
import models.board.InfoPassive;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;

public class PrePlayPanel extends MyPanel{

     private MyButton deckReader, online, practice, AI, confirm;
     private MyCardButton card1, card2, card3;
     private BoardController boardController;
    private String[] InfoPassives;
    private ArrayList<InfoPassive> passives;
    private int card1Parity = 0, card2Parity =0, card3Parity =0, num=0;
    private Deck deck ;


    public PrePlayPanel() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(configLoader.readInteger("mainFrameWidth"), configLoader.readInteger("mainFrameHeight")));
    }

     public void setUpChooseMode(){

            this.removeAll();
            this.backGroundFile = "chooseModeBG.jpg";
            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    MyButton source = (MyButton) actionEvent.getSource();
                    if (source == deckReader) {
                        boardController = new DeckReader();
                        ChoosePassive(boardController.getFriendlyPlayer());
                        ChoosePassive(boardController.getEnemyPlayer());
                    }
                    if (source == online) boardController = new Online();
                    if (source == practice) {
                        boardController = new Practice();
                        ChoosePassive(boardController.getFriendlyPlayer());
                        ChoosePassive(boardController.getEnemyPlayer());
                        ChooseFirstCards(boardController.getFriendlyPlayer(),true);

                    }
                    if (source == AI) {
                        boardController = new AI();
                        ChoosePassive(boardController.getFriendlyPlayer());
                        ChooseFirstCards(boardController.getFriendlyPlayer(),false);

                    }
                    if (boardController.getClass() != DeckReader.class) checkNecessaryItems();
                    GameFrame.getInstance().setFirstGame(false);
                }
            };
            deckReader = new MyButton("Deck Reader", "pinkCrystal150.png", this, actionListener, 200, 81);
            deckReader.setBounds(25, 200, 200, 81);
            online = new MyButton("online", "pinkCrystal150.png", this, actionListener, 200, 81);
            online.setBounds(25, 300, 200, 81);
            practice = new MyButton("Practice", "pinkCrystal150.png", this, actionListener, 200, 81);
            practice.setBounds(975, 200, 200, 81);
            AI = new MyButton("AI", "pinkCrystal150.png", this, actionListener, 200, 81);
            AI.setBounds(975, 300, 200, 81);
            customComponent.backToMenuButton(this, 25, 625, null);
            GameFrame.getInstance().goToPanel("");
            this.repaint();
            revalidate();

    }

    private void checkNecessaryItems(){
            if (Controller.getInstance().getMainPlayer().getPlayersDeck() == null) {
                JOptionPane.showMessageDialog(null, "you don't have a current deck\n let's go to status and choose you deck");
                GameFrame.getInstance().goToPanel("statusPanel");
            }
            if (Controller.getInstance().getMainPlayer().getPlayersDeck().getHero() == null) {
                JOptionPane.showMessageDialog(null, "you don't have a choosed hero for your deck\n let's go to collections and choose a hero! ");
                GameFrame.getInstance().goToPanel("collectionPanel");
            }
            if (Controller.getInstance().getMainPlayer().getPlayersDeck().getCards().size() < 10) {
                JOptionPane.showMessageDialog(null, "you don't have a enough number of cards in your deck\n let's go to collections and have at least 10 cards in your deck \n also you can change your deck in status");
                GameFrame.getInstance().goToPanel("collectionPanel");
            }
    }

    private void ChoosePassive(Player player) {
        passives = InfoPassive.getRandomPassives(3);
        InfoPassives = new String[]{passives.get(0).getName(), passives.get(1).getName(), passives.get(2).getName()};
        int response = JOptionPane.showOptionDialog(null, passives.get(0).getName() + " : " + passives.get(0).getExplanation() +
                        "\n" + passives.get(1).getName() + " : " + passives.get(1).getExplanation() + "\n" + passives.get(2).getName() + " : " + passives.get(2).getExplanation(),
                player.getPlayerName() + " : choose Info Passive", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, InfoPassives, InfoPassives[0]);
        player.setInfoPassive(passives.get(response));
        boardController.getPlayerLogger().log(Level.INFO, "passive " + passives.get(response) + " is selected for : "+player.getPlayerName()+" - PlayPanel");
    }

    private synchronized void ChooseFirstCards(Player player, boolean chooseForEnemy){
//        synchronized (this) {
            this.removeAll();
            this.backGroundFile = "Choose3CardBG.jpg";
            ArrayList<String> replacedCard = new ArrayList<>();
            ActionListener actionListener = actionEvent -> {
                if (actionEvent.getSource() == card1) {
                    card1Parity++;
                    if (card1Parity % 2 == 1)
                        card1.setBorder(BorderFactory.createEtchedBorder(Color.MAGENTA, Color.BLACK));
                    else card1.setBorder(null);
                }
                if (actionEvent.getSource() == card2) {
                    card2Parity++;
                    if (card2Parity % 2 == 1)
                        card2.setBorder(BorderFactory.createEtchedBorder(Color.MAGENTA, Color.BLACK));
                    else card2.setBorder(null);
                }
                if (actionEvent.getSource() == card3) {
                    card3Parity++;
                    if (card3Parity % 2 == 1)
                        card3.setBorder(BorderFactory.createEtchedBorder(Color.MAGENTA, Color.BLACK));
                    else card3.setBorder(null);
                }
                if (actionEvent.getSource() == confirm) {
                    if (card1Parity % 2 == 1) replacedCard.add(card1.getName());
                    if (card2Parity % 2 == 1) replacedCard.add(card2.getName());
                    if (card3Parity % 2 == 1) replacedCard.add(card3.getName());
                    boardController.getFirstChoices(replacedCard, player);
                    boardController.initialGameSetUps(player);
                    num++;
                    if(chooseForEnemy) {
                        if (num == 1) ChooseFirstCards(boardController.getEnemyPlayer(), true);
                        if (num == 2) {
                            GameFrame.getInstance().goToPanel("mainPlayPanel");
                        }
                    }
                    if(!chooseForEnemy)GameFrame.getInstance().goToPanel("mainPlayPanel");
                }
            };
            card1 = new MyCardButton(player.getHandsCards().get(0).getName(), 200, this, actionListener);
            card1.setBounds(250, 200, card1.getWidth(), card1.getHeight());
            card2 = new MyCardButton(player.getHandsCards().get(1).getName(), 200, this, actionListener);
            card2.setBounds(500, 200, card1.getWidth(), card1.getHeight());
            card3 = new MyCardButton(player.getHandsCards().get(2).getName(), 200, this, actionListener);
            card3.setBounds(750, 200, card1.getWidth(), card1.getHeight());
            confirm = new MyButton("Confirm", "pinkCrystal150.png", this, actionListener);
            confirm.setBounds(500, 550, confirm.getWidth(), confirm.getHeight());
            this.repaint();
            revalidate();
//        }
    }

     public BoardController getBoardController() {
         return boardController;
     }
 }
