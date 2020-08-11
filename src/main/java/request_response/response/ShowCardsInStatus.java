package request_response.response;

import client.ClientGui;
import request_response.request.DrawInformationOnCard;

import java.util.HashMap;

public class ShowCardsInStatus extends Response{
    private HashMap<Long,String> cards;

    public ShowCardsInStatus(HashMap<Long,String> cards) {
        this.cards = cards;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getStatusPanel().updateCardsPanel(cards);
        for (Long id : clientGui.getCardButtons().keySet())clientGui.getActionController().drawInformationOnCard(id);

    }
}
