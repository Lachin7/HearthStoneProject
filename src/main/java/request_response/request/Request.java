package request_response.request;

import server.ClientHandler;

public abstract class Request {
   public abstract void execute(ClientHandler clientHandler);
}
