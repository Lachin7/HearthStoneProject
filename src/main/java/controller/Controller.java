package controller;

import models.Player;

public class Controller {

    private static Controller controller;
    Player currentPlayer = new Player();

    PlayerController playerController = new PlayerController();
    CardController cardController;

    public static Controller getInstance(){
        if(controller==null) controller = new Controller();
        return controller;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
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
