package request_response.request;

import server.ClientHandler;

public class UpdateGameMana extends Request {
    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.sendResponse("UpdateGameMana",new request_response.response.UpdateGameMana(clientHandler.getBoardController().getFriendlyPlayer().getCurrentMana(),clientHandler.getBoardController().getEnemyPlayer().getCurrentMana()));
    }
}
