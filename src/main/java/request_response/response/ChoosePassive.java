package request_response.response;

import client.ClientGui;

public class ChoosePassive extends Response{
    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getPrePlayPanel().choosePassive();
    }
}
