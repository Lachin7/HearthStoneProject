package request_response.response;

import client.Client;

import java.util.HashMap;

public class DrawQuestReward extends Response{
    HashMap<String,Integer> friendlyQR,enemyQR;

    public DrawQuestReward(HashMap<String, Integer> friendlyQR, HashMap<String, Integer> enemyQR) {
        this.friendlyQR = friendlyQR;
        this.enemyQR = enemyQR;
    }

    @Override
    public void execute(Client client) {
        client.getPlayPanel().drawQuestReward(friendlyQR,enemyQR);
    }
}
