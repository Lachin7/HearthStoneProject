package request_response.response;

import client.Client;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateDeckCollection extends Response{
    private HashMap<Long,String> cards;
    private ArrayList<String> deckNames;
    private String hero;
    public UpdateDeckCollection(HashMap<Long,String> cards, String hero, ArrayList<String> deckNames) {
        this.cards = cards;
        this.deckNames = deckNames;
        this.hero = hero;
    }
    @Override
    public void execute(Client client) {
        if (cards.size()!=0)client.getCollectionPanel().updateDeckCards(hero,cards);
        if (deckNames.size()!=0)client.getCollectionPanel().updateDecksBar(deckNames);
    }
}
