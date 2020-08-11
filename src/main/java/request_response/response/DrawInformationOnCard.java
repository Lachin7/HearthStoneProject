package request_response.response;

import client.ClientGui;
import client.gui.myComponents.MyCardButton;
import server.models.Cards.Card;

public class DrawInformationOnCard extends Response{
    private int mana, hp, attack,  durability;
    private boolean locked, hasShield, hasTaunt, canAttack;
    private Card.type type;
    private Long id;

    public DrawInformationOnCard(Long id,int mana, int hp, int attack, int durability, boolean locked, boolean hasShield, boolean hasTaunt,boolean canAttack, Card.type type) {
        this.mana = mana;
        this.hp = hp;
        this.attack = attack;
        this.durability = durability;
        this.locked = locked;
        this.hasShield = hasShield;
        this.hasTaunt = hasTaunt;
        this.canAttack = canAttack;
        this.type =type;
        this.id = id;
    }

    @Override
    public void execute(ClientGui clientGui) {
        for (MyCardButton cardButton : clientGui.getCardButtons().values())if (cardButton.getId()==id)
            cardButton.drawInformationOnCard(mana,hp,attack,durability,locked,hasShield,hasTaunt,canAttack,type);


    }
}
