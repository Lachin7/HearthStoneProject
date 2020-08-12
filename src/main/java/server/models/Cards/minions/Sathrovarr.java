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
public class Sathrovarr extends Minion {
    public Sathrovarr() {
        super(9, "Sathrovarr", "Choose a friendly minion. Add a copy of it to your hand, deck and battlefield.\n", Card.rarity.LEGENDARY, HeroClass.NEUTRAL,Card.type.MINION,SubType.DEMON,20, 5, 5);
        this.targets = (new ArrayList<>(Arrays.asList(Target.FRIENDLY_MINION)));
        hasInitialMoveTarget =true;
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitSathrovarr(this,target,boardController);
    }
}
