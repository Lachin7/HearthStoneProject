package server.controller.util;

import server.models.Deck;

import java.util.Comparator;

public class MyDeckComparator {

    private final java.util.Comparator<Deck> deckComparator = (deck1, deck2) -> {
        int cups = deck1.getCups() - deck2.getCups();
        int compareWinToAll = (int)(deck1.winToAll()-deck2.winToAll());
        int compareWins = deck1.getWinGamesPlayed()-deck2.getWinGamesPlayed();
        int compareTotalGames = deck1.getAllGamesPlayed()- deck2.getAllGamesPlayed();
        int compareAvePrice = deck1.averagePrice() - deck2.averagePrice();
        if (cups!=0)return cups;
        if(compareWinToAll!=0) return compareWinToAll;
        if(compareWins!=0) return compareWins;
        if(compareTotalGames!=0) return compareTotalGames;
        return compareAvePrice;
    };

    public Comparator<Deck> getDeckComparator() {
        return deckComparator;
    }

}
