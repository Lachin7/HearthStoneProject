package request_response.request;

import server.ClientHandler;
import server.controller.CardController;

import javax.swing.*;

public class AddCardToDeckCollection extends Request {
    private String cardName, currentDeck;

    public AddCardToDeckCollection(String cardName, String currentDeck) {
        this.cardName = cardName;
        this.currentDeck = currentDeck;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        CardController cardController = clientHandler.getCardController();
        if(!cardController.canAddToDeck(cardName,currentDeck)) JOptionPane.showMessageDialog(null,"you can't have more than two same cards in a deck");
        else if(cardController.isLocked(cardController.createCard(cardName))) JOptionPane.showMessageDialog(null,"you don't have this card");
        else if(cardController.wrongHeroClass(cardName,currentDeck))JOptionPane.showMessageDialog(null,"the cards hero doesnt match deck's hero");
        else if(cardController.getTheDeck(currentDeck).getCards().size()>= 30 ) JOptionPane.showMessageDialog(null,"your deck is full!");
        else cardController.addCardToDeck(cardName,currentDeck);
    }
}
