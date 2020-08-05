package request_response.response;

import client.Client;

import javax.swing.*;

public class Message extends Response{
    private String message;
    public Message(String message){
        this.message = message;
    }
    @Override
    public void execute(Client client) {
        JOptionPane.showMessageDialog(null,message);
    }
}
