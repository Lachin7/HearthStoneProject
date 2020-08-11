package server.controller.modes;

import request_response.response.GoToPanel;
import request_response.response.Message;
import server.ClientHandler;
import server.controller.BoardController;
import server.models.util.GameThread;

public class GoldenTime extends Online {
    public GoldenTime(ClientHandler clientHandler) {
        super(clientHandler);
    }
    public void timesUp(){
        clientHandler.sendResponse("Message",new Message("you Lost the game :("));
        playerController.makePlayerLoser(clientHandler.getMainPlayer());
        clientHandler.sendResponse("GoToPanel",new GoToPanel("mainMenu"));
        clientHandler.getEnemy().getBoardController().getGameThread().setRunning(false);
        clientHandler.getEnemy().sendResponse("Message",new Message("YOU WON THE GAME!!"));
        playerController.makePlayerWinner(clientHandler.getEnemy().getMainPlayer());
        clientHandler.getEnemy().sendResponse("GoToPanel",new GoToPanel("mainMenu"));
    }

//    @Override
//    protected void defineThread() {
//        gameThread = new GameThread(this,60000);
//        gameThread.start();
//    }

//    @Override
//    protected void restartThread() {
//
//    }
}
