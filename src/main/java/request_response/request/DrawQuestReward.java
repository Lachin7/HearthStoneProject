package request_response.request;

import server.ClientHandler;
import server.models.board.Side;

import java.util.HashMap;

public class DrawQuestReward extends Request {

    private Side side;
    public DrawQuestReward(Side side) {
        this.side =side;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        HashMap<String , Integer> s = new HashMap<>();
        if (side == Side.FRIENDLY) clientHandler.getBoardController().getFriendlyQuestRewards().forEach(questRewardMaker -> s.put(questRewardMaker.getName(),questRewardMaker.getPercent()));
        else clientHandler.getBoardController().getEnemyQuestRewards().forEach(questRewardMaker -> s.put(questRewardMaker.getName(),questRewardMaker.getPercent()));
        clientHandler.sendResponse("DrawQuestReward",new request_response.response.DrawQuestReward(s));
    }
}
