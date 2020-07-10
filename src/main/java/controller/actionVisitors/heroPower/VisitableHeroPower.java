package controller.actionVisitors.heroPower;

import controller.BoardController;
import models.Heroes.Hero;

public interface VisitableHeroPower {
    void accept(BoardController boardController);
}
