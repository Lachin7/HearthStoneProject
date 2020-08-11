package server.models.Cards.spells;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Spell;
import server.models.Cards.Target;
import server.models.Character;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
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
