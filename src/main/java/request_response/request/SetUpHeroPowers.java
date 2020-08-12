package request_response.request;

import server.ClientHandler;
import server.controller.Board.BoardController;

public class SetUpHeroPowers extends Request{
    @Override
    public void execute(ClientHandler clientHandler) {
        BoardController b = clientHandler.getBoardController();
        clientHandler.sendResponse("SetUpHeroPowers",new request_response.response.SetUpHeroPowers(b.getCurrentSide(),b.getAllowance(b.getCurrentSide()),b.hasManaForPower(),b.getCurrentPlayer().getChoosedHero().getHeroPower().isHasTarget()));

    }
}
