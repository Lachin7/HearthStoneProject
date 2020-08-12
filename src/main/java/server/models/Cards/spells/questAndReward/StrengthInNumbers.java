package server.models.Cards.spells.questAndReward;

import server.controller.Board.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Character;

import javax.persistence.Entity;

@Entity
public class StrengthInNumbers extends QuestAndReward {
    public StrengthInNumbers() {
        super(1, "StrengthInNumbers", "Sidequest: Spend 10 Mana on minions.Reward: Summon a minion from your deck.", Card.rarity.COMMON, HeroClass.NEUTRAL, Card.type.SPELL, 5, "Sidequest: Spend 10 Mana on minions.", "Reward: Summon a minion from your deck.");
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitStrengthInNumbers(this,target,boardController);
    }
}
