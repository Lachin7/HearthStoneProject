package models.Cards.minions;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

public class HighPriestAmet extends Minion {
    public HighPriestAmet() {
        super(4, "HighPriestAmet", "Whenever you summon a minion, set its Health equal to this minion's.", Card.rarity.LEGENDARY, HeroClass.PRIEST,Card.type.MINION,SubType.NULL,20, 7, 2);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitHighPriestAmet(this,target,boardController);
    }
}
