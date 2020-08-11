package request_response.request;

import request_response.response.Response;
import request_response.response.SetUpChatAndWatch;
import request_response.response.Switch;
import server.ClientHandler;
import server.controller.BoardController;
import server.controller.modes.AI;
import server.controller.modes.Online;
import server.controller.modes.Practice;

public class SetUpPlayForPlayer extends Request {
    @Override
    public void execute(ClientHandler clientHandler) {
        if (!(clientHandler.getBoardController() instanceof AI) && !(clientHandler.getBoardController() instanceof Practice) )
        clientHandler.sendResponse("SetUpChatAndWatch",new SetUpChatAndWatch());

        clientHandler.sendResponse("SetBattleGroundPic",new request_response.response.SetBattleGroundPic(clientHandler.getMainPlayer().getPlayBackGround()));
        clientHandler.sendResponse("SetHeroes",new request_response.response.SetHeroes(clientHandler.getBoardController().getFriendlyPlayer().getChoosedHero().toString(),clientHandler.getBoardController().getEnemyPlayer().getChoosedHero().toString()));

        if (clientHandler.getBoardController() instanceof Online && clientHandler.getBoardController().getSwitchTimes()%2==1)clientHandler.sendResponse("Switch",new Switch());

    }

}
