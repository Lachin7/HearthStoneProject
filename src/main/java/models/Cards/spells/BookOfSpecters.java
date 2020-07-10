package models.Cards.spells;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Spell;
import models.Character;

public class BookOfSpecters extends Spell {
    public BookOfSpecters() {
        super(2, "BookOfSpecters", "Draw 3 cards. Discard any spells drawn.", Card.rarity.EPIC, HeroClass.MAGE, Card.type.SPELL, 5);
    }

    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitBookOfSpecters(this,target,boardController);
    }
}
