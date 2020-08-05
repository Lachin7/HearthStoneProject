package models.Cards.minions;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Character;

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
