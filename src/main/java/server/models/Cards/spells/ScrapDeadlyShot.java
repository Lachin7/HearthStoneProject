package server.models.Cards.spells;

import server.controller.Board.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Spell;
import server.models.Character;

import javax.persistence.Entity;

@Entity
public class ScrapDeadlyShot extends Spell {
    public ScrapDeadlyShot() {
        super(5, "ScrapDeadlyShot", "deal 1 damage to all enemy minions", Card.rarity.RARE, HeroClass.HUNTER, Card.type.SPELL, 10);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitScrapDeadlyShot(this,target,boardController);
    }
}
