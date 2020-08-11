package server.models.util;

import client.gui.panels.PlayPanel;
import lombok.Setter;
import server.controller.BoardController;
import server.controller.modes.GoldenTime;

import java.awt.*;

public class GameThread {
    private  long startTime;
    private volatile boolean isRunning = true, paused=false;
    private final Object pauseLock = new Object();
    private Thread thread, golden;
    private int warningLimit, timePerTurn, goldenTime, timeSpend;
    private BoardController boardController;
    public GameThread(BoardController boardController,int timePerTurn, int warningLimit){
        this.boardController = boardController;
        this.timePerTurn = timePerTurn;
        this.warningLimit = warningLimit;
        startTime = System.currentTimeMillis();
        thread = new Thread(this::run);
    }
    public GameThread(BoardController boardController,int goldenTime){
        this.boardController = boardController;
        this.goldenTime =goldenTime;
        startTime = System.currentTimeMillis();
        golden = new Thread(this::runGold);
    }

    public void run() {
        while (isRunning){
            if(System.currentTimeMillis() - startTime >= warningLimit){
                boardController.setTime((System.currentTimeMillis() - startTime)/1000+"");
//                boardController.setTime("<html> <font color = rgb(255,0,0)>"+(System.currentTimeMillis() - startTime)/1000+"</font><html>");

            }
            if(System.currentTimeMillis() - startTime >= timePerTurn){
                boardController.endTurn();
                startTime = System.currentTimeMillis();
            }
        }
    }

    public void runGold() {
        while (isRunning) {
            synchronized (pauseLock) {
                if (!isRunning) break;
                if (paused) {
                    try {
                        synchronized (pauseLock) {
                            pauseLock.wait(); // will cause this Thread to block until
                            // another thread calls pauseLock.notifyAll()
                            // Note that calling wait() will
                            // relinquish the synchronized lock that this
                            // thread holds on pauseLock so another thread
                            // can acquire the lock to call notifyAll()
                            // (link with explanation below this code)
                        }
                    } catch (InterruptedException e) {
                        break;
                    }
                    if (!isRunning) break;
                }
            }
            if(timeSpend >=goldenTime)((GoldenTime)boardController).timesUp();

            // Your code here
        }
        while (isRunning){
            if (boardController.getSwitchTimes()%2==0 && System.currentTimeMillis()-startTime>=goldenTime){
//                boardController.TimeIsUp();
                boardController.setTime((goldenTime-startTime)/1000+"");
            }
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        if(!running)boardController.setTime("");
        isRunning = running;
    }

    public void restart(){
        startTime = System.currentTimeMillis();
        boardController.setTime("");
    }

    public void start() {
        if (thread!=null) thread.start();
        else if (golden!=null)golden.start();
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }
}
