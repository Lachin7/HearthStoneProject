package client.gui.panels.subPanels;

import client.actionController.PlayActionController;
import client.gui.myComponents.MyButton;
import client.gui.myComponents.MyJLabel;
import client.gui.myComponents.MyPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class RunningGamesList extends MyPanel {
    private HashMap<String,String> names;
    private PlayActionController actionController;

    public RunningGamesList(PlayActionController actionController) {
        super(null, true, new FlowLayout(), null);
        this.actionController = actionController;
        this.setLayout(new FlowLayout());
//        this.setBackGroundFile("oldPaperBg.jpg");
        this.setPreferredSize(new Dimension(configLoader.readInteger("runningGamesListPanelWidth"), configLoader.readInteger("runningGamesListPanelHeight")));
    }

    public void setLabels(HashMap<String, String> names){
        if (names.size()==0) new MyJLabel("          no ruuning games exist  right now!          ",Color.YELLOW,20,this,0,0,400,30);
        for (Map.Entry<String,String> entry : names.entrySet()){
            MyJLabel label =  new MyJLabel(entry.getKey() + "           VS.        " + entry.getValue(),Color.YELLOW,20,this,0,0,400,30);
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    actionController.declareChosenMatchToWatch(entry.getKey(),entry.getValue());
                    actionController.goToPanel("wait");
                }
            });
        }
//            new MyButton(entry.getKey() + "                VS.                  " + entry.getValue(), null, this, actionEvent -> actionController.declareChosenMatchToWatch(entry.getKey(),entry.getValue()));

    }

}
