package request_response.response;

import client.ClientGui;
import request_response.request.DrawInformationOnCard;

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
    public void execute(ClientGui clientGui) {
        if (cards.size()!=0) clientGui.getCollectionPanel().updateDeckCards(hero,cards);
        if (deckNames.size()!=0) clientGui.getCollectionPanel().updateDecksBar(deckNames);
        for (Long id : clientGui.getCardButtons().keySet())clientGui.getActionController().drawInformationOnCard(id);

    }
}
