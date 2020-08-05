package request_response.response;

import client.Client;

public class GoToPanel extends Response{
    private String panel;

    public GoToPanel(String panel) {
        this.panel = panel;
    }

    @Override
    public void execute(Client client) {
        if (panel.equalsIgnoreCase("mainMenu"))client.goToPanel(client.getMainMenu());
        else if (panel.equalsIgnoreCase("playPanel"))client.goToPanel(client.getPlayPanel());
        else if (panel.equalsIgnoreCase("prePlayPanel"))client.goToPanel(client.getPrePlayPanel());
        else if (panel.equalsIgnoreCase("collectionPanel"))client.goToPanel(client.getCollectionPanel());
        else if (panel.equalsIgnoreCase("statusPanel"))client.goToPanel(client.getStatusPanel());
        else if (panel.equalsIgnoreCase("settingsPanel"))client.goToPanel(client.getSettingsPanel());
        else if (panel.equalsIgnoreCase("shopPanel"))client.goToPanel(client.getShopPanel());
        else if (panel.equalsIgnoreCase("signInPanel"))client.goToPanel(client.getSignInPanel());
    }
}
