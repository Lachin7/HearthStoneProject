package client.gui.panels;

import client.actionController.CollectionPanelAction;
import client.gui.myComponents.*;
import server.models.Cards.Card;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.*;

public class CollectionPanel extends MyPanel implements ChangeListener{

    private JPanel horizontalBar, classCardsBar, decksBar, currentDeckBar, cardsShowCase, deckCards;
    private MyButton allCards, lockedCards, unlockedCards, classCards, Mage, Rouge, Warlock, Priest, hunter, Neutral, searchIcon, showDecks, creat, deleteCard, deleteDeck, rename, changeHero;
    private JLabel shownCardDeck;
    private String currentDeck;
    private JSlider manaFilter;
    private JTextField searchField;
    private String selectedCardInDeck;
    private String[] cardOptions;
    private CollectionPanelAction actionController;

    public CollectionPanel(CollectionPanelAction actionController) {
        this.backGroundFile = "CollectionBG.jpg";
        this.setLayout(null);
        this.setPreferredSize(new Dimension(configLoader.readInteger("mainFrameWidth"),configLoader.readInteger("mainFrameHeight")));
        this.actionController = actionController;
        makeHorizontalBar();
        makeMainCase();
        makeDeckBar();
        makeManaFilter();
        new MyButton("back to Menu", "pinkCrystal100.png", this, actionEvent -> actionController.back(),25, 620);
        new MyButton("exit", "pinkCrystal100.png", this, actionEvent -> actionController.exit(),130,   620);
    }

    private void makeHorizontalBar() {
        horizontalBar = new MyPanel(null, true, new FlowLayout(), this);
        horizontalBar.setBounds(25, 10, 1150, 75);
        allCards = new MyButton("All Cards", "blueCrystal120.png", horizontalBar, actionEvent -> actionController.setShowCase(-1, null, null, null, false, false));
        lockedCards = new MyButton("Locked Cards", "blueCrystal120.png", horizontalBar, actionEvent -> actionController.setShowCase(-1, null, null, null, true, false));
        unlockedCards = new MyButton("Unlocked Cards", "blueCrystal120.png", horizontalBar, actionEvent -> actionController.setShowCase(-1, null, null, null, false, true));
        classCards = new MyButton("Class Cards", "blueCrystal120.png", horizontalBar, actionEvent -> actionController.setShowCase(-1, null, Card.HeroClass.MAGE, null, false, false));
        Neutral = new MyButton("Neutral Cards", "blueCrystal120.png", horizontalBar, actionEvent -> actionController.setShowCase(-1, null, Card.HeroClass.NEUTRAL, null, false, false));
        searchField = new JTextField("", 12);
        horizontalBar.add(searchField);
        searchField.setFont(new Font("Ariel", Font.PLAIN, 18));
        searchIcon = new MyButton("", "SearchIcon.png", horizontalBar, actionEvent -> actionController.setShowCase(-1, null, null, searchField.getText(), false, false));
    }

