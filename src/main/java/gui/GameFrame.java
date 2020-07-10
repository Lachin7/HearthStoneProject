package gui;

import controller.Controller;
import controller.PlayerController;
import gui.panels.*;
import resLoader.MyAudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;

import static JSON.jsonForGame.jsonForGame.GameFromjson;


public class GameFrame extends JFrame {
    private SignInPanel signInPanel;
    private MainMenu mainMenu;
    private PlayPanel playPanel;
    private ShopPanel shopPanel;
    private CollectionPanel collectionPanel;
    private StatusPanel statusPanel;
    private SettingsPanel settingsPanel;
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPane;
    private String currentPanel;
    private MyAudioPlayer audioPlayer;
    private Boolean firstGame = true;
    private PlayerController playerController;
    private PrePlayPanel prePlayPanel;


    private static  GameFrame myGameFrame = null;
    public static GameFrame getInstance(){
        if(myGameFrame == null) myGameFrame = new GameFrame();
        return myGameFrame;
    }

    private GameFrame(){
        this.setSize(1200,700);
        this.setTitle(" Hearth Stone ");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(new JPanel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        audioPlayer = MyAudioPlayer.getInstance();
        playerController = new PlayerController();
        cardPane = new JPanel();
        cardPane.setLayout(cardLayout);
        cardPane.setBounds(0,0,1200,700);
        prePlayPanel = new PrePlayPanel();cardPane.add("playPanel",prePlayPanel);
        this.add(cardPane);
    }

    public void goToPanel(String panelName){
        setPanels();
        if(panelName.equals("signInPanel")) {
            audioPlayer.playMainMusic("melodyloops-light-of-hope.wav");
        }
        if(panelName.equals("playPanel")){
            if(Controller.getInstance().getMainPlayer().getSignedUp()||!firstGame){
                int result = JOptionPane.showOptionDialog(null,"do you want to resume the previous game or have a new game?", "let's play!",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null, new String[]{"new game", "continue"},0);
                if(result == 0) {
                    prePlayPanel.setUpChooseMode();
                    Controller.getInstance().getMainPlayer().setMakeNewGame(true);
                }
                if(result == 1) {
                    Controller.getInstance().getMainPlayer().setMakeNewGame(false);
                    try {
                        playPanel = GameFromjson();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    goToPanel("mainPlayPanel");
                }
            }
           //todo else prePlayPanel.setUpChooseMode();
            if(Controller.getInstance().getMainPlayer().getMakeNewGame()==null||Controller.getInstance().getMainPlayer().getMakeNewGame()) {
                prePlayPanel.setUpChooseMode();
            }
        }
        if(panelName.equals("mainPlayPanel")){
            playPanel = new PlayPanel(prePlayPanel.getBoardController());
            cardPane.add("mainPlayPanel",playPanel);
        }
        cardLayout.show(cardPane,panelName);
        currentPanel = panelName;
        this.setTitle("Hearth Stone - "+panelName);
        Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO,"Panel changed to " + panelName);

    }

    private void setPanels(){
        mainMenu = new MainMenu(); cardPane.add("mainMenu",mainMenu);
        statusPanel = new StatusPanel(); cardPane.add("statusPanel", statusPanel);
        collectionPanel = new CollectionPanel(); cardPane.add("collectionPanel",collectionPanel);
        shopPanel = new ShopPanel(); cardPane.add("shopPanel",shopPanel);
        signInPanel = new SignInPanel(); cardPane.add("signInPanel",signInPanel);
        settingsPanel = new SettingsPanel(); cardPane.add("settingsPanel",settingsPanel);
    }

    public SignInPanel getSignInPanel() {
        return signInPanel;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public PlayPanel getPlayPanel() {
        return playPanel;
    }

    public ShopPanel getShopPanel() {
        return shopPanel;
    }

    public CollectionPanel getCollectionPanel() {
        return collectionPanel;
    }

    public StatusPanel getStatusPanel() {
        return statusPanel;
    }

    public SettingsPanel getSettingsPanel() {
        return settingsPanel;
    }

    public String getCurrentPanel() {
        return currentPanel;
    }

    public void setFirstGame(Boolean firstGame) {
        this.firstGame = firstGame;
    }
}
