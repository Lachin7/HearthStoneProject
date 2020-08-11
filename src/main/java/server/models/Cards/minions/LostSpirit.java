package server.models.Cards.minions;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Character;

import javax.persistence.Entity;

@Entity
public class LostSpirit extends Minion {
    public LostSpirit() {
        super(2, "LostSpirit", "Deathrattle: Give your minions +1 Attack", Card.rarity.COMMON, HeroClass.NEUTRAL,Card.type.MINION,SubType.NULL,5, 1, 1);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitLostSpirit(this,target,boardController);
    }
}
