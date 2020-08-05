package request_response.response;

import client.Client;

public class SignIn extends Response{
    private String message;
    public SignIn(String message){
        this.message = message;
    }
    @Override
    public void execute(Client client) {
        client.signIn(message);
    }
}
