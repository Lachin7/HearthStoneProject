package request_response.response;

import client.Client;

public class SetHeroes extends Response{
    private String friendly, enemy;
    public SetHeroes(String friendly, String enemy) {
        this.enemy = enemy;
        this.friendly =friendly;
    }

    @Override
    public void execute(Client client) {
        client.getPlayPanel().setHeroes(friendly,enemy);
    }
}
