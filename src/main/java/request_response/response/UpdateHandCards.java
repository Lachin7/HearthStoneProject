package request_response.response;

import client.ClientGui;
import client.gui.myComponents.GuiCard;
import request_response.request.DrawInformationOnCard;
import server.models.board.Side;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateHandCards extends Response{
    private Side side;
    private boolean allowance, cardBacksVisible;
    private ArrayList<GuiCard> cards;
    private int maxFieldSize;

    public UpdateHandCards(Side side, boolean allowance, ArrayList<GuiCard>  cards,boolean cardBacksVisible,int maxFieldSize ) {
        this.side = side;
        this.cardBacksVisible = cardBacksVisible;
        this.allowance = allowance;
        this.cards = cards;
        this.maxFieldSize = maxFieldSize;
    }

    @Override
    public void execute(ClientGui clientGui) {

        clientGui.getPlayPanel().updateHandCards(side,cards,allowance,cardBacksVisible,maxFieldSize);
        for (Long id : clientGui.getCardButtons().keySet())clientGui.getActionController().drawInformationOnCard(id);

    }
}
