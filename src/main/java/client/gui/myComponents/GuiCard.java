package client.gui.myComponents;

import client.actionController.ActionController;
import lombok.Getter;
import lombok.Setter;
import resLoader.ImageLoader;
import server.models.Cards.Card;

import java.awt.*;

public class GuiCard {
    @Getter
    private String cardName;
    @Getter
    private long id;
    @Setter
    @Getter
    private int mana=-1, hp=-30, attack=-30,durability=-30;
    @Getter
    private Card.type type;
    @Getter
    private boolean canAttack=false,hasTaunt=false,locked=false,hasShield=false;

    public GuiCard(String cardName, long id, int mana, int hp, int attack, int durability, Card.type type,boolean canAttack, boolean hasTaunt, boolean locked, boolean hasShield) {
        this.cardName = cardName;
        this.id = id;
        this.mana = mana;
        this.hp = hp;
        this.attack = attack;
        this.durability = durability;
        this.type = type;
        this.canAttack = canAttack;
        this.hasTaunt = hasTaunt;
        this.locked = locked;
        this.hasShield = hasShield;
    }
}
