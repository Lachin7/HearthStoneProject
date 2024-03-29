package server.models.Cards.minions;

import server.controller.Board.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Character;

import javax.persistence.Entity;

@Entity
public class FungalBruiser extends Minion {
    public FungalBruiser() {
        super(10, "FungalBruiser", "Rush", Card.rarity.COMMON, HeroClass.NEUTRAL,Card.type.MINION,SubType.NULL,5, 9, 9);
        this.canAttack = true;
        this.isRush = true;
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitFungalBruiser(this,target,boardController);
    }
}
