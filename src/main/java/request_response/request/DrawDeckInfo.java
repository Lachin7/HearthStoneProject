package request_response.request;

import models.Deck;
import server.ClientHandler;

public class DrawDeckInfo extends Request {
    private String name;

    public DrawDeckInfo(String name) {
        this.name = name;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        Deck deck = clientHandler.getCardController().getTheDeck(name);
        String hero ="hero not selected",mostUsedCard= "you dont have any cards in this deck";
        boolean current = false;
        if (deck.getHero()!=null)hero = deck.getHero()+"";
        if(deck.getCards().size()!=0) mostUsedCard = deck.getCardsSortedByValue().get(0).toString();
        if (clientHandler.getMainPlayer().getDeck().getName().equals(name))current= true;
        clientHandler.sendResponse("DrawDeckInfo", new request_response.response.DrawDeckInfo(name,hero,mostUsedCard,deck.getCups(),deck.getWinGamesPlayed(),deck.getAllGamesPlayed(),deck.winToAll(),deck.averagePrice(),current));
    }
}
