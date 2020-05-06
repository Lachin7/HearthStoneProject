package models.Cards;
import com.google.gson.annotations.Expose;

public class Minion extends Card {
    @Expose private long HP ;
    @Expose private long Attack;

    public long getHP() {
        return HP;
    }
    public void setHP(long HP) {
        this.HP = HP;
    }
    public long getAttack() {
        return Attack;
    }
    public void setAttack(long attack) {
        Attack = attack;
    }

    public Minion(int manaCost, String name, String description, Card.rarity rarity, HeroClass heroClass, Card.type type, int price , int HP, int Attack) {
        super(manaCost, name, description, rarity, heroClass, type , price);
        this.HP = HP;
        this.Attack = Attack;
    }

//    @Override
//    public String toString() {
//        return super.toString().substring(0,super.toString().indexOf("}")) + ", HP  : "+ this.HP + ", Attack : "+ this.Attack + " } ";
//    }
}
