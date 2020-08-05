package gui.myComponents;

import client.actionController.ActionController;
import gui.GameFrame;
import gui.panels.PlayPanel;
import resLoader.ImageLoader;

import java.awt.*;
import java.io.IOException;

import static JSON.jsonForGame.jsonForGame.jsonFileMakerForGame;
import static JSON.jsonForPlayers.jsonForPlayers.jsonTofilePlayer;

public class CustomComponent {

    private ImageLoader imageLoader;
    private ActionController actionController;
    public CustomComponent(){
        imageLoader = ImageLoader.getInstance();
    }

    public void backToMenuButton(Container container, int x, int y, ActionController actionController){
//        if(playPanel!=null) {
////            try {
////                //todo
////                jsonFileMakerForGame(playPanel.getBoardController());
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//        }
        MyButton back = new MyButton("back to Menu", "pinkCrystal100.png", container, actionEvent -> actionController.backToMenu());
        back.setBounds(x,y,100,41);

    }

    public void exit(Container container, int x, int y){
        MyButton exit = new MyButton("save & exit game", "pinkCrystal100.png", container, actionEvent -> {
//            try {
            // TODO: 8/2/20
//                jsonTofilePlayer(Controller.getInstance().getMainPlayer());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            System.exit(0);
        });
        exit.setBounds(x,y,100,41);
    }
}
