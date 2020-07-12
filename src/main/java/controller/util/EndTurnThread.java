package controller.util;

import gui.panels.PlayPanel;

import java.awt.*;

public class EndTurnThread extends Thread{

    private final long startTime;
    private boolean isRunning;
    private final PlayPanel playPanel;
    public EndTurnThread(PlayPanel playPanel){
        this.playPanel = playPanel;
        startTime = System.currentTimeMillis();
        isRunning = true;
    }
    @Override
    public void run() {
        while (isRunning){
            if(System.currentTimeMillis() - startTime >= 40000){
                playPanel.getTimeRemaining().setText((System.currentTimeMillis() - startTime)/1000+"");
                playPanel.getTimeRemaining().setFont(new Font("Ariel",Font.BOLD,50));
            }
             if(System.currentTimeMillis() - startTime >= 60000) {
                 isRunning = false;
                 playPanel.getTimeRemaining().setText("");
                 playPanel.endTurn();
             }
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        if(!running)playPanel.getTimeRemaining().setText("");
        isRunning = running;
    }
}
