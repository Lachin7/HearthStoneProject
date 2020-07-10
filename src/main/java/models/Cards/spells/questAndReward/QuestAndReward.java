package models.Cards.spells.questAndReward;

import com.google.gson.annotations.Expose;
import models.Cards.Card;
import models.Cards.Spell;

public  class QuestAndReward extends Spell {
    @Expose protected String quest,reward;

    public QuestAndReward(int manaCost, String name, String description, Card.rarity rarity, HeroClass heroClass, Card.type type, int price, String quest, String reward) {
        super(manaCost, name, description, rarity, heroClass, type, price);
        this.quest = quest;
        this.reward = reward;
    }
}
