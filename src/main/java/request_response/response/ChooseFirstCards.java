package request_response.response;

import client.ClientGui;

import java.util.HashMap;

public class ChooseFirstCards extends Response{
    private HashMap<Long,String> handsCards;
    public ChooseFirstCards(HashMap<Long,String> handsCards) {
        this.handsCards = handsCards;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getPrePlayPanel().chooseFirstCards(handsCards);
    }
}
