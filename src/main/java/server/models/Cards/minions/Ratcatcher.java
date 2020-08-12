package server.models.Cards.minions;

import server.controller.Board.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Cards.Target;
import server.models.Character;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
public class Ratcatcher extends Minion {
    public Ratcatcher() {
        super(3, "Ratcatcher", "Rush\n" +
                "Battlecry: Destroy a friendly minion and gain its Attack and Health.", Card.rarity.EPIC, HeroClass.WARLOCK,Card.type.MINION,SubType.MURLOC,15, 2, 2);
        this.isRush = true;
        this.hasInitialMoveTarget = true;
        this.targets= new ArrayList<>(Arrays.asList(Target.FRIENDLY_MINION));
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitRatcatcher(this,target,boardController);
    }
}
