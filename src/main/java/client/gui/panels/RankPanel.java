package client.gui.panels;

import client.actionController.StatusActionController;
import client.gui.myComponents.MyButton;
import client.gui.myComponents.MyJLabel;
import client.gui.myComponents.MyPanel;
import server.models.util.MyPair;


import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class RankPanel extends MyPanel {
    private MyPanel topTen, me;
    private MyJLabel rank, name, cup;
    private Color color ;
    private StatusActionController actionController;
    public RankPanel(StatusActionController actionController){
        this.actionController = actionController;
        this.setLayout(null);
        this.setBackGroundFile("b&p_bg.jpg");
        this.setPreferredSize(new Dimension(configLoader.readInteger("rankPanelWidth"), configLoader.readInteger("rankPanelHeight")));

        topTen = new MyPanel(null,true,null,this);
        topTen.setBounds(50,20,400,290);
        me = new MyPanel(null,true,null,this);
        me.setBounds(50,330,400,330);

        rank = new MyJLabel("  Rank",new Color(115, 8, 177),25,topTen,0,0,100,30);
        name = new MyJLabel("  Name",new Color(115, 8, 177),25,topTen,100,0,200,30);
        cup = new MyJLabel("  Cups",new Color(115, 8, 177),25,topTen,300,0,100,30);
        color = new Color(53, 8, 177);
        new MyButton("back to Menu", "pinkCrystal100.png", this, actionEvent -> actionController.back(),25,665);
        actionController.showRanks();
    }

    public void showTopTen(LinkedHashMap<String , Integer> players){
        int i = 1;
        for (Map.Entry<String,Integer> entry : players.entrySet()){
            new MyJLabel("   "+i,color,20,topTen,0,(i-1)*25+30,100,25);
            new MyJLabel("   "+entry.getKey(),color,20,topTen,100,(i-1)*25+30,200,25);
            new MyJLabel("   "+entry.getValue(),color,20,topTen,300,(i-1)*25+30,100,25);
            i++;
        }
    }

    public void showMyRank(LinkedHashMap<MyPair<String , Integer>,Integer> players){
        int i = 1;
        for (Map.Entry<MyPair<String, Integer>, Integer> entry : players.entrySet()){
            new MyJLabel("   "+entry.getKey().getValue(),color,20,me,0,(i-1)*25,100,25);
            new MyJLabel("   "+entry.getKey().getKey(),color,20,me,100,(i-1)*25,200,25);
            new MyJLabel("   "+entry.getValue(),color,20,me,300,(i-1)*25,100,25);
            i++;
        }
    }
}
