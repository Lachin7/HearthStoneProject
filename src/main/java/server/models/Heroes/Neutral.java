package server.models.Heroes;

import javax.persistence.Entity;

@Entity
public class Neutral extends Hero {

    public Neutral() {
        this.name = "Neutral";
    }

}
