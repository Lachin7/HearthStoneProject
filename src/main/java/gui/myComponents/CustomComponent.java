package gui.myComponents;

import controller.Controller;
import gui.GameFrame;
import gui.ResLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static JSON.jsonForPlayers.jsonForPlayers.jsonTofilePlayer;

public class CustomComponent {

    ResLoader resLoader;
    public CustomComponent(){
        resLoader = new ResLoader();
    }

    public void backToMenuButton(Container container, int x, int y){
        MyButton back = new MyButton("back to Menu", "pinkCrystal100.png", container, actionEvent -> GameFrame.getInstance().goToPanel("mainMenu"));
        back.setBounds(x,y,100,41);
    }

    public void exit(Container container, int x, int y){
        MyButton exit = new MyButton("save & exit game", "pinkCrystal100.png", container, actionEvent -> {
            try {
                jsonTofilePlayer(Controller.getInstance().getCurrentPlayer());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        });
        exit.setBounds(x,y,100,41);
    }
}
