package server.models.Cards.spells;

import server.controller.Board.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Spell;
import server.models.Cards.Target;
import server.models.Character;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
public class Polymorph extends Spell {
    public Polymorph() {
        super(4, "Polymorph", "Transform a minion into a 1/1 Sheep.", Card.rarity.COMMON, HeroClass.MAGE, Card.type.SPELL, 5);
        this.hasInitialMoveTarget = true;
        this.targets = (new ArrayList<>(Arrays.asList(Target.FRIENDLY_MINION,Target.ENEMY_MINION)));
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitPolymorph(this,target,boardController);
    }
}
