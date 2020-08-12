package request_response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import request_response.response.*;

import java.lang.reflect.Type;
import java.util.HashMap;

public class JsonResponseMaker {
    private HashMap<String, Class> responses ;
    private Gson gson;

    public JsonResponseMaker(){
        responses = new HashMap<>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.enableComplexMapKeySerialization();
        gson = gsonBuilder.create();
        setResponsesMap();
    }

    private void setResponsesMap() {
        responses.put("AddViewer", AddViewer.class);
        responses.put("AskForPermissionToWatch", AskForPermissionToWatch.class);
        responses.put("ChooseFirstCards", ChooseFirstCards.class);
        responses.put("ChoosePassive", ChoosePassive.class);
        responses.put("CollectionCardsShowCase", CollectionCardsShowCase.class);
        responses.put("DeleteViewer", DeleteViewer.class);
        responses.put("DrawDeckInfo", DrawDeckInfo.class);
        responses.put("DrawInformationOnCard", DrawInformationOnCard.class);
        responses.put("DrawPlayChanges", DrawPlayChanges.class);
        responses.put("DrawQuestReward", DrawQuestReward.class);
        responses.put("EndTurn", EndTurn.class);
        responses.put("ExitPlay", ExitPlay.class);
        responses.put("GoToPanel", GoToPanel.class);
        responses.put("InitialMoveTargeting", InitialMoveTargeting.class);
        responses.put("Message", Message.class);
        responses.put("SetBattleGroundPic", SetBattleGroundPic.class);
        responses.put("SetBuyShowCase", SetBuyShowCase.class);
        responses.put("SetDiscoverPanel", SetDiscoverPanel.class);
        responses.put("SetHeroes", SetHeroes.class);
        responses.put("SetSelectedCardInShop", SetSelectedCardInShop.class);
        responses.put("SetSellShowCase", SetSellShowCase.class);
        responses.put("SetUpChatAndWatch", SetUpChatAndWatch.class);
        responses.put("SetUpHeroPowers", SetUpHeroPowers.class);
        responses.put("ShowCardsInStatus", ShowCardsInStatus.class);
        responses.put("ShowChatMessage", ShowChatMessage.class);
        responses.put("ShowDecksInStatus", ShowDecksInStatus.class);
        responses.put("ShowMyRank", ShowMyRank.class);
        responses.put("ShowTopTen", ShowTopTen.class);
        responses.put("ShowWatchableGames", ShowWatchableGames.class);
        responses.put("SignIn", SignIn.class);
        responses.put("SignUp", SignUp.class);
        responses.put("Switch", Switch.class);
        responses.put("UpdateDeckCollection", UpdateDeckCollection.class);
        responses.put("UpdateFieldCards", UpdateFieldCards.class);
        responses.put("UpdateGameMana", UpdateGameMana.class);
        responses.put("UpdateHandCards", UpdateHandCards.class);

    }

    public Response makeResponse(String responseName, String json) {
        return gson.fromJson(json, (Type) responses.get(responseName));
    }
}
