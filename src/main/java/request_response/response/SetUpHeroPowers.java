package request_response.response;

import client.Client;
import models.board.Side;

public class SetUpHeroPowers extends Response{
    private Side side;
    private boolean allowance,  hasEnoughMana,  hasTarget ;

    public SetUpHeroPowers(Side side, boolean allowance, boolean hasEnoughMana, boolean hasTarget) {
        this.side = side;
        this.allowance = allowance;
        this.hasEnoughMana = hasEnoughMana;
        this.hasTarget = hasTarget;
    }

    @Override
    public void execute(Client client) {
        client.getPlayPanel().setUpHeroPowers(side,allowance,hasEnoughMana,hasTarget);
    }
}
