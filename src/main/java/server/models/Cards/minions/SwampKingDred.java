package server.models.Cards.minions;

import server.controller.Board.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Character;

import javax.persistence.Entity;

@Entity
public class SwampKingDred extends Minion {
    public SwampKingDred() {
        super(7, "SwampKingDred", "After your opponent plays a minion, attack it.", Card.rarity.LEGENDARY, HeroClass.HUNTER,Card.type.MINION,SubType.BEAST,20, 9, 9);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitSwampKingDred(this,target,boardController);
    }
}
