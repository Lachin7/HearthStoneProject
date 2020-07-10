package models.Cards.minions;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

public class SwampKingDred extends Minion {
    public SwampKingDred() {
        super(7, "SwampKingDred", "After your opponent plays a minion, attack it.", Card.rarity.LEGENDARY, HeroClass.HUNTER,Card.type.MINION,SubType.BEAST,20, 9, 9);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitSwampKingDred(this,target,boardController);
    }
}
