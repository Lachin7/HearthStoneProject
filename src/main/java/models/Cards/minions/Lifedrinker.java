package models.Cards.minions;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

import javax.persistence.Entity;

@Entity
public class Lifedrinker extends Minion {
    public Lifedrinker() {
        super(4, "Lifedrinker", "Battlecry: Deal 3 damage to the enemy hero. Restore 3 Health to your hero.", Card.rarity.RARE, HeroClass.NEUTRAL, Card.type.MINION,SubType.BEAST,10, 3, 3);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitLifedrinker(this,target,boardController);
    }
}
