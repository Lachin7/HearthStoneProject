package request_response.response;

import client.Client;

import java.util.ArrayList;

public class ShowDecksInStatus extends Response{
    private ArrayList<String> decks;

    public ShowDecksInStatus(ArrayList<String> decks) {
        this.decks = decks;
    }

    @Override
    public void execute(Client client) {
        client.getStatusPanel().showDecks(decks);
    }
}
