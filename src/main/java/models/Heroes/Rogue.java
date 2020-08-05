package models.Heroes;

import javax.persistence.Entity;

@Entity
public class Rogue extends Hero {

    public Rogue() {
        this.HP = 30;
        this.name ="Rogue";
        this.heroPowerCost = 3;
        this.heroPower = HeroPower.RogueHPower;
    }

    public String getName() {
        return "Rogue";
    }

}