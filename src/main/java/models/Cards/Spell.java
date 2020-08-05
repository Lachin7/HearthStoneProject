package models.Cards;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public abstract class Spell extends Card {
    @Column
    private boolean canRestore = false,hasDiscovery = false;
    @Column
    @Getter
    @Setter
    private int restoreAmount = 0;

    public Spell(int manaCost, String name, String description, rarity rarity, HeroClass heroClass, type type, int price) {
        super(manaCost, name, description, rarity, heroClass, type, price);
    }

    public Spell() {

    }

    public boolean getCanRestore() {
        return canRestore;
    }

    public void setCanRestore(boolean canRestore) {
        this.canRestore = canRestore;
    }

    public boolean HasDiscovery() {
        return hasDiscovery;
    }

    public void setHasDiscovery(boolean hasDiscovery) {
        this.hasDiscovery = hasDiscovery;
    }

}
