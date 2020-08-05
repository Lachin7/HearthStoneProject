package server.controller.modes;

import models.board.Side;
import server.ClientHandler;
import server.controller.BoardController;

public class WatchGame extends BoardController {
    public WatchGame(ClientHandler c1,ClientHandler c2) {
        super(c1);

    }

    @Override
    protected void setPlayers() {

    }

    @Override
    public boolean getAllowance(Side side) {
        return false;
    }

    @Override
    protected int getHandCardsLimit() {
        return 12;
    }
}
