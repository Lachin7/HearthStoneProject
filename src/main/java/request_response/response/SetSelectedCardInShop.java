package request_response.response;

import client.Client;

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
    public void execute(Client client) {
        client.getShopPanel().setSelectedCard(id,name,coins,price);
    }
}
