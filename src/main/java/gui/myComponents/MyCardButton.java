package gui.myComponents;

import controller.Controller;
import gui.panels.SettingsPanel;
import models.Cards.Card;
import controller.CardController;
import gui.ResLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

public class MyCardButton extends JButton {

   protected Boolean locked ;
   private ResLoader resLoader = new ResLoader();
   private CardController cardController = new CardController();


    public MyCardButton(String cardName , int width, Container container){
        if(cardName==null) this.setIcon(new ImageIcon(resLoader.imageLoader(Controller.getInstance().getCurrentPlayer().getCardSkin()).getScaledInstance(width,width*138/100 +3,Image.SCALE_SMOOTH)));
        else this.setIcon(new ImageIcon(resLoader.imageLoader("Cards/" + cardName + ".png").getScaledInstance(width,width*138/100,Image.SCALE_SMOOTH)));
        this.setSize(width, width * 138 / 100);
        if(cardName!=null) {
            this.setName(cardName);
            Card card = cardController.creatCard(cardName);
            locked = cardController.isLocked(card);
            this.setToolTipText("Name:" + card.getName() + ", " +
                    "type:" + card.getType() + ", " + "Class:" + card.getHeroClass() + ", " + "price:" + card.getPrice() + ", " +
                    "ManaCost:" + card.getManaCost() + ", " + "Description:" + card.getDescription() + ", " + "Rarity:" + card.getRarity());
        if(locked) this.setIcon(new ImageIcon(makeGrayImage( resLoader.imageLoader("Cards/" + cardName + ".png")).getScaledInstance(width,width*138/100,Image.SCALE_SMOOTH)));
        }
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorder(null);
//        this.addClickListener();
        if(container!=null)container.add(this);
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
       if(locked!=null && locked) {
           graphics.drawImage(resLoader.imageLoader("LockedforCards.png").getScaledInstance(40,40,Image.SCALE_SMOOTH),this.getWidth()/2-20,5,null);
       }
    }

    private static BufferedImage makeGrayImage(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        ColorConvertOp colorConvertOp = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        colorConvertOp.filter(image, result);
        return result;
    }



    public void addClickListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                setBorder(BorderFactory.createEtchedBorder(Color.GREEN,Color.BLACK));

            }
            @Override
            public void mouseExited(MouseEvent mouseEvent){
                setBorder(null);
            }
        });
    }



    public void addMakeBiggerListener(Container container, int width2, int x, int y) {
        MyCardButton myCardButton = this;
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent mouseEvent){
               myCardButton.setSize(width2,width2*138/100);
               myCardButton.setBounds(x,y,width2,width2*138/100);
               container.add(myCardButton);
            }
            @Override
            public void mouseExited(MouseEvent mouseEvent){
                container.remove(myCardButton);
            }
        });

    }

    private volatile int draggedAtX, draggedAtY;

    public void addDragButton(Component component,Container parent,Container target){

        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                setSize(100, 138);
                setPreferredSize(new Dimension(100, 138));
                draggedAtX = e.getX();
                draggedAtY = e.getY();
                parent.add((Component) e.getSource());
//                removeActionListener(addMakeBiggerListener);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(e.getX() - draggedAtX + getLocation().x, e.getY() - draggedAtY + getLocation().y);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e){
                target.add(component);
            }
        });
    }




}

