package server.models.Cards.minions;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Character;

import javax.persistence.Entity;

@Entity
public class FrozenShadoweaver extends Minion {
    public FrozenShadoweaver() {
        super(3, "FrozenShadoweaver", "", Card.rarity.COMMON, HeroClass.NEUTRAL,Card.type.MINION,SubType.NULL,5, 3, 4);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitFrozenShadoweaver(this,target,boardController);
    }
}
