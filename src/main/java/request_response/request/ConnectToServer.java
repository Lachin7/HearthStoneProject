package request_response.request;

import server.ClientHandler;

public class ConnectToServer extends Request{

    private int port;
    private String host;
    public ConnectToServer(int port, String host){
        this.port = port;
        this.host = host;
    }
    @Override
    public void execute(ClientHandler clientHandler) {
//        clientHandler.connectToServer(port,host);
    }
}
