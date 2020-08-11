package request_response.request;

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
    }
}
