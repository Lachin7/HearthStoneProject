package request_response.request;

import request_response.response.GoToPanel;
import request_response.response.Message;
import server.ClientHandler;
import server.controller.Board.modes.*;

public class DeclareGameMode extends Request {
    private String gameMode;

    public DeclareGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        /** check necessary items before starting */
        if (!gameMode.equalsIgnoreCase("Deck Reader") && !gameMode.equalsIgnoreCase("Watch Games")) {
            if (clientHandler.getMainPlayer().getDeck() == null) {
                clientHandler.sendResponse("Message", new Message("you don't have a current deck\n let's go to status and choose you deck"));
                clientHandler.sendResponse("GoToPanel", new GoToPanel("status"));
            }
            if (clientHandler.getMainPlayer().getDeck().getHero() == null) {
                clientHandler.sendResponse("Message", new Message("you don't have a choosed hero for your deck\n let's go to collections and choose a hero! "));
                clientHandler.sendResponse("GoToPanel", new GoToPanel("collection"));
            }
            if (clientHandler.getMainPlayer().getDeck().getCards().size() < 10) {
                clientHandler.sendResponse("Message", new Message("you don't have a enough number of cards in your deck\n let's go to collections and have at least 10 cards in your deck \n also you can change your deck in status"));
                clientHandler.sendResponse("GoToPanel", new GoToPanel("collection"));
            } else {
                /** if player was ok with those items , then initials it and ask for new game or if not choose the passive */
                if (gameMode.equalsIgnoreCase("AI")) clientHandler.setBoardController(new AI(clientHandler));
                else if (gameMode.equalsIgnoreCase("Practice"))
                    clientHandler.setBoardController(new Practice(clientHandler));
                else if (gameMode.equalsIgnoreCase("Online"))
                    clientHandler.setBoardController(new Online(clientHandler));
                else if (gameMode.equalsIgnoreCase("One Shot"))
                    clientHandler.setBoardController(new OneShot(clientHandler));
                else if (gameMode.equalsIgnoreCase("Golden Time"))
                    clientHandler.setBoardController(new GoldenTime(clientHandler));
                else if (gameMode.equalsIgnoreCase("Tavern Brawl"))
                    clientHandler.setBoardController(new TavernBrawl(clientHandler));

                if (gameMode.equalsIgnoreCase("AI") || gameMode.equalsIgnoreCase("Practice"))
                    new ChooseGameSetUps(clientHandler.getBoardController()).execute(clientHandler);
                else clientHandler.getServer().requestOnlineGame(clientHandler);
            }
        } else if (gameMode.equalsIgnoreCase("Deck Reader")) {
            clientHandler.setBoardController(new DeckReader(clientHandler));
            clientHandler.getServer().requestOnlineGame(clientHandler);
        }

    }
}
