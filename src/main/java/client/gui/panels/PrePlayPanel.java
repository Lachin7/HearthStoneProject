package client.gui.panels;

import client.actionController.PlayActionController;
import client.gui.GameFrame;
import client.gui.myComponents.MyButton;
import client.gui.myComponents.MyCardButton;
import client.gui.myComponents.MyJLabel;
import client.gui.myComponents.MyPanel;
import client.gui.panels.subPanels.RunningGamesList;
import lombok.Getter;
import server.models.board.InfoPassive;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class PrePlayPanel extends MyPanel {

    private MyButton deckReader, online, practice, AI, goldenTime, oneShot, tavernBrawl, confirm, watchGame;
    private MyCardButton card1, card2, card3;
    private String[] InfoPassives;
    private ArrayList<InfoPassive> passives;
    private int card1Parity = 0, card2Parity = 0, card3Parity = 0;
    private PlayActionController playActionController;
    @Getter
    private RunningGamesList gamesList;


    public PrePlayPanel(PlayActionController playActionController) {
        this.playActionController = playActionController;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(configLoader.readInteger("mainFrameWidth"), configLoader.readInteger("mainFrameHeight")));
        setUpChooseMode();
    }

    public void setUpChooseMode() {
        this.removeAll();
        this.backGroundFile = "chooseModeBG.jpg";
        ActionListener actionListener = actionEvent -> {
            MyButton source = (MyButton) actionEvent.getSource();
            if (source == watchGame) playActionController.showWatchableGames();
            else {
//                if (source!=AI && source!=practice)playActionController.goToPanel("wait");
                playActionController.declareGameMode(source.getName());
               if (source!=practice&& source!=AI)new MyJLabel("Wait for Opponent to Join", Color.YELLOW, 40, this, 350, 400, 600, 50);
            }
        };
        deckReader = new MyButton("Deck Reader", "pinkCrystal150.png", this, actionListener, 25, 200, 200, 81);
        online = new MyButton("online", "pinkCrystal150.png", this, actionListener, 25, 300, 200, 81);
        practice = new MyButton("Practice", "pinkCrystal150.png", this, actionListener, 25, 400, 200, 81);
        AI = new MyButton("AI", "pinkCrystal150.png", this, actionListener, 25, 500, 200, 81);
        oneShot = new MyButton("one Shot", "pinkCrystal150.png", this, actionListener, 975, 200, 200, 81);
        goldenTime = new MyButton("golden Time", "pinkCrystal150.png", this, actionListener, 975, 300, 200, 81);
        tavernBrawl = new MyButton("Tavern Brawl", "pinkCrystal150.png", this, actionListener, 975, 400, 200, 81);
        watchGame = new MyButton("Watch Games", "pinkCrystal150.png", this, actionListener, 975, 500, 200, 81);
        new MyButton("back to Menu", "pinkCrystal100.png", this, actionEvent -> playActionController.back(),25, 625);
        this.repaint();
        revalidate();
    }

    public void choosePassive() {
        passives = InfoPassive.getRandomPassives(3);
        InfoPassives = new String[]{passives.get(0).getName(), passives.get(1).getName(), passives.get(2).getName()};
        int response = JOptionPane.showOptionDialog(null, passives.get(0).getName() + " : " + passives.get(0).getExplanation() +
                        "\n" + passives.get(1).getName() + " : " + passives.get(1).getExplanation() + "\n" + passives.get(2).getName() + " : " + passives.get(2).getExplanation(),
                 " : choose Info Passive", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, InfoPassives, InfoPassives[0]);
        if (response!=-1)playActionController.declarePassive(passives.get(response));
    }

    public synchronized void chooseFirstCards(HashMap<Long,String> cards) {
        this.removeAll();
        this.backGroundFile = "Choose3CardBG.jpg";
        ArrayList<Long> replacedCard = new ArrayList<>();
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
                if (card1Parity % 2 == 1) replacedCard.add(card1.getId());
                if (card2Parity % 2 == 1) replacedCard.add(card2.getId());
                if (card3Parity % 2 == 1) replacedCard.add(card3.getId());
                playActionController.declareFirstCardChoices(replacedCard);
                playActionController.launchGame();
            }
        };
        card1 = new MyCardButton(playActionController, (Long) cards.keySet().toArray()[0], (String) cards.values().toArray()[0], 200,this, actionListener, true,250, 200);
        card2 = new MyCardButton(playActionController, (Long) cards.keySet().toArray()[1], (String) cards.values().toArray()[1], 200,this, actionListener, true,500, 200);
        card3 = new MyCardButton(playActionController, (Long) cards.keySet().toArray()[2], (String) cards.values().toArray()[2], 200,this, actionListener, true,750, 200);
        confirm = new MyButton("Confirm", "pinkCrystal150.png", this, actionListener,500, 550);
        this.repaint();
        revalidate();
    }

    public void setWatchableGamesList(HashMap<String, String> names){
        gamesList = new RunningGamesList(playActionController);
        gamesList.setLabels(names);
        this.add(gamesList);
        gamesList.setBounds(400,50,400,300);
    }

}
