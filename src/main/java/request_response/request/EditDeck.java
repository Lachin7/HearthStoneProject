package request_response.request;


import javafx.util.Pair;
import models.Cards.Card;
import server.ClientHandler;
import server.controller.CardController;

import javax.swing.*;
import java.util.logging.Level;

public class EditDeck extends Request{
    private String createdDeckName, deleteCard, rename, currentDeck;
    private boolean changeHero,deleteDeck;
    private String [] heroOptions;

    public EditDeck(String createdDeckName, String deleteCard, String rename, boolean changeHero, boolean deleteDeck, String currentDeck) {
        this.createdDeckName = createdDeckName;
        this.deleteCard = deleteCard;
        this.rename = rename;
        this.changeHero = changeHero;
        this.deleteDeck = deleteDeck;
        this.currentDeck = currentDeck;
        heroOptions = new String[]{"Mage","Warlock","Rogue","Hunter","Priest"};
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        CardController cardController = clientHandler.getCardController();
        if (createdDeckName!=null){
            if(!cardController.validDeckName(createdDeckName)) JOptionPane.showMessageDialog(null, "this name already exist for one of your decks!","There's a bug on you!", JOptionPane.ERROR_MESSAGE);
            else {
                cardController.creatDeck(createdDeckName);
                clientHandler.getPlayerLOGGER().log(Level.FINE,"created deck : " +createdDeckName+" - Collection");
            }
        }
        if (deleteCard!=null){
            cardController.removeFromDeck(deleteCard, currentDeck);
            clientHandler.getPlayerLOGGER().log(Level.FINE,"deleted card : " +deleteCard+" - Collection");
        }
        if (rename!=null){
            if(!cardController.validDeckName(rename)) JOptionPane.showMessageDialog(null, "There's a bug on you!", "this name already exist for one of your decks!", JOptionPane.ERROR_MESSAGE);
            else {
                cardController.getTheDeck(currentDeck).setName(rename);
                clientHandler.getPlayerLOGGER().log(Level.INFO,"renamed deck : " +currentDeck+ "to " + rename + " - Collection");
            }
        }
        if (changeHero){
            if(!cardController.canChangeDeckHero(currentDeck)) JOptionPane.showMessageDialog(null,"can't change the hero cause you already have special cards of a heroClass ");
            else  {
                int ans = JOptionPane.showOptionDialog(null,"choose hero for you deck","",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null,heroOptions,heroOptions[0]);
                if(ans==0) cardController.getTheDeck(currentDeck).setHero(Card.HeroClass.MAGE);
                else if(ans==1) cardController.getTheDeck(currentDeck).setHero(Card.HeroClass.WARLOCK);
                else if(ans==2) cardController.getTheDeck(currentDeck).setHero(Card.HeroClass.ROGUE);
                else if(ans==3) cardController.getTheDeck(currentDeck).setHero(Card.HeroClass.HUNTER);
                else if(ans==4) cardController.getTheDeck(currentDeck).setHero(Card.HeroClass.PRIEST);
                clientHandler.getPlayerLOGGER().log(Level.INFO,"changed deck hero - Collection");
            }
        }
        if (deleteDeck){
            cardController.removeDeck(currentDeck);
            clientHandler.getPlayerLOGGER().log(Level.INFO,"deleted deck " + currentDeck + " - Collection");
        }
        new UpdateDeckShowCase(currentDeck).execute(clientHandler);
    }
}
