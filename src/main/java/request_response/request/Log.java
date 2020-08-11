package request_response.request;

import server.ClientHandler;

import java.util.logging.Level;

public class Log extends Request{
    private String message;
    public Log(String message){
        this.message =message;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.log(message);
    }
}
