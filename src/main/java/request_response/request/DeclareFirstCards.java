package request_response.request;

import request_response.response.GoToPanel;
import server.ClientHandler;
import server.controller.modes.Online;

import java.util.ArrayList;

public class DeclareFirstCards extends Request {
    private ArrayList<Long> cards;

    public DeclareFirstCards(ArrayList<Long> cards) {
        this.cards = cards;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        if (clientHandler.getBoardController() instanceof Online) clientHandler.sendResponse("GoToPanel",new GoToPanel("wait"));
        clientHandler.getBoardController().getFirstChoices(cards,clientHandler.getBoardController().getCurrentPlayer());
        clientHandler.getBoardController().initialGameSetUps(clientHandler.getBoardController().getCurrentPlayer());
    }
}
