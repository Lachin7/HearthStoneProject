package models.Cards.weapons;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Weapon;
import models.Character;

import javax.persistence.Entity;

@Entity
public class FieryWarAxe extends Weapon {
    public FieryWarAxe() {
        super(3, "FieryWarAxe", "", Card.rarity.COMMON, HeroClass.NEUTRAL, Card.type.WEAPON, 5, 2, 3);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitFieryWarAxe(this,target,boardController);
    }

}
