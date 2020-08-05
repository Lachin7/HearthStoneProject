package server.controller.actionVisitors.passive;

import server.controller.BoardController;
import models.Player;

public interface VisitablePassive {
    void accept(PassiveVisitor passiveVisitor, Player player , BoardController boardController);
}
