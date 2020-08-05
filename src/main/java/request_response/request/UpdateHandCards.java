package request_response.request;

import models.board.Side;
import server.ClientHandler;

import java.util.HashMap;

public class UpdateHandCards extends Request {
    private Side side;

    public UpdateHandCards(Side side) {
        this.side = side;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        HashMap<Long, String> cards = new HashMap<>();
        if (side == Side.FRIENDLY)
            clientHandler.getBoardController().getFriendlyPlayer().getHandsCards().forEach(card -> cards.put(card.getId(), card.getName()));
        else
            clientHandler.getBoardController().getEnemyPlayer().getHandsCards().forEach(card -> cards.put(card.getId(), card.getName()));
        clientHandler.sendResponse("UpdateHandCards", new request_response.response.UpdateHandCards(side, clientHandler.getBoardController().getAllowance(side), cards));
    }
}
