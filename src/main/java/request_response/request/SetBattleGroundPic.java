package request_response.request;

import server.ClientHandler;

public class SetBattleGroundPic extends Request {

    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.sendResponse("SetBattleGroundPic",new request_response.response.SetBattleGroundPic(clientHandler.getMainPlayer().getPlayBackGround()));
    }
}
