package models.Cards.minions;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Minion;
import models.Cards.Target;
import models.Character;

import java.util.ArrayList;
import java.util.Arrays;

public class RocketAugmerchant extends Minion {
    public RocketAugmerchant() {
        super(1, "RocketAugmerchant", "Rush\n" + "Battlecry: Destroy a friendly minion and gain its Attack and Health.", Card.rarity.COMMON, HeroClass.NEUTRAL,Card.type.MINION,SubType.NULL,5, 1, 2);
        this.targets = new ArrayList<>(Arrays.asList(Target.FRIENDLY_MINION,Target.ENEMY_MINION));
        this.setHasInitialMoveTarget(true);

    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitRocketAugmerchant(this,target,boardController);
    }
}
