package controller.modes;

import controller.BoardController;
import controller.Controller;
import models.Cards.Card;
import models.Heroes.Mage;
import models.Player;

import java.util.ArrayList;

public class Practice extends BoardController {

    public Practice(){
        super();
    }

    @Override
    protected void setPlayers() {
        if(isANewGame()) {
            chooseFriendAsMain();
             //todo you can go and choose controller.getMainPlayer ... or create them here
            chooseEnemyAsMain();
        }
    }

//    @Override
//    public void checkForNewGame() {
//        super.checkForNewGame();
//        switchPlayers();
//        super.checkForNewGame();
//    }

//    @Override
//    public void endTurn() {
//        switchPlayers();
//        super.endTurn();
//    }
}
