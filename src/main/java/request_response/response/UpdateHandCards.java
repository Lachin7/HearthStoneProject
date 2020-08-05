package request_response.response;

import client.Client;
import models.board.Side;

import java.util.HashMap;

public class UpdateHandCards extends Response{
    private Side side;
    private boolean allowance;
    private HashMap<Long,String> cards;

    public UpdateHandCards(Side side, boolean allowance, HashMap<Long, String> cards) {
        this.side = side;
        this.allowance = allowance;
        this.cards = cards;
    }

    @Override
    public void execute(Client client) {
        client.getPlayPanel().updateHandCards(side,cards,allowance);
    }
}
