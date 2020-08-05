package models.Cards.minions;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

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
