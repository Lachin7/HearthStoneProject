package models;

import controller.Controller;
import models.Cards.Card;
import com.google.gson.annotations.Expose;
import models.Cards.Minion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Deck {

    @Expose ArrayList<Card> cards;
    @Expose String name;
    @Expose int allGamesPlayed = 0 ,winGamesPlayed = 0 ;
    @Expose Card.HeroClass hero;
    @Expose double winToAll;
    @Expose Card mostUsedCard;
    private Comparator<Deck>  deckComparator = (deck1, deck2) -> {
        int compareWinToAll = (int)(deck1.winToAll()-deck2.winToAll());
        int compareWins = deck1.getWinGamesPlayed()-deck2.getWinGamesPlayed();
        int compareTotalGames = deck1.getAllGamesPlayed()- deck2.getAllGamesPlayed();
        int compareAvePrice = deck1.averagePrice() - deck2.averagePrice();
        if(compareWinToAll!=0) return compareWinToAll;
        if(compareWins!=0) return compareWins;
        if(compareTotalGames!=0) return compareTotalGames;
        return compareAvePrice;
    };
    private Comparator<Card> mostUsedCardCompare = (card2,card1) ->{
        int usedTimes = usedTimes(card1)-usedTimes(card2);
        int price = card1.getPrice()-card2.getPrice();
        int mana = card1.getManaCost()-card2.getManaCost();
//        int type = card1.getType()-card2.getType();
        if(usedTimes!=0)return usedTimes;
        if(price!=0)return price;
        return mana;
//        if(card1.getType()== Card.type.MINION) return card1;
//        return card2;
    };



    public Deck(String name, ArrayList<Card> cards){
        this.name = name;
        this.cards = cards;
        winToAll = winToAll();
    }

    public int averagePrice(){
        if(cards.size()==0) return 0;
        int result = 0 ;
        for(Card card : cards){
            result+= card.getPrice();
        }
        result = result/cards.size();
        return result;
    }

    public double winToAll(){
        if(this.getAllGamesPlayed()==0){
            return 0;
        }
        else {
            return this.getWinGamesPlayed()/this.getAllGamesPlayed();
        }
    }

    public int usedTimes(Card card){
        int result = 0 ;
        for(Card card1 : cards){
            if(card == card1){
                result++;
            }
        }
        return result;
    }


    @Override
    public String toString() {
        return name;
    }



    public ArrayList<Card> getCards() {
        cards.sort(Comparator.comparing(Card::getName));
        return cards;
    }

    public ArrayList<Card> getCardsSortedByValue(){
        Collections.sort(cards,getMostUsedCardCompare());
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {

        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAllGamesPlayed() {
        return allGamesPlayed;
    }

    public void setAllGamesPlayed(int allGamesPlayed) {
        this.allGamesPlayed = allGamesPlayed;
    }

    public int getWinGamesPlayed() {
        return winGamesPlayed;
    }

    public void setWinGamesPlayed(int winGamesPlayed) {
        this.winGamesPlayed = winGamesPlayed;
    }

    public Card getMostUsedCard() {
        return mostUsedCard;
    }

    public Card.HeroClass getHero() {
        return hero;
    }

    public void setHero(Card.HeroClass hero) {
        this.hero = hero;
        if(Controller.getInstance().getMainPlayer().getPlayersDeck()==this)Controller.getInstance().getMainPlayer().setPlayersChoosedHero(hero);
    }

    public Comparator<Deck> getDeckComparator() {
        return deckComparator;
    }

    public Comparator<Card> getMostUsedCardCompare() {
        return mostUsedCardCompare;
    }

//    public Card mostUsedCard(){
//        ArrayList<Card> nominatedCards = this.getCards();
//        Card selected = this.getCards().get(0);
//        for(Card card : nominatedCards){
//            if(usedTimes(card) < usedTimes(selected)){
//                nominatedCards.remove(card);
//            }
//            if(usedTimes(card) > usedTimes(selected)){
//                nominatedCards.remove(selected);
//                selected = card;
//            }
//        }
//        for(Card card : nominatedCards){
//            if(card.getPrice() < selected.getPrice()){
//                nominatedCards.remove(card);
//            }
//            if(card.getPrice() > selected.getPrice()){
//                nominatedCards.remove(selected);
//                selected = card;
//            }
//        }
//        for(Card card : nominatedCards){
//            if(card.getManaCost() < selected.getManaCost()){
//                nominatedCards.remove(card);
//            }
//            if(card.getManaCost() > selected.getManaCost()){
//                nominatedCards.remove(selected);
//                selected = card;
//            }
//        }
//        for (Card  card : nominatedCards){
//            if(card.getType().toString().equalsIgnoreCase("Minion"))
//                selected = card;
//            break;
//        }
//        return selected;
//    }
}
