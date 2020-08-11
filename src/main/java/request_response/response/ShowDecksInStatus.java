package request_response.response;

import client.ClientGui;

import java.util.ArrayList;

public class ShowDecksInStatus extends Response{
    private ArrayList<String> decks;

    public ShowDecksInStatus(ArrayList<String> decks) {
        this.decks = decks;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getStatusPanel().showDecks(decks);
    }
}
