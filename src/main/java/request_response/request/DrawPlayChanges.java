package request_response.request;

import server.ClientHandler;

public class DrawPlayChanges extends Request {

    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.sendResponse("DrawPlayChanges", new request_response.response.DrawPlayChanges(clientHandler.getBoardController().getFriendlyPlayer().getChoosedHero().getHP(), clientHandler.getBoardController().getEnemyPlayer().getChoosedHero().getHP(), clientHandler.getBoardController().getFriendlyPlayer().getDeckCardsInGame().size(), clientHandler.getBoardController().getEnemyPlayer().getDeckCardsInGame().size(),clientHandler.getBoardController().getFriendlyPlayer().getCurrentMana(),clientHandler.getBoardController().getEnemyPlayer().getCurrentMana()));
    }
}
