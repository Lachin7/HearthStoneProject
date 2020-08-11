package client.gui.myComponents;

import client.actionController.ActionController;
import lombok.Getter;
import lombok.Setter;
import server.models.Cards.Card;
import resLoader.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

public class MyCardButton extends JButton implements Comparable<JButton> {

    private ImageLoader imageLoader;
    private String cardName;
    private long id;
    @Setter
    @Getter
    private int width, height, mana=-1, hp=-30, attack=-30,durability=-30;
    private Graphics g;
    @Getter
    private Card.type type;
    private ActionController actionController;
    private boolean isInGame=false,canAttack=false,hasTaunt=false,locked=false,hasShield=false;

    public MyCardButton(ActionController actionController, long id, String cardName, int width, Container container, ActionListener actionListener, boolean isInGame) {
        imageLoader = ImageLoader.getInstance();
        this.actionController = actionController;
        this.isInGame = isInGame;
        if (cardName != null && !cardName.equals("")) {
            this.setIcon(new ImageIcon(imageLoader.getCardsImages().get(cardName).getScaledInstance(width, width * 136 / 100, Image.SCALE_SMOOTH)));
            this.setName(cardName);
            this.id = id;
            this.cardName = cardName;
            actionController.getClientGui().getCardButtons().put(id,this);
            actionController.drawInformationOnCard(id);
        }
        else setIcon(new ImageIcon(imageLoader.loadImage("BlushRoomCardBack.png").getScaledInstance(width, width * 136 / 100, Image.SCALE_SMOOTH)));
        setSize(width, width * 136 / 100);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorder(null);
        this.width = width;
        this.height = width * 136 / 100;
        if (container != null) container.add(this);
        if (actionListener != null) addActionListener(actionListener);

    }

    public MyCardButton(ActionController actionController, long id, String cardName, int width, Container container, ActionListener actionListener, boolean isInGame,int x ,int y) {
        this(actionController,id,cardName,width,container,actionListener,isInGame);
        setBounds(x,y,width,height);
    }

        public void setCardSize(int width) {
        setSize(width, width * 136 / 100);
        setPreferredSize(new Dimension(width, width * 136 / 100));
        this.width = width;
        this.height = width * 136 / 100;
        setIcon(new ImageIcon(imageLoader.getCardsImages().get(cardName).getScaledInstance(width, width * 136 / 100, Image.SCALE_SMOOTH)));
        //todo have the filed version of the cards
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.g = graphics;
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Ariel", Font.BOLD, width / 6));
        if (mana != -1) g.drawString(mana + "", width / 9, height / 85 * 15);
        if (hp != -30) g.drawString(hp + "", width * 45 / 53, height * 92 / 100);
        if (attack != -30) g.drawString(attack + "", width / 9, height * 92 / 100);
        if (durability != -30) g.drawString(durability + "", width * 45 / 53, height * 92 / 100);
        if (locked && !isInGame) {
            g.drawImage(imageLoader.loadImage("LockedforCards.png").getScaledInstance(40, 40, Image.SCALE_SMOOTH), width / 2 - 20, 5, null);
//            setIcon(new ImageIcon(makeGrayImage(imageLoader.getCardsImages().get(cardName)).getScaledInstance(width, width * 138 / 100, Image.SCALE_SMOOTH)));
        }
        if (hasShield && isInGame) g.drawString("D shield", width / 3, height / 2);
        if (hasTaunt && isInGame) g.drawString("Taunt", width / 3, height / 3);
    }

    public void drawInformationOnCard(int mana, int hp, int attack, int durability, boolean locked, boolean hasShield, boolean hasTaunt,boolean canAttack, Card.type type) {
        this.mana = mana;
        this.hp = hp ;
        this.attack = attack;
        this.durability = durability;
        this.locked  = locked;
        this.hasShield = hasShield;
        this.type = type;
        this.canAttack = canAttack;
        this.hasTaunt = hasTaunt;
        repaint();
        revalidate();
    }

    private static BufferedImage makeGrayImage(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        ColorConvertOp colorConvertOp = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        colorConvertOp.filter(image, result);
        return result;
    }

    public void addClickListener(int total) {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
          if (!isInGame || mana<=total) setBorder(BorderFactory.createEtchedBorder(Color.MAGENTA, Color.BLACK));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                setBorder(null);
            }
        });
    }

    public long getId() {
        return id;
    }

    @Override
    public int compareTo(JButton o) {
        return this.getX() - o.getX();
    }

    public boolean canAttack() {
        return canAttack;
    }

    public boolean isTaunt() {
        return hasTaunt;
    }
}

