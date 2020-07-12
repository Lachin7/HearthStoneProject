package gui.myComponents;

import controller.Controller;
import gui.GameFrame;
import gui.panels.PlayPanel;
import resLoader.ImageLoader;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

import static JSON.jsonForGame.jsonForGame.jsonFileMakerForGame;
import static JSON.jsonForPlayers.jsonForPlayers.jsonTofilePlayer;

public class CustomComponent {

    ImageLoader imageLoader;
    public CustomComponent(){
        imageLoader = ImageLoader.getInstance();
    }

    public void backToMenuButton(Container container, int x, int y, PlayPanel playPanel){
        if(playPanel!=null) {
            try {
                //todo
                jsonFileMakerForGame(playPanel.getBoardController());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        MyButton back = new MyButton("back to Menu", "pinkCrystal100.png", container, actionEvent -> GameFrame.getInstance().goToPanel("mainMenu"));
        back.setBounds(x,y,100,41);

    }

    public void exit(Container container, int x, int y){
        MyButton exit = new MyButton("save & exit game", "pinkCrystal100.png", container, actionEvent -> {
            try {
                jsonTofilePlayer(Controller.getInstance().getMainPlayer());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        });
        exit.setBounds(x,y,100,41);
    }
}
