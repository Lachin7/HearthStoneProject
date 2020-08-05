package gui.panels;

import client.actionController.ActionController;
import gui.myComponents.*;

import java.awt.*;

public class MainMenu extends MyPanel{

    public MainMenu(ActionController actionController){
        this.backGroundFile = "mainMenuBg.jpg";
        this.setPreferredSize(new Dimension(configLoader.readInteger("mainFrameWidth"),configLoader.readInteger("mainFrameHeight")));
        this.setLayout(null);

        new MyButton("play", "blueCrystal150.png", this, actionEvent -> actionController.getClient().goToPanel(actionController.getClient().getPrePlayPanel()), 130, 80);
        new MyButton("shop","blueCrystal150.png",this, actionEvent -> actionController.getClient().goToPanel(actionController.getClient().getShopPanel()), 310,180);
        new MyButton("collection","blueCrystal150.png",this, actionEvent -> actionController.getClient().goToPanel(actionController.getClient().getCollectionPanel()), 490,280);
        new MyButton("status","blueCrystal150.png",this, actionEvent -> actionController.getClient().goToPanel(actionController.getClient().getStatusPanel()), 670,380);
        new MyButton("settings","blueCrystal150.png",this, actionEvent -> actionController.getClient().goToPanel(actionController.getClient().getSettingsPanel()), 850,480);
        new MyButton("Rank","blueCrystal150.png",this, actionEvent -> actionController.getClient().goToPanel(actionController.getClient().getRankPanel()), 1030,580);
    }
}
