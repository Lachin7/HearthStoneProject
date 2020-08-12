package request_response.request;

import lombok.SneakyThrows;
import request_response.response.Message;
import server.ClientHandler;

public class ShowEvents extends Request {
    @SneakyThrows
    @Override
    public void execute(ClientHandler clientHandler) {
            clientHandler.sendResponse("Message", new Message(clientHandler.getBoardController().getEvents()));
    }
}
