package request_response.response;

import client.ClientGui;

public class Switch extends Response {
    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getPlayPanel().addSwitch();
    }
}
