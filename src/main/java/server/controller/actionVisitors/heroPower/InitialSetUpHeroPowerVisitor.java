package server.controller.actionVisitors.heroPower;

import server.controller.BoardController;
import server.models.Cards.Minion;
import server.models.Character;

import java.util.Random;

public class InitialSetUpHeroPowerVisitor implements HeroPowerVisitor {

    @Override
    public void visitMageHeroPower(Character target, BoardController boardController) {
        if(target instanceof Minion)boardController.changeMinion((Minion) target,-1,0);
        boardController.applyManaCost(2);
    }

    @Override
    public void visitRougeHeroPower(Character target, BoardController boardController) {
        boardController.applyManaCost(3);
        String cardName = boardController.getOpponentPlayer().getFieldCardsInGame().get(0).getName();
        boardController.getCurrentPlayer().getHandsCards().add(boardController.getCardController().createCard(cardName));
        if(boardController.getCurrentPlayer().getChoosedHero().getWeapon()!=null) boardController.draw();

    }

    @Override
    public void visitWarlockHeroPower(Character target, BoardController boardController) {
        boardController.changeHero(boardController.getCurrentPlayer().getChoosedHero(),-2,0);
        int rand = new Random().nextInt(2);
        if(rand==0)boardController.changeMinion(boardController.getCurrentPlayer().getFieldCardsInGame().get(0),1,1);
        else boardController.draw();
    }

    @Override
    public void visitHunterHeroPower(Character target, BoardController boardController) {


    }

    @Override
    public void visitPriestHeroPower(Character target, BoardController boardController) {
        boardController.applyManaCost(2);
        boardController.changeMinion((Minion) target,4,0);
    }
}
