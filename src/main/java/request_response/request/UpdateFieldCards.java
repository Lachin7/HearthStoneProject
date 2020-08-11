package request_response.request;

import server.controller.modes.Online;
import server.models.board.Side;
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
        System.out.println("execute update field req");
        HashMap<Long, String> cards = new HashMap<>();
        if (side == Side.FRIENDLY) {
            clientHandler.getBoardController().syncFriendlyFieldComponents(ids);
            clientHandler.getBoardController().getFriendlyPlayer().getFieldCardsInGame().forEach(card -> cards.put(card.getId(), card.getName()));
        } else {
            clientHandler.getBoardController().syncEnemyFieldComponents(ids);
            clientHandler.getBoardController().getEnemyPlayer().getFieldCardsInGame().forEach(card -> cards.put(card.getId(), card.getName()));
        }
        clientHandler.sendResponse("UpdateFieldCards", new request_response.response.UpdateFieldCards(side, clientHandler.getBoardController().getAllowance(side), cards,clientHandler.getBoardController().tauntExist()));
        System.out.println("update field req by : "+ clientHandler.getMainPlayer().getName());

        if (clientHandler.getBoardController() instanceof Online && clientHandler.getBoardController().getAllowance(side)) {
            clientHandler.getEnemy().sendResponse("UpdateFieldCards",new request_response.response.UpdateFieldCards(side.getOpposite(), clientHandler.getEnemy().getBoardController().getAllowance(side.getOpposite()), cards,clientHandler.getBoardController().tauntExist()));
            System.out.println("update field req by : "+ clientHandler.getEnemy().getMainPlayer().getName());
        }


    }
}
