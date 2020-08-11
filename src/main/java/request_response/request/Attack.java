package request_response.request;

import server.ClientHandler;

public class Attack extends Request {
    private Long attacker, target;

    public Attack(long attacker, Long target) {
        this.attacker = attacker;
        this.target = target;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        if(target==null) clientHandler.getBoardController().attack(clientHandler.getBoardController().getMinionWithID(attacker),clientHandler.getBoardController().getCurrentPlayer().getChoosedHero());
        else clientHandler.getBoardController().attack(clientHandler.getBoardController().getMinionWithID(attacker), clientHandler.getBoardController().getMinionWithID(target));
    }
}
