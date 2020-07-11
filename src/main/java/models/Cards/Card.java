package models.Cards;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import controller.BoardController;
import controller.actionVisitors.card.VisitableCard;
import controller.actionVisitors.card.CardVisitor;
import models.Character;


import java.util.ArrayList;

public abstract class Card implements VisitableCard {

    /** defining fields here */
    @Expose protected int manaCost ;
    @Expose protected String name , description;
    @Expose protected rarity rarity;
    @Expose protected type type;
    @Expose protected HeroClass heroClass;
    @Expose protected int price;
    @Expose protected long id;
    @Expose protected boolean hasInitialMoveTarget;
    @Expose protected ArrayList<Target> targets;


    /** defining relevant enums and classes here */
    public enum rarity {
        COMMON,RARE,EPIC,LEGENDARY
    }

    public enum type {
        MINION,SPELL, WEAPON
    }

    public enum  HeroClass {
        NEUTRAL,MAGE,WARLOCK,ROGUE,HUNTER,PRIEST;
    }

    public Card(){


    }

    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {

    }

    /** defining constructor here */
    public Card(int manaCost, String name, String description, rarity rarity, HeroClass heroClass,type type, int price){
        this.manaCost = manaCost;
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.heroClass = heroClass;
        this.type = type;
        this.price = price;
        this.id = System.currentTimeMillis();
//        System.out.println(System.currentTimeMillis());
    }



    /** defining methods here */
    @Override
    public String toString() {
    return this.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) return false;
        if (this == obj) return true;
        Card ex = (Card) obj;
        return this.toString().equals(ex.toString()) ;
    }

    public Card cardFromJson(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        return gson.fromJson(gson.toJson(this, Card.class), Card.class);
    }


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
    public int getPrice() {
        return price;
    }
    public Card.type getType() {
        return type;
    }
    public void setType(Card.type type) {
        this.type = type;
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
    public boolean getHasInitialMoveTarget() {
        return hasInitialMoveTarget;
    }
    public void setHasInitialMoveTarget(boolean hasInitialMoveTarget) {
        this.hasInitialMoveTarget = hasInitialMoveTarget;
    }

    public long getId() {
        return id;
    }

    public ArrayList<Target> getTargets() {
        return targets;
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
    //  return "\" {Card name : \""    + "\"" + this.name + "\""   + "\"" +", mana Cost :"+ "\""   + "\""  + this.manaCost + ", hero Calss : " + "\"" + this.heroClass + ", type : " + this.type + ", rarity : " + this.rarity + ", description : " + this.description + "}  ";

}
