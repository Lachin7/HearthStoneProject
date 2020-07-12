package gui.panels;

import controller.PlayerController;
import gui.Constants.GuiCons;
import resLoader.ImageLoader;
import gui.myComponents.MyButton;
import gui.myComponents.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignInPanel extends MyPanel {

    private final JLabel WelcomeText;
    private final JLabel EnterName;
    private final JLabel EnterPass;
    private final JLabel noAccount;
    private final JLabel CreatName;
    private final JLabel CreatPass;
    private final JLabel ErrorSignIn;
    private final JLabel ErrorSignUp;
    private final JTextField userName;
    private final JTextField password;
    private final JTextField CreatedName;
    private final JTextField CreatedPass;
    private final JButton signIn;
    private final JButton signUp;
    private final PlayerController playerController;

    public SignInPanel(){

        playerController =new PlayerController();
        this.setLayout(null);
        this.setPreferredSize(new Dimension(GuiCons.getWidth(),GuiCons.getHeight()));

        WelcomeText = new JLabel("Welcome! Let's Sign In! ");
        WelcomeText.setForeground(Color.MAGENTA);
        WelcomeText.setFont(new Font("Segoe UI", Font.BOLD, 25));
        WelcomeText.setBounds(750,100,300,40);
        this.add(WelcomeText);

        EnterName = new JLabel("Enter your user name : ");
//        EnterName.setForeground(new Color(0x2F170C));
        EnterName.setBounds(700,150,200,30);
        userName = new JTextField();
        userName.setBounds(850,150,200,30);
        this.add(EnterName); this.add(userName);

        EnterPass = new JLabel("Enter your password : ");
        EnterPass.setBounds(700,200,200,30);
//        EnterPass.setForeground(new Color(0x2F170C));
        password = new JPasswordField();
        password.setBounds(850,200,200,30);
        this.add(EnterPass); this.add(password);

        ErrorSignIn = new JLabel("");
        ErrorSignIn.setBounds(700,300,400,30);
        ErrorSignIn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        ErrorSignIn.setForeground(Color.MAGENTA);
//        ErrorSignIn.setForeground(new Color(0x2F170C));
        this.add(ErrorSignIn);

        signIn = new MyButton("Sign In!","pinkCrystal100.png",this,null);
        signIn.setBounds(820,250,signIn.getWidth(),signIn.getHeight());
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               ErrorSignIn.setText(playerController.SignIn(userName.getText(),password.getText()));
            }

        });



        noAccount = new JLabel("Don't have an account? Sign Up! ");
        noAccount.setBounds(680,350,450,30);
        noAccount.setForeground(Color.GREEN);
        noAccount.setFont(new Font("Segoe UI", Font.BOLD, 25));
        this.add(noAccount);


        CreatName = new JLabel("Creat your user name : ");
        CreatName.setBounds(700,400,200,30);
        CreatedName = new JTextField();
        CreatedName.setBounds(850,400,200,30);
        this.add(CreatName); this.add(CreatedName);

        CreatPass = new JLabel("Creat your password : ");
        CreatPass.setBounds(700,450,200,30);
        CreatedPass = new JPasswordField();
        CreatedPass.setBounds(850,450,200,30);
        this.add(CreatPass); this.add(CreatedPass);

        ErrorSignUp = new JLabel("");
        ErrorSignUp.setBounds(700,550,400,30);
        ErrorSignUp.setForeground(Color.GREEN);
        this.add(ErrorSignUp);

        signUp = new MyButton("Sign Up!","GreenCrystal.png",this,null);
        signUp.setBounds(820,500,100,50);
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ErrorSignUp.setText(playerController.SignUp(CreatedName.getText(),CreatedPass.getText()));
//             playerController.SignUp(CreatedName.getText(),CreatedPass.getText(),ErrorSignUp);
            }
        });


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageLoader.loadImage("rsz_loginhs.jpg"),0,0,null);
        g.drawImage(imageLoader.loadImage("HS_background.jpg"),535,0,null);
    }

}
