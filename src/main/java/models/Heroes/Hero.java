package models.Heroes;

import lombok.Getter;
import lombok.Setter;
import models.Cards.Target;
import models.Cards.Weapon;
import models.Character;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class Hero implements Character {
    @Id
    @Getter
    @Setter
    protected String name;
    @Column
    @Getter
    @Setter
    protected long id;
    @Column
    @Getter
    @Setter
    protected int HP, Attack = 0, maxHp, maxAttack, heroPowerCost;
    @Getter
    @Setter
    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection(targetClass = Target.class)
    @Column
    @Enumerated(EnumType.STRING)
    protected List<Target> heroPowerTarget;
    @JoinColumn
    @Getter
    @Setter
    @ManyToOne
    protected Weapon weapon;
    @Column
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    protected HeroPower heroPower;

    public Hero() { id = System.nanoTime(); }

    protected ArrayList<Target> getAllCharacters() {
        ArrayList<Target> targets = new ArrayList<>();
        targets.add(Target.FRIENDLY_MINION);
        targets.add(Target.FRIENDLY_HERO);
        targets.add(Target.ENEMY_HERO);
        targets.add(Target.ENEMY_MINION);
        return targets;
    }
}

