package gui.panels;

import controller.*;
import gui.Constants.GuiCons;
import gui.ResLoader;
import models.Cards.Card;
import models.Deck;
import gui.myComponents.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;

public class StatusPanel extends MyPanel {

    private ResLoader resLoader;
    private JPanel cards,  buttonsPanel ;
    private String selectedDeck, mostUsedCard ="";
    MyButton selectDeck ;
    CardController cardController ;

    public StatusPanel(){
        this.backGroundFile = "StatusBG.jpg";
        this.setPreferredSize(new Dimension(GuiCons.getWidth(),GuiCons.getHeight()));
        this.setLayout(null);
        resLoader = new ResLoader();
        cardController = new CardController();
        System.out.println(Controller.getInstance().getCurrentPlayer().getDecks());

        cards = new MyPanel(null,true,new GridLayout(3,6,4,7),this);
        JScrollPane jScrollPane = new JScrollPane(cards, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setBounds(50,320,800,300);
        this.add(jScrollPane); jScrollPane.setOpaque(false); jScrollPane.getViewport().setOpaque(false);
        buttonsPanel = new MyPanel(null,true,new FlowLayout(),this);
//        JScrollPane jScrollPane2 = new JScrollPane(buttonsPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        buttonsPanel.setBounds(950,50,200,580);
        this.add(buttonsPanel);
        new CustomComponent().backToMenuButton(this,50,630);
        new CustomComponent().exit(this,200,630);
        selectDeck = new MyButton("select deck", "GreenCrystal.png", this, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(selectedDeck==null) JOptionPane.showMessageDialog(cards.getParent(),"you should choose a deck from the deck bar first");
                else {
                    Controller.getInstance().getCurrentPlayer().setPlayersDeck(cardController.getTheDeck(selectedDeck));
                    if(cardController.getTheDeck(selectedDeck).getHero()!=null) Controller.getInstance().getCurrentPlayer().setPlayersChoosedHero(cardController.getTheDeck(selectedDeck).getHero());
                    Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO,((MyButton) actionEvent.getSource()).getText()+" button clicked - Status - changed current deck to : " + selectedDeck);
                }
            }
        });
        selectDeck.setBounds(750,250,selectDeck.getWidth(),selectDeck.getHeight());
        showDecks();
    }

    void showDecks(){
        buttonsPanel.removeAll();
        for (Deck deck : Controller.getInstance().getCurrentPlayer().getDecks()){
            JButton jButton = new MyButton(deck.toString(),"darkBlueButton.jpg",buttonsPanel,null);
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Controller.getInstance().getCurrentPlayer().setPlayersDeck(deck);
                    selectedDeck = deck.getName();
                    updateCardsPanel();
                    Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO," button clicked - Status - to show information of deck " + deck.getName());
                    repaint();
                }
            });
        }
        buttonsPanel.repaint();
        revalidate();
    }

    void updateCardsPanel(){
        cards.removeAll();
        for (Card card : cardController.getTheDeck(selectedDeck).getCards()){
           new MyCardButton(card.getName(),100,cards);
        }
        cards.repaint();
        revalidate();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

       if(selectedDeck!=null) {
           g2d.setFont(new Font("Ariel",Font.BOLD,13));
           g2d.setColor(new Color(0x31263A));
           Deck deck = cardController.getTheDeck(selectedDeck);
           g2d.drawImage(resLoader.imageLoader("bigPInkArea.png"),200,35,null);
           g2d.drawString("Name : " + deck.getName(), 240, 80);
           g2d.drawString("Total wins with this deck : " + deck.getWinGamesPlayed(), 240, 95);
           g2d.drawString("Total games played with this : " + deck.getAllGamesPlayed(), 240, 110);
           g2d.drawString("wins to all : " + deck.winToAll(), 240, 125);
           g2d.drawString("Average price of cards : " + deck.averagePrice(), 240, 140);
           if(deck.getHero()!=null)g2d.drawString("hero  : " + deck.getHero(), 240, 155);
           if(deck.getCards().size()!=0)g2d.drawString("most used card  : " + deck.getCardsSortedByValue().get(0).toString(), 240, 170);
           if(Controller.getInstance().getCurrentPlayer().getPlayersDeck().getName().equals(selectedDeck)) g2d.drawString("this is selected as your current deck", 220, 187);
           g2d.drawString("click on an another deck to change your current deck", 220, 202);

       }
    }
}
