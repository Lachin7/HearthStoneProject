package client.actionController;

import client.Client;
import request_response.request.*;

public class ShopActionController extends ActionController{
    public ShopActionController(Client client) {
        super(client);
    }

    public void setBuyShowCase() {
        client.getAudioPlayer().playQuick("Switch-SoundBible.com-350629905.wav");
        client.sendRequest("SetBuyShowCase",new SetBuyShowCase());
    }

    public void setSellShowCase() {
        client.getAudioPlayer().playQuick("Switch-SoundBible.com-350629905.wav");
        client.sendRequest("SetSellShowCase",new SetSellShowCase());
    }

    public void buyCard(long id, String name ) {
        client.sendRequest("BuyCard",new BuyCard(id, name));
    }

    public void sellCard(long id, String name) {
        client.sendRequest("SellCard",new SellCard(id,name));
    }

    public void setSelectedCardInShop(Long key, String value) {
        client.sendRequest("SetSelectedCardInShop",new SetSelectedCardInShop(key,value));
    }
}
