package gui.panels;

import controller.CardController;
import models.Cards.Card;

public class test5 {
    public static void main(String[] args) {
        CardController cardController = new CardController();
        Card card = cardController.creatCard("Polymorph");
        Card card2 = cardController.creatCard("Polymorph");
        System.out.println(card.getId());
        System.out.println(card2.getId());
    }
}
