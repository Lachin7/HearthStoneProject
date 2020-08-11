package server.models.Cards.minions;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Character;

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