    private void makeDeckBar() {
        decksBar = new MyPanel(null, true, new FlowLayout(), this);
        decksBar.setBounds(975, 100, 200, 550);
        showDecks = new MyButton("show decks", "darkBlueButton.jpg", decksBar, actionEvent -> actionController.editDeck(null, null, null, false, false,currentDeck));
        creat = new MyButton("Creat Deck", "blueCrystal150.png", decksBar, actionEvent -> {
            String name = JOptionPane.showInputDialog(null, "Creat a name for your deck");
            actionController.editDeck(name, null, null, false, false, currentDeck); });
        currentDeckBar = new MyPanel(null, true, null, this);
        currentDeckBar.setBounds(650, 100, 300, 550);
        deckCards = new MyPanel(null, true, new GridLayout(6, 2, 4, 4), currentDeckBar);
        JScrollPane deckScrollBar = new JScrollPane(deckCards, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        deckScrollBar.setBounds(0, 0, 300, 345);
        deckScrollBar.setOpaque(false);
        deckScrollBar.getViewport().setOpaque(false);
        deckCards.setOpaque(false);
        currentDeckBar.add(deckScrollBar);
        deleteCard = new MyButton("Delete Card", "blueCrystal100.png", currentDeckBar, actionEvent -> {
            int dialogButton = JOptionPane.showConfirmDialog(null, "are you sure you want to delete " + selectedCardInDeck, "warning!", JOptionPane.YES_NO_OPTION);
            if (dialogButton == JOptionPane.YES_OPTION) {
                actionController.editDeck(null, selectedCardInDeck, null, false, false, currentDeck);
                actionController.updateDeckShowCase(currentDeck);
                shownCardDeck.setIcon(new ImageIcon(imageLoader.loadImage("BlushRoomCardBack.png").getScaledInstance(140, 198, Image.SCALE_SMOOTH))); } else remove(dialogButton); }, 170, 350);
        deleteDeck = new MyButton("Delete Deck", "blueCrystal100.png", currentDeckBar, actionEvent -> {
            int dialogButton = JOptionPane.showConfirmDialog (null, "are you sure you want to delete this deck? " ,"warning!",JOptionPane.YES_NO_OPTION);
            if(dialogButton==JOptionPane.YES_OPTION) actionController.editDeck(null,null,null,false,true,currentDeck); }, 170, 400);
        changeHero = new MyButton("ChangeHero", "blueCrystal100.png", currentDeckBar, actionEvent -> actionController.editDeck(null, null, null, true, false, currentDeck), 170, 500);
        rename = new MyButton("Rename", "blueCrystal100.png", currentDeckBar, actionEvent -> {
            String name = JOptionPane.showInputDialog(null, "make another name for your deck");
            actionController.editDeck(null, null, name, false, false, currentDeck); }, 170, 450);
        shownCardDeck = new JLabel(new ImageIcon(imageLoader.loadImage("BlushRoomCardBack.png").getScaledInstance(140, 198, Image.SCALE_SMOOTH)));
        shownCardDeck.setBounds(15, 350, 140, 198);
        currentDeckBar.add(shownCardDeck);
    }

    private void makeMainCase() {
        classCardsBar = new MyPanel(null, true, new FlowLayout(FlowLayout.LEFT, 0, 2), null);
        Mage = new MyButton("Mage Cards", "blueCrystal120.png", classCardsBar, actionEvent -> actionController.setShowCase(-1, null, Card.HeroClass.MAGE, null, false, false));
        Rouge = new MyButton("Rouge Cards", "blueCrystal120.png", classCardsBar, actionEvent -> actionController.setShowCase(-1, null, Card.HeroClass.ROGUE, null, false, false));
        Warlock = new MyButton("Warlock Cards", "blueCrystal120.png", classCardsBar, actionEvent -> actionController.setShowCase(-1, null, Card.HeroClass.WARLOCK, null, false, false));
        Priest = new MyButton("Priest Cards", "blueCrystal120.png", classCardsBar, actionEvent -> actionController.setShowCase(-1, null, Card.HeroClass.PRIEST, null, false, false));
        hunter = new MyButton("Hunter Cards", "blueCrystal120.png", classCardsBar, actionEvent -> actionController.setShowCase(-1, null, Card.HeroClass.HUNTER, null, false, false));
        cardsShowCase = new MyPanel(null, true, new GridLayout(8, 5, 5, 5), this);
        JScrollPane cardsScrollBar = new JScrollPane(cardsShowCase, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cardsScrollBar.setBounds(25, 100, 600, 500);
        cardsScrollBar.setOpaque(false);
        cardsScrollBar.getViewport().setOpaque(false);
        cardsShowCase.setOpaque(false);
        this.add(cardsScrollBar);
        cardOptions = new String[]{"Buy this Card", "Add to this deck"};
    }

    private void makeManaFilter() {
        manaFilter = new JSlider(0, 0, 10, 0);
        manaFilter.setBackground(new Color(0x34A0B4));
        manaFilter.setPaintTicks(true);
        manaFilter.setMinorTickSpacing(1);
        manaFilter.setPaintLabels(true);
        manaFilter.addChangeListener(this);
        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        for (int i = 1; i <= 10; i++)
            labels.put(i, new JLabel(new ImageIcon(imageLoader.loadImage("mana.png").getScaledInstance(18, 19, Image.SCALE_SMOOTH))));
        manaFilter.setLabelTable(labels);
        manaFilter.setBounds(350, 620, 250, 40);
        this.add(manaFilter);
    }

    public void updateCardsShowCase(Boolean hasClassBar, HashMap<Long,String> cards) {
        cardsShowCase.removeAll();
        if (hasClassBar) cardsShowCase.add(classCardsBar);
        for (Map.Entry<Long,String> entry : cards.entrySet()) {
            MyCardButton myCardButton = new MyCardButton(actionController, entry.getKey(), entry.getValue(), 100, cardsShowCase, actionEvent -> {
                int ans = JOptionPane.showOptionDialog(null, "what do you want to do with this card?", "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        new ImageIcon(imageLoader.getCardsImages().get(entry.getValue()).getScaledInstance(170, 17 * 138 / 10, Image.SCALE_SMOOTH)), cardOptions, cardOptions[0]);
                if (ans == 0) {
//                   if(!cardController.canBuy(card.getName())) JOptionPane.showMessageDialog(null,"you already have this card \n no need to buy it!");
                    actionController.setSelectedCardInShop(entry.getKey(),entry.getValue());
//                    actionController.getClient().getShopPanel().getSelectedCard().setIcon(new ImageIcon(imageLoader.loadImage("Cards/" + entry.getValue() + ".png").getScaledInstance(200, 276, Image.SCALE_SMOOTH)));
//                    actionController.getClient().getShopPanel().getSelectedCard().setContentAreaFilled(false);
//                    actionController.getClient().getShopPanel().getSelectedCard().setBorderPainted(false);
//                    actionController.getClient().getShopPanel().getSelectedCard().setOpaque(false);
                    actionController.getClientGui().getShopPanel().getSelectedCard().setName(entry.getValue());
                    actionController.log("button clicked to buy a card - Collection - changing panel to shop");
                    actionController.getClientGui().goToPanel("shop");
                }
                if (ans == 1) {
                    if (currentDeck == null) JOptionPane.showMessageDialog(null, "first choose a deck from the deck bar ");
                    else {
                        actionController.addCardToDeck(entry.getValue(), currentDeck);
                        actionController.log("button clicked for adding a card to deck - Collection - card : " + entry.getValue() + " to deck : " + currentDeck);
                        actionController.updateDeckShowCase(currentDeck);
                    }
                }
            }, false);
            myCardButton.addClickListener(-1);
        }
        cardsShowCase.repaint();
        revalidate();
    }

    public void updateDeckCards(String hero, HashMap<Long,String> cards) {
        deckCards.removeAll();
        if (hero != null) deckCards.add(new JLabel(new ImageIcon(imageLoader.loadImage("hero pics/" + hero.toLowerCase() + ".png").getScaledInstance(140, 190, Image.SCALE_SMOOTH))));
        for (Map.Entry<Long,String> entry : cards.entrySet()) {
            new MyCardButton(actionController, entry.getKey(), entry.getValue(), 100, deckCards, actionEvent -> {
                selectedCardInDeck = entry.getValue();
                shownCardDeck.setIcon(new ImageIcon(imageLoader.getCardsImages().get(entry.getValue()).getScaledInstance(140, 197, Image.SCALE_SMOOTH)));
                actionController.log("card clicked for showing its info - Collection ");
            },false);
        }
        deckCards.repaint();
        revalidate();
    }

    public void updateDecksBar(ArrayList<String> deckNames) {
        decksBar.removeAll();
        for (String deck : deckNames) {
            JButton jButton = new MyButton(deck, "darkBlueButton.jpg", decksBar, null);
            jButton.addActionListener(actionEvent -> {
                currentDeck = deck;
                actionController.updateDeckShowCase(currentDeck);
            });
        }
        decksBar.add(creat);
        decksBar.repaint();
        revalidate();
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        actionController.setShowCase(manaFilter.getValue(), null, null, null, false, false);
        actionController.log("mana filter state changed to " + manaFilter.getValue());
    }
}
