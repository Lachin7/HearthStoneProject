package request_response.request;

import server.ClientHandler;

import java.util.logging.Level;

public class SellCard extends Request {
    private long id;
    private String name;
    public SellCard(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.getCardController().sellCard(id);
        clientHandler.getPlayerLOGGER().log(Level.INFO, "button clicked to sell a card - Shop - " + " sold card " + name + " successfully");
    }
}
