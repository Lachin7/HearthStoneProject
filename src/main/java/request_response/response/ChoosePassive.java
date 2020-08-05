package request_response.response;

import client.Client;

public class ChoosePassive extends Response{
    @Override
    public void execute(Client client) {
        client.getPrePlayPanel().choosePassive();
    }
}
