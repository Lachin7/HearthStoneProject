package gui.panels;

import client.actionController.SettingsActionController;
import gui.myComponents.MyJLabel;
import server.controller.PlayerController;
import gui.myComponents.MyButton;
import gui.myComponents.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SettingsPanel extends MyPanel implements ActionListener {

    private MyPanel itemsPanel,skin,theme,sound,playerInfo;
    private  MyButton skinCard,Theme,deleteAccount,mute,decrease,increase,BlushRoom,CakeoftheDead,BansheeQueen,BlizzardEvents2018,HSplayBoard,HA_BG;
    private JLabel selected,info,deleteWarning;
    private String skinFileName = "BlushRoomCardBack.png",bgFileName = "HSplayBoard.jpg";
    private int muteUnmute = 0;
    private SettingsActionController actionController;

    public SettingsPanel(SettingsActionController actionController){
        this.actionController = actionController;
        this.setPreferredSize(new Dimension(configLoader.readInteger("mainFrameWidth"),configLoader.readInteger("mainFrameHeight")));
        this.setLayout(null);
        this.backGroundFile = configLoader.readString("settingsBackground");


        itemsPanel = new MyPanel(null,true,new FlowLayout(FlowLayout.CENTER,5,0),this);
        itemsPanel.setBounds(25,10,1150,80);

        skinCard = new MyButton("Card Skins", "blueCrystal150.png",itemsPanel,this);
        Theme = new MyButton("Theme", "blueCrystal150.png",itemsPanel,this);
        deleteAccount = new MyButton("delete Account", "blueCrystal150.png",itemsPanel,this);

        setSkin();
        setTheme();

        sound = new MyPanel(null,true,new FlowLayout(),this);
        sound.setBounds(50,100,200,500);
        mute = new MyButton("mute/un mute", "blueCrystal150.png",sound, this);
        increase = new MyButton("increase volume", "blueCrystal150.png",sound, this);
        decrease = new MyButton("decrease volume", "blueCrystal150.png",sound, this);

        customComponent.exit(this,140,620);
        customComponent.backToMenuButton(this,25,620,actionController);

        selected = new JLabel();
        selected.setBounds(600,610,400,30);
        selected.setForeground(Color.MAGENTA);
        selected.setFont(new Font("Ariel",Font.BOLD,17));
        this.add(selected);

    }

    private void setSkin(){
        skin = new MyPanel(null,true,new FlowLayout(),this);
        skin.setBounds(300,100,850,500);
        BlushRoom = new MyButton("BlushRoom","BlushRoomCardBack.png",skin,this,150,207,150,207);
        CakeoftheDead = new MyButton("Cake of the Dead","CakeoftheDeadCardBack.png",skin,this,150,207,150,207);
        BansheeQueen = new MyButton("Banshee Queen","BansheeQueenCardBack.png",skin,this,150,207,150,207);
        BlizzardEvents2018 = new MyButton("Blizzard Events2018 CardBack","BlizzardEvents2018CardBack.png",skin,this,150,207,150,207);
    }

    private void setTheme(){
        theme = new MyPanel(null,true,new FlowLayout(),this);
        HSplayBoard = new MyButton("HSplayBoard","HSplayBoard.jpg",theme,this,360,210);
        HA_BG = new MyButton("HA_BG","HA_BG.png",theme,this,360,210);
        theme.setBounds(300,100,850,500);
        theme.setVisible(false);
    }

//    private void setPlayerInfo(){
//        playerInfo = new MyPanel(null,true,null,this);
//        playerInfo.setBounds(300,100,850,600);
//        info = new JLabel(playerController.getFriendlyPlayersInfo());
//        info.setBounds(25,50,800,300);
//        playerInfo.add(info);
//        deleteWarning = new MyJLabel("delete your account?! \nIf you are sure of DELETING your account, enter your Password : ",Color.BLUE,15,playerInfo,25,400,800,100);
//        playerInfo.setVisible(false);
//    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==skinCard){
            theme.setVisible(false);
            skin.setVisible(true);
        }
        if(actionEvent.getSource()==Theme){
            skin.setVisible(false);
            theme.setVisible(true);
        }
//        if(actionEvent.getSource()==deleteAccount){
//            try {
//                Controller.getInstance().getPlayerController().deleteThePlayer();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        if(actionEvent.getSource()==mute){
            if(muteUnmute%2==0) audioPlayer.stopSound();
            else audioPlayer.startSound();
            muteUnmute++;
        }
        if(actionEvent.getSource()==increase){
            audioPlayer.increaseSound();
        }
        if(actionEvent.getSource()==decrease){
            audioPlayer.decreaseSound();
        }
//        if(actionEvent.getSource()==BlushRoom||actionEvent.getSource()==CakeoftheDead||actionEvent.getSource()==BansheeQueen||actionEvent.getSource()==BlizzardEvents2018){
//            setSkinFileName(((MyButton) actionEvent.getSource()).getIconFile());
//            Controller.getInstance().getMainPlayer().setCardSkin(((MyButton) actionEvent.getSource()).getIconFile());
//            selected.setText(((MyButton) actionEvent.getSource()).getName() + " is selected");
//        }
//        if(actionEvent.getSource()==HA_BG||actionEvent.getSource()==HSplayBoard){
//            setBgFileName(((MyButton) actionEvent.getSource()).getIconFile());
//            Controller.getInstance().getMainPlayer().setPlayBackGround(((MyButton) actionEvent.getSource()).getIconFile());
//            selected.setText(((MyButton) actionEvent.getSource()).getName() + " is selected");
//        }
    }

    public void setSkinFileName(String skinFileName) {
        this.skinFileName = skinFileName;
    }

    public void setBgFileName(String bgFileName) {
        this.bgFileName = bgFileName;
    }
}
