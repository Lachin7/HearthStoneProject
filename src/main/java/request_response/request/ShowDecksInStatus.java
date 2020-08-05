package request_response.request;

import server.ClientHandler;

import java.util.ArrayList;

public class ShowDecksInStatus extends Request {
    @Override
    public void execute(ClientHandler clientHandler) {
        ArrayList<String> result = new ArrayList<>();
        clientHandler.getMainPlayer().getDecks().forEach(deck -> result.add(deck.getName()));
        clientHandler.sendResponse("ShowDecksInStatus",new request_response.response.ShowDecksInStatus(result));
    }
}
