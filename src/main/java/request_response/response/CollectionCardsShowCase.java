package request_response.response;

import client.Client;

import java.util.ArrayList;
import java.util.HashMap;

public class CollectionCardsShowCase extends Response {
    private HashMap<Long,String> cards;
    private boolean hasClassBar;

    public CollectionCardsShowCase(boolean hasClassBar, HashMap<Long,String> cards) {
        this.cards = cards;
        this.hasClassBar = hasClassBar;
    }

    @Override
    public void execute(Client client) {
        client.getCollectionPanel().updateCardsShowCase(hasClassBar, cards);
    }
}
