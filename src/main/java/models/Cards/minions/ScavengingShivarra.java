package models.Cards.minions;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

public class ScavengingShivarra extends Minion {
    public ScavengingShivarra() {
        super(7, "ScavengingShivarra", "Battlecry: Deal 1 damage to all enemy minions" , Card.rarity.COMMON, HeroClass.NEUTRAL,Card.type.MINION,SubType.DEMON,5, 4, 6);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitScavengingShivarra(this,target,boardController);
    }
}
