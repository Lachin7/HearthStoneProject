package request_response.request;

import models.board.InfoPassive;
import server.ClientHandler;
import server.controller.modes.Practice;

public class DeclarePassive extends Request{
    private InfoPassive passive;

    public DeclarePassive(InfoPassive passive) {
        this.passive = passive;
    }
    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.getBoardController().getCurrentPlayer().setInfoPassive(passive);
    }
}
