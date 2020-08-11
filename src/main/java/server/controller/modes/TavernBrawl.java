package server.controller.modes;

import server.ClientHandler;

public class TavernBrawl extends Online {
    public TavernBrawl(ClientHandler clientHandler) {
        super(clientHandler);
        timePerTurn = 60000;
        manaLimit = 10;
        handCardsLimit = 12;
        fieldSizeLimit = 7;
    }

    @Override
    public int getManaLimit() {
        return super.getManaLimit();
    }

    @Override
    public int getFieldSizeLimit() {
        return super.getFieldSizeLimit();
    }

    @Override
    public int getTimePerTurn() {
        return super.getTimePerTurn();
    }

    @Override
    public int getHandCardsLimit() {
        return super.getHandCardsLimit();
    }
}
