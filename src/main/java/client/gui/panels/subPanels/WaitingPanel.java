package client.gui.panels.subPanels;

import client.gui.myComponents.MyJLabel;
import client.gui.myComponents.MyPanel;

import javax.swing.*;
import java.awt.*;

public class WaitingPanel extends MyPanel {

    public WaitingPanel(){
        super("Choose3CardBG.jpg",false,null,null);
        this.setPreferredSize(new Dimension(configLoader.readInteger("waitingPanelWidth"), configLoader.readInteger("waitingPanelHeight")));
        new MyJLabel("Please Wait ...", Color.YELLOW,25,this,50,70,250,40);
    }

}
