package request_response.response;

import client.ClientGui;

import javax.swing.*;

public class ExitPlay extends Response{

    private String message;
    public ExitPlay(String message){
        this.message = message;
    }
    @Override
    public void execute(ClientGui clientGui) {
        JOptionPane.showMessageDialog(null,message);
        clientGui.goToPanel("mainMenu");
    }
}
