package models.Cards;
import com.google.gson.annotations.Expose;
import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Character;

public abstract class Weapon extends Card {
    @Expose private int durability,Attack;

    public Weapon(int manaCost, String name, String description, Card.rarity rarity, HeroClass heroClass, Card.type type, int price, int durability, int Attack) {
        super(manaCost, name, description, rarity, heroClass, type , price);
        this.durability = durability;
        this.Attack = Attack;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public void setAttack(int attack) {
        Attack = attack;
    }

    public int getDurability() {
        return durability;
    }

    public int getAttack() {
        return Attack;
    }

    //    @Override
//    public String toString() {
//        return super.toString().substring(0,super.toString().indexOf("}")) + ", durability  : "+ this.durability + ", Attack : "+ this.Attack + " } *";
//    }
}
