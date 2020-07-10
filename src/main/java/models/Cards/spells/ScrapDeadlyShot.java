package models.Cards.spells;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Cards.Spell;
import models.Character;

public class ScrapShot extends Spell {
    public ScrapShot(int manaCost, String name, String description, Card.rarity rarity, HeroClass heroClass, Card.type type, int price, String Quest, String Reward) {
        super(manaCost, name, description, rarity, heroClass, type, price, Quest, Reward);
    }
    @Override
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController) {
        cardVisitor.visitScrapShot(this,target,boardController);
    }
}
