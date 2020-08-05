package request_response.request;

import server.ClientHandler;

public class EndTurn extends Request {
    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.getBoardController().endTurn();
    }
}
