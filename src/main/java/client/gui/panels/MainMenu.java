package client.gui.panels;

import client.actionController.ActionController;
import client.gui.myComponents.*;

import java.awt.*;

public class MainMenu extends MyPanel{

    public MainMenu(ActionController actionController){
        this.backGroundFile = "mainMenuBg.jpg";
        this.setPreferredSize(new Dimension(configLoader.readInteger("mainFrameWidth"),configLoader.readInteger("mainFrameHeight")));
        this.setLayout(null);

        new MyButton("play", "blueCrystal150.png", this, actionEvent -> actionController.goToPanel("prePlay"), 130, 80);
        new MyButton("shop","blueCrystal150.png",this, actionEvent -> actionController.goToPanel("shop"), 310,180);
        new MyButton("collection","blueCrystal150.png",this, actionEvent -> actionController.goToPanel("collection"), 490,280);
        new MyButton("status","blueCrystal150.png",this, actionEvent -> actionController.goToPanel("status"), 670,380);
        new MyButton("settings","blueCrystal150.png",this, actionEvent -> actionController.goToPanel("settings"), 850,480);
        new MyButton("rank","blueCrystal150.png",this, actionEvent -> actionController.goToPanel("rank"), 1030,580);
    }
}
