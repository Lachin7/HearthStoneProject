package server.controller.modes;

import models.Cards.Minion;
import models.board.Side;
import server.ClientHandler;
import server.controller.BoardController;

public class TavernBrawl extends Online {
    public TavernBrawl(ClientHandler clientHandler) {
        super(clientHandler);
    }

    @Override
    protected void changeManaForTurn() {
        super.changeManaForTurn();
    }

    @Override
    protected int getManaLimit() {
        return super.getManaLimit();
    }

    @Override
    protected int getHandCardsLimit() {
        return 12;
    }
}
