package gui.myComponents;

import controller.Controller;
import gui.animation.SimpleMove;
import models.Cards.Card;
import controller.CardController;
import models.Cards.Minion;
import models.Cards.Weapon;
import resLoader.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.util.ArrayList;

public class MyCardButton extends JButton implements Comparable<JButton> {

   protected Boolean locked ;
   private ImageLoader imageLoader = ImageLoader.getInstance();
   private CardController cardController = new CardController();
   private SimpleMove simpleMove;
   private String cardName;
   private long id ;
   private boolean isInGame = false;
   private Card card;
   private int manaCost;


    public MyCardButton(String cardName , int width, Container container){
        if(cardName.equals("")) this.setIcon(new ImageIcon(imageLoader.loadImage(Controller.getInstance().getMainPlayer().getCardSkin()).getScaledInstance(width,width*136/100 +3,Image.SCALE_SMOOTH)));
        else{
            this.cardName=cardName;
            this.setIcon(new ImageIcon(imageLoader.loadImage("Cards/" + cardName + ".png").getScaledInstance(width,width*136/100,Image.SCALE_SMOOTH)));
        }
        this.setSize(width, width * 136 / 100);
        if(!cardName.equals("")) {
            this.setName(cardName);
            Card card = cardController.createCard(cardName);
            locked = cardController.isLocked(card);
            this.setToolTipText("Name:" + card.getName() + ", " +
                    "type:" + card.getType() + ", " + "Class:" + card.getHeroClass() + ", " + "price:" + card.getPrice() + ", " +
                    "ManaCost:" + card.getManaCost() + ", " + "Description:" + card.getDescription() + ", " + "Rarity:" + card.getRarity());
            manaCost = card.getManaCost();
            id = card.getId();
            this.card = card;

        if(locked) this.setIcon(new ImageIcon(makeGrayImage( imageLoader.loadImage("Cards/" + cardName + ".png")).getScaledInstance(width,width*138/100,Image.SCALE_SMOOTH)));
        }
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorder(null);
        if(container!=null)container.add(this);
    }

    public MyCardButton(Card card, int width, Container container){
        this(card.getName(),width,container);
        id = card.getId();
        this.card = card;
        this.isInGame = true;
    }

    public MyCardButton(String cardName, int width, Container container, ActionListener actionListener){
        this(cardName,width,container);
        addActionListener(actionListener);
    }

    public void setCardSize(int width){
        setSize(width, width*136/100);
        setPreferredSize(new Dimension(width, width*136/100));
        setIcon(new ImageIcon(imageLoader.loadImage("Cards/" + cardName + ".png").getScaledInstance(width,width*136/100,Image.SCALE_SMOOTH)));
        //todo have the filed version of the cards
    }



    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
       if(locked!=null && locked && !isInGame) {
           graphics.drawImage(imageLoader.loadImage("LockedforCards.png").getScaledInstance(40,40,Image.SCALE_SMOOTH),this.getWidth()/2-20,5,null);
       }
       int width = this.getWidth();
       int height = this.getHeight();
       graphics.setColor(Color.WHITE);
       graphics.setFont(new Font("Ariel",Font.BOLD,width/6));
       graphics.drawString(manaCost +"",width/9,height/85*15);
       if(card!=null){
           if(card instanceof Minion){
               graphics.drawString(((Minion) card).getAttack()+"",width/9,height*92/100);
               graphics.drawString(((Minion) card).getHP()+"",width*45/53,height*92/100);
           }
           if(card instanceof Weapon){
               graphics.drawString(((Weapon) card).getAttack()+"",width/9,height*92/100);
               graphics.drawString(((Weapon) card).getDurability()+"",width*45/53,height*92/100);
           }
       }
       if(isInGame){
           if(card instanceof Minion){
               //todo make it better
               if(((Minion) card).hasDivineShield()) graphics.drawString("D shield",width/3,height/2);
               if(((Minion) card).hasTaunt()) graphics.drawString("Taunt",width/3,height/3);
           }
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
            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createEtchedBorder(Color.MAGENTA,Color.BLACK));

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
               myCardButton.setSize(width2,width2*136/100);
               myCardButton.setBounds(x,y,width2,width2*136/100);
               container.add(myCardButton);
            }
            @Override
            public void mouseExited(MouseEvent mouseEvent){
                container.remove(myCardButton);
            }
        });

    }

    private volatile int draggedAtX, draggedAtY;

    public void addDragButton(Container parent,Container target){

        addMouseMotionListener(new MouseAdapter(){
            ArrayList<Component> cards = new ArrayList<>();
            public void mousePressed(MouseEvent e){
                setSize(100, 138);
                setPreferredSize(new Dimension(100, 138));
                draggedAtX = e.getX();
                draggedAtY = e.getY();
                for(Component component : target.getComponents()) cards.add(component);

            }

            @Override
            public void mouseDragged(MouseEvent e) {
                target.add((Component) e.getSource());
                parent.remove((Component) e.getSource());
                setSize(50, 69);
                setPreferredSize(new Dimension(50, 69));
                setLocation(e.getX() - draggedAtX + getLocation().x, e.getY() - draggedAtY + getLocation().y);
            }

            @Override
            public void mouseReleased(MouseEvent e){
                for(Component component : cards){
//                    if(component.getX())
                }
//                (Component) ((Component) e.getSource()).setBounds();
            }
        });

    }

    public long getId() {
        return id;
    }

    public void addCanAttackListener(ArrayList<Component> characters) {

    }

    @Override
    public int compareTo(JButton o) {
        return this.getX()-o.getX();
    }
}

