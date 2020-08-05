package gui.panels;

import client.actionController.ActionController;
import client.actionController.InitialSetUp;
import gui.myComponents.MyButton;
import gui.myComponents.MyJLabel;
import gui.myComponents.MyPanel;

import javax.swing.*;
import java.awt.*;

public class ConnectionPanel extends MyPanel {

    private MyJLabel error;
    private JTextField ipField, portField;
    private InitialSetUp actionController;

    public ConnectionPanel(InitialSetUp actionController) {
        this.backGroundFile = "b&p_bg.jpg";
        this.setPreferredSize(new Dimension(configLoader.readInteger("ConnectionPanelWidth"), configLoader.readInteger("ConnectionPanelHeight")));
        this.setLayout(null);
        this.actionController = actionController;
        new MyJLabel("Welcome!", new Color(59, 50, 109), 25, this, 180, 50, 400, 30);
        new MyJLabel("Enter IP : ", new Color(18, 54, 161), 25, this, 50, 100, 200, 30);
        ipField = new JTextField();
        this.add(ipField);
        ipField.setBounds(250, 100, 150, 30);
        new MyJLabel("Enter Port : ", new Color(18, 54, 161), 25, this, 50, 150, 200, 30);
        portField = new JTextField();
        this.add(portField);
        portField.setBounds(250, 150, 150, 30);
        error = new MyJLabel("", new Color(78, 5, 92), 20, this, 100, 320, 400, 30);
        new MyButton("Connect", "blueCrystal120.png", this, actionEvent -> {
            try {
                actionController.connectToServer(Integer.parseInt(portField.getText()), ipField.getText());
            } catch (NumberFormatException e) {
                error.setText("port should be a number!");
            }
        }, 165, 250);
    }

    public MyJLabel getError() {
        return error;
    }
}
