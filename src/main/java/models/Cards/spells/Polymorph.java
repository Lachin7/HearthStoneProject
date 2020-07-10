package models.Cards.spells;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Spell;
import models.Cards.Target;
import models.Character;

import java.util.ArrayList;
import java.util.Arrays;

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
