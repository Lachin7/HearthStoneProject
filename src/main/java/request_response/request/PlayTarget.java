package request_response.request;

import server.ClientHandler;

public class PlayTarget extends Request {
    private long attacker, target;

    public PlayTarget(long attacker, long target) {
        this.attacker = attacker;
        this.target = target;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.getBoardController().playTarget(clientHandler.getCardController().getCardWithId(attacker), target);
    }
}
