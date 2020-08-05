package request_response.request;

import server.ClientHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowCardsInStatus extends Request {
    private String deck;
    public ShowCardsInStatus(String deck) {
        this.deck =deck;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        HashMap<Long,String> cards =new HashMap<>();
        clientHandler.getCardController().getTheDeck(deck).getCards().forEach(card -> cards.put(card.getId(),card.getName()));
        clientHandler.sendResponse("ShowCardsInStatus",new request_response.response.ShowCardsInStatus(cards));
    }
}
