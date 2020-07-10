package models.Cards.minions;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

public class Dragonrider extends Minion {
    public Dragonrider() {
        super(3, "Dragonrider", ""
                , Card.rarity.COMMON,  HeroClass.NEUTRAL,Card.type.MINION,SubType.NULL,5, 3, 4);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitDragonrider(this,target,boardController);
    }
}
