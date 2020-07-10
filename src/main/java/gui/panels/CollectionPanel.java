package gui.panels;

import gui.GameFrame;
import resLoader.MyAudioPlayer;
import resLoader.ImageLoader;
import models.Cards.Card;
import controller.*;
import gui.myComponents.*;
import models.Deck;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.Level;

public class CollectionPanel extends MyPanel implements ChangeListener , ActionListener{

    private JPanel horizontalBar, classCardsBar, decksBar, currentDeckBar,cardsShowCase,deckCards;
    private MyButton allCards, lockedCards, unlockedCards, classCards, Mage, Rouge, Warlock, Priest, hunter, Neutral;
    private MyButton searchIcon, creat ,deleteCard, deleteDeck, rename,changeHero;
    private JLabel shownCardDeck;
    private String currentDeck;
    private JSlider manaFilter ;
    private JTextField searchField;
    private CardController cardController;
    private String  selectedCardInDeck;
    private String[] cardOptions , heroOptions;
    private SettingsPanel settings;
    private MyAudioPlayer audioPlayer;

    public CollectionPanel(){
        cardController = new CardController();
        settings = new SettingsPanel();
        this.backGroundFile = "CollectionBG.jpg";
        this.setLayout(null);
        audioPlayer = MyAudioPlayer.getInstance();

        makeHorizontalBar();
        makeMainCase();
        makeDeckBar();
        makeManaFilter();
        updateDecksBar();
        customComponent.backToMenuButton(this,25,620,null);
        customComponent.exit(this,130,620);
        heroOptions = new String[]{"Mage","Warlock","Rogue","Hunter","Priest"};
    }

    private void makeHorizontalBar(){
        horizontalBar = new MyPanel(null,true,new FlowLayout(),this);
        horizontalBar.setBounds(25,10,1150,75);
        allCards = new MyButton("All Cards","blueCrystal120.png",horizontalBar,this);
        lockedCards = new MyButton("Locked Cards","blueCrystal120.png",horizontalBar,this);
        unlockedCards = new MyButton("Unlocked Cards","blueCrystal120.png",horizontalBar,this);
        classCards = new MyButton("Class Cards","blueCrystal120.png",horizontalBar,this);
        Neutral = new MyButton("Neutral Cards","blueCrystal120.png",horizontalBar,this);
        searchField = new JTextField("",12); horizontalBar.add(searchField);
        searchField.setFont(new Font("Ariel",Font.PLAIN,18));
        searchIcon = new MyButton("","SearchIcon.png",horizontalBar,this);
    }

