package request_response.response;

import client.ClientGui;

public class SignUp extends Response{
    private String message;
    public SignUp(String message){
        this.message = message;
    }
    @Override
    public void execute(ClientGui clientGui) {
        clientGui.signUp(message);
    }
}
