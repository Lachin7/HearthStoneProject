package server.models.Heroes;

import javax.persistence.Entity;

@Entity
public class Hunter extends Hero {

    public Hunter(){
        this.HP = 30;
        this.name = "Hunter";
        this.heroPowerCost = 0;
        this.heroPower = HeroPower.HunterHPower;
    }

}
