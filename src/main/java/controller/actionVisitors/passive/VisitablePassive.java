package controller.actionVisitors.passive;

import controller.BoardController;
import models.Player;

public interface VisitablePassive {
    void accept(PassiveVisitor passiveVisitor, Player player , BoardController boardController);
}
