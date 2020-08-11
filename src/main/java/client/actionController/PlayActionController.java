package client.actionController;

import client.ClientGui;
import client.gui.myComponents.MyCardButton;
import server.models.board.InfoPassive;
import server.models.board.Side;
import request_response.request.*;

import java.util.ArrayList;

public class PlayActionController extends ActionController{
    public PlayActionController(ClientGui clientGui) {
        super(clientGui);
    }
    public void declareGameMode(String mode){
        clientGui.sendRequest("DeclareGameMode",new DeclareGameMode(mode));
    }

    public void declarePassive(InfoPassive passive) {
        clientGui.sendRequest("DeclarePassive", new DeclarePassive(passive));
    }

//    public void chooseFirstCards(boolean chooseForEnemy) {
//        client.sendRequest("ChooseFirstCards",new ChooseFirstCards(chooseForEnemy));
//    }

    public void declareFirstCardChoices(ArrayList<Long> replacedCard) {
        clientGui.sendRequest("DeclareFirstCards",new DeclareFirstCards(replacedCard));
    }

    public void drawPlayChanges() {
        clientGui.sendRequest("DrawPlayChanges",new DrawPlayChanges());
    }

    public void updateHandCards(Side side) {
        clientGui.sendRequest("UpdateHandCards",new UpdateHandCards(side));
    }

    public void initialPlay(long id) {
        clientGui.sendRequest("InitialPlay",new InitialPlay(id));
    }

    public void updateFieldCards(Side side, ArrayList<MyCardButton> fieldComponents) {
        ArrayList<Long> ids = new ArrayList<>();
        for (MyCardButton cardButton : fieldComponents)ids.add(cardButton.getId());
        clientGui.sendRequest("UpdateFieldCards",new UpdateFieldCards(side,ids));
    }

    public void initialMoveTargeting(long id) {
        clientGui.sendRequest("InitialMoveTargeting",new InitialMoveTargeting(id));
    }

    public void playTarget(long attacker, long target) {
        clientGui.sendRequest("PlayTarget",new PlayTarget(attacker,target));
    }

    public void attack(long attacker, long target) {
        clientGui.sendRequest("Attack",new Attack(attacker,target));
    }

    public void endTurn() {
        clientGui.sendRequest("EndTurn",new EndTurn());
    }

    public void setDiscovery(String name) {
        clientGui.sendRequest("SetDiscovery",new SetDiscovery(name));
    }

    public void showQuestReward(Side side) {
        clientGui.sendRequest("DrawQuestReward",new DrawQuestReward(side));
    }

    public void updateMana() {
        clientGui.sendRequest("UpdateGameMana",new UpdateGameMana());
    }

    public void showEvents() {
        clientGui.sendRequest("ShowEvents",new ShowEvents() );
    }

    public void launchGame() {
        clientGui.sendRequest("LaunchGame",new LaunchGame(false));
//        clientGui.goToPanel("play");
    }

    public void playHeroPower() {
        clientGui.sendRequest("PlayHeroPower",new PlayHeroPower());
    }

    public void playTargetedPower(long id) {
        clientGui.sendRequest("PlayTargetedPower",new PlayTargetedPower(id));
    }

    public void declareChosenMatchToWatch(String key, String value) {
        clientGui.sendRequest("DeclareChosenMatchToWatch",new DeclareChosenMatchToWatch(key,value));
    }

    public void showWatchableGames() {
        clientGui.sendRequest("ShowWatchableGames",new ShowWatchableGames());
    }

    public void sendChatMessage(String text) {
        clientGui.sendRequest("SendChatMessage",new SendChatMessage(text));
    }


    public void setUpPlayForPlayer() {
        clientGui.sendRequest("SetUpPlayForPlayer",new SetUpPlayForPlayer());
    }

    public void setUpHeroPowers() {
        clientGui.sendRequest("SetUpHeroPowers",new SetUpHeroPowers());
    }

    public void dismissViewer(String name) {
        clientGui.sendRequest("DismissViewer",new DismissViewer(name));
    }

    @Override
    public void back() {
        clientGui.sendRequest("ExitPlay",new ExitPlay());
        super.back();
    }

    @Override
    public void exit() {
        clientGui.sendRequest("ExitPlay",new ExitPlay());
        super.exit();
    }
}
