package gui.myComponents;

import client.actionController.ActionController;
import javafx.util.Pair;
import models.Cards.Card;
import server.controller.CardController;
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

public class MyCardButton extends JButton implements Comparable<JButton> {

    private final ImageLoader imageLoader = ImageLoader.getInstance();
    private String cardName;
    private long id;
    private int width, height, mana, hp;
    private Graphics g;
    private Card.type type;
    private ActionController actionController;
    private boolean isInGame,canAttack,isTaunt;

    public MyCardButton(ActionController actionController, long id, String cardName, int width, Container container, ActionListener actionListener, boolean isInGame) {
        this.actionController = actionController;
        this.isInGame = isInGame;
        if (cardName != null && !cardName.equals("")) {
            this.setIcon(new ImageIcon(imageLoader.getCardsImages().get(cardName).getScaledInstance(width, width * 136 / 100, Image.SCALE_SMOOTH)));
            this.setName(cardName);
            this.id = id;
            this.cardName = cardName;
        }
        setSize(width, width * 136 / 100);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorder(null);
        this.width = getWidth();
        this.height = getHeight();
        if (container != null) container.add(this);
        if (actionListener != null) addActionListener(actionListener);
    }

    public void setCardSize(int width) {
        setSize(width, width * 136 / 100);
        setPreferredSize(new Dimension(width, width * 136 / 100));
        setIcon(new ImageIcon(imageLoader.getCardsImages().get(cardName).getScaledInstance(width, width * 136 / 100, Image.SCALE_SMOOTH)));
        //todo have the filed version of the cards
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.g = graphics;
        if (cardName != null && !cardName.equals("")) actionController.drawInformationOnCard(this, id);
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Ariel", Font.BOLD, width / 6));
    }

    public void drawInformationOnCard(int mana, int hp, int attack, int durability, boolean locked, boolean hasShield, boolean hasTaunt,boolean canAttack, Card.type type) {
        if (mana != -1) g.drawString(mana + "", width / 9, height / 85 * 15);
        if (hp != -1) g.drawString(hp + "", width * 45 / 53, height * 92 / 100);
        if (attack != -30) g.drawString(attack + "", width / 9, height * 92 / 100);
        if (durability != -30) g.drawString(durability + "", width * 45 / 53, height * 92 / 100);
        if (locked && !isInGame) {
            g.drawImage(imageLoader.loadImage("LockedforCards.png").getScaledInstance(40, 40, Image.SCALE_SMOOTH), width / 2 - 20, 5, null);
            setIcon(new ImageIcon(makeGrayImage(imageLoader.getCardsImages().get(cardName)).getScaledInstance(width, width * 138 / 100, Image.SCALE_SMOOTH)));
        }
        if (hasShield) g.drawString("D shield", width / 3, height / 2);
        if (hasTaunt) g.drawString("Taunt", width / 3, height / 3);
        this.mana = mana;
        this.type = type;
        this.canAttack = canAttack;
        this.isTaunt = hasTaunt;
//        if(cardName.equals("")) this.setIcon(new ImageIcon(imageLoader.loadImage(Controller.getInstance().getMainPlayer().getCardSkin()).getScaledInstance(width,width*136/100 +3,Image.SCALE_SMOOTH)));

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
                setBorder(BorderFactory.createEtchedBorder(Color.MAGENTA, Color.BLACK));
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

    public int getMana() {
        return mana;
    }

    public Card.type getType() {
        return type;
    }

    public boolean canAttack() {
        return canAttack;
    }

    public int getHp() {
        return hp;
    }

    public boolean isTaunt() {
        return isTaunt;
    }
}

