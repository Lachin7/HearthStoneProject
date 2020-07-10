package models.Cards.minions;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

public class ConchguardWarlord extends Minion {
    public ConchguardWarlord() {
        super(8, "ConchguardWarlord", "Taunt\n", Card.rarity.COMMON,  HeroClass.HUNTER,Card.type.MINION,SubType.NULL,5, 9, 5);
        this.isTaunt = true;
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitConchguardWarlord(this,target,boardController);
    }
}
