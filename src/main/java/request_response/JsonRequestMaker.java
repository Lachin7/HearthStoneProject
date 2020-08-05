package request_response;

import com.google.gson.Gson;
import request_response.request.*;
import request_response.request.Request;

import java.lang.reflect.Type;
import java.util.HashMap;

public class JsonRequestMaker {

    private HashMap<String, Class> requests ;
    private Gson gson;

    public JsonRequestMaker(){
        requests = new HashMap<>();
        gson = new Gson();
        setUpRequestsMap();
    }

    private void setUpRequestsMap() {
        requests.put("AddCardToDeckCollection", AddCardToDeckCollection.class);
        requests.put("Attack", Attack.class);
        requests.put("BuyCard", BuyCard.class);
        requests.put("CheckNecessaryItems", CheckNecessaryItems.class);
        requests.put("ChooseGameSetUps", ChooseGameSetUps.class);
        requests.put("CollectionCardsShowCase", CollectionCardsShowCase.class);
        requests.put("ConnectToServer", ConnectToServer.class);
        requests.put("DeclareChosenMatchToWatch", DeclareChosenMatchToWatch.class);
        requests.put("DeclareFirstCards", DeclareFirstCards.class);
        requests.put("DeclareGameMode", DeclareGameMode.class);
        requests.put("DeclarePassive", DeclarePassive.class);
        requests.put("DrawDeckInfo", DrawDeckInfo.class);
        requests.put("DrawInformationOnCard", DrawInformationOnCard.class);
        requests.put("DrawPlayChanges", DrawPlayChanges.class);
        requests.put("DrawQuestReward", DrawQuestReward.class);
        requests.put("EditDeck", EditDeck.class);
        requests.put("EndTurn", EndTurn.class);
        requests.put("InitialMoveTargeting", InitialMoveTargeting.class);
        requests.put("InitialPlay", InitialPlay.class);
        requests.put("Log", Log.class);
        requests.put("PlayHeroPower", PlayHeroPower.class);
        requests.put("PlayTarget", PlayTarget.class);
        requests.put("PlayTargetedPower", PlayTargetedPower.class);
        requests.put("SellCard", SellCard.class);
        requests.put("SendChatMessage", SendChatMessage.class);
        requests.put("SetBattleGroundPic", SetBattleGroundPic.class);
        requests.put("SetBuyShowCase", SetBuyShowCase.class);
        requests.put("SetDeckAsCurrent", SetDeckAsCurrent.class);
        requests.put("SetDiscovery", SetDiscovery.class);
        requests.put("SetHeroes", SetHeroes.class);
        requests.put("SetSelectedCardInShop", SetSelectedCardInShop.class);
        requests.put("SetSellShowCase", SetSellShowCase.class);
        requests.put("SetUpHeroPowers", SetUpHeroPowers.class);
        requests.put("ShowCardsInStatus", ShowCardsInStatus.class);
        requests.put("ShowDecksInStatus", ShowDecksInStatus.class);
        requests.put("ShowEvents", ShowEvents.class);
        requests.put("ShowMyRank", ShowMyRank.class);
        requests.put("ShowTopTen", ShowTopTen.class);
        requests.put("ShowWatchableGames", ShowWatchableGames.class);
        requests.put("SignIn", AddCardToDeckCollection.class);
        requests.put("SignUp", SignUp.class);
        requests.put("UpdateDeckShowCase", UpdateDeckShowCase.class);
        requests.put("UpdateGameMana", UpdateGameMana.class);
        requests.put("UpdateHandCards", UpdateHandCards.class);
        requests.put("WatchGames", WatchGames.class);
    }

    public Request makeRequest(String requestName, String json) {
        System.out.println("req : "+requestName);
        System.out.println("json : "+json);
        return (Request) gson.fromJson(json, requests.get(requestName));
    }
}
