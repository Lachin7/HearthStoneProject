package request_response.request;

import request_response.response.Message;
import server.ClientHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ShowEvents extends Request {
    @Override
    public void execute(ClientHandler clientHandler) {
        try {
            clientHandler.sendResponse("Message", new Message(Files.readString(clientHandler.getBoardController().getEventsLog().toPath(), StandardCharsets.US_ASCII)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
