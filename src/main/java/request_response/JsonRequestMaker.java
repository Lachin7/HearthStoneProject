package request_response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import request_response.request.*;
import request_response.request.Request;

import java.lang.reflect.Type;
import java.util.HashMap;

public class JsonRequestMaker {

    private HashMap<String, Class> requests ;
    private Gson gson;

    public JsonRequestMaker(){
        requests = new HashMap<>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.enableComplexMapKeySerialization();
        gson = gsonBuilder.create();
        setUpRequestsMap();
    }

    private void setUpRequestsMap() {
        requests.put("AddCardToDeckCollection", AddCardToDeckCollection.class);
        requests.put("Attack", Attack.class);
        requests.put("BuyCard", BuyCard.class);
        requests.put("ChangeSetting", ChangeSetting.class);
        requests.put("ChooseGameSetUps", ChooseGameSetUps.class);
        requests.put("CollectionCardsShowCase", CollectionCardsShowCase.class);
        requests.put("DeclareChosenMatchToWatch", DeclareChosenMatchToWatch.class);
        requests.put("DeclareFirstCards", DeclareFirstCards.class);
        requests.put("DeclareGameMode", DeclareGameMode.class);
        requests.put("DeclarePassive", DeclarePassive.class);
        requests.put("DeleteAccount", DeleteAccount.class);
        requests.put("DismissViewer", DismissViewer.class);
        requests.put("DrawDeckInfo", DrawDeckInfo.class);
        requests.put("DrawInformationOnCard", DrawInformationOnCard.class);
        requests.put("DrawPlayChanges", DrawPlayChanges.class);
        requests.put("DrawQuestReward", DrawQuestReward.class);
        requests.put("EditDeck", EditDeck.class);
        requests.put("EndTurn", EndTurn.class);
        requests.put("ExitPlay", ExitPlay.class);
        requests.put("InitialMoveTargeting", InitialMoveTargeting.class);
        requests.put("InitialPlay", InitialPlay.class);
        requests.put("LaunchGame", LaunchGame.class);
        requests.put("Log", Log.class);
        requests.put("PlayHeroPower", PlayHeroPower.class);
        requests.put("PlayTarget", PlayTarget.class);
        requests.put("PlayTargetedPower", PlayTargetedPower.class);
        requests.put("SellCard", SellCard.class);
        requests.put("SendChatMessage", SendChatMessage.class);
        requests.put("SetBuyShowCase", SetBuyShowCase.class);
        requests.put("SetDeckAsCurrent", SetDeckAsCurrent.class);
        requests.put("SetDiscovery", SetDiscovery.class);
        requests.put("SetSelectedCardInShop", SetSelectedCardInShop.class);
        requests.put("SetSellShowCase", SetSellShowCase.class);
        requests.put("SetUpHeroPowers", SetUpHeroPowers.class);
        requests.put("SetUpPlayForPlayer", SetUpPlayForPlayer.class);
        requests.put("ShowCardsInStatus", ShowCardsInStatus.class);
        requests.put("ShowDecksInStatus", ShowDecksInStatus.class);
        requests.put("ShowEvents", ShowEvents.class);
        requests.put("ShowRanks", ShowRanks.class);
        requests.put("ShowWatchableGames", ShowWatchableGames.class);
        requests.put("SignIn", SignIn.class);
        requests.put("SignUp", SignUp.class);
        requests.put("UpdateDeckShowCase", UpdateDeckShowCase.class);
        requests.put("UpdateFieldCards", UpdateFieldCards.class);
        requests.put("UpdateGameMana", UpdateGameMana.class);
        requests.put("UpdateHandCards", UpdateHandCards.class);
        requests.put("WatchAgreement", WatchAgreement.class);
    }

    public Request makeRequest(String requestName, String json) {
//        System.out.println("req : "+requestName);
//        System.out.println("json : "+json);
        return gson.fromJson(json, (Type) requests.get(requestName));
    }
}
