package request_response.response;

import client.Client;
import models.board.Side;

import java.util.HashMap;

public class UpdateFieldCards extends Response{
    private Side side;
    private boolean allowance, tauntExist;
    private HashMap<Long,String> cards;

    public UpdateFieldCards(Side side, boolean allowance, HashMap<Long, String> cards,boolean tauntExist) {
        this.side = side;
        this.allowance = allowance;
        this.cards = cards;
        this.tauntExist = tauntExist;
    }

    @Override
    public void execute(Client client) {
        client.getPlayPanel().updateFieldCards(side,cards,allowance,tauntExist);
    }
}
