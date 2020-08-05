package client.actionController;

import client.Client;
import gui.myComponents.MyCardButton;
import request_response.request.DrawInformationOnCard;
import request_response.request.SignIn;
import request_response.request.SignUp;

public class ActionController {

    protected Client client;
    public ActionController(Client client){
        this.client = client;
    }

    public void log(String message){
        client.log(message);
    }

    public Client getClient() {
        return client;
    }

    public void drawInformationOnCard(MyCardButton card ,long id) {
        client.sendRequest("DrawInformationOnCard",new DrawInformationOnCard(card,id));
    }

    public void backToMenu() {
        //todo override it for play
        client.goToPanel(client.getMainMenu());
    }
}
