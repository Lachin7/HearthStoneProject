package client.actionController;

import client.Client;
import request_response.request.SignIn;
import request_response.request.SignUp;

public class InitialSetUp extends ActionController{
    public InitialSetUp(Client client) {
        super(client);
    }
    public void connectToServer(int port, String host){
        client.setUpClient(host,port);
    }

    public void signIn(String name, String pass) {
        client.sendRequest("SignIn", new SignIn(name,pass));
    }

    public void signUp(String name, String pass) {
        client.sendRequest("SignUp",new SignUp(name,pass));
    }
}
