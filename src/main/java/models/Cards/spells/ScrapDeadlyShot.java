package models.Cards.spells;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Spell;
import models.Character;

public class ScrapDeadlyShot extends Spell {
    public ScrapDeadlyShot() {
        super(5, "ScrapDeadlyShot", "deal 1 damage to all enemy minions", Card.rarity.RARE, HeroClass.HUNTER, Card.type.SPELL, 10);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitScrapDeadlyShot(this,target,boardController);
    }
}
