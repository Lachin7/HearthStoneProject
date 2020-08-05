package request_response.response;

import client.Client;

import java.util.HashMap;

public class ShowWatchableGames extends Response{
    private HashMap<String,String> games;

    public ShowWatchableGames(HashMap<String, String> games) {
        this.games = games;
    }

    @Override
    public void execute(Client client) {
        client.getRunningGamesList().setLabels(games);
    }
}
