package models.Cards.minions;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

import javax.persistence.Entity;

@Entity
public class TombWarden extends Minion {
    public TombWarden() {
        super(8, "TombWarden", "Taunt\n" +
                "Battlecry: Summon a copy of this minion.", Card.rarity.RARE, HeroClass.NEUTRAL,Card.type.MINION,SubType.MECH,10, 6, 3);
        this.isTaunt = true;
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitTombWarden(this,target,boardController);
    }
}
