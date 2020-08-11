package server.models.Cards;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public abstract class Weapon extends Card {
    @Column
    @Getter
    @Setter
    private int durability, Attack;

    public Weapon() {
    }

    public Weapon(int manaCost, String name, String description, Card.rarity rarity, HeroClass heroClass, Card.type type, int price, int durability, int Attack) {
        super(manaCost, name, description, rarity, heroClass, type, price);
        this.durability = durability;
        this.Attack = Attack;
    }
}
