package models.Cards.minions;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

public class SecurityRover extends Minion {
    public SecurityRover() {
        super(9, "SecurityRover", "Whenever this minion takes damage, summon a 2/3 Mech with Taunt." , Card.rarity.RARE, HeroClass.NEUTRAL,Card.type.MINION,SubType.MECH,10, 6, 2);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitSecurityRover(this,target,boardController);
    }
}
