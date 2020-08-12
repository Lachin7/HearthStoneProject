package server.models.Cards.weapons;

import server.controller.Board.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Weapon;
import server.models.Character;

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
