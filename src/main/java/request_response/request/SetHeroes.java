package request_response.request;

import server.ClientHandler;

public class SetHeroes extends Request {
    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.sendResponse("SetHeroes",new request_response.response.SetHeroes(clientHandler.getBoardController().getFriendlyPlayer().getChoosedHero().toString(),clientHandler.getBoardController().getEnemyPlayer().getChoosedHero().toString()));
    }
}
