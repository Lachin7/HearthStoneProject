package request_response.request;

import lombok.SneakyThrows;
import request_response.response.Message;
import server.ClientHandler;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ShowEvents extends Request {
    @SneakyThrows
    @Override
    public void execute(ClientHandler clientHandler) {
            clientHandler.sendResponse("Message", new Message(Files.readString(clientHandler.getBoardController().getEventsLog().toPath(), StandardCharsets.US_ASCII)));
    }
}
