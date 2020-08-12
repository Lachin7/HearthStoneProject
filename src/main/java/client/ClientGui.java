package client;

import client.actionController.*;
import client.gui.GameFrame;
import client.gui.myComponents.MyCardButton;
import client.gui.panels.*;
import client.gui.panels.subPanels.RunningGamesList;
import client.gui.panels.subPanels.WaitingPanel;
import lombok.Getter;
import request_response.request.Log;
import request_response.request.Request;
import request_response.response.Response;
import resLoader.MyAudioPlayer;
import server.models.util.MyPair;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientGui extends Thread {


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
    private WaitingPanel waitingPanel;
    @Getter
    private MyAudioPlayer audioPlayer;
    private ClientNetwork clientNetwork;
    private LinkedList<Response> responses;
    private final LinkedList<Response> tempResponseList;
    @Getter
    private HashMap<Long,MyCardButton> cardButtons;
    @Getter
    private ActionController actionController;

    ClientGui() {
        responses = new LinkedList<>();
        tempResponseList = new LinkedList<>();
        runningGamesList = new RunningGamesList(new PlayActionController(this));
        frame = new GameFrame();
        connectionPanel = new ConnectionPanel(new InitialSetUp(this));
        audioPlayer = MyAudioPlayer.getInstance();
        actionController = new ActionController(this);
        ScheduledExecutorService ex = Executors.newSingleThreadScheduledExecutor();
        ex.scheduleAtFixedRate(() -> {
            synchronized (tempResponseList) {
                responses.addAll(tempResponseList);
                tempResponseList.clear();
            }
            if (responses.size()!=0) {
                responses.forEach(response -> response.execute(this));
                responses.clear();
            }
            frame.repaint();
            frame.revalidate();
        },0,16, TimeUnit.MILLISECONDS);
    }



    public void setUpClient(String host, int port) {
        signInPanel = new SignInPanel(new InitialSetUp(this));
        mainMenu = new MainMenu(new ActionController(this));
        goToPanel("signInPanel");
        clientNetwork = new ClientNetwork(host,port,this);
        clientNetwork.start();
    }

    public void sendRequest(String requestName, Request request) {
        clientNetwork.sendRequest(requestName, request);
    }

    public void goToPanel(JPanel panel) {
        frame.getContentPane().setVisible(false);
        frame.setContentPane(panel);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setTitle("Hearth Stone");
    }

    public void goToPanel(String panelName) {
        audioPlayer.playQuick("Button_Push.wav");
        cardButtons = new HashMap<>();
        if (panelName.contains("connection")) goToPanel(connectionPanel = new ConnectionPanel(new InitialSetUp(this)));
        else if (panelName.contains("play")) setUpPlay();
        else if (panelName.contains("prePlay"))
            goToPanel(prePlayPanel = new PrePlayPanel(new PlayActionController(this)));
        else if (panelName.contains("collection"))
            goToPanel(collectionPanel = new CollectionPanel(new CollectionPanelAction(this)));
        else if (panelName.contains("status"))
            goToPanel(statusPanel = new StatusPanel(new StatusActionController(this)));
        else if (panelName.contains("settings"))
            goToPanel(settingsPanel = new SettingsPanel(new SettingsActionController(this)));
        else if (panelName.contains("shop")) goToPanel(shopPanel = new ShopPanel(new ShopActionController(this)));
        else if (panelName.contains("signIn")) goToPanel(signInPanel = new SignInPanel(new InitialSetUp(this)));
        else if (panelName.contains("mainMenu")) {
            goToPanel(mainMenu = new MainMenu(new InitialSetUp(this)));
            audioPlayer.playMainMusic("melodyloops-light-of-hope.wav");
        }
        else if (panelName.contains("rank")) goToPanel(rankPanel = new RankPanel(new StatusActionController(this)));
        else if (panelName.contains("wait")) goToPanel(waitingPanel = new WaitingPanel());

        log("changed panel to : " + panelName);
    }

    private void setUpPlay(){
        PlayActionController actionController = new PlayActionController(this);
        goToPanel(playPanel = new PlayPanel(actionController));
        actionController.setUpPlayForPlayer();
        actionController.drawPlayChanges();
        audioPlayer.playMainMusic("PlayGound.wav");
        playPanel.updateHands();
        playPanel.updateBothFields();
    }

    public void log(String message) {
        new Log(message);
    }

    public void signIn(String message) {
        signInPanel.getErrorSignIn().setText(message);
        if (message.contains("successfully")) goToPanel("mainMenu");
    }

    public void signUp(String message) {
        signInPanel.getErrorSignUp().setText(message);
        if (message.contains("successfully")) goToPanel("mainMenu");
    }

    public void executeResponse(Response response) {
        if (response != null) {
            synchronized (tempResponseList) {
                tempResponseList.add(response);
            }
        }
    }

    public void setBuyShowCase(HashMap<MyPair<Long, String>, Integer> cards, long coins) {
        getShopPanel().setBuyShowCase(cards, coins);
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
