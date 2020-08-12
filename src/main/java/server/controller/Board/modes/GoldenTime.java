package server.controller.Board.modes;

import request_response.response.GoToPanel;
import request_response.response.Message;
import server.ClientHandler;
import server.models.util.GameThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

    @Override
    public void defineThread() {
        gameThread = new GameThread(this,120000);
        gameThread.start();
        if (switchTimes%2==1)gameThread.pause();
        ScheduledExecutorService ex = Executors.newSingleThreadScheduledExecutor();
        ex.scheduleAtFixedRate(this::updateChanges,0,1, TimeUnit.SECONDS);
    }

    @Override
    protected void restartThread() {
        if(switchTimes%2==0)gameThread.pause();
        else gameThread.resume();
    }
}
