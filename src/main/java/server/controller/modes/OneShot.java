package server.controller.modes;

import server.models.Cards.Minion;
import server.ClientHandler;

public class OneShot extends Online {
    public OneShot(ClientHandler clientHandler) {
        super(clientHandler);
    }
    @Override
    public void changeMinion(Minion minion, int hpToAdd, int attackToAdd) {
        super.changeMinion(minion,hpToAdd,attackToAdd);
        if (hpToAdd<0)minion.setHP(0);
    }
}
