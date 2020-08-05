package gui.panels;

import client.actionController.ShopActionController;
import javafx.util.Pair;
import gui.myComponents.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class ShopPanel extends MyPanel implements ActionListener {
    private final JPanel itemsPanel, cardsShowCase, cardPanel;
    private final JLabel price, shopLabel, playersCoins;
    private MyCardButton selectedCard;
    private final JButton CardsYouCanBuyButton, CardsYouCanSellButton, BuyCardButton, SellCardButton;
    private ShopActionController actionController;

    public ShopPanel(ShopActionController actionController) {
        this.setPreferredSize(new Dimension(configLoader.readInteger("mainFrameWidth"),configLoader.readInteger("mainFrameHeight")));
        this.setLayout(null);
        this.backGroundFile = "rsz_wood_wallpapers_1080p_1.jpg";
        this.actionController = actionController;
        itemsPanel = new MyPanel(null, true, new FlowLayout(FlowLayout.LEFT, 5, 0), this);
        itemsPanel.setBounds(25, 10, 1150, 80);
        shopLabel = new JLabel(new ImageIcon(imageLoader.loadImage("rsz_shoplable.png").getScaledInstance(150, 64, Image.SCALE_SMOOTH)));
        itemsPanel.add(shopLabel);
        CardsYouCanBuyButton = new MyButton("Cards You Can Buy", "blueCrystal150.png", itemsPanel, this);
        CardsYouCanSellButton = new MyButton("Cards You Can Sell", "blueCrystal150.png", itemsPanel, this);
        cardsShowCase = new MyPanel(null, true, new GridLayout(2, 5, 10, 20), this);
        JScrollPane scrollPane = new JScrollPane(cardsShowCase, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(25, 100, 800, 500);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        this.add(scrollPane);
        cardPanel = new MyPanel(null, true, new FlowLayout(), this);
        cardPanel.setBounds(830, 100, 350, 500);
        playersCoins = new JLabel("you have  ????  coins", new ImageIcon(imageLoader.loadImage("coin.png").getScaledInstance(40, 40, Image.SCALE_SMOOTH)), JLabel.HORIZONTAL);
        playersCoins.setFont(new Font("Ariel", Font.BOLD, 20));
        cardPanel.add(playersCoins);
        selectedCard = new MyCardButton(actionController, 40, "", 200, cardPanel, null, false);
        price = new JLabel("price :  ???        ", new ImageIcon(imageLoader.loadImage("coin.png").getScaledInstance(40, 40, Image.SCALE_SMOOTH)), JLabel.HORIZONTAL);
        price.setFont(new Font("Ariel", Font.BOLD, 20));
        cardPanel.add(price);
        BuyCardButton = new MyButton("Buy Card", "blueCrystal150.png", cardPanel, this);
        SellCardButton = new MyButton("Sell Card", "blueCrystal150.png", cardPanel, this);
        SellCardButton.setVisible(false);
        customComponent.backToMenuButton(this, 35, 620, actionController);
        customComponent.exit(this, 150, 620);
    }


    public void setBuyShowCase(HashMap<Pair<Long, String>, Integer> lockedCards, long coins) {
        cardsShowCase.removeAll();
        playersCoins.setText("you have " + coins + " coins");
        for (Map.Entry<Pair<Long, String>, Integer> card : lockedCards.entrySet()) {
            new MyCardButton(actionController, card.getKey().getKey(), card.getKey().getValue(), 100, cardsShowCase, actionEvent -> {
                selectedCard = new MyCardButton(actionController, card.getKey().getKey(), card.getKey().getValue(), 200, cardPanel, null, false);
                price.setText("    price        :      " + card.getValue());
                actionController.log("card " + card.getKey().getValue() + " clicked in buy show case - Shop - showing information of card in side panel");
            }, false);
        }
        cardsShowCase.repaint();
        revalidate();
    }

    public void setSellShowCase(HashMap<Pair<Long, String>, Integer> unLockedCards, long coins) {
        cardsShowCase.removeAll();
        playersCoins.setText("you have " + coins + " coins");
        for (Map.Entry<Pair<Long, String>, Integer> card : unLockedCards.entrySet()) {
            new MyCardButton(actionController, card.getKey().getKey(), card.getKey().getValue(), 100, cardsShowCase, actionEvent -> {
                selectedCard = new MyCardButton(actionController, card.getKey().getKey(), card.getKey().getValue(), 200, cardPanel, null, false);
                price.setText("    price        :      " + card.getValue());
                actionController.log("card " + card.getKey().getValue() + "card " + card.getKey().getValue() + " clicked in sell show case - Shop - showing information of card in side panel");
            }, false);
        }
        cardsShowCase.repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton button = (JButton) actionEvent.getSource();
        if (button == CardsYouCanBuyButton) {
            actionController.setBuyShowCase();
            BuyCardButton.setVisible(true);
            SellCardButton.setVisible(false);
        }
        else if (button == CardsYouCanSellButton) {
            actionController.setSellShowCase();
            BuyCardButton.setVisible(false);
            SellCardButton.setVisible(true);
        }
        else if (button == BuyCardButton) {
            if (selectedCard.getName().equals(""))JOptionPane.showMessageDialog(null,"you should select a card first ");
            else{
                actionController.buyCard(selectedCard.getId(),selectedCard.getName());
                selectedCard = new MyCardButton(actionController, 555,"" , 200, cardPanel, null, false);
                price.setText("price :     ???   ");
                actionController.setBuyShowCase();
            }
        }
        else if (button == SellCardButton) {
            if (selectedCard.getName().equals(""))JOptionPane.showMessageDialog(null,"you should select a card first ");
            else {
                actionController.sellCard(selectedCard.getId(),selectedCard.getName());
                JOptionPane.showMessageDialog(null, "sold card " + selectedCard.getName() + " successfully", "", JOptionPane.INFORMATION_MESSAGE);
                selectedCard = new MyCardButton(actionController, 555,"" , 200, cardPanel, null, false);
                price.setText("price :      ???   ");
                actionController.setSellShowCase();
            }
        }
    }


    public void setSelectedCard(Long id, String name, long coins, String cardPrice) {
        selectedCard = new MyCardButton(actionController, id, name, 200, cardPanel, null, false);
        price.setText("     price     :      "+cardPrice);
        playersCoins.setText("you have " + coins +" coins");

        //todo coins && price
//        playersCoins.setText("you have " + Controller.getInstance().getMainPlayer().getPlayerCoins() + " coins");
//        this.selectedCard = selectedCard;
//        price.setText("    price        :      " + CardController.creatCard(selectedCard.getName()).getPrice());
//
    }

    public MyCardButton getSelectedCard() {
        return selectedCard;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

}
