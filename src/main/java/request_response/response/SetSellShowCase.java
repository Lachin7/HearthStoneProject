package request_response.response;

import client.ClientGui;
import request_response.request.DrawInformationOnCard;
import server.models.util.MyPair;

import java.util.HashMap;

public class SetSellShowCase extends Response{
    private HashMap<MyPair<Long,String>,Integer> cards;
    private long coins;

    public SetSellShowCase(HashMap<MyPair<Long, String>, Integer> cards, long coins) {
        this.cards = cards;
        this.coins = coins;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getShopPanel().setSellShowCase(cards,coins);
        for (Long id : clientGui.getCardButtons().keySet())clientGui.getActionController().drawInformationOnCard(id);

    }
}
