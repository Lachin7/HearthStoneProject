package request_response.request;

import server.ClientHandler;

public class SetSelectedCardInShop extends Request{
    private long id;
    private String name;
    public SetSelectedCardInShop(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        String price = "   ???   ";
        if (!name.equals("")) price = clientHandler.getCardController().getCardWithId(id).getManaCost()+"";
        clientHandler.sendResponse("",new request_response.response.SetSelectedCardInShop(id,clientHandler.getMainPlayer().getCoins(),name,price+""));
    }
}
