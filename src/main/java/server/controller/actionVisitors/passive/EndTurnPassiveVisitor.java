package server.controller.actionVisitors.passive;

import server.controller.Board.BoardController;
import server.models.Cards.Minion;
import server.models.Player;

import java.util.ArrayList;
import java.util.Random;

public class EndTurnPassiveVisitor implements PassiveVisitor{
    @Override
    public void visitTwiceDraw(Player player, BoardController boardController) {

    }

    @Override
    public void visitOffCards(Player player, BoardController boardController) {

    }

    @Override
    public void visitWarriors(Player player, BoardController boardController) {

    }

    @Override
    public void visitNurse(Player player, BoardController boardController) {
        ArrayList<Minion> minions = new ArrayList<>();
        for(Minion minion : boardController.getCurrentPlayer().getFieldCardsInGame()) if(minion.getMaxHp()< minion.getHP()) minions.add(minion);
        if(minions.size()!=0){
            int rand = new Random().nextInt(minions.size());
            boardController.restoreHp(minions.get(rand));
        }
    }

    @Override
    public void visitManaJump(Player player, BoardController boardController) {

    }

    @Override
    public void visitPotionOfVitality(Player player, BoardController boardController){

    }
}
