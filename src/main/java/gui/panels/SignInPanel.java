package gui.panels;

import client.actionController.ActionController;
import client.actionController.InitialSetUp;
import gui.myComponents.MyJLabel;
import gui.myComponents.MyButton;
import gui.myComponents.MyPanel;

import javax.swing.*;
import java.awt.*;

public class SignInPanel extends MyPanel {

    private  MyJLabel ErrorSignIn, ErrorSignUp;
    private  JTextField userName, password, CreatedName, CreatedPass;
    private final InitialSetUp actionController;

    public SignInPanel(InitialSetUp actionController){
        this.setLayout(null);
        this.setPreferredSize(new Dimension(configLoader.readInteger("mainFrameWidth"),configLoader.readInteger("mainFrameHeight")));
        this.actionController = actionController;
        setUpComponents();
    }

    private void setUpComponents(){
        new MyJLabel("Welcome! Let's Sign In! ", Color.MAGENTA,25,this, 750,100,300,40);
        new MyJLabel("Enter your user name : ", new Color(12, 12, 94),15,this, 670,150,200,30);
        userName = new JTextField();
        userName.setBounds(850,150,200,30);
        this.add(userName);
        new MyJLabel("Enter your password : ", new Color(12, 12, 94),15,this,670,200,200,30);
        password = new JPasswordField();
        password.setBounds(850,200,200,30);
        this.add(password);
        ErrorSignIn = new MyJLabel("", Color.MAGENTA,13,this,700,300,400,30);
        new MyButton("Sign In!", "pinkCrystal100.png", this, actionEvent -> { actionController.signIn(userName.getText(),password.getText());}, 820,250);

        new MyJLabel("Don't have an account? Sign Up! ", Color.GREEN,25,this, 670,350,450,30);
        new MyJLabel("Creat your user name : ", new Color(12, 12, 94),15,this, 670,400,200,30);
        CreatedName = new JTextField();
        CreatedName.setBounds(850,400,200,30);
        this.add(CreatedName);
        new MyJLabel("Creat your password : ", new Color(12, 12, 94),15,this, 680,450,200,30);
        CreatedPass = new JPasswordField();
        CreatedPass.setBounds(850,450,200,30);
        this.add(CreatedPass);
        ErrorSignUp = new MyJLabel("", Color.GREEN,25,this, 670,550,450,30);
        new MyButton("Sign Up!", "GreenCrystal.png", this, actionEvent -> actionController.signUp(CreatedName.getText(),CreatedPass.getText()), 820, 500);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageLoader.loadImage("rsz_loginhs.jpg"),0,0,null);
        g.drawImage(imageLoader.loadImage("HS_background.jpg"),535,0,null);
    }

    public MyJLabel getErrorSignIn() {
        return ErrorSignIn;
    }

    public MyJLabel getErrorSignUp() {
        return ErrorSignUp;
    }
}
