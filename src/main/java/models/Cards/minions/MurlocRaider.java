package models.Cards.minions;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

import javax.persistence.Entity;

@Entity
public class MurlocRaider extends Minion {
    public MurlocRaider() {
        super(1, "MurlocRaider", "", Card.rarity.COMMON, HeroClass.NEUTRAL,Card.type.MINION,SubType.MURLOC,5, 1, 2);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitMurlocRaider(this,target,boardController);
    }
}
