package server.models.Cards.minions;

import server.controller.Board.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Character;

import javax.persistence.Entity;

@Entity

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
