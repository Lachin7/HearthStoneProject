package models.Cards.spells;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Spell;
import models.Cards.Target;
import models.Character;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
public class PsycheSplit extends Spell {
    public PsycheSplit() {
        super(5, "PsycheSplit", "Give a minion +1/+2. Summon a copy of it.", Card.rarity.RARE, HeroClass.PRIEST, Card.type.SPELL, 10);
        this.targets = new ArrayList<>(Arrays.asList(Target.FRIENDLY_MINION,Target.ENEMY_MINION));
        this.hasInitialMoveTarget = true;
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitPsycheSplit(this,target,boardController);
    }
}
