package request_response.request;

import server.ClientHandler;
import server.models.util.MyPair;

import java.util.HashMap;

public class ShowWatchableGames extends Request {
    @Override
    public void execute(ClientHandler clientHandler) {
        HashMap<String,String> games = new HashMap<>();
        for (MyPair<ClientHandler,ClientHandler> pair : clientHandler.getServer().getRunningGames().keySet())games.put(pair.getKey().getMainPlayer().getName(),pair.getValue().getMainPlayer().getName());
//        for (Map.Entry<MyPair<ClientHandler,ClientHandler>,HashMap<ClientHandler,Integer>> entry : clientHandler.getServer().getRunningGames().entrySet())
//            games.put(entry.getKey().getKey().getMainPlayer().getName(),entry.getKey().getValue().getMainPlayer().getName());
        clientHandler.sendResponse("ShowWatchableGames",new request_response.response.ShowWatchableGames(games));
    }
}
