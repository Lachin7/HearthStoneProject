package request_response.response;

import client.ClientGui;

public class DrawDeckInfo extends Response{
    private String name , hero,moseUsedCard;
    private int cups , wins, totalGames,  avePrice;
    private boolean currentDeck;
    private double winsToAll;

    public DrawDeckInfo(String name, String hero, String moseUsedCard, int cups, int wins, int totalGames, double winsToAll, int avePrice, boolean currentDeck) {
        this.name = name;
        this.hero = hero;
        this.moseUsedCard = moseUsedCard;
        this.cups = cups;
        this.wins = wins;
        this.totalGames = totalGames;
        this.winsToAll = winsToAll;
        this.avePrice = avePrice;
        this.currentDeck = currentDeck;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getStatusPanel().drawDeckInfo(name,cups,wins,totalGames,winsToAll,avePrice,hero,moseUsedCard,currentDeck);
    }
}
