package client.gui.panels.subPanels;

import client.actionController.PlayActionController;
import client.gui.myComponents.MyJLabel;
import client.gui.myComponents.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class WatchListPanel extends MyPanel {

    private HashMap<String,MyJLabel> viewers;

    private PlayActionController actionController;
    public WatchListPanel(PlayActionController actionController) {
        super(null, true, new FlowLayout(), null);
        this.actionController = actionController;
//        this.setBackGroundFile("oldPaperBg.jpg");
        this.setPreferredSize(new Dimension(configLoader.readInteger("WatchListPanelWidth"), configLoader.readInteger("WatchListPanelHeight")));
        viewers = new HashMap<>();
    }


    public void addMember(String name){
      MyJLabel label =   new MyJLabel(name,Color.MAGENTA,16,this,0,0,150,20);
      label.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
             int ans= JOptionPane.showConfirmDialog(null,"do you want to dismiss this viewer?","",JOptionPane.YES_NO_OPTION);
             if (ans==JOptionPane.YES_OPTION) {
                 actionController.dismissViewer(name);
             }
          }
      });
      viewers.put(name,label);
    }

    public void deleteMember(String name) {
       remove( viewers.get(name));
       this.removeAll();
       viewers.remove(name);
       for (MyJLabel label: viewers.values()) this.add(label);
       this.repaint();
       revalidate();
    }
}
