package client.gui;

import javax.swing.*;


public class GameFrame extends JFrame {

    private Boolean firstGame = true;

    private static  GameFrame myGameFrame = null;
    public static GameFrame getInstance(){
        if(myGameFrame == null) myGameFrame = new GameFrame();
        return myGameFrame;
    }

    public GameFrame(){
        this.setSize(1200,700);
        this.setTitle(" Hearth Stone ");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(new JPanel());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
    }

    public void setFirstGame(Boolean firstGame) {
        this.firstGame = firstGame;
    }
}
