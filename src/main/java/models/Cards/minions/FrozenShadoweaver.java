package models.Cards.minions;

import models.Cards.Card;
import models.Cards.Minion;

public class FrozenShadoweaver extends Minion {
    public FrozenShadoweaver(int manaCost, String name, String description, Card.rarity rarity, HeroClass heroClass, Card.type type, int price, int HP, int Attack) {
        super(manaCost, name, description, rarity, heroClass, type, price, HP, Attack);
    }
}
