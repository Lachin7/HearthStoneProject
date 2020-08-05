package request_response.request;

import request_response.response.Message;
import server.ClientHandler;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;

public class BuyCard extends Request {
    private long id;
    private String name;
    public BuyCard(long id,String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        if (!clientHandler.getCardController().canBuy(name))
            clientHandler.sendResponse("Message",new Message("can't buy this card , you don't have enough coins"));
        else {
            clientHandler.sendResponse("Message",new Message("bought card " + name + " successfully"));
            clientHandler.getCardController().buyCard(id);
            clientHandler.getPlayerLOGGER().log(Level.INFO, "button clicked to buy a card - Shop - " + " bought card " + name + " successfully");
        }
    }
}
