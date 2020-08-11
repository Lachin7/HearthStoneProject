package request_response.response;

import client.ClientGui;
import server.models.util.MyPair;


import java.util.LinkedHashMap;

public class ShowMyRank extends Response{
    LinkedHashMap<MyPair<String , Integer>,Integer> players;

    public ShowMyRank(LinkedHashMap<MyPair<String, Integer>, Integer> players) {
        this.players = players;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getRankPanel().showMyRank(players);
    }
}
