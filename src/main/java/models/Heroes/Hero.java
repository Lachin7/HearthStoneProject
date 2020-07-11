package models.Heroes;

import com.google.gson.annotations.Expose;
import models.Cards.Target;
import models.Cards.Weapon;
import models.Character;

import java.io.IOException;
import java.util.ArrayList;

public abstract class  Hero implements Character {

   @Expose protected int HP, Attack = 0, maxHp, maxAttack;
   @Expose protected String name;
   @Expose protected long id;
   @Expose protected int heroPowerCost;
   @Expose protected ArrayList<Target> heroPowerTarget;
   @Expose protected Weapon weapon;
   @Expose protected HeroPower heroPower;


   public Hero(){
      id = System.currentTimeMillis();
   }

   @Override
   public void setHP(int hp) {
      HP = hp;
   }
   @Override
   public void setAttack(int attack) {
      Attack = attack;
   }

   @Override
   public int getMaxHp() {
      return maxHp;
   }

   @Override
   public void setMaxHp(int hp) {
      this.maxHp = hp;
   }

   @Override
   public int getMaxAttack() {
      return maxAttack;
   }

   @Override
   public void setMaxAttack(int attack) {
      this.maxAttack = attack;
   }

   @Override
   public long getId() {
      return id;
   }

   @Override
   public int getHP() {
      return HP;
   }
   @Override
   public int getAttack() {
      return Attack;
   }

   public HeroPower getHeroPower(){
      return heroPower;
   }

   public String getName(){
      return name;
   }

   protected ArrayList<Target> getAllCharacters(){
      ArrayList<Target> targets = new ArrayList<>();
      targets.add(Target.FRIENDLY_MINION);
      targets.add(Target.FRIENDLY_HERO);
      targets.add(Target.ENEMY_HERO);
      targets.add(Target.ENEMY_MINION);
      return targets;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getHeroPowerCost() {
      return heroPowerCost;
   }

   public void setHeroPowerCost(int heroPowerCost) {
      this.heroPowerCost = heroPowerCost;
   }

   public ArrayList<Target> getHeroPowerTarget() {
      return heroPowerTarget;
   }

   public void setHeroPowerTarget(ArrayList<Target> heroPowerTarget) {
      this.heroPowerTarget = heroPowerTarget;
   }

   public Weapon getWeapon() {
      return weapon;
   }

   public void setWeapon(Weapon weapon) {
      this.weapon = weapon;
   }

   //   public boolean isHasWeapon() {
//      return hasWeapon;
//   }
//
//   public void setHasWeapon(boolean hasWeapon) {
//      this.hasWeapon = hasWeapon;
//   }

   //   public void SpecialPower(){}
//   public void HeroPower(){
//   }
   //   @Expose private String heroPower;
//   @Expose private String specialPower;

   //   ArrayList<card> HeroDeckCards =new ArrayList<>();
//   public ArrayList<card> getHeroDeckCards() {
//      return HeroDeckCards;
//   }
//   public void setHeroDeckCards(ArrayList<card> heroDeckCards) {
//      HeroDeckCards = heroDeckCards;
//   }
//   ArrayList<Card> heroAllCards = new ArrayList<>();
//   public ArrayList<Card> getHeroAllCards() throws IOException {
//      return heroAllCards;
//   }
//   public void setHeroAllCards(ArrayList<Card> heroAllCards) {
//      this.heroAllCards = heroAllCards;
//   }



}

