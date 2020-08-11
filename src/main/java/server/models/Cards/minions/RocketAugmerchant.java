package server.models.Cards.minions;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Cards.Target;
import server.models.Character;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
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
