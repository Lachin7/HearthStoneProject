package models.Cards.minions;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

import javax.persistence.Entity;

@Entity
public class Starscryer extends Minion {
    public Starscryer() {
        super(9, "Starscryer", "Battlecry: Draw a spell.\n"  , Card.rarity.COMMON, HeroClass.MAGE,Card.type.MINION,SubType.NULL,5, 1, 3);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitStarscryer(this,target,boardController);
    }
}
