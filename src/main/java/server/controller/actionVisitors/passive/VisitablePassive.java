package server.controller.actionVisitors.passive;

import server.controller.Board.BoardController;
import server.models.Player;

public interface VisitablePassive {
    void accept(PassiveVisitor passiveVisitor, Player player , BoardController boardController);
}
