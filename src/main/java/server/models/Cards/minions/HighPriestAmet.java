package server.models.Cards.minions;

import server.controller.Board.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Character;

import javax.persistence.Entity;

@Entity
public class HighPriestAmet extends Minion {
    public HighPriestAmet() {
        super(4, "HighPriestAmet", "Whenever you summon a minion, set its Health equal to this minion's.", Card.rarity.LEGENDARY, HeroClass.PRIEST,Card.type.MINION,SubType.NULL,20, 7, 2);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitHighPriestAmet(this,target,boardController);
    }
}
