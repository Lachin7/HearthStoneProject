package models.Cards.spells;

import models.Cards.Card;
import models.Cards.Spell;

public class StrengthInNumbers extends Spell {
    public StrengthInNumbers(int manaCost, String name, String description, Card.rarity rarity, HeroClass heroClass, Card.type type, int price, String Quest, String Reward) {
        super(manaCost, name, description, rarity, heroClass, type, price, Quest, Reward);
    }
}
