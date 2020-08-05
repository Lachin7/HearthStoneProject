package request_response.response;

import client.Client;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowCardsInStatus extends Response{
    private HashMap<Long,String> cards;

    public ShowCardsInStatus(HashMap<Long,String> cards) {
        this.cards = cards;
    }

    @Override
    public void execute(Client client) {
        client.getStatusPanel().updateCardsPanel(cards);
    }
}
