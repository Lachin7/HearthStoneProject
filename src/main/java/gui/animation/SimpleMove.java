package gui.animation;

import gui.myComponents.MyCardButton;
import gui.panels.PlayPanel;
import models.Cards.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class SimpleMove {

    ArrayList<MyCardButton> buttons;
    Object monitor;
    boolean isRunning =true;
    int sizeToReach = 0;

    public  SimpleMove(Object monitor,ArrayList<MyCardButton> buttons){
       this.buttons = buttons;
       this.monitor = monitor;
    }

    public SimpleMove(){

    }

    public  void animate(JComponent component, Point newPoint, int frames, int interval) {

        Rectangle compBounds = component.getBounds();


        Point oldPoint = new Point(compBounds.x, compBounds.y),
                animFrame = new Point((newPoint.x - oldPoint.x) / frames,
                        (newPoint.y - oldPoint.y) / frames);

        new Timer(interval, new ActionListener() {
            int currentFrame = 0;
            public void actionPerformed(ActionEvent e) {
                component.setBounds(oldPoint.x + (animFrame.x * currentFrame),
                        oldPoint.y + (animFrame.y * currentFrame),
                        compBounds.width,
                        compBounds.height);

                if (currentFrame != frames) {
                    currentFrame++;
//                    System.out.println(size);
//                    System.out.println(sizeToReach);
//                    System.out.println("kkkkk");
//                    if(component.getLocation()==newPoint.getLocation()){
//                        sizeToReach++;
//                        if(size==sizeToReach)playPanel.afterHandToDeck(card,cardButton);
//                        System.out.println(size);
//                        System.out.println(sizeToReach);
//                    }

                }


                else{

                    ((Timer)e.getSource()).stop();
                }

            }
        }).start();
    }
    public void move(Container container ,JComponent component, Point newPoint, int duration, int delay){
        double deltaX = newPoint.getX()-component.getX(), deltaY = newPoint.getY()-component.getY();
        double xMove = deltaX/(duration/delay), yMove = deltaY/(duration/delay);
        int x = component.getX(), y = component.getY();
        for (int i = 0; i < duration/delay; i++) {
             x = (int) (x+xMove);
             y = (int) (y+yMove);
            component.setBounds(x,y,component.getWidth(),component.getHeight());
            container.repaint();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
