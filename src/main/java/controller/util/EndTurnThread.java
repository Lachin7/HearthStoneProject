package controller.util;

import gui.panels.PlayPanel;

import java.awt.*;

public class EndTurnThread extends Thread{

    private long startTime;
    private boolean isRunning;
    private PlayPanel playPanel;
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
                playPanel.getTimeRemaining().setBackground(Color.RED);
            }
             if(System.currentTimeMillis() - startTime >= 60000) {
                 playPanel.getTimeRemaining().setText("");
                 playPanel.endTurn();
                 isRunning = false;
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
