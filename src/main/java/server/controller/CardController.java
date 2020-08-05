package server.controller;

import models.Cards.Card;
import models.Deck;
import models.Player;
import server.ClientHandler;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static JSON.jsonForCards.jsonForCards.creatCardFromjson;

public class CardController {


    private ArrayList<Card> AllCardsInGame, LockedCards, allCardsProduced;
    private ClientHandler clientHandler;

    public CardController(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        AllCardsInGame = getALLCardsExistingInGame();
        allCardsProduced = new ArrayList<>();
    }

    public static ArrayList<Card> getALLCardsExistingInGame() {
        ArrayList<Card> arrayList = new ArrayList<>();
        File AllCards = new File("./src/main/java/JSON/jsonForCards/jsonFilesForCards");
        File[] CardFiles = AllCards.listFiles();
        for (File file : CardFiles) {
            String fileName = file.getName();
            fileName = fileName.substring(0, fileName.length() - 5);
            arrayList.add(creatCardFromjson(fileName));
        }
        return arrayList;
    }

    public ArrayList<Card> getHeroCardsInGame(Card.HeroClass heroClass) {
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : AllCardsInGame) {
            if (card.getHeroClass() == heroClass) result.add(card);
        }
        return result;
    }


    public Boolean isLocked(Card card) {
        return !clientHandler.getMainPlayer().getAllCards().contains(card);
    }

    public ArrayList<Card> getLockedCards() {
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : AllCardsInGame) if (isLocked(card)) result.add(card);
        return result;
    }

    private Boolean isInDecks(Card card) {
        for (Deck deck : clientHandler.getMainPlayer().getDecks())
            if (deck.getCards().contains(card)) return true;
        return false;
    }

    public ArrayList<Card> getTypeCards(Card.type type) {
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : AllCardsInGame) if (card.getType() == type) result.add(card);
        return result;
    }

    public ArrayList<Card> getCardsForSell() {
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : clientHandler.getMainPlayer().getAllCards()) if (!isInDecks(card)) result.add(card);
        return result;
    }

    public Card creatCard(String name) {
        Card card = creatCardFromjson(name);
        allCardsProduced.add(card);
        return card;
    }


    public Boolean canBuy(String cardName) {
        Card card = creatCard(cardName);
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

    public ArrayList<Card> getAllCardsInGame() {
        return AllCardsInGame;
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

    public Card createCard(String name) {
        Card card = creatCard(name);
        try {
            String className = card.getClass() + "";
            className = className.substring(6);
            card = (Card) Class.forName(className).getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return card;
    }

    public void addCardToDeck(String cardName, String deckName) {
        getTheDeck(deckName).getCards().add(creatCard(cardName));
        if (creatCard(cardName).getHeroClass() != Card.HeroClass.NEUTRAL)
            getTheDeck(deckName).setHero(creatCard(cardName).getHeroClass());
    }

    public void removeFromDeck(String cardName, String deckName) {
        getTheDeck(deckName).getCards().remove(creatCard(cardName));
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
        if (creatCard(name).getHeroClass() == Card.HeroClass.NEUTRAL) return false;
        if (getTheDeck(currentDeck).getHero() == null) return false;
        else return creatCard(name).getHeroClass() != getTheDeck(currentDeck).getHero();
    }

    public Card getCardWithId(long id) {
        for (Card card : allCardsProduced) if (card.getId() == id) return card;
        System.out.println("card id not found!!!!");
        return null;
    }
}
