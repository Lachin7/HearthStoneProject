package models.Cards.minions;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

public class CurioCollector extends Minion {
    public CurioCollector() {
        super(5, "CurioCollector", "Whenever you draw a card, gain +1/+1."
                , Card.rarity.RARE,  HeroClass.HUNTER, Card.type.MINION,SubType.NULL,10, 4, 4);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitCurioCollector(this,target,boardController);
    }
}