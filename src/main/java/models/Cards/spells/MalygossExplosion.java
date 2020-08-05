package models.Cards.spells;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Spell;
import models.Character;

import javax.persistence.Entity;

@Entity
public class MalygossExplosion extends Spell {
    public MalygossExplosion() {
        super(1, "MalygossExplosion", "Discover a weapon from any class. Add it to your Adventure Deck with +2/+2.", Card.rarity.COMMON, HeroClass.ROGUE, Card.type.SPELL, 5);
    }

    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {

    }
//    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
//        cardVisitor.visitMalygossExplosion(this,target,boardController);
//    }
}
