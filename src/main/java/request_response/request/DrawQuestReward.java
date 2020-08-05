package request_response.request;

import server.ClientHandler;

import java.util.HashMap;

public class DrawQuestReward extends Request {

    @Override
    public void execute(ClientHandler clientHandler) {
        HashMap<String , Integer> f = new HashMap<>(),e = new HashMap<>();
        clientHandler.getBoardController().getFriendlyQuestRewards().forEach(questRewardMaker -> f.put(questRewardMaker.getName(),questRewardMaker.getPercent()));
        clientHandler.getBoardController().getEnemyQuestRewards().forEach(questRewardMaker -> e.put(questRewardMaker.getName(),questRewardMaker.getPercent()));
        clientHandler.sendResponse("DrawQuestReward",new request_response.response.DrawQuestReward(f,e));
    }
}
