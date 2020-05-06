package gui.myComponents;

import gui.ResLoader;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    private ResLoader resLoader;
    protected String backGroundFile;
    protected Boolean grayBackground = false;

    public MyPanel(){
        resLoader = new ResLoader();
    }

    public MyPanel(String backGroundFile,Boolean grayBackground,LayoutManager layoutManager,Container container){
        resLoader = new ResLoader();
        if(backGroundFile!=null) this.backGroundFile = backGroundFile;
        this.grayBackground = grayBackground;
        this.setLayout(layoutManager);
        this.setOpaque(false);
        this.setBorder(BorderFactory.createLineBorder(new Color(0x2F170C),3,true));
        if(container!=null)container.add(this);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(backGroundFile!= null) g.drawImage(resLoader.imageLoader(backGroundFile),0,0,null);
        if(grayBackground==true) {
            g.setColor(new Color(49, 49, 55, 180));
            g.fillRect(0,0,this.getWidth(),this.getHeight());
        }
    }
}
