package request_response.request;

import javafx.util.Pair;
import models.Cards.Card;
import server.ClientHandler;

import java.util.HashMap;
import java.util.logging.Level;

public class SetSellShowCase extends Request {
    @Override
    public void execute(ClientHandler clientHandler) {
        HashMap<Pair<Long,String>,Integer> cards = new HashMap<>();
        for (Card card : clientHandler.getCardController().getCardsForSell())cards.put(new Pair<>(card.getId(),card.getName()),card.getPrice());
        clientHandler.getPlayerLOGGER().log(Level.FINE,"button clicked to illustrate sell show case - Shop");
        clientHandler.sendResponse("SetSellShowCase",new request_response.response.SetSellShowCase(cards,clientHandler.getMainPlayer().getCoins()));
    }
}
