package request_response.request;

import server.ClientHandler;

import javax.swing.*;

public class DeleteAccount extends Request {
    private String pass;
    public DeleteAccount(String pass) {
        this.pass = pass;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
       String message =  clientHandler.getPlayerController().deleteThePlayer(pass);
       if (message.contains("incorrect")) JOptionPane.showMessageDialog(null,message);
    }
}
