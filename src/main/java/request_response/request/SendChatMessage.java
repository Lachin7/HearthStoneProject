package request_response.request;

import request_response.response.ShowChatMessage;
import server.ClientHandler;
import server.controller.modes.WatchGame;

public class SendChatMessage extends Request {
    private String text;
    public SendChatMessage(String text) {
        this.text = text;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
      if (!(clientHandler.getBoardController() instanceof WatchGame)) clientHandler.getServer().sendMessage(clientHandler,text);
//        clientHandler.getEnemy().sendResponse("ShowChatMessage",new ShowChatMessage(clientHandler.getMainPlayer().getName(),text));
    }
}
