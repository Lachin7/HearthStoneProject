package request_response.request;

import server.ClientHandler;

public class PlayHeroPower extends Request {
    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.getBoardController().playHeroPower();
    }
}
