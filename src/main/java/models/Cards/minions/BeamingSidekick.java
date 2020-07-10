package models.Cards.minions;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Cards.Target;
import models.Character;

import java.util.ArrayList;
import java.util.Arrays;


public class BeamingSidekick extends Minion {
    public BeamingSidekick() {
        super(1, "BeamingSidekick", "Battlecry: Give a friendly minion +2 Health.", Card.rarity.COMMON,  HeroClass.NEUTRAL,Card.type.MINION,SubType.NULL,5, 2, 1);
        this.hasInitialMoveTarget = true;
        this.targets = (new ArrayList<>(Arrays.asList(Target.FRIENDLY_MINION)));
    }

    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitBeamingSidekick(this,target,boardController);
    }
}
