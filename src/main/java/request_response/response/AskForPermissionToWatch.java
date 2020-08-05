package request_response.response;

import client.Client;
import request_response.request.WatchAgreement;

import javax.swing.*;

public class AskForPermissionToWatch extends Response {
    private String viewer;
    public AskForPermissionToWatch(String viewer) {
        this.viewer = viewer;
    }

    @Override
    public void execute(Client client) {
        int ans = JOptionPane.showConfirmDialog(null,"do you let "+ viewer +" watch this game ?","asking for your permission",JOptionPane.YES_NO_OPTION);
        if (ans==JOptionPane.YES_OPTION) client.sendRequest("WatchAgreement",new WatchAgreement(viewer));
    }
}
