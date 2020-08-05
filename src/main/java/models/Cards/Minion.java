package models.Cards;

import lombok.Getter;
import lombok.Setter;
import models.Character;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public abstract class Minion extends Card implements Character {
    @Column
    @Getter
    @Setter
    protected int HP, Attack, maxHp, maxAttack;
    @Column
    protected boolean hasDivineShield, canAttack, isRush, isTaunt, isPoisonous;
    @Column
    @Getter
    @Setter
    protected SubType subType;

    public Minion(){}

    public enum SubType {
        NULL, BEAST, DEMON, DRAGON, ELEMENTAL, MECH, MURLOC, PIRATE, TOTEM
    }

    public long getId() {
        return id;
    }

    public Minion(int manaCost, String name, String description, Card.rarity rarity, HeroClass heroClass, Card.type type, SubType subType, int price, int HP, int Attack) {
        super(manaCost, name, description, rarity, heroClass, type, price);
        this.HP = HP;
        this.Attack = Attack;
        maxHp = HP;
        maxAttack = Attack;
        this.subType = subType;
    }

    public boolean hasDivineShield() {
        return hasDivineShield;
    }

    public void setHasDivineShield(boolean hasDivineShield) {
        this.hasDivineShield = hasDivineShield;
    }

    public boolean canAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public boolean CanAttackHero() {
        return isRush;
    }

    public void setRush(boolean rush) {
        this.isRush = rush;
    }

    public boolean isRush() {
        return this.isRush;
    }

    public boolean hasTaunt() {
        return isTaunt;
    }

    public void setHasTaunt(boolean taunt) {
        isTaunt = taunt;
    }

    public boolean isPoisonous() {
        return isPoisonous;
    }

    public void setPoisonous(boolean poisonous) {
        isPoisonous = poisonous;
    }


//    public boolean isCanAttackToMinion() {
//        return canAttackToMinion;
//    }
//
//    public void setCanAttackToMinion(boolean canAttackToMinion) {
//        this.canAttackToMinion = canAttackToMinion;
//    }
//
//    public boolean canAttackToCharacter() {
//        return canAttackToCharacter;
//    }
//
//    public void setCanAttackToCharacter(boolean canAttackToCharacter) {
//        this.canAttackToCharacter = canAttackToCharacter;
//    }


//    @Override
//    public void setHP(int hp) {
//
//    }
//
//    @Override
//    public void setHP(int hp) {
//
//    }


//    @Override
//    public int getHP() {
//        return 0;
//    }
//
//    @Override
//    public int getAttack() {
//        return 0;
//    }
//    public void change(int hpAdded , int attackAdded) {
//        this.setHP(this.getHP() + hpAdded);
//        this.setAttack(this.getAttack() + attackAdded);
//    }


    //    @Override
//    public String toString() {
//        return super.toString().substring(0,super.toString().indexOf("}")) + ", HP  : "+ this.HP + ", Attack : "+ this.Attack + " } ";
//    }
}
