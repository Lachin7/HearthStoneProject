package models.Cards.minions;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

public class RiverCrocolisk extends Minion{
    public RiverCrocolisk() {
        super(2, "RiverCrocolisk", "", Card.rarity.EPIC, Card.HeroClass.WARLOCK,Card.type.MINION, Minion.SubType.BEAST,15, 2, 3);
    }

    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {

    }
//    @Override
//    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
//        cardVisitor.visitRatcatcher(this,target,boardController);
//    }
}
