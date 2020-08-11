package request_response.response;

import client.ClientGui;

import javax.swing.*;

public class Message extends Response{
    private String message;
    public Message(String message){
        this.message = message;
    }
    @Override
    public void execute(ClientGui clientGui) {
        JOptionPane.showMessageDialog(null,message);
    }
}
