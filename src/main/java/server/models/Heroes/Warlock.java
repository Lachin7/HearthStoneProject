package server.models.Heroes;

import javax.persistence.Entity;

@Entity
public class Warlock extends Hero {

    public Warlock() {
        this.HP = 35;
        this.name="Warlock";
        this.heroPowerCost =0;
        this.heroPower = HeroPower.WarlockHeroPower;
   }

    public void HeroPower() {
        this.HP -=2;
    }

    @Override
    public String toString() {
        return "WARLOCK";
    }
    public String getName() {
        return "Warlock";
    }

}
