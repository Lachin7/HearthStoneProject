package controller;

import JSON.JsonAdapter;
import models.Cards.Card;
import com.google.gson.*;

import models.Cards.minions.*;
import models.Cards.spells.*;

import models.Cards.spells.questAndReward.LearnDraconic;
import models.Cards.spells.questAndReward.StrengthInNumbers;
import models.Cards.weapons.*;
import models.Deck;
import models.Player;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static JSON.jsonForCards.jsonForCards.creatCardFromjson;

public class CardController {


    ArrayList<Card> AllCardsInGame, AllMageCards, AllRougeCards, AllWarlockCards, AllNeutralCards,AllPriestCards,AllHunterCards,LockedCards;

    public CardController(){
        AllCardsInGame = getALLCardsExistingInGame();
        AllMageCards = getHeroCardsInGame(Card.HeroClass.MAGE);
        AllRougeCards = getHeroCardsInGame(Card.HeroClass.ROGUE);
        AllWarlockCards = getHeroCardsInGame(Card.HeroClass.WARLOCK);
        AllNeutralCards = getHeroCardsInGame(Card.HeroClass.NEUTRAL);
        AllPriestCards = getHeroCardsInGame(Card.HeroClass.PRIEST);
        AllHunterCards = getHeroCardsInGame(Card.HeroClass.HUNTER);
    }


    public static ArrayList<Card> getALLCardsExistingInGame() {
        ArrayList<Card> arrayList = new ArrayList<>();
        File AllCards = new File("./src/main/java/JSON/jsonForCards/jsonFilesForCards");
        File[] CardFiles = AllCards.listFiles();
        for(File file : CardFiles){
            String fileName = file.getName();
            fileName = fileName.substring(0,fileName.length()-5);
            arrayList.add(creatCardFromjson(fileName));
        }
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(Card.class, new JsonAdapter<>());
//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
//
//        for (File file : CardFiles){
//            FileReader fileReader = null;
//            try {
//                fileReader = new FileReader("./src/main/java/JSON/jsonForCards/jsonFilesForCards/" + file.getName());
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            Card card = gson.fromJson(fileReader, Card.class);
//            arrayList.add(card);
//            try {
//                fileReader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return arrayList;
    }

    private ArrayList<Card> getHeroCardsInGame(Card.HeroClass heroClass){
        ArrayList<Card> result = new ArrayList<>();
        for(Card card : AllCardsInGame){
            if(card.getHeroClass()== heroClass) result.add(card);
        }
        return result;
    }



    public Boolean isLocked(Card card){
        if(Controller.getInstance().getMainPlayer().getALLPlayersCards().contains(card)) return false;
        return true;
    }

    public ArrayList<Card> getLockedCards(){
        ArrayList<Card> result = new ArrayList<>();
        for(Card card : AllCardsInGame){
            if(isLocked(card)) result.add(card);
        }
        return result;
    }

    private Boolean isInDecks(Card card){
        for (Deck deck : Controller.getInstance().getMainPlayer().getDecks()){
                if(deck.getCards().contains(card)) return true;
        }
        return false;
    }
    public ArrayList<Card> getTypeCards(Card.type type){
        ArrayList<Card> result = new ArrayList<>();
        for(Card card : AllCardsInGame){
            if(card.getType()== type) result.add(card);
        }
        return result;
    }

    public ArrayList<Card> getCardsForSell(){
        ArrayList<Card> result = new ArrayList<>();
        for (Card card : Controller.getInstance().getMainPlayer().getALLPlayersCards()){
            if(!isInDecks(card)) result.add(card);
        }
        return result;
    }

    public static Card creatCard(String name) {
       return creatCardFromjson(name);
    }


//     Card card = creatCard(name);
//        try {
//            String className = card.getClass()+"";
//            className = className.substring(6);
//            card =  (Card) Class.forName(className).getConstructor().newInstance();
//        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return card;


//

    public Boolean canBuy(String cardName){
        Card card = creatCard(cardName);
        if(!isLocked(card)) return false;
        return card.getPrice() <= Controller.getInstance().getMainPlayer().getPlayerCoins();
    }

    public void buyCard(String cardName) {
        Card card = creatCard(cardName);
        Controller.getInstance().getMainPlayer().getALLPlayersCards().add(card);
        Controller.getInstance().getMainPlayer().setPlayerCoins(Controller.getInstance().getMainPlayer().getPlayerCoins()-card.getPrice());
    }


