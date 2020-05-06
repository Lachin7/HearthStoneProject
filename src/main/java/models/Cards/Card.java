package models.Cards;

import com.google.gson.annotations.Expose;

public class Card {

    /** defining fields here */
    @Expose private int manaCost ;
    @Expose private String name , description;
    @Expose private rarity rarity;
    @Expose private type type;
    @Expose private HeroClass heroClass;
    @Expose private int price;

    /** defining getters and setters here */
    public int getManaCost() {
        return manaCost;
    }
    public void setManaCost(int mana) {
        manaCost = mana;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public HeroClass getHeroClass() {
        return this.heroClass;
    }
    public void setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
    }
    public Card.type getType() {
        return type;
    }
    public void setType(Card.type type) {
        this.type = type;
    }
    public int getPrice() {
        return price;
    }

    public Card.rarity getRarity() {
        return rarity;
    }

    public void setRarity(Card.rarity rarity) {
        this.rarity = rarity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /** defining relevant enums and classes here */
    public enum rarity {
        COMMON,RARE,EPIC,LEGENDARY
    }

    public enum type {
        MINION,SPELL, WEAPON
    }

    public enum  HeroClass {
        MAGE,WARLOCK,ROGUE,HUNTER,PRIEST,NEUTRAL;

        @Override
        public String toString() {
            return this.name() + "";
        }
    }

    /** defining constructor here */
    public Card(int manaCost, String name, String description, rarity rarity, HeroClass heroClass, type type , int price){
        this.manaCost = manaCost;
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.heroClass = heroClass;
        this.type = type;
        this.price = price;
    }

    /** defining methods here */
    @Override
    public String toString() {
       //  return "\" {Card name : \""    + "\"" + this.name + "\""   + "\"" +", mana Cost :"+ "\""   + "\""  + this.manaCost + ", hero Calss : " + "\"" + this.heroClass + ", type : " + this.type + ", rarity : " + this.rarity + ", description : " + this.description + "}  ";
    return this.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) return false;
        if (this == obj) return true;
        Card ex = (Card) obj;
        return this.toString().equals(ex.toString()) ;
    }







    //    public Long getCardCost(card card){
//        if(card.rarity == models.Cards.card.rarity.COMMON){
//            return new Long(5);
//        }
//        if(card.rarity == models.Cards.card.rarity.RARE){
//            return new Long(10);
//        }
//        if(card.rarity == models.Cards.card.rarity.EPIC){
//            return new Long(15);
//        }
//        if(card.rarity == models.Cards.card.rarity.LEGENDARY){
//            return new Long(20);
//        }
//        else
//            return new Long(0);
//    }
}
