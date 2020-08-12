package server.models.Cards.spells;

import server.controller.Board.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Spell;
import server.models.Character;

import javax.persistence.Entity;

@Entity
public class FriendlySmith extends Spell {

    public FriendlySmith() {
        super(1, "FriendlySmith", "Discover a weapon from any class. Add it to your Adventure Deck with +2/+2.", Card.rarity.COMMON, HeroClass.ROGUE, Card.type.SPELL, 5);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitFriendlySmith(this,target,boardController);
    }

}
