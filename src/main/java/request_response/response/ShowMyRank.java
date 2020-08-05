package request_response.response;

import client.Client;
import javafx.util.Pair;

import java.util.LinkedHashMap;

public class ShowMyRank extends Response{
    LinkedHashMap<Pair<String , Integer>,Integer> players;

    public ShowMyRank(LinkedHashMap<Pair<String, Integer>, Integer> players) {
        this.players = players;
    }

    @Override
    public void execute(Client client) {
        client.getRankPanel().showMyRank(players);
    }
}
