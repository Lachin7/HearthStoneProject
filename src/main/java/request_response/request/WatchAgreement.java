package request_response.request;

import server.ClientHandler;

public class WatchAgreement extends Request {
    private String viewer;
    public WatchAgreement(String viewer) {
        this.viewer = viewer;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.getServer().watchAgreement(clientHandler,viewer);
    }
}