    public void sellCard(String cardName) {
        Card card = creatCard(cardName);
        Controller.getInstance().getMainPlayer().getALLPlayersCards().remove(card);
        Controller.getInstance().getMainPlayer().setPlayerCoins(Controller.getInstance().getMainPlayer().getPlayerCoins()+card.getPrice());
    }

    public ArrayList<Card> searchFilter(String text) {
        ArrayList<Card> result =new ArrayList<>();
        for(Card  card : AllCardsInGame) {
            if (card.getName().toLowerCase().contains(text.toLowerCase())) result.add(card);
        }
        return result;
    }

    public Boolean validDeckName(String name){
        if(name.equals("")) return false;
        for(Deck deck : Controller.getInstance().getMainPlayer().getDecks()){
            if(deck.getName().equals(name)) return false;
        }
        return true;
    }

    public void creatDeck(String name){
        if(validDeckName(name)) {
            Deck deck = new Deck(name, new ArrayList<>());
            Controller.getInstance().getMainPlayer().getDecks().add(deck);
            System.out.println(Controller.getInstance().getMainPlayer().getDecks());
        }
    }

    public ArrayList<Card> getAllCardsInGame() {
        return AllCardsInGame;
    }

    public ArrayList<Card> getAllMageCards() {
        return AllMageCards;
    }

    public ArrayList<Card> getAllRougeCards() {
        return AllRougeCards;
    }

    public ArrayList<Card> getAllWarlockCards() {
        return AllWarlockCards;
    }

    public ArrayList<Card> getAllNeutralCards() {
        return AllNeutralCards;
    }

    public ArrayList<Card> getAllPriestCards() {
        return AllPriestCards;
    }

    public ArrayList<Card> getAllHunterCards() {
        return AllHunterCards;
    }

    public Player getCurrentPlayer() {
        return Controller.getInstance().getMainPlayer();
    }

    public Deck getTheDeck(String deckName){
        for(Deck deck : Controller.getInstance().getMainPlayer().getDecks()){
            if(deck.getName().equals(deckName)) return deck;
        }
        return null;
    }

    public boolean canAddToDeck(String name, String deckName) {
        int i = 0;
        for(Card card : getTheDeck(deckName).getCards()){
            if(card.getName().equals(name)) i++;
            if(i>=2) return false;
        }
        return true;
    }

    public  Card createCard(String name){
        Card card = creatCard(name);
        try {
            String className = card.getClass()+"";
            className = className.substring(6);
            card =  (Card) Class.forName(className).getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return card;
    }

    public void addCardToDeck(String cardName, String deckName) {
        getTheDeck(deckName).getCards().add(creatCard(cardName));
        if(creatCard(cardName).getHeroClass()!= Card.HeroClass.NEUTRAL) getTheDeck(deckName).setHero(creatCard(cardName).getHeroClass());
    }

    public void removeFromDeck(String cardName, String deckName) {
        getTheDeck(deckName).getCards().remove(creatCard(cardName));
    }

    public void removeDeck(String currentDeck) {
        Controller.getInstance().getMainPlayer().getDecks().remove(getTheDeck(currentDeck));
        if(Controller.getInstance().getMainPlayer().getPlayersDeck().getName().equals(currentDeck)) Controller.getInstance().getMainPlayer().setPlayersDeck(null);
    }

    public Boolean canChangeDeckHero(String deckName){
        for(Card card : getTheDeck(deckName).getCards()) if(card.getHeroClass()!= Card.HeroClass.NEUTRAL) return false;
        return true;
    }

    public boolean wrongHeroClass(String name, String currentDeck) {
        if(creatCard(name).getHeroClass()== Card.HeroClass.NEUTRAL) return false;
        if(getTheDeck(currentDeck).getHero() == null ) return false;
        else return creatCard(name).getHeroClass() != getTheDeck(currentDeck).getHero();
    }




//    public void setDecksHeroOnCard(){
//        for (Deck deck : Controller.getInstance().getCurrentPlayer().getDecks()){
//            for (Card card : deck.getCards()){
//            }
//        }
//    }


//    public void updateCardsShowCase(JPanel panel, ArrayList<Card> cards, Card shownCard){
//        panel.removeAll();
//        for (Card card : cards){
//            MyCardButton myCardButton = new MyCardButton(card.getName(),100,panel);
//            myCardButton.addClickListener();
//            myCardButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent actionEvent) {
//                    shownCard = card;
//                }
//            });
//        }
//        panel.repaint();
//        panel.revalidate();
//    }

}
