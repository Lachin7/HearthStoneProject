package server.models.util;

import server.controller.Board.BoardController;
import server.controller.Board.modes.GoldenTime;

public class GameThread {
    private  long startTime;
    private volatile boolean isRunning = true, paused=false;
    private final Object pauseLock = new Object();
    private Thread thread, golden;
    private int warningLimit, timePerTurn, goldenTime, timeSpend=0;
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
            if(System.currentTimeMillis() - startTime >= warningLimit) boardController.setTime((System.currentTimeMillis() - startTime)/1000+"");
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
                            pauseLock.wait();
                        }
                    } catch (InterruptedException e) {
                        break;
                    }
                    if (!isRunning) break;
                }
            }
            boardController.setTime("t"+(System.currentTimeMillis()-startTime+timeSpend)/1000);
            if(System.currentTimeMillis()-startTime+timeSpend >=goldenTime)((GoldenTime)boardController).timesUp();
        }
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
        timeSpend += (System.currentTimeMillis()-startTime);
        paused = true;
    }

    public void resume() {
        startTime =System.currentTimeMillis();
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }
}
