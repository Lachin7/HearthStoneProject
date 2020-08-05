package request_response.request;

import server.ClientHandler;
import server.controller.modes.Practice;

import java.util.ArrayList;

public class DeclareFirstCards extends Request {
    private ArrayList<String> cards;

    public DeclareFirstCards(ArrayList<String> cards) {
        this.cards = cards;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.getBoardController().getFirstChoices(cards,clientHandler.getBoardController().getCurrentPlayer());
        clientHandler.getBoardController().initialGameSetUps(clientHandler.getBoardController().getCurrentPlayer());
    }
}
