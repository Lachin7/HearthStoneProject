package server.controller.actionVisitors.heroPower;

import server.controller.BoardController;
import models.Character;

public interface HeroPowerVisitor {
    void visitMageHeroPower(Character target , BoardController boardController);
    void visitRougeHeroPower(Character target ,BoardController boardController);
    void visitWarlockHeroPower(Character target ,BoardController boardController);
    void visitHunterHeroPower(Character target ,BoardController boardController);
    void visitPriestHeroPower(Character target ,BoardController boardController);
}
