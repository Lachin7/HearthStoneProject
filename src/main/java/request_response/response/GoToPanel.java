package request_response.response;

import client.ClientGui;

public class GoToPanel extends Response{
    private String panel;

    public GoToPanel(String panel) {
        this.panel = panel;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.goToPanel(panel);
    }
}
