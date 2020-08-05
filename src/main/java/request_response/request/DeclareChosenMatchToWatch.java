package request_response.request;

import server.ClientHandler;

public class DeclareChosenMatchToWatch extends Request {
    private String key, value;
    public DeclareChosenMatchToWatch(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.getServer().declareChosenMatchToWatch(key,value,clientHandler);
    }
}
