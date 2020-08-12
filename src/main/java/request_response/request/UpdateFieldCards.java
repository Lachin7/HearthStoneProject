package request_response.request;

import client.gui.myComponents.GuiCard;
import server.controller.Board.modes.Online;
import server.models.board.Side;
import server.ClientHandler;

import java.util.ArrayList;
import java.util.LinkedList;

public class UpdateFieldCards extends Request {
    private Side side;
    LinkedList<Long> ids;

    public UpdateFieldCards(Side side, LinkedList<Long> ids) {
        this.side = side;
        this.ids = ids;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        ArrayList<GuiCard> cards = new ArrayList<>();
        if (side == Side.FRIENDLY) {
            clientHandler.getBoardController().syncFriendlyFieldComponents(ids);
            clientHandler.getBoardController().getFriendlyPlayer().getFieldCardsInGame().forEach(card -> cards.add(clientHandler.getCardController().creatGuiCard(card)));
        } else {
            clientHandler.getBoardController().syncEnemyFieldComponents(ids);
            clientHandler.getBoardController().getEnemyPlayer().getFieldCardsInGame().forEach(card -> cards.add(clientHandler.getCardController().creatGuiCard(card)));
        }
        clientHandler.sendResponse("UpdateFieldCards", new request_response.response.UpdateFieldCards(side, clientHandler.getBoardController().getAllowance(side), cards,clientHandler.getBoardController().tauntExist()));

        if (clientHandler.getBoardController() instanceof Online && clientHandler.getBoardController().getAllowance(side)) {
            clientHandler.getEnemy().sendResponse("UpdateFieldCards",new request_response.response.UpdateFieldCards(side.getOpposite(), clientHandler.getEnemy().getBoardController().getAllowance(side.getOpposite()), cards,clientHandler.getBoardController().tauntExist()));
        }

    }
}
