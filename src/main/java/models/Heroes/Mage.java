package models.Heroes;
import models.Cards.Card;
import com.google.gson.annotations.Expose;
import models.Cards.Target;

import java.util.ArrayList;

public class Mage extends Hero {

    public Mage() {
        this.HP = 30;
        this.name = "Mage";
        this.heroPowerCost = 2;
        this.heroPowerTarget = getAllCharacters();
    }


    @Override
    public String toString() {
        return "MAGE";
    }



//    public void HeroPower(long manaCost , Minion minion) {
//        manaCost = 2;
//        if(gameCLI.getInstance().getCurrentPlayer().getPlayersManaInCurrentTurn() >= manaCost) {
//            minion.setHP(minion.getHP() - 1); gameCLI.getInstance().getCurrentPlayer().setPlayersManaInCurrentTurn(gameCLI.getInstance().getCurrentPlayer().getPlayersManaInCurrentTurn()-manaCost);
//        }
//        else
//            System.out.println("You don't have enough mana in this turn ");
//    }
//    public void HeroPower(long manaCost ,Hero hero) {
//        manaCost = 2;
//        if(gameCLI.getInstance().getCurrentPlayer().getPlayersManaInCurrentTurn() >= manaCost) {
//            hero.setHP(hero.getHP() - 1); gameCLI.getInstance().getCurrentPlayer().setPlayersManaInCurrentTurn(gameCLI.getInstance().getCurrentPlayer().getPlayersManaInCurrentTurn()-manaCost);
//        }
//        else
//            System.out.println("You don't have enough mana in this turn ");
//    }
//    for(models.Cards.card card : getALLCardsExistingInGame()){
//         ArrayList<card> MageAllCards =new ArrayList<>();
//         ArrayList<card> WarlockAllCards =new ArrayList<>();
//         ArrayList<card> RogueAllCards =new ArrayList<>();
//        if(card.getHeroClass() == models.Cards.card.HeroClass.MAGE){
//            MageAllCards.add(card);
//        }
//        if(card.getHeroClass() == models.Cards.card.HeroClass.ROGUE){
//           RogueAllCards.add(card);
//        }
//        if(card.getHeroClass() == models.Cards.card.HeroClass.WARLOCK){
//           WarlockAllCards.add(card);
//        }

    //    @Override
//    public void setHeroDeckCards(ArrayList<card> heroDeckCards) {
//        this.MageDeckCards = heroDeckCards;
//    }
//    @Override
//    public ArrayList<card> getHeroDeckCards() {
//
//        return this.MageDeckCards;
//    }
//    @Expose public ArrayList<Card> mageAllCards =new ArrayList<>();
//    @Override
//    public  ArrayList<Card> getHeroAllCards() throws IOException {
//        return this.mageAllCards;
//    }
//    @Override
//    public void setHeroAllCards(ArrayList<Card> heroAllCards) {
//        this.mageAllCards = heroAllCards;
//    }
//     for(Card card : getALLCardsExistingInGame()) {
//        if(card.getHeroClass() == Card.HeroClass.MAGE){
//            mageAllCards.add(card);
//        }
//    }
//    @Override
//    public void SpecialPower(){
//        if(gameCLI.getInstance().getCurrentPlayer().getPlayersChoosedHero()== this)
//        for (Card card :   gameCLI.getInstance().getCurrentPlayer().getALLPlayersCards()) {
//            if(card.getType()== Card.type.SPELL){
//               card.setManaCost(card.getManaCost()-2);
//            }
//        }
//    }
}
