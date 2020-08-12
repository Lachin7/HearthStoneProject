package server.models.Cards.minions;

import server.controller.Board.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Character;

import javax.persistence.Entity;

@Entity
public class MurlocRaider extends Minion {
    public MurlocRaider() {
        super(1, "MurlocRaider", "", Card.rarity.COMMON, HeroClass.NEUTRAL, Card.type.MINION, SubType.MURLOC, 5, 1, 2);
    }

    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitMurlocRaider(this, target, boardController);
    }
}
