package request_response.request;

import javafx.util.Pair;
import models.Cards.Card;
import server.ClientHandler;

import java.util.HashMap;
import java.util.logging.Level;

public class SetBuyShowCase extends Request {
    @Override
    public void execute(ClientHandler clientHandler) {
        HashMap<Pair<Long,String>,Integer> cards = new HashMap<>();
        for (Card card : clientHandler.getCardController().getLockedCards())cards.put(new Pair<>(card.getId(),card.getName()),card.getPrice());
        clientHandler.sendResponse("SetBuyShowCase",new request_response.response.SetBuyShowCase(cards,clientHandler.getMainPlayer().getCoins()));
        clientHandler.getPlayerLOGGER().log(Level.FINE,"button clicked to illustrate buy show case - Shop");
    }
}
