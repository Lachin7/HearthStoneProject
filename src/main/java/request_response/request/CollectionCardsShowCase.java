package request_response.request;

import server.models.Cards.Card;
import server.ClientHandler;
import server.controller.CardController;

import java.util.HashMap;

public class CollectionCardsShowCase extends Request{

    private int mana;
    private String search;
    private Card.type type;
    private Card.HeroClass heroClass;
    private boolean locked, unlocked;

    public CollectionCardsShowCase(int mana, Card.type type, Card.HeroClass heroClass, String search, boolean locked, boolean unlocked) {
        this.mana = mana;
        this.search =search;
        this.type = type;
        this.heroClass = heroClass;
        this.locked = locked;
        this.unlocked = unlocked;
    }


    @Override
    public void execute(ClientHandler clientHandler) {
        HashMap<Long,String> result = new HashMap<>();
       CardController cardController =  clientHandler.getCardController();
       if (type!=null) cardController.getTypeCards(type).forEach(card -> result.put(card.getId(),card.getName()));
       if (heroClass!=null)cardController.getHeroCardsInGame(heroClass).forEach(card -> result.put(card.getId(),card.getName()));
       if (mana!=-1) for (Card card : cardController.getAllCardsInGame())if (card.getManaCost()==mana) result.put(card.getId(),card.getName());
       if (search!=null) cardController.searchFilter(search).forEach(card -> result.put(card.getId(),card.getName()));
       if (locked) cardController.getLockedCards().forEach(card -> result.put(card.getId(),card.getName()));
       if (unlocked) clientHandler.getMainPlayer().getAllCards().forEach(card -> result.put(card.getId(),card.getName()));
       else cardController.getAllCardsInGame().forEach(card -> result.put(card.getId(),card.getName()));
        clientHandler.sendResponse("CollectionCardsShowCase",new request_response.response.CollectionCardsShowCase(type!=null,result));
    }
}
