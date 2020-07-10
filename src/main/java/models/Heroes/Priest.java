package models.Heroes;

public class Priest extends Hero{

   public Priest(){
       this.HP = 30;
       this.name = "Priest";
       this.heroPowerCost =2;
       this.heroPowerTarget = getAllCharacters();
   }

//   @Override
//   public void HeroPower(){}
//   @Override
//   public void SpecialPower(){}

}
