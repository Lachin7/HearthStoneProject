package request_response.response;

import client.Client;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ShowTopTen extends Response{
    private LinkedHashMap<String ,Integer> players;

    public ShowTopTen(LinkedHashMap<String, Integer> players) {
        this.players = players;
    }

    @Override
    public void execute(Client client) {
        client.getRankPanel().showTopTen(players);
    }
}
