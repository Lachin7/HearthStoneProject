package gui.panels;

import controller.Controller;
import gui.Constants.GuiCons;
import gui.GameFrame;
import resLoader.MyAudioPlayer;
import gui.myComponents.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;

public class MainMenu extends MyPanel implements ActionListener {

    private final JButton play;
    private final JButton shop;
    private final JButton status;
    private final JButton collection;
    private final JButton settings;
    private final JButton signIn ;
    private final MyAudioPlayer audioPlayer;


    public MainMenu(){
        this.backGroundFile = "mainMenuBg.jpg";
        this.setPreferredSize(new Dimension(GuiCons.getWidth(),GuiCons.getHeight()));
        this.setLayout(null);
        audioPlayer = MyAudioPlayer.getInstance();

        play = new MyButton("play","blueCrystal150.png",this,this::actionPerformed);
        play.setBounds(130,80,150,64);
        shop = new MyButton("shop","blueCrystal150.png",this,this::actionPerformed);
        shop.setBounds(310,180,150,64);
        collection = new MyButton("collection","blueCrystal150.png",this,this::actionPerformed);
        collection.setBounds(490,280,150,64);
        status = new MyButton("status","blueCrystal150.png",this,this::actionPerformed);
        status.setBounds(670,380,150,64);
        settings = new MyButton("settings","blueCrystal150.png",this,this::actionPerformed);
        settings.setBounds(850,480,150,64);
         signIn = new MyButton("signIn","blueCrystal150.png",this,this::actionPerformed);
        signIn.setBounds(1030,580,150,64);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        audioPlayer.playQuick("Button_Push.wav");
        GameFrame.getInstance().goToPanel(((MyButton) actionEvent.getSource()).getText()+"Panel");
       Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO,((MyButton) actionEvent.getSource()).getText()+" button clicked - MainMenu");

    }


}
