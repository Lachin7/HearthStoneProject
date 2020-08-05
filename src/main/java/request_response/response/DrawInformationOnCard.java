package request_response.response;

import client.Client;
import gui.myComponents.MyCardButton;
import models.Cards.Card;

public class DrawInformationOnCard extends Response{
    private int mana, hp, attack,  durability;
    private boolean locked, hasShield, hasTaunt, isInGame, canAttack;
    private MyCardButton card;
    private Card.type type;

    public DrawInformationOnCard(MyCardButton card,int mana, int hp, int attack, int durability, boolean locked, boolean hasShield, boolean hasTaunt,boolean canAttack, Card.type type) {
        this.mana = mana;
        this.hp = hp;
        this.attack = attack;
        this.durability = durability;
        this.locked = locked;
        this.hasShield = hasShield;
        this.hasTaunt = hasTaunt;
        this.canAttack = canAttack;
//        this.isInGame = isInGame;
        this.card = card;
    }

    @Override
    public void execute(Client client) {
        card.drawInformationOnCard(mana,hp,attack,durability,locked,hasShield,hasTaunt,canAttack,type);
    }
}
