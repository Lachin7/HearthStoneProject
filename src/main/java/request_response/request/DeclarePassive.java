package request_response.request;

import server.controller.Board.modes.Practice;
import server.models.board.InfoPassive;
import server.ClientHandler;

public class DeclarePassive extends Request{
    private InfoPassive passive;

    public DeclarePassive(InfoPassive passive) {
        this.passive = passive;
    }
    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.getBoardController().getCurrentPlayer().setInfoPassive(passive);
        if(clientHandler.getBoardController() instanceof Practice)clientHandler.getBoardController().addSwitch();
    }
}
