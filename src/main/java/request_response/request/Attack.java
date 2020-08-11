package request_response.request;

import server.ClientHandler;

public class Attack extends Request {
    private long attacker, target;

    public Attack(long attacker, long target) {
        this.attacker = attacker;
        this.target = target;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.getBoardController().attack(clientHandler.getBoardController().getMinionWithID(attacker), clientHandler.getBoardController().getMinionWithID(target));
    }
}
