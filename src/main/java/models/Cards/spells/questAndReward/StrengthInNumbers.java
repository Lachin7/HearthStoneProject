package models.Cards.spells.questAndReward;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Spell;
import models.Character;

public class StrengthInNumbers extends QuestAndReward {
    public StrengthInNumbers() {
        super(1, "StrengthInNumbers", "Sidequest: Spend 10 Mana on minions.Reward: Summon a minion from your deck.", Card.rarity.COMMON, HeroClass.NEUTRAL, Card.type.SPELL, 5, "Sidequest: Spend 10 Mana on minions.", "Reward: Summon a minion from your deck.");
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitStrengthInNumbers(this,target,boardController);
    }
}
