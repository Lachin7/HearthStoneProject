package server.models.Cards.spells.questAndReward;

import lombok.Getter;
import lombok.Setter;
import server.models.Cards.Card;
import server.models.Cards.Spell;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public abstract class QuestAndReward extends Spell {
    @Column
    @Getter
    @Setter
     protected String quest,reward;
     public QuestAndReward(){
         super();
     }

    public QuestAndReward(int manaCost, String name, String description, Card.rarity rarity, HeroClass heroClass, Card.type type, int price, String quest, String reward) {
        super(manaCost, name, description, rarity, heroClass, type, price);
        this.quest = quest;
        this.reward = reward;
    }
}
