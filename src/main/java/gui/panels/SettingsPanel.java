package gui.panels;

import controller.Controller;
import gui.Constants.GuiCons;
import gui.MyAudioPlayer;
import gui.ResLoader;
import gui.myComponents.CustomComponent;
import gui.myComponents.MyButton;
import gui.myComponents.MyCardButton;
import gui.myComponents.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends MyPanel implements ActionListener {

    private MyPanel itemsPanel, skin, theme, sound;
    private ResLoader resLoader ;
    private MyButton skinCard, Theme, BlushRoom,CakeoftheDead,BansheeQueen,BlizzardEvents2018, HSplayBoard, HA_BG, mute, decrease, increase;
    private JLabel selected;
    private String skinFileName = "BlushRoomCardBack.png";
    private String bgFileName = "HSplayBoard.jpg";
    private MyAudioPlayer audioPlayer;
    private CustomComponent customComponent;
    private int muteUnmute = 0;

    public SettingsPanel(){

        resLoader = new ResLoader();
        this.setPreferredSize(new Dimension(GuiCons.getWidth(),GuiCons.getHeight()));
        this.setLayout(null);
        this.backGroundFile = "rsz_wood_wallpapers_1080p_1.jpg";
        customComponent = new CustomComponent();
        audioPlayer = MyAudioPlayer.getInstance();

        itemsPanel = new MyPanel(null,true,new FlowLayout(FlowLayout.CENTER,5,0),this);
        itemsPanel.setBounds(25,10,1150,80);

        skinCard = new MyButton("Card Skins", "blueCrystal150.png",itemsPanel,this);
        Theme = new MyButton("Theme", "blueCrystal150.png",itemsPanel,this);

        skin = new MyPanel(null,true,new FlowLayout(),this);
         BlushRoom = new MyButton("BlushRoom","BlushRoomCardBack.png",skin,this,150,207);
         CakeoftheDead = new MyButton("CakeoftheDead","CakeoftheDeadCardBack.png",skin,this,150,207);
         BansheeQueen = new MyButton("BansheeQueen","BansheeQueenCardBack.png",skin,this,150,207);
         BlizzardEvents2018 = new MyButton("BlizzardEvents2018CardBack","BlizzardEvents2018CardBack.png",skin,this,150,207);
        skin.setBounds(500,100,550,500);

        theme = new MyPanel(null,true,new FlowLayout(),this);
        HSplayBoard = new MyButton("HSplayBoard","HSplayBoard.jpg",theme,this,360,210);
        HA_BG = new MyButton("HA_BG","HA_BG.png",theme,this,360,210);
        theme.setBounds(500,100,550,500);
        theme.setVisible(false);

        sound = new MyPanel(null,true,new FlowLayout(),this);
        sound.setBounds(100,100,200,500);
        mute = new MyButton("mute/un mute", "blueCrystal150.png",sound, this);
        increase = new MyButton("increase volume", "blueCrystal150.png",sound, this);
        decrease = new MyButton("decrease volume", "blueCrystal150.png",sound, this);

        customComponent.exit(this,140,620);
        customComponent.backToMenuButton(this,25,620);

        selected = new JLabel();
        selected.setBounds(600,610,400,30);
        selected.setForeground(Color.MAGENTA);
        selected.setFont(new Font("Ariel",Font.BOLD,17));
        this.add(selected);

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource()==skinCard){
            theme.setVisible(false);skin.setVisible(true);
        }
        if(actionEvent.getSource()==Theme){
        skin.setVisible(false);theme.setVisible(true);
        }
        if(actionEvent.getSource()==mute){
            if(muteUnmute%2==0){
                audioPlayer.stopSound();
            }
            else {
                audioPlayer.startSound();
            }
            muteUnmute++;
        }
        if(actionEvent.getSource()==increase){
            audioPlayer.increaseSound();
        }
        if(actionEvent.getSource()==decrease){
            audioPlayer.decreaseSound();
        }
        if(actionEvent.getSource()==BlushRoom||actionEvent.getSource()==CakeoftheDead||actionEvent.getSource()==BansheeQueen||actionEvent.getSource()==BlizzardEvents2018){
            setSkinFileName(((MyButton) actionEvent.getSource()).getIconFile());
            Controller.getInstance().getCurrentPlayer().setCardSkin(((MyButton) actionEvent.getSource()).getIconFile());
            selected.setText(((MyButton) actionEvent.getSource()).getName() + " is selected");
        }
        if(actionEvent.getSource()==HA_BG||actionEvent.getSource()==HSplayBoard){
            setBgFileName(((MyButton) actionEvent.getSource()).getIconFile());
            Controller.getInstance().getCurrentPlayer().setPlayBackGround(((MyButton) actionEvent.getSource()).getIconFile());
            selected.setText(((MyButton) actionEvent.getSource()).getName() + " is selected");
        }
    }

    public void setSkinFileName(String skinFileName) {
        this.skinFileName = skinFileName;
    }

    public void setBgFileName(String bgFileName) {
        this.bgFileName = bgFileName;
    }
}
