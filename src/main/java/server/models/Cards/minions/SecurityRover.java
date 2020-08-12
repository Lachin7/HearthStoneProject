package server.models.Cards.minions;

import server.controller.Board.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Character;

import javax.persistence.Entity;

@Entity
public class SecurityRover extends Minion {
    public SecurityRover() {
        super(9, "SecurityRover", "Whenever this minion takes damage, summon a 2/3 Mech with Taunt." , Card.rarity.RARE, HeroClass.NEUTRAL,Card.type.MINION,SubType.MECH,10, 6, 2);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitSecurityRover(this,target,boardController);
    }
}
