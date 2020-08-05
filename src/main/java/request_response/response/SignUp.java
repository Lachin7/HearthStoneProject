package request_response.response;

import client.Client;

public class SignUp extends Response{
    private String message;
    public SignUp(String message){
        this.message = message;
    }
    @Override
    public void execute(Client client) {
        client.signUp(message);
    }
}
