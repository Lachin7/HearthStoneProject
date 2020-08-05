package client;

import gui.panels.PlayPanel;

public class ClientMain {
    public static void main(String[] args) {
        Client client = new Client();
        client.goToPanel(client.getConnectionPanel());
    }
}
