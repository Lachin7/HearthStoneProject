package gui.panels;

import client.actionController.StatusActionController;
import models.Deck;
import gui.myComponents.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatusPanel extends MyPanel {

    private JPanel cards, buttonsPanel;
    private String selectedDeck;
    private MyButton selectDeck ;
    private Graphics2D g2d  ;
    private StatusActionController actionController;

    public StatusPanel(StatusActionController actionController){
        this.actionController = actionController;
        this.backGroundFile = "StatusBG.jpg";
        this.setPreferredSize(new Dimension(configLoader.readInteger("mainFrameWidth"),configLoader.readInteger("mainFrameHeight")));
        this.setLayout(null);
        cards = new MyPanel(null,true,new GridLayout(3,6,4,7),this);
        JScrollPane jScrollPane = new JScrollPane(cards, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setBounds(50,320,800,300);
        this.add(jScrollPane); jScrollPane.setOpaque(false); jScrollPane.getViewport().setOpaque(false);
        buttonsPanel = new MyPanel(null,true,new FlowLayout(),this);
        buttonsPanel.setBounds(950,50,200,580);
        customComponent.backToMenuButton(this,50,630,actionController);
        customComponent.exit(this,200,630);
        selectDeck = new MyButton("select deck", "GreenCrystal.png", this, actionEvent -> {
            if(selectedDeck==null) JOptionPane.showMessageDialog(cards.getParent(),"you should choose a deck from the deck bar first");
            else actionController.setDeckAsCurrent(selectedDeck); },750,250);
        actionController.showDecksInStatus();
    }

    public void showDecks(ArrayList<String> decks){
        buttonsPanel.removeAll();
        for (String deck : decks){
            new MyButton(deck, "darkBlueButton.jpg", buttonsPanel, actionEvent -> {
                selectedDeck = deck;
                actionController.showCardsInStatus(deck);
                actionController.log(" button clicked - Status - to show information of deck " + deck);
                repaint();
            });
        }
        buttonsPanel.repaint();
        revalidate();
    }

    public void updateCardsPanel(HashMap<Long,String> cardsList){
        cards.removeAll();
        for (Map.Entry<Long,String> entry : cardsList.entrySet()) new MyCardButton(actionController,entry.getKey(),entry.getValue(),100,cards,null,false);
        cards.repaint();
        revalidate();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.g2d = g2d;
       if(selectedDeck!=null) {
           g2d.setFont(new Font("Ariel",Font.BOLD,13));
           g2d.setColor(new Color(0x31263A));
           g2d.drawImage(imageLoader.loadImage("bigPInkArea.png"),200,35,null);
           actionController.drawDeckInfo(selectedDeck);
       }
    }

    public void drawDeckInfo(String name ,int cups ,int wins,int totalGames, double winsToAll, int avePrice, String hero, String moseUsedCard,boolean currentDeck){
        g2d.drawString("Name : " + name, 240, 50);
        g2d.drawString("cups : " + cups, 240, 80);
        g2d.drawString("Total wins with this deck : " + wins, 240, 95);
        g2d.drawString("Total games played with this : " + totalGames, 240, 110);
        g2d.drawString("wins to all : " + winsToAll, 240, 125);
        g2d.drawString("Average price of cards : " + avePrice, 240, 140);
        g2d.drawString("hero  : " + hero, 240, 155);
        g2d.drawString("most used card  : " + moseUsedCard, 240, 170);
        if(currentDeck) g2d.drawString("this is selected as your current deck", 220, 187);
        g2d.drawString("click on an another deck to change your current deck", 220, 202);
    }
}
