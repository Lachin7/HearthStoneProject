package server.models.Cards.minions;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Character;

import javax.persistence.Entity;

@Entity
public class DreadScale extends Minion {
    public DreadScale() {
        super(3, "DreadScale", "At the end of your turn, deal 1 damage to all other minions.", Card.rarity.LEGENDARY,  HeroClass.HUNTER,Card.type.MINION,SubType.BEAST,20, 2, 4);
    }
    @Override
    public synchronized void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitDreadScale(this,target,boardController);
    }
}
