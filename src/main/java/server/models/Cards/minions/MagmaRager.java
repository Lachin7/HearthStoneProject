package server.models.Cards.minions;

import server.controller.Board.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Character;

import javax.persistence.Entity;

@Entity
public class MagmaRager extends Minion {
    public MagmaRager() {
        super(5, "MagmaRager", "", Card.rarity.COMMON, HeroClass.NEUTRAL,Card.type.MINION,SubType.DRAGON,5, 6, 6);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitMagmaRager(this,target,boardController);
    }
}
