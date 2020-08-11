package request_response.response;

import client.ClientGui;

public class DrawPlayChanges extends Response{
    private int friendlyHeroHp,  enemyHeroHp,  friendlyDeckSize,  enemyDeckSize,friendlyMana,enemyMana;
    private String time;

    public DrawPlayChanges(int friendlyHeroHp, int enemyHeroHp, int friendlyDeckSize, int enemyDeckSize,int friendlyMana,int enemyMana, String time) {
        this.friendlyHeroHp = friendlyHeroHp;
        this.enemyHeroHp = enemyHeroHp;
        this.friendlyDeckSize = friendlyDeckSize;
        this.enemyDeckSize = enemyDeckSize;
        this.friendlyMana = friendlyMana;
        this.enemyMana = enemyMana;
        this.time = time;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getPlayPanel().setChanges(friendlyHeroHp,enemyHeroHp,friendlyDeckSize,enemyDeckSize,friendlyMana,enemyMana,time);
    }
}
