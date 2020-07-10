package controller.actionVisitors.heroPower;

import controller.BoardController;
import models.Character;

public class InitialSetUpVisitor implements HeroPowerVisitor {

    @Override
    public void visitMageHeroPower(Character target, BoardController boardController) {
        boardController.changeCharacter(target,-1,0);
        boardController.applyManaCost(2);
    }

    @Override
    public void visitRougeHeroPower(Character target, BoardController boardController) {

        boardController.applyManaCost(3);
    }

    @Override
    public void visitWarlockHeroPower(Character target, BoardController boardController) {

    }

    @Override
    public void visitHunterHeroPower(Character target, BoardController boardController) {

    }

    @Override
    public void visitPriestHeroPower(Character target, BoardController boardController) {
        boardController.applyManaCost(2);
    }
}
