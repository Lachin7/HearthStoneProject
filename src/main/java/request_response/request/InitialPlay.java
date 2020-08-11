package request_response.request;

import server.ClientHandler;

public class InitialPlay extends Request {
    private long id;
    public InitialPlay(long id) {
        this.id = id;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        clientHandler.getBoardController().initialPlay(clientHandler.getCardController().getCardWithId(id));
//        clientHandler.sendResponse("InitialPlay",new request_response.response.InitialPlay());
    }
}
