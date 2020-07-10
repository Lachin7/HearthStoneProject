package models.Cards.weapons;

import models.Cards.Card;
import models.Cards.Weapon;

public class HeadhuntersHatchet extends Weapon {
    public HeadhuntersHatchet(int manaCost, String name, String description, Card.rarity rarity, HeroClass heroClass, Card.type type, int price, int durability, int Attack) {
        super(manaCost, name, description, rarity, heroClass, type, price, durability, Attack);
    }
}
