package client.actionController;

import client.ClientGui;
import request_response.request.SignIn;
import request_response.request.SignUp;

public class InitialSetUp extends ActionController{
    public InitialSetUp(ClientGui clientGui) {
        super(clientGui);
    }
    public void connectToServer(int port, String host){
        clientGui.setUpClient(host,port);
    }

    public void signIn(String name, String pass) {
        clientGui.sendRequest("SignIn", new SignIn(name,pass));
    }

    public void signUp(String name, String pass) {
        clientGui.sendRequest("SignUp",new SignUp(name,pass));
    }
}
