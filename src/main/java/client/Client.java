package client;

import client.actionController.*;
import com.google.gson.Gson;
import gui.GameFrame;
import gui.panels.*;
import gui.panels.subPanels.RunningGamesList;
import lombok.Getter;
import request_response.JsonResponseMaker;
import request_response.request.Log;
import request_response.request.Request;
import request_response.response.Response;
import resLoader.MyAudioPlayer;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Thread{

    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private Gson gson;
    private JsonResponseMaker jsonResponseMaker;

    private ArrayList<Request> requests;
    private boolean isRunning;

    private GameFrame frame;
    @Getter
    private ConnectionPanel connectionPanel;
    @Getter
    private SignInPanel signInPanel;
    @Getter
    private MainMenu mainMenu;
    @Getter
    private PrePlayPanel prePlayPanel;
    @Getter
    private PlayPanel playPanel;
    @Getter
    private ShopPanel shopPanel;
    @Getter
    private StatusPanel statusPanel;
    @Getter
    private CollectionPanel collectionPanel;
    @Getter
    private SettingsPanel settingsPanel;
    @Getter
    private RunningGamesList runningGamesList;
    @Getter
    private RankPanel rankPanel;
    @Getter
    private MyAudioPlayer audioPlayer;

    public Client(){
        gson = new Gson();
        requests = new ArrayList<>();
        frame = new GameFrame();
        connectionPanel = new ConnectionPanel(new InitialSetUp(this));
        audioPlayer = MyAudioPlayer.getInstance();
        jsonResponseMaker = new JsonResponseMaker();
    }

    private void setUpGui(){
        collectionPanel = new CollectionPanel(new CollectionPanelAction(this));
        prePlayPanel = new PrePlayPanel(new PlayActionController(this));
        shopPanel = new ShopPanel(new ShopActionController(this));
        statusPanel = new StatusPanel(new StatusActionController(this));
        settingsPanel = new SettingsPanel(new SettingsActionController(this));
        rankPanel = new RankPanel(new StatusActionController(this));
        runningGamesList = new RunningGamesList(new PlayActionController(this));
    }

    public void setUpClient(String host, int port){
        try {
            socket = new Socket(host, port);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
            signInPanel = new SignInPanel(new InitialSetUp(this));
            mainMenu = new MainMenu(new ActionController(this));
            goToPanel(signInPanel);
            this.start();
        } catch (IOException e) {
            connectionPanel.getError().setText("can not connect to server!");
        }
    }

    public void sendRequest(String requestName, Request request){
        out.println(requestName + "split" + gson.toJson(request));
    }


    @Override
    public void run() {
        String[] responses;
        String responseName ;
        String json;
        while (in.hasNextLine()){
            responses = in.nextLine().split("split");
            responseName = responses[0];
            json = responses[1];
            Response response = jsonResponseMaker.makeResponse(responseName,json);
            response.execute(this);
        }
    }

    public void goToPanel(JPanel panel){
        audioPlayer.playQuick("Button_Push.wav");
        if (!(panel instanceof ConnectionPanel) && !(panel instanceof SignInPanel))setUpGui();
        if(panel instanceof SignInPanel) audioPlayer.playMainMusic("melodyloops-light-of-hope.wav");
        else if(panel instanceof PrePlayPanel) prePlayPanel.setUpChooseMode();
        else if (panel instanceof PlayPanel)audioPlayer.playMainMusic("PlayGound.wav");

        frame.getContentPane().setVisible(false);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setTitle("Hearth Stone");
        //todo panel name = null !!
        log("changed panel to : "+ panel.getName());
    }

    public void log(String message){
        new Log(message);
    }

    public void signIn(String message) {
        signInPanel.getErrorSignIn().setText(message);
        if (message.contains("successfully")) goToPanel(mainMenu);

    }

    public void signUp(String message) {
        signInPanel.getErrorSignUp().setText(message);
        if (message.contains("successfully")) goToPanel(mainMenu);

    }

    public void initializePlayPanel() {
        playPanel = new PlayPanel(new PlayActionController(this));
    }




    //                if(Controller.getInstance().getMainPlayer().getSignedUp()||!firstGame){
//        int result = JOptionPane.showOptionDialog(null,"do you want to resume the previous game or have a new game?", "let's play!",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null, new String[]{"new game", "continue"},0);
//        if(result == 0) {
//            prePlayPanel.setUpChooseMode();
//            Controller.getInstance().getMainPlayer().setMakeNewGame(true);
//        }
//        if(result == 1) {
//            Controller.getInstance().getMainPlayer().setMakeNewGame(false);
//
////                    try {
////                        playPanel = new PlayPanel(GameFromjson());
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
//            goToPanel("mainPlayPanel");
//        }
//    }
//    // else prePlayPanel.setUpChooseMode();
//            if(Controller.getInstance().getMainPlayer().getMakeNewGame()==null||Controller.getInstance().getMainPlayer().getMakeNewGame()) {
//        prePlayPanel.setUpChooseMode();
//    }
}
