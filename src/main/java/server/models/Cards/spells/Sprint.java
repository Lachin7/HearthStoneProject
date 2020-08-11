package server.models.Cards.spells;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Spell;
import server.models.Character;

import javax.persistence.Entity;

@Entity
public class Sprint extends Spell {
    public Sprint() {
        super(7, "Sprint", "Draw 4 cards.", Card.rarity.COMMON, HeroClass.NEUTRAL, Card.type.SPELL, 5);

    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitSprint(this,target,boardController);
    }
}
