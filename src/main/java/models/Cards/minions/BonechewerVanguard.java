package models.Cards.minions;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

import javax.persistence.Entity;

@Entity
public class BonechewerVanguard extends Minion {
    public BonechewerVanguard() {
        super(7, "BonechewerVanguard", "Taunt\n" + "Whenever this minion takes damage, gain +2 Attack.", Card.rarity.COMMON,  HeroClass.NEUTRAL,Card.type.MINION,SubType.NULL,5, 10, 4);
        this.isTaunt =true;
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitBonechewerVanguard(this,target,boardController);
    }
}
