package models.Cards.minions;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

public class DreadScale extends Minion {
    public DreadScale() {
        super(3, "DreadScale", "At the end of your turn, deal 1 damage to all other minions.", Card.rarity.LEGENDARY,  HeroClass.HUNTER,Card.type.MINION,SubType.BEAST,20, 2, 4);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitDreadScale(this,target,boardController);
    }
}