package request_response.response;

import client.ClientGui;

public class DeleteViewer extends Response {
    private String name;
    public DeleteViewer(String name) {
        this.name = name;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getPlayPanel().getWatchListPanel().deleteMember(name);
    }
}
