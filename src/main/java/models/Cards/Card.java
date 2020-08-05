package models.Cards;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import server.controller.BoardController;
import server.controller.actionVisitors.card.VisitableCard;
import server.controller.actionVisitors.card.CardVisitor;
import models.Character;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class Card implements VisitableCard {

    /**
     * defining fields here
     */
    @Id
    @Getter
    @Setter
    protected String name ;
    @Transient
    @Getter
    @Setter
    protected long id;
    @Column
    @Getter
    @Setter
    protected int manaCost;
    @Column
    @Getter
    @Setter
    protected String description;
    @Column
    @Getter
    @Setter
    protected rarity rarity;
    @Column
    @Getter
    @Setter
    protected type type;
    @Column
    @Getter
    @Setter
    protected HeroClass heroClass;
    @Column
    @Getter
    @Setter
    protected int price;
    @Column
    protected boolean hasInitialMoveTarget;
    @Getter
    @Setter
    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection(targetClass = Target.class)
    @Column
    @Enumerated(EnumType.STRING)
    protected List<Target> targets;

    public Card() {
    }

    /**
     * defining relevant enums and classes here
     */
    public enum rarity {
        COMMON, RARE, EPIC, LEGENDARY
    }

    public enum type {
        MINION, SPELL, WEAPON
    }

    public enum HeroClass {
        NEUTRAL, MAGE, WARLOCK, ROGUE, HUNTER, PRIEST
    }

    @Override
    public abstract void accept(CardVisitor cardVisitor, Character target, BoardController boardController);

    /**
     * defining constructor here
     */
    public Card(int manaCost, String name, String description, rarity rarity, HeroClass heroClass, type type, int price) {
        this.manaCost = manaCost;
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.heroClass = heroClass;
        this.type = type;
        this.price = price;
//        this.id = System.nanoTime();
    }

    /**
     * defining methods here
     */
    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) return false;
        if (this == obj) return true;
        Card ex = (Card) obj;
        return this.toString().equals(ex.toString());
    }

    public Card cardFromJson() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        return gson.fromJson(gson.toJson(this, Card.class), Card.class);
    }

    /**
     * defining getters and setters here
     */

    public boolean getHasInitialMoveTarget() {
        return hasInitialMoveTarget;
    }

    public void setHasInitialMoveTarget(boolean hasInitialMoveTarget) {
        this.hasInitialMoveTarget = hasInitialMoveTarget;
    }
}
