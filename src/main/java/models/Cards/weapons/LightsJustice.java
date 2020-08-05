package models.Cards.weapons;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Weapon;
import models.Character;

import javax.persistence.Entity;

@Entity
public class LightsJustice extends Weapon {
    public LightsJustice() {
        super(1, "LightsJustice", "", Card.rarity.COMMON, HeroClass.NEUTRAL, Card.type.WEAPON, 5, 4, 1);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitLightsJustice(this,target,boardController);
    }
}
