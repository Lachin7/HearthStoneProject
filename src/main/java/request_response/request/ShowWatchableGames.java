package request_response.request;

import javafx.util.Pair;
import server.ClientHandler;

import java.util.HashMap;
import java.util.Map;

public class ShowWatchableGames extends Request {
    @Override
    public void execute(ClientHandler clientHandler) {
        HashMap<String,String> games = new HashMap<>();
        for (Map.Entry<Pair<ClientHandler,ClientHandler>,HashMap<ClientHandler,Integer>> entry : clientHandler.getServer().getRunningGames().entrySet())
            games.put(entry.getKey().getKey().getMainPlayer().getName(),entry.getKey().getValue().getMainPlayer().getName());
        clientHandler.sendResponse("ShowWatchableGames",new request_response.response.ShowWatchableGames(games));
    }
}
