package client.actionController;

import client.ClientGui;
import request_response.request.*;

public class StatusActionController extends ActionController{
    public StatusActionController(ClientGui clientGui) {
        super(clientGui);
    }

    public void setDeckAsCurrent(String selectedDeck) {
        clientGui.sendRequest("SetDeckAsCurrent",new SetDeckAsCurrent(selectedDeck));
    }

    public void showDecksInStatus() {
        clientGui.sendRequest("ShowDecksInStatus",new ShowDecksInStatus());
    }

    public void showCardsInStatus(String deck) {
        clientGui.sendRequest("ShowCardsInStatus",new ShowCardsInStatus(deck));
    }

    public void drawDeckInfo(String selectedDeck) {
        clientGui.sendRequest("DrawDeckInfo",new DrawDeckInfo(selectedDeck));
    }

    public void showRanks() {
        clientGui.sendRequest("ShowRanks",new ShowRanks());
    }

}
