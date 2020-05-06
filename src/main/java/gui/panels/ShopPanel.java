package gui.panels;

import gui.Constants.GuiCons;
import gui.ResLoader;
import gui.MyAudioPlayer;
import models.Cards.Card;
import controller.*;
import gui.myComponents.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;


public class ShopPanel extends MyPanel implements ActionListener {
    private ResLoader resLoader ;
    private CardController cardController = new CardController();
    private JPanel itemsPanel , cardsShowCase , cardPanel;
    private JLabel shopLabel, playersCoins, price;
    private MyCardButton selectedCard;
    private JButton CardsYouCanBuyButton, CardsYouCanSellButton, BuyCardButton, SellCardButton;
    private CustomComponent customComponent;
    private MyAudioPlayer audioPlayer;

    public ShopPanel(){
        resLoader = new ResLoader();
        customComponent = new CustomComponent();
        this.setPreferredSize(new Dimension(GuiCons.getWidth(),GuiCons.getHeight()));
        this.setLayout(null);
        this.backGroundFile = "rsz_wood_wallpapers_1080p_1.jpg";
        audioPlayer = MyAudioPlayer.getInstance();


        itemsPanel = new MyPanel(null,true,new FlowLayout(FlowLayout.LEFT,5,0),this);
        itemsPanel.setBounds(25,10,1150,80);
        shopLabel = new JLabel(new ImageIcon(resLoader.imageLoader("rsz_shoplable.png").getScaledInstance(150,64,Image.SCALE_SMOOTH)));
        itemsPanel.add(shopLabel);
        CardsYouCanBuyButton = new MyButton("Cards You Can Buy","blueCrystal150.png",itemsPanel,this);
        CardsYouCanSellButton = new MyButton("Cards You Can Sell","blueCrystal150.png",itemsPanel,this);

        cardsShowCase = new MyPanel(null,true,new GridLayout(2,5,10,20),this );
        JScrollPane scrollPane = new JScrollPane(cardsShowCase, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(25,100,800,500);
        scrollPane.getViewport().setOpaque(false); scrollPane.setOpaque(false);
        this.add(scrollPane);

        cardPanel = new MyPanel(null,true,new FlowLayout(),this);
        cardPanel.setBounds(830,100,350,500);

        playersCoins = new JLabel("you have  ????  coins",new ImageIcon(resLoader.imageLoader("coin.png").getScaledInstance(40,40,Image.SCALE_SMOOTH)),JLabel.HORIZONTAL);
        playersCoins.setFont(new Font("Ariel",Font.BOLD,20));
        cardPanel.add(playersCoins);
        selectedCard = new MyCardButton(null,200,cardPanel);
        price = new JLabel("price :  ???        " , new ImageIcon(resLoader.imageLoader("coin.png").getScaledInstance(40,40,Image.SCALE_SMOOTH)),JLabel.HORIZONTAL);
        price.setFont(new Font("Ariel",Font.BOLD,20));
        cardPanel.add(price);
        BuyCardButton = new MyButton("Buy Card","blueCrystal150.png",cardPanel,this);

        SellCardButton = new MyButton("Sell models.Cards","blueCrystal150.png",cardPanel,this::actionPerformed);
        SellCardButton.setVisible(false);

        customComponent.backToMenuButton(this, 35,620);
        customComponent.exit(this,150,620);
    }



    void setBuyShowCase(){
        cardsShowCase.removeAll();
        playersCoins.setText("you have "+ Controller.getInstance().getCurrentPlayer().getPlayerCoins()+ " coins");
        for (Card card : cardController.getLockedCards()){
           MyCardButton myCardButton = new MyCardButton(card.getName(),100,cardsShowCase);
           myCardButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent actionEvent) {
                   selectedCard.setIcon(new ImageIcon(resLoader.imageLoader("Cards/" + card.getName() + ".png").getScaledInstance(200,276,Image.SCALE_SMOOTH)));
                   selectedCard.setName(card.getName());
                   selectedCard.setContentAreaFilled(false); selectedCard.setBorderPainted(false); selectedCard.setOpaque(false);
                   price.setText("    price        :      "+ card.getPrice());
                   Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO,"card "+ card.getName()+ " clicked in buy show case - Shop - showing information of card in side panel");
               }
           });
        }
        cardsShowCase.repaint();
        revalidate();
    }

    void setSellShowCase(){
        cardsShowCase.removeAll();
        playersCoins.setText("you have "+ Controller.getInstance().getCurrentPlayer().getPlayerCoins()+ " coins");
        for (Card card : cardController.getCardsForSell()){
            MyCardButton myCardButton = new MyCardButton(card.getName(),100,cardsShowCase);
            myCardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    selectedCard.setIcon(new ImageIcon(resLoader.imageLoader("Cards/" + card.getName() + ".png").getScaledInstance(200,276,Image.SCALE_SMOOTH)));
                    selectedCard.setContentAreaFilled(false); selectedCard.setBorderPainted(false); selectedCard.setOpaque(false);
                    selectedCard.setName(card.getName());
                    price.setText("    price      :     "+ card.getPrice());
                    Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO,"card "+ card.getName()+ " clicked in sell show case - Shop - showing information of card in side panel");
                }
            });
        }
        cardsShowCase.repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton button = (JButton) actionEvent.getSource();
        if(button==CardsYouCanBuyButton){
            audioPlayer.playQuick("Switch-SoundBible.com-350629905.wav");
            setBuyShowCase();
            BuyCardButton.setVisible(true); SellCardButton.setVisible(false);
            Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO, "button clicked to illustrate buy show case - Shop");
        }
        if(button==CardsYouCanSellButton){
            audioPlayer.playQuick("Switch-SoundBible.com-350629905.wav");
            setSellShowCase();
            BuyCardButton.setVisible(false); SellCardButton.setVisible(true);
            Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO, "button clicked to illustrate sell show case - Shop");
        }
        if(button==BuyCardButton) {
            if(!cardController.canBuy(selectedCard.getName())) JOptionPane.showMessageDialog(null,"can't buy this card , you don't have enough coins","low budget",JOptionPane.WARNING_MESSAGE);
            else {
                cardController.buyCard(selectedCard.getName());
                JOptionPane.showMessageDialog(null,"bought card " +selectedCard.getName()+" successfully" ,"",JOptionPane.INFORMATION_MESSAGE);
                Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO, "button clicked to buy a card - Shop - " + " bought card " +selectedCard.getName()+" successfully");
                selectedCard.setIcon(new ImageIcon(resLoader.imageLoader(Controller.getInstance().getCurrentPlayer().getCardSkin()).getScaledInstance(200,277,Image.SCALE_SMOOTH)));
                price.setText("price :     ???   ");
                setBuyShowCase();
            }
        }
        if(button==SellCardButton){
            cardController.sellCard(selectedCard.getName());
            JOptionPane.showMessageDialog(null,"sold card " +selectedCard.getName()+" successfully" ,"",JOptionPane.INFORMATION_MESSAGE);
            Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO, "button clicked to sell a card - Shop - " + " sold card " +selectedCard.getName()+" successfully");
            selectedCard.setIcon(new ImageIcon(resLoader.imageLoader("BlushRoomCardBack.png").getScaledInstance(200,277,Image.SCALE_SMOOTH)));
            price.setText("price :      ???   ");
            setSellShowCase();
        }
    }


    public void setSelectedCard(MyCardButton selectedCard) {
        playersCoins.setText("you have "+ Controller.getInstance().getCurrentPlayer().getPlayerCoins()+ " coins");
        this.selectedCard = selectedCard;
        price.setText("    price        :      "+ cardController.creatCard(selectedCard.getName()).getPrice());
    }

    public MyCardButton getSelectedCard() {
        return selectedCard;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    //    @Override
//    public void paintComponent(Graphics g){
////        if(selectedForBuyName!=null)
////            g.drawImage(resLoader.imageLoader("models.Cards/"+selectedForBuyName+".png"),50,50,200,276,null);
//   }


//        CardsYouCanBuy = new JPanel();
//        CardsYouCanBuy.setBounds(0,0,1200,900);
//        CardsYouCanBuy.setLayout(null);
//        jTabbedPane.addTab("models.Cards You Can Buy",CardsYouCanBuy);
//        buyShowCase = new JPanel(new GridLayout(3,7,8,8));
//        buyShowCase.setBounds(600,jTabbedPane.getHeight(),1100,600);
//        JScrollPane scrollPane = new JScrollPane(buyShowCase, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//        buyShowCase.setBorder(LineBorder.createBlackLineBorder());
//        CardsYouCanBuy.add(buyShowCase); CardsYouCanBuy.add(scrollPane);
//
//
//        CardsYouCanSell = new JPanel(new BorderLayout());
//        jTabbedPane.addTab("models.Cards You Can Sell",CardsYouCanSell);
//
//        BuyCards = new JPanel(new BorderLayout());
//        jTabbedPane.addTab("Buy models.Cards",BuyCards);
//
//        SellCards = new JPanel(new BorderLayout());
//        jTabbedPane.addTab("Sell models.Cards", SellCards);
//        this.add(jTabbedPane);


//        jTabbedPane.setBackgroundAt(2,new Color(0x3C1E0C));




}
