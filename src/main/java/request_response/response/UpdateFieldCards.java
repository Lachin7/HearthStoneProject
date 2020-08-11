package request_response.response;

import client.ClientGui;
import client.gui.myComponents.GuiCard;
import request_response.request.DrawInformationOnCard;
import server.models.board.Side;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateFieldCards extends Response{
    private Side side;
    private boolean allowance, tauntExist;
    private ArrayList<GuiCard> cards;

    public UpdateFieldCards(Side side, boolean allowance, ArrayList<GuiCard> cards, boolean tauntExist) {
        this.side = side;
        this.allowance = allowance;
        this.cards = cards;
        this.tauntExist = tauntExist;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getPlayPanel().updateFieldCards(side,cards,allowance,tauntExist);
        for (Long id : clientGui.getCardButtons().keySet())clientGui.getActionController().drawInformationOnCard(id);

    }
}
