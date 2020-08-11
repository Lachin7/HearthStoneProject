package request_response.request;

import request_response.response.DeleteViewer;
import request_response.response.GoToPanel;
import server.ClientHandler;

public class DismissViewer extends Request {
    private String name;

    public DismissViewer(String name) {
        this.name = name;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        for (ClientHandler clientHandler1 : clientHandler.getServer().getWatchers(clientHandler).keySet()) {
            if (clientHandler1.getMainPlayer().getName().equals(name))
                clientHandler1.sendResponse("GoToPanel", new GoToPanel("mainMenu"));
            break;
        }
        clientHandler.sendResponse("DeleteViewer",new DeleteViewer(name));
        clientHandler.getEnemy().sendResponse("DeleteViewer",new DeleteViewer(name));
    }
}
