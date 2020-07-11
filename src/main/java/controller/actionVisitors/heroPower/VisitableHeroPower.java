package controller.actionVisitors.heroPower;

import controller.BoardController;
import models.Character;
import models.Heroes.Hero;

public interface VisitableHeroPower {
    void accept(HeroPowerVisitor heroPowerVisitor, Character target, BoardController boardController);
}
