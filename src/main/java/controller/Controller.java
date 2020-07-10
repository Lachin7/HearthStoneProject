package controller;

import models.Player;

public class Controller {

    private static Controller controller;
//    Player currentPlayer = new Player();
//    Player friendlyPlayer = new Player();
//    Player enemyPlayer = new Player();
    Player mainPlayer = new Player();

    PlayerController playerController = new PlayerController();
    CardController cardController;

    public static Controller getInstance(){
        if(controller==null) controller = new Controller();
        return controller;
    }




    public Player getMainPlayer() {
        return mainPlayer;
    }

    public void setMainPlayer(Player mainPlayer) {
        this.mainPlayer = mainPlayer;
    }




    public PlayerController getPlayerController() {
        return playerController;
    }

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public CardController getCardController() {
        return cardController;
    }

    public void setCardController(CardController cardController) {
        this.cardController = cardController;
    }
}
