package request_response.request;

import models.Cards.Card;
import request_response.response.ChoosePassive;
import request_response.response.StartOnlineGame;
import server.ClientHandler;
import server.controller.BoardController;
import server.controller.modes.AI;
import server.controller.modes.DeckReader;
import server.controller.modes.Practice;

import java.util.HashMap;

public class ChooseGameSetUps extends Request {
    private BoardController boardController;

    public ChooseGameSetUps(BoardController boardController) {
        this.boardController = boardController;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        /** choose passive*/
        clientHandler.sendResponse("ChoosePassive",new ChoosePassive());
        /** start asking for 3 cards */
        HashMap<Long,String> result = new HashMap<>();
        for (Card card : clientHandler.getBoardController().getCurrentPlayer().getHandsCards())result.put(card.getId(),card.getName());
        if (boardController instanceof Practice){
            clientHandler.sendResponse("ChooseFirstCards",new request_response.response.ChooseFirstCards(result,true));
            clientHandler.sendResponse("ChoosePassive",new ChoosePassive());
            clientHandler.getBoardController().addSwitch();
            result = new HashMap<>();
            for (Card card : clientHandler.getBoardController().getCurrentPlayer().getHandsCards())result.put(card.getId(),card.getName());
            clientHandler.getBoardController().addSwitch();
        }
        clientHandler.sendResponse("ChooseFirstCards",new request_response.response.ChooseFirstCards(result,false));

        if (!( boardController instanceof DeckReader))clientHandler.sendResponse("StartOnlineGame",new StartOnlineGame());
    }
}
