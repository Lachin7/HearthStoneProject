package request_response.response;

import client.Client;
import javafx.util.Pair;

import java.util.HashMap;

public class SetBuyShowCase extends Response{
    private HashMap<Pair<Long,String>,Integer>  cards;
    private long coins;

    public SetBuyShowCase(HashMap<Pair<Long, String>, Integer> cards, long coins) {
        this.cards = cards;
        this.coins = coins;
    }

    @Override
    public void execute(Client client) {
        client.getShopPanel().setBuyShowCase(cards,coins);
    }
}
