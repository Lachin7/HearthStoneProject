package request_response.response;

import client.ClientGui;

import java.util.HashMap;

public class CollectionCardsShowCase extends Response {
    private HashMap<Long,String> cards;
    private boolean hasClassBar;

    public CollectionCardsShowCase(boolean hasClassBar, HashMap<Long,String> cards) {
        this.cards = cards;
        this.hasClassBar = hasClassBar;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getCollectionPanel().updateCardsShowCase(hasClassBar, cards);
        for (Long id : clientGui.getCardButtons().keySet())clientGui.getActionController().drawInformationOnCard(id);

    }
}
