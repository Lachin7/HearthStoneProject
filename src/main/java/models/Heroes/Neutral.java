package models.Heroes;
import models.Cards.Card;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;

import static controller.CardController.getALLCardsExistingInGame;

public class Neutral extends Hero {

  //  static Neutral Neutral = new Neutral();
  @Expose
  public ArrayList<Card> nuetralAllCards =new ArrayList<>();

    public Neutral() {
        for(Card card : getALLCardsExistingInGame()) {
            if(card.getHeroClass() == Card.HeroClass.NEUTRAL){
                nuetralAllCards.add(card);
            }
        }
    }
//    public static Neutral getInstance(){
//        return Neutral;
//    }
    @Override
    public String toString() {
        return "NEUTRAL";
    }

    public String getName() {
        return "Neutral";
    }

//    public static void main(String[] args) throws IOException {
//        jsonFileMakerForHeroes(new Neutral());
//    }
}
