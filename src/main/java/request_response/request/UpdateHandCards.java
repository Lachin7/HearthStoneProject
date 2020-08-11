package request_response.request;

import client.gui.myComponents.GuiCard;
import server.controller.modes.Online;
import server.models.board.Side;
import server.ClientHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateHandCards extends Request {
    private Side side;

    public UpdateHandCards(Side side) {
        this.side = side;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        ArrayList<GuiCard> cards = new ArrayList<>();
        if (side == Side.FRIENDLY)
            clientHandler.getBoardController().getFriendlyPlayer().getHandsCards().forEach(card -> cards.add(clientHandler.getCardController().creatGuiCard(clientHandler.getCardController().getCardWithId(card.getId()))));
        else
            clientHandler.getBoardController().getEnemyPlayer().getHandsCards().forEach(card -> cards.add(clientHandler.getCardController().creatGuiCard(clientHandler.getCardController().getCardWithId(card.getId()))));
        clientHandler.sendResponse("UpdateHandCards", new request_response.response.UpdateHandCards(side, clientHandler.getBoardController().getAllowance(side), cards,clientHandler.getBoardController().getCardBackVisible(side)));

        if (clientHandler.getBoardController() instanceof Online ) clientHandler.getEnemy().sendResponse("UpdateHandCards",new request_response.response.UpdateHandCards(side.getOpposite(), clientHandler.getEnemy().getBoardController().getAllowance(side.getOpposite()), cards,clientHandler.getEnemy().getBoardController().getCardBackVisible(side.getOpposite())));
    }
}
