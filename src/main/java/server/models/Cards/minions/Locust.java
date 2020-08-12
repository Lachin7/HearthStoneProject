package server.models.Cards.minions;

import server.controller.Board.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Character;

import javax.persistence.Entity;

@Entity
public class Locust extends Minion {
    public Locust() {
        super(1, "Locust", "Rush", Card.rarity.COMMON, HeroClass.NEUTRAL,Card.type.MINION,SubType.BEAST,5, 1, 1);
        this.canAttack = true;
        this.isRush = false;
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitLocust(this,target,boardController);
    }
}
