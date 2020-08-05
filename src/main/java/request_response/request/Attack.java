package request_response.request;

import server.ClientHandler;
import server.controller.BoardController;

public class Attack extends Request {
    private long attacker, target;

    public Attack(long attacker, long target) {
        this.attacker = attacker;
        this.target = target;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.getBoardController().attack(clientHandler.getBoardController().getTheCardWithID(attacker), clientHandler.getBoardController().getTheCardWithID(target));
    }
}
