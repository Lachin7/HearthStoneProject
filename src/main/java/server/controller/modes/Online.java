package server.controller.modes;

import lombok.Setter;
import models.board.Side;
import server.ClientHandler;
import server.controller.BoardController;

public class Online extends BoardController {
    @Setter
    private ClientHandler enemy;
    public Online(ClientHandler clientHandler) {
        super(clientHandler);
    }

    @Override
    protected void setPlayers() {
        chooseMainAsFriend();
        resetPlayer(enemyPlayer);
    }

    @Override
    public boolean getAllowance(Side side) {
        return side == Side.FRIENDLY;
    }
}
