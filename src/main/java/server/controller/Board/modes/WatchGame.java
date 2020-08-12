package server.controller.Board.modes;

import request_response.response.GoToPanel;
import request_response.response.Message;
import server.models.Player;
import server.models.board.Side;
import server.ClientHandler;
import server.controller.Board.BoardController;

public class WatchGame extends BoardController {

    public WatchGame( ClientHandler c1, ClientHandler c2) {
        super(c1);
        friendlyPlayer = c1.getMainPlayer();
        enemyPlayer = c2.getMainPlayer();
    }

    @Override
    public void setPlayers() {

    }

    @Override
    public boolean getAllowance(Side side) {
        return false;
    }

    @Override
    public void exitPlay(boolean youExited) {

    }

    @Override
    public boolean getCardBackVisible(Side side) {
        return false;
    }


    @Override
    public void initialDeckToHand(Player player) {

    }

    @Override
    public void endTurn() {

    }

    @Override
    public void checkGameFinished() {
        clientHandler.sendResponse("Message",new Message("game finished "));
        clientHandler.sendResponse("GoToPanel",new GoToPanel("mainMenu"));
    }
}
