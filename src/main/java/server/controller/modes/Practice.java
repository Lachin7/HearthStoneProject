package server.controller.modes;

import lombok.Getter;
import lombok.Setter;
import request_response.response.EndTurn;
import request_response.response.GoToPanel;
import request_response.response.Message;
import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.board.Side;
import server.ClientHandler;
import server.controller.BoardController;

public class Practice extends BoardController {


    @Getter
    @Setter
    private int launchReq = 0;
    public Practice(ClientHandler clientHandler) {
        super(clientHandler);
        defineThread();
    }

    @Override
    protected void setPlayers() {
        if(isANewGame()) {
            chooseMainAsFriend();
             //todo you can go and choose server.controller.getMainPlayer ... or create them here
            chooseMainAsEnemy();
            if (enemyPlayer!=null)initialDeckToHand(enemyPlayer);
        }
    }

    @Override
    public void endTurn() {
        restartThread();
        for (Card card : getCurrentPlayer().getFieldCardsInGame()) card.accept(endTurnCardVisitor, null, this);
        getCurrentPlayer().getInfoPassive().accept(endTurnPassiveVisitor, getCurrentPlayer(), this);
        changeManaForTurn(friendlyPlayer);
        changeManaForTurn(enemyPlayer);
        switchTimes++;
        for (Minion minion : getCurrentPlayer().getFieldCardsInGame()) minion.setCanAttack(true);
        turnDraw();
        clientHandler.sendResponse("EndTurn",new EndTurn());
    }

    @Override
    protected void checkGameFinished() {
        if(friendlyPlayer.getChoosedHero().getHP()<=0){
            clientHandler.sendResponse("Message",new Message("friendly player lost and the enemy won"));
            clientHandler.sendResponse("GoToPanel",new GoToPanel("mainMenu"));
        }
        if(enemyPlayer.getChoosedHero().getHP()<=0){
            clientHandler.sendResponse("Message",new Message("friendly player won and the enemy lost"));
            clientHandler.sendResponse("GoToPanel",new GoToPanel("mainMenu"));
        }
    }

    @Override
    public boolean getAllowance(Side side) {
        return (side == Side.FRIENDLY && switchTimes %2== 0) || side == Side.ENEMY && switchTimes%2 == 1;
    }

    @Override
    public void exitPlay(boolean youExited) {
        gameFinished = true;
    }

    @Override
    public boolean getCardBackVisible(Side side) {
        return (side==Side.ENEMY && switchTimes%2==0)||(side==Side.FRIENDLY && switchTimes%2==1);
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
