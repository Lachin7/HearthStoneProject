package models.Cards.spells;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Spell;
import models.Character;

import javax.persistence.Entity;

@Entity
public class SwarmOfLocusts extends Spell {
    public SwarmOfLocusts() {
        super(6, "SwarmOfLocusts", "Summon seven 1/1 Locusts with Rush.", Card.rarity.RARE, HeroClass.HUNTER, Card.type.SPELL, 10);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitSwarmOfLocusts(this,target,boardController);
    }
}
