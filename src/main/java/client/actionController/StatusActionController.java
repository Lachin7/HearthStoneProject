package client.actionController;

import client.Client;
import request_response.request.*;

public class StatusActionController extends ActionController{
    public StatusActionController(Client client) {
        super(client);
    }

    public void setDeckAsCurrent(String selectedDeck) {
        client.sendRequest("SetDeckAsCurrent",new SetDeckAsCurrent(selectedDeck));
    }

    public void showDecksInStatus() {
        client.sendRequest("ShowDecksInStatus",new ShowDecksInStatus());
    }

    public void showCardsInStatus(String deck) {
        client.sendRequest("ShowCardsInStatus",new ShowCardsInStatus(deck));
    }

    public void showTopTen() {
        client.sendRequest("ShowTopTen",new ShowTopTen());
    }

    public void showMyRank() {
        client.sendRequest("ShowMyRank",new ShowMyRank());
    }

    public void drawDeckInfo(String selectedDeck) {
        client.sendRequest("DrawDeckInfo",new DrawDeckInfo(selectedDeck));
    }
}
