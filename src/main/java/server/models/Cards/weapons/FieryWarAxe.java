package server.models.Cards.weapons;

import server.controller.Board.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Weapon;
import server.models.Character;

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
