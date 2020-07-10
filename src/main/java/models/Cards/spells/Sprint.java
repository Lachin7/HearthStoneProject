package models.Cards.spells;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Spell;
import models.Character;

public class Sprint extends Spell {
    public Sprint() {
        super(7, "Sprint", "Draw 4 cards.", Card.rarity.COMMON, HeroClass.NEUTRAL, Card.type.SPELL, 5);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitSprint(this,target,boardController);
    }
}
