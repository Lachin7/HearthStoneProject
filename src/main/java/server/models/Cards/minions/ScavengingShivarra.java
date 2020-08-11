package server.models.Cards.minions;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Character;

import javax.persistence.Entity;

@Entity
public class ScavengingShivarra extends Minion {
    public ScavengingShivarra() {
        super(7, "ScavengingShivarra", "Battlecry: Deal 1 damage to all enemy minions" , Card.rarity.COMMON, HeroClass.NEUTRAL,Card.type.MINION,SubType.DEMON,5, 4, 6);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitScavengingShivarra(this,target,boardController);
    }
}
