package request_response.request;

import server.ClientHandler;

public class PlayTargetedPower extends Request {
    private long id;
    public PlayTargetedPower(long id) {
        this.id = id;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.getBoardController().playTargetedPower(id);
    }
}
