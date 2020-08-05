package models.Heroes;

import javax.persistence.Entity;

@Entity
public class Priest extends Hero{

   public Priest(){
       this.HP = 30;
       this.name = "Priest";
       this.heroPowerCost =2;
       this.heroPowerTarget = getAllCharacters();
       this.heroPower = HeroPower.PriestHPower;
   }
}
