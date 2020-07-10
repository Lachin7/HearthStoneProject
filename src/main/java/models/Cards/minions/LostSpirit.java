package models.Cards.minions;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

public class LostSpirit extends Minion {
    public LostSpirit() {
        super(2, "LostSpirit", "Deathrattle: Give your minions +1 Attack", Card.rarity.COMMON, HeroClass.NEUTRAL,Card.type.MINION,SubType.NULL,5, 1, 1);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitLostSpirit(this,target,boardController);
    }
}
