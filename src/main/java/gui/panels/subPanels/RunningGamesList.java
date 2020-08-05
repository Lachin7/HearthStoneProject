package gui.panels.subPanels;

import client.actionController.PlayActionController;
import gui.myComponents.MyButton;
import gui.myComponents.MyPanel;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RunningGamesList extends MyPanel {
    private HashMap<String,String> names;
    private PlayActionController actionController;

    public RunningGamesList(PlayActionController actionController) {
        this.actionController = actionController;
        this.setLayout(new FlowLayout());
        this.setBackGroundFile("oldPaperBg.jpg");
        this.setPreferredSize(new Dimension(configLoader.readInteger("WatchListPanelWidth"), configLoader.readInteger("WatchListPanelHeight")));
    }

    public void setLabels(HashMap<String, String> names){
        for (Map.Entry<String,String> entry : names.entrySet())
            new MyButton(entry.getKey() + "                VS.                  " + entry.getValue(), "Choose3CardBG.jpg", this, actionEvent -> actionController.declareChosenMatchToWatch(entry.getKey(),entry.getValue()));

    }

}
