package request_response.request;

import request_response.response.ShowChatMessage;
import server.ClientHandler;

public class SendChatMessage extends Request {
    private String text;
    public SendChatMessage(String text) {
        this.text = text;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.getEnemy().sendResponse("ShowChatMessage",new ShowChatMessage(clientHandler.getEnemy().getMainPlayer().getName(),text));
    }
}
