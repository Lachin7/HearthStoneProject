package controller.threads;

import controller.BoardController;
import models.Cards.Card;

public class QuestRewardMaker {

    private int manaToSpend, spendedMana;
    private boolean isRunning = true;
    private BoardController boardController;
    private Card.type type;
    private String cardToSummon, name;

    public QuestRewardMaker(int manaToSpend, Card.type type, String cardToSummon, BoardController boardController,String name) {
        this.manaToSpend = manaToSpend;
        this.boardController = boardController;
        this.type= type;
        this.cardToSummon = cardToSummon;
        this.name = name;
    }

    public int getPercent(){
        if(spendedMana==0)return 0;
        return (manaToSpend/spendedMana)*100;
    }

    public void cardIsDrawn(int cost, Card.type type){
        if(this.type==type) spendedMana+=cost;
        if(spendedMana>=manaToSpend){
            boardController.summon(cardToSummon,1);
            isRunning = false;
        }
    }

    public String getName() {
        return name;
    }

    //    @Override
//    public void run() {
//        while (isRunning){
//            boardController.set
//
//        }
//    }
}
