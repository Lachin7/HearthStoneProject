package request_response.request;

import javafx.util.Pair;
import server.ClientHandler;
import server.models.Player;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

public class ShowRanks extends Request {
    @Override
    public void execute(ClientHandler clientHandler) {
        LinkedHashMap<Pair<String , Integer>,Integer> players = new LinkedHashMap<>();
        List<Player> all = clientHandler.getServer().getAllPlayers();
        all.sort(Comparator.comparing(Player::getCups));
        for (int i = 0; i < all.size() ; i++) {
            if (all.get(i).getName().equals(clientHandler.getMainPlayer().getName()) ){
                for (int j = i-5; j < i ; j++) if (j>=0) players.put(new Pair<>(all.get(j).getName(),j),all.get(j).getCups());
                for (int j = i; j < i+6 ; j++) if (j<all.size()) players.put(new Pair<>(all.get(j).getName(),j),all.get(j).getCups());
            }
        }
        clientHandler.sendResponse("ShowMyRank",new request_response.response.ShowMyRank(players));



        LinkedHashMap<String,Integer> players1 = new LinkedHashMap<>();
        for (int i = 0; i < 10 ; i++)if (i<all.size())players1.put(all.get(i).getName(),all.get(i).getCups());
        clientHandler.sendResponse("ShowTopTen",new request_response.response.ShowTopTen(players1));

    }
}
