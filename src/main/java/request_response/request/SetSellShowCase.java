package request_response.request;

import server.models.Cards.Card;
import server.models.util.MyPair;
import server.ClientHandler;

import java.util.HashMap;

public class SetSellShowCase extends Request {
    @Override
    public void execute(ClientHandler clientHandler) {
        HashMap<MyPair<Long,String>,Integer> cards = new HashMap<>();
        for (Card card : clientHandler.getCardController().getCardsForSell())cards.put(new MyPair<>(card.getId(),card.getName()),card.getPrice());
        clientHandler.log("button clicked to illustrate sell show case - Shop");
        clientHandler.sendResponse("SetSellShowCase",new request_response.response.SetSellShowCase(cards,clientHandler.getMainPlayer().getCoins()));
    }
}
