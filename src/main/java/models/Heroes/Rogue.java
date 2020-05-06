package models.Heroes;

import models.Cards.Card;
import cliAndMenus.gameCLI;
import com.google.gson.annotations.Expose;

public class Rogue extends Hero {


    public Rogue() {
        this.HP = 30;
        this.name ="Rogue";
    }

    public void SpecialPower() {
        if (gameCLI.getInstance().getCurrentPlayer().getPlayersChoosedHero() == this) {
            for (Card card : gameCLI.getInstance().getCurrentPlayer().getALLPlayersCards()) {
//                if ((Rogue) card.getHeroClass()!= Rogue.getInstance()|| card.getHeroClass() != (HeroClass) Neutral.getInstance()) {
//                    card.setManaCost(card.getManaCost() - 2);
//                }
            }
        }
    }

    @Override
    public String toString() {
        return "ROGUE";
    }
    public String getName() {
        return "Rogue";
    }

}

//    @Override
//    public  ArrayList<card> getHeroAllCards() {
//        return this.RogueAllCards;
//    }
//    @Override
//    public void setHeroAllCards(ArrayList<card> heroAllCards) {
//        this.RogueAllCards = heroAllCards;
//    }
//    @Override
//    public void setHeroDeckCards(ArrayList<card> heroDeckCards) {
//        this.RogueDeckCards = heroDeckCards;
//    }
//    @Override
//    public ArrayList<card> getHeroDeckCards() {
//        return this.RogueDeckCards;
//    }

//    public void HeroPower(){
//        Random random = new Random(enemyPlayer.getPlayersDeckCards().size());
//        gameCLI.getInstance().getCurrentPlayer().getPlayersDeckCards().add(enemyPlayer.getPlayersDeckCards().get(random.nextInt()));
//    }
//    public void UpgratedHeroPower(){
//        Random random = new Random(enemyPlayer.getPlayersDeckCards().size());
//        gameCLI.getInstance().getCurrentPlayer().getPlayersBoardCards().add(enemyPlayer.getPlayersDeckCards().get(random.nextInt()));
//        Random random2 = new Random(gameCLI.getInstance().getCurrentPlayer().getPlayersDeckCards().size());
//        gameCLI.getInstance().getCurrentPlayer().getPlayersBoardCards().add(gameCLI.getInstance().getCurrentPlayer().getPlayersDeckCards().get(random.nextInt()));
//    }

//    @Expose public ArrayList<Card> rogueAllCards =new ArrayList<>();
//    @Override
//    public  ArrayList<Card> getHeroAllCards() {
//        return rogueAllCards;
//    }
//   for(Card card : getALLCardsExistingInGame()) {
//           if(card.getHeroClass() == Card.HeroClass.ROGUE){
//           rogueAllCards.add(card);
//           }
//           }