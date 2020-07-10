package models.Cards.minions;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Cards.Target;
import models.Character;

import java.util.ArrayList;
import java.util.Arrays;

public class Ratcatcher extends Minion {
    public Ratcatcher() {
        super(3, "Ratcatcher", "Rush\n" +
                "Battlecry: Destroy a friendly minion and gain its Attack and Health.", Card.rarity.EPIC, HeroClass.WARLOCK,Card.type.MINION,SubType.MURLOC,15, 2, 2);
        this.isRush = true;
        this.targets= new ArrayList<>(Arrays.asList(Target.FRIENDLY_MINION));
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitRatcatcher(this,target,boardController);
    }
}
