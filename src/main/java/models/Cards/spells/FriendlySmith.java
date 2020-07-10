package models.Cards.spells;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Spell;
import models.Character;

public class FriendlySmith extends Spell {

    public FriendlySmith() {
        super(1, "FriendlySmith", "Discover a weapon from any class. Add it to your Adventure Deck with +2/+2.", Card.rarity.COMMON, HeroClass.ROGUE, Card.type.SPELL, 5);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitFriendlySmith(this,target,boardController);
    }

}
