package models.Cards.minions;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

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
