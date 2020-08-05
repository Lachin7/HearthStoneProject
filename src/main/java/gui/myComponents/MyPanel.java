package gui.myComponents;

import client.actionController.ActionController;
import resLoader.*;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    protected ConfigLoader configLoader;
    protected ImageLoader imageLoader;
    protected MyAudioPlayer audioPlayer;
    protected CustomComponent customComponent;
    protected String backGroundFile;
    protected Boolean grayBackground = false;

    public MyPanel(){
        imageLoader = ImageLoader.getInstance();
        configLoader = new ConfigLoader("guiConfig");
        audioPlayer = MyAudioPlayer.getInstance();
        customComponent = new CustomComponent();
    }

    public MyPanel(String backGroundFile,Boolean grayBackground,LayoutManager layoutManager,Container container){
        this();
        if(backGroundFile!=null) this.backGroundFile = backGroundFile;
        this.grayBackground = grayBackground;
        this.setLayout(layoutManager);
        this.setOpaque(false);
        if(grayBackground)this.setBorder(BorderFactory.createLineBorder(new Color(0x2F170C),3,true));
        if(container!=null)container.add(this);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(backGroundFile!= null) g.drawImage(imageLoader.loadImage(backGroundFile),0,0,null);
        if(grayBackground) {
            g.setColor(new Color(49, 49, 55, 180));
            g.fillRect(0,0,this.getWidth(),this.getHeight());
        }
    }

    public void setBackGroundFile(String backGroundFile) {
        this.backGroundFile = backGroundFile;
    }
}
