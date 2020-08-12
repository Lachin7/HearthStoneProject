package server.controller;

import client.gui.myComponents.GuiCard;
import lombok.Getter;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Cards.Weapon;
import server.models.Deck;
import server.models.Player;
import resLoader.database.DataBase;
import server.ClientHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardController {

    @Getter
    private List<Card> AllCardsInGame;
    private ClientHandler clientHandler;
    private DataBase dataBase;

    public CardController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        dataBase = clientHandler.getServer().getDataBase();
        AllCardsInGame = fetchAllCards();
    }

    private List<Card> fetchAllCards() {
        List<Card> cards = dataBase.fetchAll(Card.class);
        clientHandler.getServer().getAllProducedCards().addAll(cards);
        return cards;
    }

    public ArrayList<Card> getHeroCardsInGame(Card.HeroClass heroClass) {
//        dataBase.fetchWithCondition(Card.class, "Card.HeroClass",heroClass);
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : AllCardsInGame) if (card.getHeroClass() == heroClass) result.add(card);
        return result;
    }


    public Boolean isLocked(Card card) {
        for (Card card1 : clientHandler.getMainPlayer().getAllCards())if (card1.getName().equals(card.getName())) return false;
        return true;
    }

    public ArrayList<Card> getLockedCards() {
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : AllCardsInGame) if (isLocked(card)) result.add(card);
        return result;
    }

    private boolean isInDecks(Card card) {
        for (Deck deck : clientHandler.getMainPlayer().getDecks())
            for (Card c : deck.getCards()) if (card.getName().equals(c.getName()))return true;
        return false;
    }

    public ArrayList<Card> getTypeCards(Card.type type) {
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : AllCardsInGame) if (card.getType() == type) result.add(card);
        return result;
    }

    public ArrayList<Card> getCardsForSell() {
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : getAllCardsInGame()) if (!isInDecks(card)) result.add(card);
        return result;
    }

    public Card createCard(String name) {
        Card card = dataBase.fetch(Card.class,name);
        clientHandler.getServer().getAllProducedCards().add(card);
        return card;
    }

    public List<Card> createDefaultDeck(){
        return new ArrayList<>(Arrays.asList(createCard("BeamingSidekick"),createCard("BonechewerVanguard"),createCard("ConchguardWarlord"),createCard("Dragonrider"),createCard("BeamingSidekick"),createCard("DreadScale"),createCard("FrozenShadoweaver"),createCard("FungalBruiser"),createCard("BeamingSidekick"),createCard("GoblinBomb"),createCard("HighPriestAmet"),createCard("Lifedrinker"),createCard("Locust"),createCard("LostSpirit"),createCard("MagmaRager"),createCard("MurlocRaider"),createCard("Ratcatcher"),createCard("RocketAugmerchant"),createCard("Sathrovarr"),createCard("ScavengingShivarra")));
    }


    public Boolean canBuy(String cardName) {
        Card card = createCard(cardName);
        if (!isLocked(card)) return false;
        return card.getPrice() <= clientHandler.getMainPlayer().getCoins();
    }

    public void buyCard(long id) {
        clientHandler.getMainPlayer().getAllCards().add(getCardWithId(id));
        clientHandler.getMainPlayer().setCoins(clientHandler.getMainPlayer().getCoins() - getCardWithId(id).getPrice());
    }

    public void sellCard(long id) {
        clientHandler.getMainPlayer().getAllCards().remove(getCardWithId(id));
        clientHandler.getMainPlayer().setCoins(clientHandler.getMainPlayer().getCoins() + getCardWithId(id).getPrice());
    }

    public ArrayList<Card> searchFilter(String text) {
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : AllCardsInGame) if (card.getName().toLowerCase().contains(text.toLowerCase())) result.add(card);
        return result;
    }

    public Boolean validDeckName(String name) {
        if (name.equals("")) return false;
        for (Deck deck : clientHandler.getMainPlayer().getDecks()) if (deck.getName().equals(name)) return false;
        return true;
    }

    public void creatDeck(String name) {
        if (validDeckName(name)) {
            Deck deck = new Deck(name, new ArrayList<>());
            clientHandler.getMainPlayer().getDecks().add(deck);
            System.out.println(clientHandler.getMainPlayer().getDecks());
        }
    }


    public Player getCurrentPlayer() {
        return clientHandler.getMainPlayer();
    }

    public Deck getTheDeck(String deckName) {
        for (Deck deck : clientHandler.getMainPlayer().getDecks()) if (deck.getName().equals(deckName)) return deck;
        return null;
    }

    public boolean canAddToDeck(String name, String deckName) {
        int i = 0;
        for (Card card : getTheDeck(deckName).getCards()) {
            if (card.getName().equals(name)) i++;
            if (i >= 2) return false;
        }
        return true;
    }


    public void addCardToDeck(String cardName, String deckName) {
        getTheDeck(deckName).getCards().add(createCard(cardName));
        if (createCard(cardName).getHeroClass() != Card.HeroClass.NEUTRAL)
            getTheDeck(deckName).setHero(createCard(cardName).getHeroClass());
    }

    public void removeFromDeck(String cardName, String deckName) {
        getTheDeck(deckName).getCards().remove(createCard(cardName));
    }

    public void removeDeck(String currentDeck) {
        clientHandler.getMainPlayer().getDecks().remove(getTheDeck(currentDeck));
        if (clientHandler.getMainPlayer().getDeck().getName().equals(currentDeck))
            clientHandler.getMainPlayer().setDeck(null);
    }

    public Boolean canChangeDeckHero(String deckName) {
        for (Card card : getTheDeck(deckName).getCards())
            if (card.getHeroClass() != Card.HeroClass.NEUTRAL) return false;
        return true;
    }

    public boolean wrongHeroClass(String name, String currentDeck) {
        if (createCard(name).getHeroClass() == Card.HeroClass.NEUTRAL) return false;
        if (getTheDeck(currentDeck).getHero() == null) return false;
        else return createCard(name).getHeroClass() != getTheDeck(currentDeck).getHero();
    }

    public Card getCardWithId(long id) {
        for (Card card : clientHandler.getServer().getAllProducedCards()) {
            if (card !=null && card.getId() == id) {
                return card;
            }
        }
        System.out.println("card id not found!!!!" );
        return null;
    }

    public GuiCard creatGuiCard(Card card) {
        int hp = -30,attack = -30,durability = -30;
        boolean hasShield = false, hasTaunt = false, canAttack = false;
        if (card instanceof Minion) {
            hp = ((Minion) card).getHP();
            attack = ((Minion) card).getAttack();
            hasShield = ((Minion) card).hasDivineShield();
            hasTaunt = ((Minion) card).hasTaunt();
            canAttack = ((Minion) card).canAttack();
        }
        if (card instanceof Weapon){
            attack = ((Weapon) card).getAttack();
            durability = ((Weapon) card).getDurability();
        }
       return new GuiCard(card.getName(),card.getId(),card.getManaCost(),hp,attack,durability,card.getType(),canAttack,hasTaunt,isLocked(card),hasShield);
    }
}
