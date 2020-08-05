package server.controller.util;

import models.Deck;
import models.Player;

import java.util.Comparator;

public class PlayerComparator {
    private final Comparator<Player> playersSetUpComparator = (player1, player2) -> {
        int wins5 = player1.getWinLoseHistoryIn(5)-player2.getWinLoseHistoryIn(5);
        int cups = player1.getCups()-player2.getCups();
        int wins = player1.getWins() - player2.getWins();
        if(wins5!=0) return wins5;
        if(cups!=0) return cups;
        return wins;
    };

    public Comparator<Player> getPlayersSetUpComparator() {
        return playersSetUpComparator;
    }
}
