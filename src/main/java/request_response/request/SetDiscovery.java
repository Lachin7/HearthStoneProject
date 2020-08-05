package request_response.request;

import server.ClientHandler;

public class SetDiscovery extends Request {
    private String name ;

    public SetDiscovery(String name) {
        this.name = name;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.getBoardController().setDiscovery(name);
    }
}
