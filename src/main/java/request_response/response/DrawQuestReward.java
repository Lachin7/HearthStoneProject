package request_response.response;

import client.ClientGui;

import java.util.HashMap;

public class DrawQuestReward extends Response{
    HashMap<String,Integer> QR;

    public DrawQuestReward(HashMap<String, Integer> QR) {
        this.QR = QR;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getPlayPanel().showQuestReward(QR);
    }
}
