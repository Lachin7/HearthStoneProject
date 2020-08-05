package models.Cards.weapons;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Weapon;
import models.Character;

import javax.persistence.Entity;

@Entity
public class SerratedTooth extends Weapon {
    public SerratedTooth() {
        super(1, "SerratedTooth", "", Card.rarity.COMMON, HeroClass.NEUTRAL, Card.type.WEAPON, 5, 3, 1);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitSerratedTooth(this,target,boardController);
    }
}
