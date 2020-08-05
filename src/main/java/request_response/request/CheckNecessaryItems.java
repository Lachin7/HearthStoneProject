package request_response.request;

import request_response.response.GoToPanel;
import server.ClientHandler;

import javax.swing.*;

public class CheckNecessaryItems extends Request {
    @Override
    public void execute(ClientHandler clientHandler) {
        if (clientHandler.getMainPlayer().getDeck() == null) {
            JOptionPane.showMessageDialog(null, "you don't have a current deck\n let's go to status and choose you deck");
            clientHandler.sendResponse("GoToPanel",new GoToPanel("status"));
        }
        if (clientHandler.getMainPlayer().getDeck().getHero() == null) {
            JOptionPane.showMessageDialog(null, "you don't have a choosed hero for your deck\n let's go to collections and choose a hero! ");
            clientHandler.sendResponse("GoToPanel",new GoToPanel("collection"));
        }
        if (clientHandler.getMainPlayer().getDeck().getCards().size() < 10) {
            JOptionPane.showMessageDialog(null, "you don't have a enough number of cards in your deck\n let's go to collections and have at least 10 cards in your deck \n also you can change your deck in status");
            clientHandler.sendResponse("GoToPanel",new GoToPanel("collection"));
        }

    }
}
