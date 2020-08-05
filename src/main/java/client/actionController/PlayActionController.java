package client.actionController;

import client.Client;
import gui.myComponents.MyCardButton;
import models.board.InfoPassive;
import models.board.Side;
import request_response.request.*;

import java.util.ArrayList;

public class PlayActionController extends ActionController{
    public PlayActionController(Client client) {
        super(client);
    }
    public void declareGameMode(String mode){
        client.sendRequest("DeclareGameMode",new DeclareGameMode(mode));
    }

    public void declarePassive(InfoPassive passive) {
        client.sendRequest("DeclarePassive", new DeclarePassive(passive));
    }

//    public void chooseFirstCards(boolean chooseForEnemy) {
//        client.sendRequest("ChooseFirstCards",new ChooseFirstCards(chooseForEnemy));
//    }

    public void declareFirstCardChoices(ArrayList<String> replacedCard) {
        client.sendRequest("DeclareFirstCards",new DeclareFirstCards(replacedCard));
    }

    public void checkNecessaryItems() {
        client.sendRequest("checkNecessaryItems",new CheckNecessaryItems());
    }

    public void requestWatchingGames() {
        client.sendRequest("WatchGames",new WatchGames());
    }

    public void setBattleGroundPic() {
        client.sendRequest("SetBattleGroundPic",new SetBattleGroundPic());
    }

    public void setHeroes() {
        client.sendRequest("SetHeroes",new SetHeroes());
    }

    public void drawPlayChanges() {
        client.sendRequest("DrawPlayChanges",new DrawPlayChanges());
    }

    public void updateHandCards(Side side) {
        client.sendRequest("UpdateHandCards",new UpdateHandCards(side));
    }

    public void initialPlay(long id) {
        client.sendRequest("InitialPlay",new InitialPlay(id));
    }

    public void updateFieldCards(Side side, ArrayList<MyCardButton> fieldComponents) {
        ArrayList<Long> ids = new ArrayList<>();
        for (MyCardButton cardButton : fieldComponents)ids.add(cardButton.getId());
        client.sendRequest("UpdateFieldCards",new UpdateFieldCards(side,ids));
    }

    public void initialMoveTargeting(long id) {
        client.sendRequest("InitialMoveTargeting",new InitialMoveTargeting(id));
    }

    public void playTarget(long attacker, long target) {
        client.sendRequest("Pla yTarget",new PlayTarget(attacker,target));
    }

    public void attack(long attacker, long target) {
        client.sendRequest("Attack",new Attack(attacker,target));
    }

    public void endTurn() {
        client.sendRequest("EndTurn",new EndTurn());
    }

    public void setDiscovery(String name) {
        client.sendRequest("SetDiscovery",new SetDiscovery(name));
    }

    public void DrawQuestReward() {
        client.sendRequest("DrawQuestReward",new DrawQuestReward());
    }

    public void updateMana() {
        client.sendRequest("UpdateGameMana",new UpdateGameMana());
    }

    public void showEvents() {
        client.sendRequest("ShowEvents",new ShowEvents() );
    }

    public void launchGame() {
        client.initializePlayPanel();
        client.goToPanel(client.getPlayPanel());
    }

    public void setUpHeroPowers() {
        client.sendRequest("SetUpHeroPowers",new SetUpHeroPowers());
    }

    public void playHeroPower() {
        client.sendRequest("PlayHeroPower",new PlayHeroPower());
    }

    public void playTargetedPower(long id) {
        client.sendRequest("PlayTargetedPower",new PlayTargetedPower(id));
    }

    public void declareChosenMatchToWatch(String key, String value) {
        client.sendRequest("DeclareChosenMatchToWatch",new DeclareChosenMatchToWatch(key,value));
    }

    public void showWatchableGames() {
        client.sendRequest("ShowWatchableGames",new ShowWatchableGames());
    }

    public void sendChatMessage(String text) {
        client.sendRequest("SendChatMessage",new SendChatMessage(text));
    }
}
