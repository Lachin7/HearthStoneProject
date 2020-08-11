package server.models.Heroes;

import javax.persistence.Entity;

@Entity
public class Mage extends Hero {

    public Mage() {
        this.HP = 30;
        this.name = "Mage";
        this.heroPowerCost = 2;
        this.heroPowerTarget = getAllCharacters();
        this.heroPower = HeroPower.MageHPower;
    }


    @Override
    public String toString() {
        return "MAGE";
    }
}
