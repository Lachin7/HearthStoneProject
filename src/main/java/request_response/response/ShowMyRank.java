package request_response.response;

import client.ClientGui;
import javafx.util.Pair;

import java.util.LinkedHashMap;

public class ShowMyRank extends Response{
    LinkedHashMap<Pair<String , Integer>,Integer> players;

    public ShowMyRank(LinkedHashMap<Pair<String, Integer>, Integer> players) {
        this.players = players;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getRankPanel().showMyRank(players);
    }
}
