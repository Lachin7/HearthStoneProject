package models.Cards.minions;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

import javax.persistence.Entity;

@Entity
public class Sheep extends Minion {
    public Sheep() {
        super(1, "Sheep", ""  , Card.rarity.COMMON, HeroClass.NEUTRAL,Card.type.MINION,SubType.BEAST,5, 1, 1);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitSheep(this,target,boardController);
    }
}
