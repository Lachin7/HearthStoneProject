package request_response.request;

import server.ClientHandler;

import java.util.logging.Level;

public class SetDeckAsCurrent extends Request {
    private String selectedDeck;
    public SetDeckAsCurrent(String selectedDeck) {
        this.selectedDeck = selectedDeck;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.getMainPlayer().setDeck(clientHandler.getCardController().getTheDeck(selectedDeck));
        if(clientHandler.getCardController().getTheDeck(selectedDeck).getHero()!=null) clientHandler.getMainPlayer().setPlayersChoosedHero(clientHandler.getCardController().getTheDeck(selectedDeck).getHero());
        clientHandler.getPlayerLOGGER().log(Level.FINE," button clicked - Status - changed current deck to : " + selectedDeck);
    }
}
