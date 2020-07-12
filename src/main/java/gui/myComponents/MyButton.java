package gui.myComponents;

import resLoader.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class MyButton extends JButton {

    private final ImageLoader imageLoader = ImageLoader.getInstance();
    private final String IconFile;
    private String name;
    private long id ;

    public MyButton(String name, String IconFile, Container container, ActionListener actionListener){
        this.IconFile = IconFile;
        this.name = name;
        Font font = new Font("",Font.BOLD,12);
        BufferedImage image = imageLoader.loadImage(IconFile);
        this.setFont(font);
        this.setText(name);
        this.setIcon(new ImageIcon(image));
        this.setPreferredSize(new Dimension(image.getWidth(),image.getHeight()));
        this.setSize(image.getWidth(),image.getHeight());
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setRolloverEnabled(false);
        this.setBorderPainted(false);
        this.setAlignmentX(JButton.CENTER_ALIGNMENT);
        this.setFocusable(false);
        if(container!=null) container.add(this);
        if(actionListener!= null) this.addActionListener(actionListener);
    }

    public MyButton(String name, String IconFile, Container container, ActionListener actionListener, int width, int height){
        this(name,IconFile,container,actionListener);
        this.setIcon(new ImageIcon(imageLoader.loadImage(IconFile).getScaledInstance(width,height,Image.SCALE_SMOOTH)));
        this.setSize(width,height);
        this.setPreferredSize(new Dimension(width,height));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIconFile() {
        return IconFile;
    }

    @Override
    public String getName() {
        return name;
    }
}
