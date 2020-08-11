package client.gui.panels;

import client.actionController.StatusActionController;
import client.gui.myComponents.MyButton;
import client.gui.myComponents.MyCardButton;
import client.gui.myComponents.MyJLabel;
import client.gui.myComponents.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatusPanel extends MyPanel {

    private JPanel cards, buttonsPanel;
    private String selectedDeck;
    private Color color1, color2;
    private StatusActionController actionController;

    public StatusPanel(StatusActionController actionController) {
        this.actionController = actionController;
        this.backGroundFile = "StatusBG.jpg";
        this.setPreferredSize(new Dimension(configLoader.readInteger("mainFrameWidth"), configLoader.readInteger("mainFrameHeight")));
        this.setLayout(null);
        cards = new MyPanel(null, true, new GridLayout(3, 6, 4, 7), this);
        JScrollPane jScrollPane = new JScrollPane(cards, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setBounds(50, 320, 800, 300);
        this.add(jScrollPane);
        jScrollPane.setOpaque(false);
        jScrollPane.getViewport().setOpaque(false);
        buttonsPanel = new MyPanel(null, true, new FlowLayout(), this);
        buttonsPanel.setBounds(950, 50, 200, 580);
        new MyButton("back to Menu", "pinkCrystal100.png", this, actionEvent -> actionController.back(), 50, 630);
        new MyButton("exit", "pinkCrystal100.png", this, actionEvent -> actionController.exit(),200, 630);
        new MyButton("select deck", "GreenCrystal.png", this, actionEvent -> {
            if (selectedDeck == null)
                JOptionPane.showMessageDialog(cards.getParent(), "you should choose a deck from the deck bar first");
            else actionController.setDeckAsCurrent(selectedDeck);
        }, 750, 250);
        color1 = new Color(25, 45, 172);
        color2 = new Color(34, 4, 76);
        actionController.showDecksInStatus();
    }

    public void showDecks(ArrayList<String> decks) {
        buttonsPanel.removeAll();
        for (String deck : decks) {
            new MyButton(deck, "darkBlueButton.jpg", buttonsPanel, actionEvent -> {
                selectedDeck = deck;
                actionController.showCardsInStatus(deck);
                actionController.drawDeckInfo(selectedDeck);
                actionController.log(" button clicked - Status - to show information of deck " + deck);
                repaint();
            });
        }
        buttonsPanel.repaint();
        revalidate();
    }

    public void updateCardsPanel(HashMap<Long, String> cardsList) {
        cards.removeAll();
        for (Map.Entry<Long, String> entry : cardsList.entrySet())
            new MyCardButton(actionController, entry.getKey(), entry.getValue(), 100, cards, null, false);
        cards.repaint();
        revalidate();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (selectedDeck != null) g2d.drawImage(imageLoader.loadImage("bigPInkArea.png"), 200, 35, null);
    }

    public void drawDeckInfo(String name, int cups, int wins, int totalGames, double winsToAll, int avePrice, String hero, String moseUsedCard, boolean currentDeck) {
        new MyJLabel("Name : " + name, color1, 15, this, 240, 50, 400, 20);
        new MyJLabel("cups : " + cups, color1, 15, this, 240, 80, 400, 20);
        new MyJLabel("Total wins with this deck : " + wins, color1, 15, this, 240, 95, 400, 20);
        new MyJLabel("Total games played with this : " + totalGames, color1, 15, this, 240, 110, 400, 20);
        new MyJLabel("wins to all : " + winsToAll, color1, 15, this, 240, 125, 400, 20);
        new MyJLabel("Average price of cards : " + avePrice, color1, 15, this, 240, 140, 400, 20);
        new MyJLabel("hero  : " + hero, color1, 15, this, 240, 155, 400, 20);
        new MyJLabel("most used card  : " + moseUsedCard, color1, 15, this, 240, 170, 400, 20);
        if (currentDeck) new MyJLabel("this is selected as your current deck", color2, 15, this, 240, 187, 400, 20);
        new MyJLabel("click on an another deck to change your current deck", color2, 15, this, 240, 202, 400, 20);
        //todo u can have the labels out of the method
    }
}
