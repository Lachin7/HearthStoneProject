package server.controller.Board.modes;

import server.ClientHandler;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Character;

public class TavernBrawl extends Online {
    public TavernBrawl(ClientHandler clientHandler) {
        super(clientHandler);
        timePerTurn = 60000;
        manaLimit = 10;
        handCardsLimit = 12;
        fieldSizeLimit = 7;
        manaPerTurn = 1;
    }

    @Override
    public int getManaLimit() {
        return super.getManaLimit();
    }

    @Override
    public int getManaPerTurn() {
        return super.getManaPerTurn();
    }

    @Override
    public int getFieldSizeLimit() {
        return super.getFieldSizeLimit();
    }

    @Override
    public int getTimePerTurn() {
        return super.getTimePerTurn();
    }

    @Override
    public int getHandCardsLimit() {
        return super.getHandCardsLimit();
    }

    @Override
    public void setPlayers() {
        super.setPlayers();
        //you can modify decks here
        // or change hp / attack / mana and make custom cards
    }

    @Override
    public void applyManaCostForCard(Card card) {
        super.applyManaCostForCard(card);
        //can change the mana cost here based on the card
    }

    @Override
    public void summon(String cardName, int number) {
        super.summon(cardName, number);
        //can change hp / attack / mana while summoning
    }

    @Override
    public void changeCharacter(Character character, int hpToAdd, int attackToAdd) {
        super.changeCharacter(character, hpToAdd, attackToAdd);
    }

    public void changeMana(long id, int mana){
        cardController.getCardWithId(id).setManaCost(mana);
    }
    public void changeHp(long id, int hp){
        Card card = cardController.getCardWithId(id);
      if (card instanceof Minion)((Minion) card).setHP(hp);
    }
    public void changeAttack(long id, int attack){
        Card card = cardController.getCardWithId(id);
        if (card instanceof Minion)((Minion) card).setAttack(attack);
    }
}
