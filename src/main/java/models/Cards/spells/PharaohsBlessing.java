package models.Cards.spells;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Spell;
import models.Cards.Target;
import models.Character;

import java.util.ArrayList;
import java.util.Arrays;

public class PharaohsBlessing extends Spell {
    public PharaohsBlessing() {
        super(6, "PharaohsBlessing", "Give a minion +4/+4, Divine Shield, and Taunt.", Card.rarity.RARE, HeroClass.NEUTRAL, Card.type.SPELL, 10);
        this.hasInitialMoveTarget = true;
        this.targets = (new ArrayList<>(Arrays.asList(Target.FRIENDLY_MINION,Target.ENEMY_MINION)));
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitPharaohsBlessing(this,target,boardController);
    }
}
