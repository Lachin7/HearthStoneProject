package models.Heroes;
import models.Cards.Card;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;

import static controller.CardController.getALLCardsExistingInGame;

public class Neutral extends Hero {

    public Neutral() {
        this.name = "Neutral";
    }

  //  static Neutral Neutral = new Neutral();

//    public static Neutral getInstance(){
//        return Neutral;
//    }

//    public static void main(String[] args) throws IOException {
//        jsonFileMakerForHeroes(new Neutral());
//    }
}
