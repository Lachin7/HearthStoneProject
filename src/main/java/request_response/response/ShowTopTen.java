package request_response.response;

import client.ClientGui;

import java.util.LinkedHashMap;

public class ShowTopTen extends Response{
    private LinkedHashMap<String ,Integer> players;

    public ShowTopTen(LinkedHashMap<String, Integer> players) {
        this.players = players;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getRankPanel().showTopTen(players);
    }
}
