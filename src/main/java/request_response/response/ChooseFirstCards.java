package request_response.response;

import client.Client;
import models.Cards.Card;

import java.util.ArrayList;
import java.util.HashMap;

public class ChooseFirstCards extends Response{
    private HashMap<Long,String> handsCards;
    private boolean chooseForEnemy;
    public ChooseFirstCards(HashMap<Long,String> handsCards, boolean chooseForEnemy) {
        this.handsCards = handsCards;
        this.chooseForEnemy = chooseForEnemy;
    }

    @Override
    public void execute(Client client) {
        client.getPrePlayPanel().chooseFirstCards(handsCards,chooseForEnemy);
    }
}
