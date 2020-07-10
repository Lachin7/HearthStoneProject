package models.Cards.spells.questAndReward;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Character;

public class LearnDraconic extends QuestAndReward {
    public LearnDraconic() {
        super(1, "LearnDraconic", "Sidequest: Spend 8 Mana on spells. Reward: Summon a 6/6 Dragon.", Card.rarity.COMMON, HeroClass.MAGE, Card.type.SPELL, 5, "Sidequest: Spend 8 Mana on spells.", "Reward: Summon a 6/6 Dragon.");
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitLearnDraconic(this,target,boardController);
    }
}
