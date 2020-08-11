package request_response.request;


import server.models.Cards.Card;
import server.models.util.MyPair;
import server.ClientHandler;

import java.util.HashMap;

public class SetBuyShowCase extends Request {
    @Override
    public void execute(ClientHandler clientHandler) {
        HashMap<MyPair<Long,String>,Integer> cards = new HashMap<>();
        for (Card card : clientHandler.getCardController().getLockedCards())cards.put(new MyPair<Long, String>(card.getId(),card.getName()),card.getPrice());
        clientHandler.sendResponse("SetBuyShowCase",new request_response.response.SetBuyShowCase(cards,clientHandler.getMainPlayer().getCoins()));
        clientHandler.log("button clicked to illustrate buy show case - Shop");
    }
}
