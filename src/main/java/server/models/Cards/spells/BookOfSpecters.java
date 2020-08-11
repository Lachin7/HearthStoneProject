package server.models.Cards.spells;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Spell;
import server.models.Character;

import javax.persistence.Entity;

@Entity
public class BookOfSpecters extends Spell {
    public BookOfSpecters() {
        super(2, "BookOfSpecters", "Draw 3 cards. Discard any spells drawn.", Card.rarity.EPIC, HeroClass.MAGE, Card.type.SPELL, 5);
    }

    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitBookOfSpecters(this,target,boardController);
    }
}
