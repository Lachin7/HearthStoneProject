package models.Cards;
import com.google.gson.annotations.Expose;

public class Weapon extends Card {
    @Expose private long durability,Attack;

    public Weapon(int manaCost, String name, String description, Card.rarity rarity, HeroClass heroClass, Card.type type, int price, int durability, int Attack) {
        super(manaCost, name, description, rarity, heroClass, type , price);
        this.durability = durability;
        this.Attack = Attack;
    }
//    @Override
//    public String toString() {
//        return super.toString().substring(0,super.toString().indexOf("}")) + ", durability  : "+ this.durability + ", Attack : "+ this.Attack + " } *";
//    }
}
