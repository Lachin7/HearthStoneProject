package server.controller.actionVisitors.heroPower;

import server.controller.Board.BoardController;
import server.models.Cards.Minion;
import server.models.Character;

public class EnemyPlayesHeroPowerVisitor implements HeroPowerVisitor{
    @Override
    public void visitMageHeroPower(Character target, BoardController boardController) {

    }

    @Override
    public void visitRougeHeroPower(Character target, BoardController boardController) {

    }

    @Override
    public void visitWarlockHeroPower(Character target, BoardController boardController) {

    }

    @Override
    public void visitHunterHeroPower(Character target, BoardController boardController) {
        boardController.changeMinion((Minion) target,-1,0);
    }

    @Override
    public void visitPriestHeroPower(Character target, BoardController boardController) {

    }
}
