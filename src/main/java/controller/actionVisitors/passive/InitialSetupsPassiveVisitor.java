package controller.actionVisitors.passive;

import controller.BoardController;
import models.Cards.Card;
import models.Player;

public class InitialSetupsPassiveVisitor implements PassiveVisitor {
    @Override
    public void visitTwiceDraw(Player player, BoardController boardController) {
        boardController.setTurnCardDrawNum(2);
    }

    @Override
    public void visitOffCards(Player player, BoardController boardController) {
       for(Card card : player.getFieldCardsInGame()) if(card.getManaCost()!=0)card.setManaCost(card.getManaCost()-1);
    }

    @Override
    public void visitWarriors(Player player, BoardController boardController) {

    }

    @Override
    public void visitNurse(Player player, BoardController boardController) {

    }

    @Override
    public void visitManaJump(Player player, BoardController boardController) {
        player.setInitialMana(1);
    }

    @Override
    public void visitPotionOfVitality(Player player, BoardController boardController){
        player.getPlayersChoosedHero().setHP(player.getPlayersChoosedHero().getHeroPowerCost()*2);
    }
}
