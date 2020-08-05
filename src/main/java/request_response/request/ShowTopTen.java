package request_response.request;

import models.Player;
import server.ClientHandler;

import java.util.*;

public class ShowTopTen extends Request {
    @Override
    public void execute(ClientHandler clientHandler) {
        LinkedHashMap<String,Integer> players = new LinkedHashMap<>();
        List<Player> all = clientHandler.getServer().getAllPlayers();
        all.sort(Comparator.comparing(Player::getCups));
        for (int i = 0; i < 10 ; i++)if (i<all.size())players.put(all.get(i).getName(),all.get(i).getCups());
        clientHandler.sendResponse("ShowTopTen",new request_response.response.ShowTopTen(players));
    }
}
