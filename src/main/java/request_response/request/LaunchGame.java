package request_response.request;

import request_response.response.GoToPanel;
import server.ClientHandler;
import server.controller.modes.AI;
import server.controller.modes.Practice;

public class LaunchGame extends Request {
    private boolean launch ;

    public LaunchGame(boolean launch) {
        this.launch = launch;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        if(launch || clientHandler.getBoardController() instanceof AI ) clientHandler.sendResponse("GoToPanel",new GoToPanel("play"));
        else if (clientHandler.getBoardController() instanceof Practice){
            ((Practice) clientHandler.getBoardController()).setLaunchReq(((Practice) clientHandler.getBoardController()).getLaunchReq()+1);
           if ( ((Practice) clientHandler.getBoardController()).getLaunchReq()==2) clientHandler.sendResponse("GoToPanel",new GoToPanel("play"));
        }
        else clientHandler.sendLaunchRequest();
    }
}
