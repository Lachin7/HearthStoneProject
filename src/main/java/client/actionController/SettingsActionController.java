package client.actionController;

import client.ClientGui;
import request_response.request.ChangeSetting;
import request_response.request.DeleteAccount;

public class SettingsActionController extends ActionController{
    public SettingsActionController(ClientGui clientGui) {
        super(clientGui);
    }

    public void deleteAccount(String pass) {
        clientGui.sendRequest("DeleteAccount",new DeleteAccount(pass));
    }

    public void declareChoseCardBack(String iconFile) {
        clientGui.sendRequest("ChangeSetting",new ChangeSetting(iconFile,null));
    }

    public void declareChoseFieldImage(String iconFile) {
        clientGui.sendRequest("ChangeSetting",new ChangeSetting(null,iconFile));
    }
}
