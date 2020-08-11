package client.actionController;

import client.ClientGui;
import server.models.Cards.Card;
import request_response.request.*;

public class CollectionPanelAction extends ActionController{
    public CollectionPanelAction(ClientGui clientGui) {
        super(clientGui);
    }

    public void setShowCase(int mana, Card.type type, Card.HeroClass heroClass, String search, boolean locked, boolean unlocked){
        clientGui.getAudioPlayer().playQuick("TinyPush.wav");
        clientGui.sendRequest("CollectionCardsShowCase",new CollectionCardsShowCase(mana,type,heroClass,search, locked,unlocked));
    }

    public void addCardToDeck(String cardName, String currentDeck) {
        clientGui.sendRequest("AddCardToDeckCollection",new AddCardToDeckCollection(cardName,currentDeck));
    }

    public void updateDeckShowCase(String currentDeck) {
        clientGui.sendRequest("UpdateDeckShowCase", new UpdateDeckShowCase(currentDeck));
    }

    public void editDeck(String createdDeckName, String deleteCard,String rename,boolean changeHero, boolean deleteDeck, String currentDeck ) {
        clientGui.sendRequest("EditDeck",new EditDeck(createdDeckName,deleteCard,rename,changeHero,deleteDeck,currentDeck));
    }

    public void setSelectedCardInShop(Long key, String value) {
       clientGui.sendRequest("SetSelectedCardInShop",new SetSelectedCardInShop(key,value));
    }
}
