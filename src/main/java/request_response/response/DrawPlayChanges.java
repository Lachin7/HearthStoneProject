package request_response.response;

import client.Client;

public class DrawPlayChanges extends Response{
    private int friendlyHeroHp,  enemyHeroHp,  friendlyDeckSize,  enemyDeckSize,friendlyMana,enemyMana;

    public DrawPlayChanges(int friendlyHeroHp, int enemyHeroHp, int friendlyDeckSize, int enemyDeckSize,int friendlyMana,int enemyMana) {
        this.friendlyHeroHp = friendlyHeroHp;
        this.enemyHeroHp = enemyHeroHp;
        this.friendlyDeckSize = friendlyDeckSize;
        this.enemyDeckSize = enemyDeckSize;
        this.friendlyMana = friendlyMana;
        this.enemyMana = enemyMana;
    }

    @Override
    public void execute(Client client) {
        client.getPlayPanel().drawChanges(friendlyHeroHp,enemyHeroHp,friendlyDeckSize,enemyDeckSize,friendlyMana,enemyMana);
    }
}
