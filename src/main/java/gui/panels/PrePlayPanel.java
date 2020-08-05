package gui.panels;

import client.actionController.PlayActionController;
import gui.GameFrame;
import gui.myComponents.MyButton;
import gui.myComponents.MyCardButton;
import gui.myComponents.MyPanel;
import models.board.InfoPassive;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PrePlayPanel extends MyPanel {

    private MyButton deckReader, online, practice, AI, goldenTime, oneShot, tavernBrawl, confirm, watchGame;
    private MyCardButton card1, card2, card3;
    private String[] InfoPassives;
    private ArrayList<InfoPassive> passives;
    private int card1Parity = 0, card2Parity = 0, card3Parity = 0;
    private PlayActionController playActionController;

    public PrePlayPanel(PlayActionController playActionController) {
        this.playActionController = playActionController;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(configLoader.readInteger("mainFrameWidth"), configLoader.readInteger("mainFrameHeight")));
    }

    public void setUpChooseMode() {
        this.removeAll();
        this.backGroundFile = "chooseModeBG.jpg";
        ActionListener actionListener = actionEvent -> {
            MyButton source = (MyButton) actionEvent.getSource();
            playActionController.declareGameMode(source.getName());
            if (source != deckReader && source != watchGame)playActionController.checkNecessaryItems();
            else playActionController.declareGameMode(source.getName());
            if (source == watchGame) playActionController.showWatchableGames();
        };
        deckReader = new MyButton("Deck Reader", "pinkCrystal150.png", this, actionListener, 25, 200, 200, 81);
        online = new MyButton("online", "pinkCrystal150.png", this, actionListener, 25, 300, 200, 81);
        practice = new MyButton("Practice", "pinkCrystal150.png", this, actionListener, 25, 400, 200, 81);
        AI = new MyButton("AI", "pinkCrystal150.png", this, actionListener, 25, 500, 200, 81);
        oneShot = new MyButton("one Shot", "pinkCrystal150.png", this, actionListener, 975, 200, 200, 81);
        goldenTime = new MyButton("golden Time", "pinkCrystal150.png", this, actionListener, 975, 300, 200, 81);
        tavernBrawl = new MyButton("tavern Brawl", "pinkCrystal150.png", this, actionListener, 975, 400, 200, 81);
        watchGame = new MyButton("Watch Games", "pinkCrystal150.png", this, actionListener, 975, 500, 200, 81);
        customComponent.backToMenuButton(this, 25, 625, playActionController);
        this.repaint();
        revalidate();
    }

    public void choosePassive() {
        passives = InfoPassive.getRandomPassives(3);
        InfoPassives = new String[]{passives.get(0).getName(), passives.get(1).getName(), passives.get(2).getName()};
        int response = JOptionPane.showOptionDialog(null, passives.get(0).getName() + " : " + passives.get(0).getExplanation() +
                        "\n" + passives.get(1).getName() + " : " + passives.get(1).getExplanation() + "\n" + passives.get(2).getName() + " : " + passives.get(2).getExplanation(),
                 " : choose Info Passive", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, InfoPassives, InfoPassives[0]);
        playActionController.declarePassive(passives.get(response));
    }

    public synchronized void chooseFirstCards(HashMap<Long,String> cards, boolean chooseForEnemy) {
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
                playActionController.declareFirstCardChoices(replacedCard);
                if (!chooseForEnemy) playActionController.launchGame();

            }
        };
        card1 = new MyCardButton(playActionController, (Long) cards.keySet().toArray()[0], (String) cards.values().toArray()[0], 200,this, actionListener, true);
        card1.setBounds(250, 200, card1.getWidth(), card1.getHeight());
        card1 = new MyCardButton(playActionController, (Long) cards.keySet().toArray()[1], (String) cards.values().toArray()[1], 200,this, actionListener, true);
        card2.setBounds(500, 200, card1.getWidth(), card1.getHeight());
        card1 = new MyCardButton(playActionController, (Long) cards.keySet().toArray()[2], (String) cards.values().toArray()[2], 200,this, actionListener, true);
        card3.setBounds(750, 200, card1.getWidth(), card1.getHeight());
        confirm = new MyButton("Confirm", "pinkCrystal150.png", this, actionListener,500, 550);
        this.repaint();
        revalidate();
    }

    public void showWatchableGames(HashMap<String,String> games){

    }

}
