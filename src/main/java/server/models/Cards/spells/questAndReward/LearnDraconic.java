package server.models.Cards.spells.questAndReward;

import server.controller.BoardController;
import server.controller.actionVisitors.card.CardVisitor;
import server.models.Cards.Card;
import server.models.Character;

import javax.persistence.Entity;

@Entity
public class LearnDraconic extends QuestAndReward {
    public LearnDraconic() {
        super(1, "LearnDraconic", "Sidequest: Spend 8 Mana on spells. Reward: Summon a 6/6 Dragon.", Card.rarity.COMMON, HeroClass.MAGE, Card.type.SPELL, 5, "Sidequest: Spend 8 Mana on spells.", "Reward: Summon a 6/6 Dragon.");
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitLearnDraconic(this,target,boardController);
    }
}
