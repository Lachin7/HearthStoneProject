package request_response.response;

import client.ClientGui;

public class SignIn extends Response{
    private String message;
    public SignIn(String message){
        this.message = message;
    }
    @Override
    public void execute(ClientGui clientGui) {
        clientGui.signIn(message);
    }
}
