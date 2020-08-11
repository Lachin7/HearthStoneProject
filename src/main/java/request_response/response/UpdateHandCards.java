package request_response.response;

import client.ClientGui;
import request_response.request.DrawInformationOnCard;
import server.models.board.Side;

import java.util.HashMap;

public class UpdateHandCards extends Response{
    private Side side;
    private boolean allowance, cardBacksVisible;
    private HashMap<Long,String> cards;

    public UpdateHandCards(Side side, boolean allowance, HashMap<Long, String> cards,boolean cardBacksVisible ) {
        this.side = side;
        this.cardBacksVisible = cardBacksVisible;
        this.allowance = allowance;
        this.cards = cards;
    }

    @Override
    public void execute(ClientGui clientGui) {

        clientGui.getPlayPanel().updateHandCards(side,cards,allowance,cardBacksVisible);
        for (Long id : clientGui.getCardButtons().keySet())clientGui.getActionController().drawInformationOnCard(id);

    }
}
