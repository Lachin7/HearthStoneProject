package client.actionController;

import client.ClientGui;
import request_response.request.*;

public class ShopActionController extends ActionController{
    public ShopActionController(ClientGui clientGui) {
        super(clientGui);
    }

    public void setBuyShowCase() {
        clientGui.getAudioPlayer().playQuick("Switch-SoundBible.com-350629905.wav");
        clientGui.sendRequest("SetBuyShowCase",new SetBuyShowCase());
    }

    public void setSellShowCase() {
        clientGui.getAudioPlayer().playQuick("Switch-SoundBible.com-350629905.wav");
        clientGui.sendRequest("SetSellShowCase",new SetSellShowCase());
    }

    public void buyCard(long id, String name ) {
        clientGui.sendRequest("BuyCard",new BuyCard(id, name));
    }

    public void sellCard(long id, String name) {
        clientGui.sendRequest("SellCard",new SellCard(id,name));
    }

    public void setSelectedCardInShop(Long key, String value) {
        clientGui.sendRequest("SetSelectedCardInShop",new SetSelectedCardInShop(key,value));
    }
}
