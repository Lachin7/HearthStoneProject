package server.controller.actionVisitors.heroPower;

import server.controller.BoardController;
import models.Character;

public interface VisitableHeroPower {
    void accept(HeroPowerVisitor heroPowerVisitor, Character target, BoardController boardController);
}
