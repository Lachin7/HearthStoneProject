package server.controller.modes;

import models.board.Side;
import server.ClientHandler;
import server.controller.BoardController;

public class Practice extends BoardController {


    public Practice(ClientHandler clientHandler) {
        super(clientHandler);
    }

    @Override
    protected void setPlayers() {
        if(isANewGame()) {
            chooseMainAsFriend();
             //todo you can go and choose server.controller.getMainPlayer ... or create them here
            chooseMainAsEnemy();
        }
    }

    @Override
    public boolean getAllowance(Side side) {
        return (side == Side.FRIENDLY && switchTimes == 0) || side == Side.ENEMY && switchTimes == 1;
    }

//    @Override
//    public void checkForNewGame() {
//        super.checkForNewGame();
//        switchPlayers();
//        super.checkForNewGame();
//    }

//    @Override
//    public void endTurn() {
//        switchPlayers();
//        super.endTurn();
//    }
}
