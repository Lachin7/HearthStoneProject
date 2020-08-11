package request_response.response;

import client.ClientGui;

public class SetHeroes extends Response{
    private String friendly, enemy;
    public SetHeroes(String friendly, String enemy) {
        this.enemy = enemy;
        this.friendly =friendly;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getPlayPanel().setHeroes(friendly,enemy);
    }
}