    private void makeDeckBar(){
        decksBar = new MyPanel(null,true,new FlowLayout(),this);
        decksBar.setBounds(975,100,200,550);
        creat = new MyButton("Creat Deck","blueCrystal150.png",decksBar,this);

        currentDeckBar = new MyPanel(null,true,null,this);
        currentDeckBar.setBounds(650,100,300,550);
        deckCards = new MyPanel(null,true,new GridLayout(6,2,4,4),currentDeckBar);
        JScrollPane deckScrollBar = new JScrollPane(deckCards, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        deckScrollBar.setBounds(0,0,300,345);
        deckScrollBar.setOpaque(false); deckScrollBar.getViewport().setOpaque(false); deckCards.setOpaque(false);
        currentDeckBar.add(deckScrollBar);
        deleteCard = new MyButton("Delete Card","blueCrystal100.png",currentDeckBar,this);
        deleteCard.setBounds(170,350,deleteCard.getWidth(),deleteCard.getHeight());
        deleteDeck = new MyButton("Delete Deck","blueCrystal100.png",currentDeckBar,this);
        deleteDeck.setBounds(170,400,deleteDeck.getWidth(),deleteDeck.getHeight());
        changeHero = new MyButton("ChangeHero","blueCrystal100.png",currentDeckBar,this);
        changeHero.setBounds(170,500,deleteCard.getWidth(),deleteCard.getHeight());
        rename = new MyButton("Rename","blueCrystal100.png",currentDeckBar,this);
        rename.setBounds(170,450,deleteCard.getWidth(),deleteCard.getHeight());
        shownCardDeck = new JLabel(new ImageIcon(imageLoader.loadImage(Controller.getInstance().getMainPlayer().getCardSkin()).getScaledInstance(140,198,Image.SCALE_SMOOTH)));
        shownCardDeck.setBounds(15,350,140,198);
        currentDeckBar.add(shownCardDeck);
    }

    private void makeMainCase(){
        classCardsBar = new MyPanel(null,true,new FlowLayout(FlowLayout.LEFT,0,2),null);
        Mage = new MyButton("Mage Cards","blueCrystal120.png",classCardsBar,this);
        Rouge = new MyButton("Rouge Cards","blueCrystal120.png",classCardsBar, this);
        Warlock = new MyButton("Warlock Cards","blueCrystal120.png",classCardsBar,this);
        Priest = new MyButton("Priest Cards","blueCrystal120.png",classCardsBar,this);
        hunter = new MyButton("Hunter Cards","blueCrystal120.png",classCardsBar,this);
        cardsShowCase = new MyPanel(null,true,new GridLayout(8,5,5,5),this);
        JScrollPane cardsScrollBar = new JScrollPane(cardsShowCase, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cardsScrollBar.setBounds(25,100,600,500);
        cardsScrollBar.setOpaque(false); cardsScrollBar.getViewport().setOpaque(false); cardsShowCase.setOpaque(false);
        this.add(cardsScrollBar);
        cardOptions = new String[]{"Buy this Card","Add to this deck"};
    }

    private void makeManaFilter(){
        manaFilter = new JSlider( 0,0,10,0);
        manaFilter.setBackground(new Color(0x34A0B4));
        manaFilter.setPaintTicks(true);
        manaFilter.setMinorTickSpacing(1);
        manaFilter.setPaintLabels(true);
        manaFilter.addChangeListener(this);
        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        for (int i = 1; i <= 10 ; i++) {
            labels.put(i,new JLabel(new ImageIcon(imageLoader.loadImage("mana.png").getScaledInstance(18,19,Image.SCALE_SMOOTH))));
        }
        manaFilter.setLabelTable(labels);
        manaFilter.setBounds(350,620,250,40);
        this.add(manaFilter);
    }

    public void updateCardsShowCase(Boolean hasClassBar,ArrayList<Card> cards){
        cardsShowCase.removeAll();
        if(hasClassBar) cardsShowCase.add(classCardsBar);
        for (Card card : cards){
            MyCardButton myCardButton = new MyCardButton(card.getName(),100,cardsShowCase);
            myCardButton.addClickListener();
            myCardButton.addActionListener(actionEvent -> {
               int ans = JOptionPane.showOptionDialog(null,"what do you want to do with this card?","",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,
                       new ImageIcon(imageLoader.loadImage("Cards/" + card.getName() + ".png").getScaledInstance(170,17*138/10,Image.SCALE_SMOOTH)),cardOptions,cardOptions[0]);
               if(ans==0){
                   if(!cardController.canBuy(card.getName())) JOptionPane.showMessageDialog(null,"you already have this card \n no need to buy it!");
                   else {
                       GameFrame.getInstance().getShopPanel().getSelectedCard().setIcon(new ImageIcon(imageLoader.loadImage("Cards/" + card.getName() + ".png").getScaledInstance(200,276,Image.SCALE_SMOOTH)));
                       GameFrame.getInstance().getShopPanel().getSelectedCard().setContentAreaFilled(false); GameFrame.getInstance().getShopPanel().getSelectedCard().setBorderPainted(false); GameFrame.getInstance().getShopPanel().getSelectedCard().setOpaque(false);
                       GameFrame.getInstance().getShopPanel().getSelectedCard().setName(card.getName());
                       GameFrame.getInstance().getShopPanel().setSelectedCard(GameFrame.getInstance().getShopPanel().getSelectedCard());
                       Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO, "button clicked to buy a card - Collection - changing panel to shop");
                       GameFrame.getInstance().goToPanel("shopPanel");
                   }
               }
               if(ans==1){
                   if(currentDeck==null) JOptionPane.showMessageDialog(cardsShowCase,"first choose a deck from the deck bar ");
                   else if(!cardController.canAddToDeck(card.getName(),currentDeck)) JOptionPane.showMessageDialog(cardsShowCase,"you can't have more than two same cards in a deck");
                   else if(cardController.isLocked(card)) JOptionPane.showMessageDialog(cardsShowCase,"you don't have this card");
                   else if(cardController.wrongHeroClass(card.getName(),currentDeck))JOptionPane.showMessageDialog(cardsShowCase,"the cards hero doesnt match deck's hero");
                   else if(cardController.getTheDeck(currentDeck).getCards().size()>= 30 ) JOptionPane.showMessageDialog(cardsShowCase,"your deck is full!");
                   else cardController.addCardToDeck(card.getName(),currentDeck);
                   Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO, "button clicked for adding a card to deck - Collection - "+ card.getName() + " added to deck : "+ currentDeck);
                   if(currentDeck!=null)updateDeckCards(cardController.getTheDeck(currentDeck).getCards());
               }
            });
        }
        cardsShowCase.repaint();
        revalidate();
    }

