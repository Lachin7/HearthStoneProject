package client.actionController;

import client.ClientGui;
import request_response.request.DrawInformationOnCard;
import request_response.request.ExitPlay;
import request_response.response.GoToPanel;

public class ActionController {

    protected ClientGui clientGui;
    public ActionController(ClientGui clientGui){
        this.clientGui = clientGui;
    }

    public void log(String message){
        clientGui.log(message);
    }

    public ClientGui getClientGui() {
        return clientGui;
    }

    public void drawInformationOnCard(Long id) {
        clientGui.sendRequest("DrawInformationOnCard",new DrawInformationOnCard(id));
    }

    public void goToPanel(String name){
        clientGui.executeResponse(new GoToPanel(name));
    }

    public void back(){
        goToPanel("mainMenu");
    }

    public void exit(){
        clientGui.sendRequest("ExitPlay",new ExitPlay());
        System.exit(0);
    }

}
