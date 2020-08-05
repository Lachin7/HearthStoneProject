package request_response.request;

import request_response.response.UpdateDeckCollection;
import server.ClientHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateDeckShowCase extends Request{
    private String deckName;

    public UpdateDeckShowCase(String deckName) {
        this.deckName = deckName;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        HashMap<Long,String> cards = new HashMap<>();
        ArrayList<String> deckNames = new ArrayList<>();
        clientHandler.getMainPlayer().getDecks().forEach(deck -> deckNames.add(deck.getName()));
        if (deckName==null) deckName = clientHandler.getMainPlayer().getDecks().get(0).getName();
        clientHandler.getCardController().getTheDeck(deckName).getCards().forEach(card -> cards.put(card.getId(),card.getName()));
        clientHandler.sendResponse("UpdateDeckShowCase", new UpdateDeckCollection(cards,clientHandler.getCardController().getTheDeck(deckName).getHero().toString(),deckNames));
    }
}
