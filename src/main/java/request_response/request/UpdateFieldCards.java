package request_response.request;

import models.board.Side;
import server.ClientHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateFieldCards extends Request {
    private Side side;
    ArrayList<Long> ids;

    public UpdateFieldCards(Side side, ArrayList<Long> ids) {
        this.side = side;
        this.ids = ids;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        HashMap<Long, String> cards = new HashMap<>();
        if (side == Side.FRIENDLY) {
            clientHandler.getBoardController().syncFriendlyFieldComponents(ids);
            clientHandler.getBoardController().getFriendlyPlayer().getFieldCardsInGame().forEach(card -> cards.put(card.getId(), card.getName()));
        } else {
            clientHandler.getBoardController().syncEnemyFieldComponents(ids);
            clientHandler.getBoardController().getEnemyPlayer().getFieldCardsInGame().forEach(card -> cards.put(card.getId(), card.getName()));
        }
        clientHandler.sendResponse("UpdateFieldCards", new request_response.response.UpdateFieldCards(side, clientHandler.getBoardController().getAllowance(side), cards,clientHandler.getBoardController().tauntExist()));
    }
}
