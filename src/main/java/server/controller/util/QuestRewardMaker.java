package server.controller.util;

import server.controller.Board.BoardController;
import server.models.Cards.Card;
import server.models.Player;

public class QuestRewardMaker {

    private final int manaToSpend;
    private int spendedMana ;
    private boolean isRunning = true;
    private final BoardController boardController;
    private final Card.type type;
    private final String cardToSummon;
    private final String name;
    private final Player player;

    public QuestRewardMaker(int manaToSpend, Card.type type, String cardToSummon, BoardController boardController, String name ) {
        this.manaToSpend = manaToSpend;
        this.boardController = boardController;
        this.player = boardController.getCurrentPlayer();
        this.type= type;
        this.cardToSummon = cardToSummon;
        this.name = name;
    }

    public int getPercent(){
        if(spendedMana==0)return 0;
        if(spendedMana==manaToSpend)return 100;
        return (spendedMana*100/manaToSpend);
    }

    public void cardIsDrawn(int cost, Card.type type){
        if(this.type==type) spendedMana+=cost;
        if(spendedMana>=manaToSpend && isRunning){
            if(player.getID()==boardController.getCurrentPlayer().getID()){
                boardController.summon(cardToSummon,1);
                boardController.getQuestRewardMakers().remove(this);
                isRunning = false;
            }
        }
    }

    public String getName() {
        return name;
    }

}
