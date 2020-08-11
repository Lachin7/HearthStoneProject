package request_response.request;

import request_response.response.Message;
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
        if(!cardController.canAddToDeck(cardName,currentDeck)) clientHandler.sendResponse("Message",new Message("you can't have more than two same cards in a deck"));
        else if(cardController.isLocked(cardController.createCard(cardName))) clientHandler.sendResponse("Message",new Message("you don't have this card"));
        else if(cardController.wrongHeroClass(cardName,currentDeck))clientHandler.sendResponse("Message",new Message("the cards hero doesnt match deck's hero "));
        else if(cardController.getTheDeck(currentDeck).getCards().size()>= 30 ) clientHandler.sendResponse("Message",new Message("your deck is full! "));
        else cardController.addCardToDeck(cardName,currentDeck);
    }
}
