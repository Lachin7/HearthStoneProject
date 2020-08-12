package server.controller.Board;

import server.models.Player;
import server.models.board.Side;

public interface Board {
    void setPlayers();
    boolean getAllowance(Side side);
    void exitPlay(boolean youExited);
    boolean getCardBackVisible(Side side);
    void initialDeckToHand(Player player);
    void endTurn();
    void checkGameFinished();
}
