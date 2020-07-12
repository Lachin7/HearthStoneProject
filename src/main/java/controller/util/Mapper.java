package controller.util;

import gui.GameFrame;
import models.Cards.Minion;

public class Mapper {
    private static final Mapper mapper = new Mapper();

    private Mapper() {
    }

    public static Mapper getInstance() {
        return mapper;
    }

    public void updateFields(){
        GameFrame.getInstance().getPlayPanel().updateBothFields();
    }

    public void updateHands(){
        GameFrame.getInstance().getPlayPanel().updateHands();
    }

    public void setDiscoverPanel(String card1, String card2, String card3){
        GameFrame.getInstance().getPlayPanel().setDiscoverPanel(card1,card2,card3);
    }

    public void addToPlayField(Minion card, int switchTimes) {
        GameFrame.getInstance().getPlayPanel().addToField(card,switchTimes);
    }

    public void endTurnPlay(){
        GameFrame.getInstance().getPlayPanel().endTurn();
    }
}
