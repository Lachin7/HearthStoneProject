package request_response.request;

import request_response.response.Switch;
import server.ClientHandler;
import server.controller.modes.Online;
import server.controller.modes.Practice;
import server.models.board.Side;

public class EndTurn extends Request {
    @Override
    public void execute(ClientHandler clientHandler) {
       if (clientHandler.getBoardController().getAllowance(Side.FRIENDLY) || clientHandler.getBoardController() instanceof Practice){
           clientHandler.getBoardController().endTurn();
           clientHandler.sendResponse("Switch",new Switch());
           if (clientHandler.getBoardController() instanceof Online){
               clientHandler.getEnemy().getBoardController().endTurn();
               clientHandler.getEnemy().sendResponse("Switch",new Switch());
           }
       }
//       if (clientHandler.getBoardController() instanceof Online)clientHandler.getEnemy().getBoardController().endTurn();
    }
}
