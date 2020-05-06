package gui;

import controller.Controller;
import controller.PlayerController;
import gui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;


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
        mainMenu = new MainMenu(); cardPane.add("mainMenu",mainMenu);
      this.add(cardPane);
    }

    public void goToPanel(String panelName){
        statusPanel = new StatusPanel(); cardPane.add("statusPanel", statusPanel);
        shopPanel = new ShopPanel(); cardPane.add("shopPanel",shopPanel);
        collectionPanel = new CollectionPanel(); cardPane.add("collectionPanel",collectionPanel);
        signInPanel = new SignInPanel(); cardPane.add("signInPanel",signInPanel);
        settingsPanel = new SettingsPanel(); cardPane.add("settingsPanel",settingsPanel);
//        if(panelName.equals("playPanel")&& Controller.getInstance().getCurrentPlayer().getPlayersDeck()==null) goToPanel("collectionPanel");
        if(panelName.equals("signInPanel")) {
            audioPlayer.playMainMusic("melodyloops-light-of-hope.wav");
        }
        if(panelName.equals("playPanel")){
            if(Controller.getInstance().getCurrentPlayer().getSignedUp()||!firstGame){
                int result = JOptionPane.showOptionDialog(null,"do you want to resume the previous game or have a new game?", "let's play!",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null, new String[]{"new game", "continue"},0);
                if(result == 0 ) Controller.getInstance().getCurrentPlayer().setMakeNewGame(true);
                if(result == 1 ) Controller.getInstance().getCurrentPlayer().setMakeNewGame(false);
            }
            if(Controller.getInstance().getCurrentPlayer().getMakeNewGame()==null||Controller.getInstance().getCurrentPlayer().getMakeNewGame()) {
                if (Controller.getInstance().getCurrentPlayer().getPlayersDeck() == null) {
                    JOptionPane.showMessageDialog(null, "you don't have a current deck\n let's go to status and choose you deck");
                    GameFrame.getInstance().goToPanel("statusPanel");
                }
                if (Controller.getInstance().getCurrentPlayer().getPlayersDeck().getHero() == null) {
                    JOptionPane.showMessageDialog(null, "you don't have a choosed hero for your deck\n let's go to collections and choose a hero! ");
                    GameFrame.getInstance().goToPanel("collectionPanel");
                }
                if (Controller.getInstance().getCurrentPlayer().getPlayersDeck().getCards().size() < 10) {
                    JOptionPane.showMessageDialog(null, "you don't have a enough number of cards in your deck\n let's go to collections and have at least 10 cards in your deck \n also you can change your deck in status");
                    GameFrame.getInstance().goToPanel("collectionPanel");
                    System.out.println(Controller.getInstance().getCurrentPlayer().getPlayersDeck().getCards());
                }
                firstGame =false;
                playerController.setPassives();
            }
            playPanel = new PlayPanel();cardPane.add("playPanel", playPanel);
        }
        cardLayout.show(cardPane,panelName);
        currentPanel = panelName;
        this.setTitle("Hearth Stone - "+panelName);
        Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO,"Panel changed to " + panelName);

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
}
