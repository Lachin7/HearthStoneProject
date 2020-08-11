package request_response.response;

import client.ClientGui;

public class AddViewer extends Response{
    private final String name;

    public AddViewer(String name) {
        this.name = name;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getPlayPanel().getWatchListPanel().addMember(name);
    }
}
