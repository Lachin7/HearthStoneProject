package models.Cards.minions;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

public class Starscryer extends Minion {
    public Starscryer() {
        super(9, "Starscryer", "Battlecry: Draw a spell.\n"  , Card.rarity.COMMON, HeroClass.MAGE,Card.type.MINION,SubType.NULL,5, 1, 3);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitStarscryer(this,target,boardController);
    }
}
