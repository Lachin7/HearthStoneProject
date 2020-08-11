package request_response.response;

import client.ClientGui;

public class SetSelectedCardInShop extends Response {
    private long id, coins;
    private String name, price;

    public SetSelectedCardInShop(long id, long coins, String name, String price) {
        this.id = id;
        this.coins = coins;
        this.name = name;
        this.price = price;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getShopPanel().setSelectedCard(id,name,coins,price);
        for (Long id : clientGui.getCardButtons().keySet())clientGui.getActionController().drawInformationOnCard(id);

    }
}
