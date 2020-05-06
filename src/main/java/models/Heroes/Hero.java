package models.Heroes;

import models.Cards.Card;
import com.google.gson.annotations.Expose;

import java.io.IOException;
import java.util.ArrayList;

public class  Hero {

   @Expose protected int HP;
   @Expose protected String name;
   @Expose private String heroPower;
   @Expose private String specialPower;
   public int getHP() {
      return HP;
   }
   public void setHP(int HP) {
      this.HP = HP;
   }
   public void SpecialPower(){}
   public void HeroPower(){}

   @Override
   public String toString(){
      if(this.getClass()==Mage.class){
         return "Mage";
      }
      if(this.getClass()==Rogue.class){
         return "Rogue";
      }
      else {
         return "Warlock";
      }
   }

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

