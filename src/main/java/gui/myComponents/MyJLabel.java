package gui.myComponents;

import javax.swing.*;
import java.awt.*;

public class MyJLabel extends JLabel {

    public MyJLabel(String text, Color foreGround, int fontSize, Container container, int x , int y, int width, int height){
        setText(text);
        setForeground(foreGround);
        Font font = new Font("Ariel",Font.BOLD,fontSize);
        setFont(font);
        container.add(this);
        setBounds(x,y, width,height);
    }

}
