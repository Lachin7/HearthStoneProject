package models.Cards.minions;

import models.Cards.Card;
import models.Cards.Minion;
import models.Cards.Spell;

public class FriendlySmith extends Spell {
    public FriendlySmith(int manaCost, String name, String description, Card.rarity rarity, HeroClass heroClass, Card.type type, int price, String Quest, String Reward) {
        super(1, "FriendlySmith", "Discover a weapon from any class. Add it to your Adventure Deck with +2/+2",
                Card.rarity.COMMON, HeroClass.ROGUE, Card.type.SPELL, 5, "", "");

    }

}
