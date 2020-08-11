package server.controller.actionVisitors.heroPower;

import server.controller.BoardController;
import server.models.Character;

public interface VisitableHeroPower {
    void accept(HeroPowerVisitor heroPowerVisitor, Character target, BoardController boardController);
}
