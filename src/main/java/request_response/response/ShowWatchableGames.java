package request_response.response;

import client.ClientGui;

import java.util.HashMap;

public class ShowWatchableGames extends Response{
    private HashMap<String,String> games;

    public ShowWatchableGames(HashMap<String, String> games) {
        this.games = games;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getPrePlayPanel().setWatchableGamesList(games);
    }
}