    public void updateDeckCards(ArrayList<Card> cards){
        deckCards.removeAll();
        if(cardController.getTheDeck(currentDeck).getHero()!=null) deckCards.add(new JLabel(new ImageIcon(imageLoader.loadImage("hero pics/"+ cardController.getTheDeck(currentDeck).getHero().toString().toLowerCase()+".png").getScaledInstance(140,190,Image.SCALE_SMOOTH))));
        for (Card card : cards){
            MyCardButton myCardButton = new MyCardButton(card.getName(),100,deckCards);
            myCardButton.addActionListener(actionEvent -> {
                selectedCardInDeck = card.getName();
                shownCardDeck.setIcon(new ImageIcon(imageLoader.loadImage("Cards/" + card.getName() + ".png").getScaledInstance(140,197,Image.SCALE_SMOOTH)));
                Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO, "card clicked for showing its info - Collection ");
            });
        }
        deckCards.repaint();
        revalidate();
    }

    public void updateDecksBar(){
        decksBar.removeAll();
        for (Deck deck : Controller.getInstance().getMainPlayer().getDecks()){
            JButton jButton = new MyButton(deck.getName(),"darkBlueButton.jpg",decksBar,null);
            jButton.addActionListener(actionEvent -> {
                currentDeck = deck.getName();
                updateDeckCards(deck.getCards());
            });
            jButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    jButton.setBorder(BorderFactory.createEtchedBorder(Color.GREEN,Color.BLACK));
                }
                @Override
                public void mouseExited(MouseEvent mouseEvent) {
                    jButton.setBorder(null);
                }
            });
        }
        decksBar.add(creat);
        decksBar.repaint();
        revalidate();
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        ArrayList<Card> arrayList = new ArrayList<>();
        for (Card card : cardController.getAllCardsInGame()){
            if(card.getManaCost()==manaFilter.getValue()) arrayList.add(card);
        }
        Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO, "mana filter state changed to "+ manaFilter.getValue());
        updateCardsShowCase(false,arrayList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        audioPlayer.playQuick("TinyPush.wav");
        JButton button = (JButton) e.getSource();
        Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO,button.getText()+" button clicked - Collection");
        if(button==allCards) updateCardsShowCase(false,cardController.getAllCardsInGame());
        if(button==lockedCards) updateCardsShowCase(false,cardController.getLockedCards());
        if(button==unlockedCards) updateCardsShowCase(false,cardController.getCurrentPlayer().getALLPlayersCards());
        if(button==Neutral) updateCardsShowCase(false,cardController.getAllNeutralCards());
        if(button==classCards) updateCardsShowCase(true,cardController.getAllMageCards());
        if(button==Mage) updateCardsShowCase(true,cardController.getAllMageCards());
        if(button==Rouge) updateCardsShowCase(true,cardController.getAllRougeCards());
        if(button==Warlock) updateCardsShowCase(true,cardController.getAllWarlockCards());
        if(button==searchIcon) updateCardsShowCase(false,cardController.searchFilter(searchField.getText()));
        if(button==Priest) updateCardsShowCase(true,cardController.getAllPriestCards());
        if(button==hunter) updateCardsShowCase(true,cardController.getAllHunterCards());

        if(button==creat) {
            String name = JOptionPane.showInputDialog(this, "Creat a name for your deck");
            if(!cardController.validDeckName(name)) JOptionPane.showMessageDialog(this, "this name already exist for one of your decks!","There's a bug on you!", JOptionPane.ERROR_MESSAGE);
            else {
                cardController.creatDeck(name);
                Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO,"created deck" +name+" - Collection");
                updateDecksBar();
            }
        }

        if(button==deleteCard){
            int dialogButton = JOptionPane.showConfirmDialog (this, "are you sure you want to delete "+ selectedCardInDeck ,"warning!",JOptionPane.YES_NO_OPTION);
            if(dialogButton==JOptionPane.YES_OPTION) {
                cardController.removeFromDeck(selectedCardInDeck,currentDeck);
                Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO,"deleted card : " +selectedCardInDeck+" - Collection");
                updateDeckCards(cardController.getTheDeck(currentDeck).getCards());
                shownCardDeck.setIcon(new ImageIcon(imageLoader.loadImage(Controller.getInstance().getMainPlayer().getCardSkin()).getScaledInstance(140,198,Image.SCALE_SMOOTH)));
            }
            else remove(dialogButton);
        }

        if(button==rename){
            String name = JOptionPane.showInputDialog(this, "make another name for your deck");
            if(!cardController.validDeckName(name)) JOptionPane.showMessageDialog(this, "There's a bug on you!", "this name already exist for one of your decks!", JOptionPane.ERROR_MESSAGE);
            else {
                cardController.getTheDeck(currentDeck).setName(name);
                Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO,"renamed deck : " +currentDeck+ "to " + name + " - Collection");
                updateDecksBar();
            }
        }

        if(button==changeHero){
            if(!cardController.canChangeDeckHero(currentDeck)) JOptionPane.showMessageDialog(this,"can't change the hero cause you already have special cards of "+ cardController.getTheDeck(currentDeck).getHero().toString());
            else  {
                int ans = JOptionPane.showOptionDialog(this,"choose hero for you deck","",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null,heroOptions,heroOptions[0]);
                if(ans==0) cardController.getTheDeck(currentDeck).setHero(Card.HeroClass.MAGE);
                if(ans==1) cardController.getTheDeck(currentDeck).setHero(Card.HeroClass.WARLOCK);
                if(ans==1) cardController.getTheDeck(currentDeck).setHero(Card.HeroClass.ROGUE);
                Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO,"changed deck hero - Collection");
                updateDecksBar();
            }
        }

        if(button==deleteDeck){
            int dialogButton = JOptionPane.showConfirmDialog (this, "are you sure you want to delete this deck? " ,"warning!",JOptionPane.YES_NO_OPTION);
            if(dialogButton==JOptionPane.YES_OPTION) cardController.removeDeck(currentDeck);
            Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO,"deleted deck " + currentDeck + " - Collection");
            updateDecksBar();
        }
    }
//    @Override
//    public void paintComponent(Graphics g){
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        Color color = new Color(49, 49, 55, 180);
//        Color c = new Color(0x2F170C);
//       // g2d.setColor(new Color(50, 50, 50, 180));
//        g2d.setColor(color);
//        g2d.fillRect(25,5,1150,75);
//        g2d.fillRect(975,100,200,550);
//        g2d.fillRect(650,100,300,550);
//        g2d.fillRect(25,100,600,500);
//    }
    //        buy = new MyButton("Buy this models.Cards","blueCrystal150.png",this,this);
//        buy.setBounds(470,610,buy.getWidth(),buy.getHeight());
//        addToDeck = new MyButton("Add to this deck","blueCrystal150.png",this,this);
//        addToDeck.setBounds(470,550,buy.getWidth(),buy.getHeight());
////        shownCardMain = new JLabel(new ImageIcon(resLoader.imageLoader("BlushRoomCardBack.png").getScaledInstance(100,139,Image.SCALE_SMOOTH)));
//        shownCardMain.setBounds(360,550,100,138);
//        this.add(shownCardMain);
}
