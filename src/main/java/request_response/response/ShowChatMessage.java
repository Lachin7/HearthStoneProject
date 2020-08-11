package request_response.response;

import client.ClientGui;

import java.awt.*;

public class ShowChatMessage extends Response {
    private String name, text;
    public ShowChatMessage(String name, String text) {
        this.name =name;
        this.text =text;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getPlayPanel().getChatPanel().newChatMessage(new Color(165, 10, 136),name,text);
    }
}
