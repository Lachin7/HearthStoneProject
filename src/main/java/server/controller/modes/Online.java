package server.controller.modes;

import lombok.Setter;
import request_response.response.EndTurn;
import request_response.response.GoToPanel;
import request_response.response.Message;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Player;
import server.models.board.Side;
import server.ClientHandler;
import server.controller.BoardController;

public class Online extends BoardController {

    public Online(ClientHandler clientHandler) {
        super(clientHandler);
    }

    @Override
    protected void setPlayers() {
        chooseMainAsFriend();
    }

    @Override
    public void endTurn() {
        System.out.println("turn ended");
        restartThread();
        if (switchTimes%2==0) {
            for (Card card : friendlyPlayer.getFieldCardsInGame()) card.accept(endTurnCardVisitor, null, this);
            friendlyPlayer.getInfoPassive().accept(endTurnPassiveVisitor, friendlyPlayer, this);
        }
        changeManaForTurn(friendlyPlayer);
        switchTimes++;
        for (Minion minion : getCurrentPlayer().getFieldCardsInGame()) minion.setCanAttack(true);
        updateChanges();
        if (switchTimes%2==0) turnDraw();
        clientHandler.sendResponse("EndTurn",new EndTurn());
    }

    @Override
    protected void checkGameFinished() {
        if (friendlyPlayer.getChoosedHero().getHP()<=0){
            clientHandler.sendResponse("Message",new Message("you Lost the game :("));
            playerController.makePlayerLoser(clientHandler.getMainPlayer());
            clientHandler.sendResponse("GoToPanel",new GoToPanel("mainMenu"));
        }
        if (enemyPlayer.getChoosedHero().getHP()<=0){
            clientHandler.sendResponse("Message",new Message("YOU WON THE GAME!!"));
            playerController.makePlayerWinner(clientHandler.getMainPlayer());
            clientHandler.sendResponse("GoToPanel",new GoToPanel("mainMenu"));
        }
    }

    @Override
    public boolean getAllowance(Side side) {
        return side == Side.FRIENDLY && switchTimes%2==0;
    }

    @Override
    public void exitPlay(boolean youExited) {
        if(!gameFinished){
            if (youExited) playerController.makePlayerLoser(clientHandler.getMainPlayer());
            else playerController.makePlayerWinner(clientHandler.getMainPlayer());
            clientHandler.setEnemy(null);
            gameFinished = true;
        }
    }

    @Override
    public boolean getCardBackVisible(Side side) {
        return side == Side.ENEMY;
    }

    public void initializeEnemy(Player enemyPlayer) {
        this.enemyPlayer = enemyPlayer;
//        resetPlayer(enemyPlayer);
//        initialDeckToHand(enemyPlayer);
    }


    //    @Override
//    public Player getEnemyPlayer() {
//       return clientHandler.getServer().getOpponent(clientHandler).getMainPlayer();
//    }
}
