package client.actionController;

import client.Client;
import javafx.util.Pair;
import models.Cards.Card;
import request_response.request.*;

public class CollectionPanelAction extends ActionController{
    public CollectionPanelAction(Client client) {
        super(client);
    }

    public void setShowCase(int mana, Card.type type, Card.HeroClass heroClass, String search, boolean locked, boolean unlocked){
        client.getAudioPlayer().playQuick("TinyPush.wav");
        client.sendRequest("CollectionCardsShowCase",new CollectionCardsShowCase(mana,type,heroClass,search, locked,unlocked));
    }

    public void addCardToDeck(String cardName, String currentDeck) {
        client.sendRequest("AddCardToDeckCollection",new AddCardToDeckCollection(cardName,currentDeck));
    }

    public void updateDeckShowCase(String currentDeck) {
        client.sendRequest("UpdateDeckShowCase", new UpdateDeckShowCase(currentDeck));
    }

    public void editDeck(String createdDeckName, String deleteCard,String rename,boolean changeHero, boolean deleteDeck, String currentDeck ) {
        client.sendRequest("EditDeck",new EditDeck(createdDeckName,deleteCard,rename,changeHero,deleteDeck,currentDeck));
    }

    public void setSelectedCardInShop(Long key, String value) {
       client.sendRequest("SetSelectedCardInShop",new SetSelectedCardInShop(key,value));
    }
}
