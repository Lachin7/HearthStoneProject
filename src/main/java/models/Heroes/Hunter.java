package models.Heroes;

public class Hunter extends Hero {

    public Hunter(){
        this.HP = 30;
        this.name = "Hunter";
        this.heroPowerCost = 0;
        this.heroPower = HeroPower.HunterHPower;
    }

//    @Override
//    public void HeroPower(){}
//    @Override
//    public void SpecialPower(){}
}
