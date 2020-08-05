package models.Cards.minions;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

import javax.persistence.Entity;

@Entity
public class GoblinBomb extends Minion {
    public GoblinBomb() {
        super(1, "GoblinBomb", "Deathrattle: Deal 2 damage to the enemy hero.", Card.rarity.COMMON, HeroClass.NEUTRAL,Card.type.MINION,SubType.MECH,5, 2, 0);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitGoblinBomb(this,target,boardController);
    }
}
