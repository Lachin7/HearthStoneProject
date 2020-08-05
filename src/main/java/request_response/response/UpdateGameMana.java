package request_response.response;

import client.Client;

public class UpdateGameMana extends Response{
    private int friendlyMana, enemyMana;

    public UpdateGameMana(int friendlyMana, int enemyMana) {
        this.friendlyMana = friendlyMana;
        this.enemyMana = enemyMana;
    }

    @Override
    public void execute(Client client) {
        client.getPlayPanel().updateMana(friendlyMana,enemyMana);
    }
}
