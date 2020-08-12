package request_response.request;

import server.ClientHandler;
import server.controller.Board.modes.Online;

public class ExitPlay extends Request {
    @Override
    public void execute(ClientHandler clientHandler) {

       if (clientHandler.getBoardController() instanceof Online) {
         if (clientHandler.getServer().getClients(clientHandler)!=null&&clientHandler.getEnemy()!=null){
             clientHandler.getEnemy().getBoardController().exitPlay(false);
             clientHandler.getEnemy().sendResponse("ExitPlay", new request_response.response.ExitPlay("game is lost due to other players request! and you WON!"));
             clientHandler.getBoardController().exitPlay(true);
            if (clientHandler.getServer().getRunningGames().containsKey(clientHandler.getServer().getClients(clientHandler))){
                clientHandler.getServer().getWatchers(clientHandler).forEach((clientHandler1, integer) -> clientHandler1.sendResponse("ExitGame",new request_response.response.ExitPlay("game is lost due to one of the players request ")));
                clientHandler.getServer().getRunningGames().remove(clientHandler.getServer().getClients(clientHandler));
            }
         }
//         else clientHandler.getServer().getWaitingList().remove(clientHandler);
       }
       else clientHandler.getServer().getDataBase().save(clientHandler.getMainPlayer());

    }
}
