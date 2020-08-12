package client.gui.panels;

import client.actionController.PlayActionController;
import client.gui.animation.SimpleMove;
import client.gui.myComponents.GuiCard;
import client.gui.myComponents.MyButton;
import client.gui.myComponents.MyCardButton;
import client.gui.myComponents.MyPanel;
import client.gui.panels.subPanels.ChatPanel;
import client.gui.panels.subPanels.WatchListPanel;
import lombok.Getter;
import lombok.Setter;
import resLoader.MyAudioPlayer;
import server.models.Cards.Card;
import server.models.Cards.Target;
import server.models.board.Side;
import server.models.util.MyPair;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PlayPanel extends MyPanel {

    private MyButton endTurn, events, newGame, hero1, hero2, power1, power2, questRewardsFriendly, questRewardsEnemy;
    private MyCardButton selectedCard, weapon1, weapon2;
    private JLabel[] friendlyManas, enemyManas;
    private MyPanel mainPlayGround, discoverPanel;
    private int switchTimes = 0;
    private final MyAudioPlayer audioPlayer;
    @Getter
    @Setter
    private ChatPanel chatPanel;
    @Getter
    @Setter
    private WatchListPanel watchListPanel;
    private int friendlyFieldY, enemyFieldY, friendlyHandY, enemyHandY, friendlyHeroHp = 30, enemyHeroHp = 30, friendlyDeckSize = 12, enemyDeckSize = 12, friendlyMana = 0, enemyMana = 0;
    private volatile int screenX = 0, screenY = 0, myX = 0, myY = 0, currentMana = 0;
    @Getter
    private PlayActionController actionController;
    private String time = "";
    private SimpleMove simpleMove;
    private final BufferedImage rope = imageLoader.loadImage("purpleRope.png");

    public PlayPanel(PlayActionController actionController) {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(configLoader.readInteger("mainFrameWidth"), configLoader.readInteger("mainFrameHeight")));
        this.actionController = actionController;
        audioPlayer = MyAudioPlayer.getInstance();
        mainPlayGround = new MyPanel(null, false, null, this);
        mainPlayGround.setBounds(100, 0, 960, 700);
        simpleMove = new SimpleMove();
        loadConfig();
        setButtons();
        setManas();
    }

    private void setButtons() {
        events = new MyButton("Events", "pinkCrystal100.png", this, actionEvent -> actionController.showEvents(), 25, 480);
        endTurn = new MyButton("", "endTurn.png", this, actionEvent -> actionController.endTurn(), 953, 304);
        questRewardsEnemy = new MyButton("QuestRewards", "pinkCrystal100.png", this, actionEvent -> actionController.showQuestReward(Side.FRIENDLY), 25, 20);
        questRewardsFriendly = new MyButton("QuestRewards", "pinkCrystal100.png", this, actionEvent -> actionController.showQuestReward(Side.ENEMY), 25, 640);
        new MyButton("back to Menu", "pinkCrystal100.png", this, actionEvent -> actionController.back(), 25, 582);
        new MyButton("exit", "pinkCrystal100.png", this, actionEvent -> actionController.exit(), 25, 530);
        selectedCard = new MyCardButton(actionController, 150, "", 150, this, null, true);
        selectedCard.setBounds(1050, 475, 150, 207);
    }

    private void loadConfig() {
        friendlyFieldY = configLoader.readInteger("friendlyFieldY");
        enemyFieldY = configLoader.readInteger("enemyFieldY");
        friendlyHandY = configLoader.readInteger("friendlyHandY");
        enemyHandY = configLoader.readInteger("enemyHandY");
    }

    public void setHeroes(String friendlyHero, String enemyHero) {
        hero1 = new MyButton("", "hero pics/" + friendlyHero.toLowerCase() + "Icon.png", mainPlayGround, null, 431, 460, 143, 151);
        power1 = new MyButton("", "hero pics/" + friendlyHero.toLowerCase() + "HeroPower.png", mainPlayGround, null, 564, 490, 100, 102);
        hero2 = new MyButton("", "hero pics/" + enemyHero.toLowerCase() + "Icon.png", mainPlayGround, null, 431, 60, 143, 151);
        power2 = new MyButton("", "hero pics/" + enemyHero.toLowerCase() + "HeroPower.png", mainPlayGround, null, 564, 80, 100, 102);
    }

    private void setManas() {
        friendlyManas = new JLabel[10];
        for (int i = 0; i < 10; i++) {
            friendlyManas[i] = new JLabel(new ImageIcon(imageLoader.loadImage("mana.png").getScaledInstance(19, 18, Image.SCALE_SMOOTH)));
            add(friendlyManas[i]);
            friendlyManas[i].setBounds(831 + (21 * i), 642, 19, 18);
            friendlyManas[i].setVisible(false);
        }
        enemyManas = new JLabel[10];
        for (int i = 0; i < 10; i++) {
            enemyManas[i] = new JLabel(new ImageIcon(imageLoader.loadImage("mana.png").getScaledInstance(19, 18, Image.SCALE_SMOOTH)));
            add(enemyManas[i]);
            enemyManas[i].setBounds(831 + (21 * i), 42, 19, 18);
            enemyManas[i].setVisible(false);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(imageLoader.loadImage("heroHealth.png").getScaledInstance(30, 42, Image.SCALE_SMOOTH), 657, 575, null);
        g2d.drawImage(imageLoader.loadImage("heroHealth.png").getScaledInstance(30, 42, Image.SCALE_SMOOTH), 657, 170, null);
        g2d.drawImage(imageLoader.loadImage("weapons.png").getScaledInstance(35, 35, Image.SCALE_SMOOTH), 500, 570, null);
        g2d.drawImage(imageLoader.loadImage("weapons.png").getScaledInstance(35, 35, Image.SCALE_SMOOTH), 500, 170, null);
        drawChanges(g2d);
        removeDeadOnes();
    }

    public void drawChanges(Graphics2D g2d) {
        g2d.drawString(friendlyHeroHp + "", 664, 605);
        g2d.drawString(enemyHeroHp + "", 664, 200);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Areil", Font.BOLD, 14));
        g2d.drawString(friendlyDeckSize + "", 1020, 500);
        g2d.drawString(enemyDeckSize + "", 1020, 300);
        g2d.drawString(friendlyMana + "", 780, 654);
        g2d.drawString(enemyMana + "", 780, 50);
        if (!time.equals("")){
           if (Integer.parseInt(time)<=61)for (int i = 0; i < Integer.parseInt(time) - 40; i++) g2d.drawImage(rope, 245 + (i * 33), 310, null);
           if (time.contains("t"))g2d.drawString(time,920,325);
        }
        updateMana();
    }

    public void showQuestReward(HashMap<String, Integer> QR) {
        String message = "";
        for (Map.Entry<String, Integer> entry : QR.entrySet())
            message += entry.getKey() + "  :  " + entry.getValue() + "\n";
        JOptionPane.showMessageDialog(null, message, "your quest and rewards", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean haveEnoughMana(int cost) {
        return (cost <= currentMana);
    }

    private void addToPanel(Component component) {
        this.add(component);
    }

    void deleteFromPanel(MyCardButton selectedCard) {
        this.remove(selectedCard);
    }

    private void addSelectedCardListener(JButton button, long id, String name) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                deleteFromPanel(selectedCard);
                selectedCard = new MyCardButton(actionController, id, name, 150, null, null, true, 1050, 475);
                addToPanel(selectedCard);
            }
        });
    }

    private int getOppositeFieldY(int height) {
        if (height == friendlyFieldY) return enemyFieldY;
        else return friendlyFieldY;
    }

    private void removeListeners(JButton component) {
        ActionListener[] actionListeners = component.getActionListeners();
        MouseListener[] mouseListeners = component.getMouseListeners();
        for (ActionListener actionListener : actionListeners) component.removeActionListener(actionListener);
        for (MouseListener mouseListener : mouseListeners) component.removeMouseListener(mouseListener);
    }

    public void setDiscoverPanel(MyPair<Long, String> c1, MyPair<Long, String> c2, MyPair<Long, String> c3) {
        discoverPanel = new MyPanel("Choose3CardBG.jpg", true, null, mainPlayGround);
        discoverPanel.setBounds(350, 250, 500, 200);
        ActionListener actionListener = actionEvent -> {
            actionController.setDiscovery(((MyCardButton) actionEvent.getSource()).getName());
            discoverPanel.setVisible(false);
        };
        new MyCardButton(actionController, c1.getKey(), c1.getValue(), 100, discoverPanel, actionListener, true, 25, 50);
        new MyCardButton(actionController, c2.getKey(), c2.getValue(), 100, discoverPanel, actionListener, true, 175, 50);
        new MyCardButton(actionController, c3.getKey(), c3.getValue(), 100, discoverPanel, actionListener, true, 300, 50);
    }

    public void initialMoveTargeting(ArrayList<Target> targets, long id) {
        MyCardButton cardButton = getButtonInField(id);
        if (cardButton != null)
            cardButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.RED, Color.LIGHT_GRAY));
        for (JButton component : getTargets(targets)) {
            if (((MyCardButton) component).getId() != id) {
                component.setBorder(BorderFactory.createBevelBorder(1, Color.GREEN, Color.LIGHT_GRAY));
                component.addActionListener(actionEvent -> {
                    if (actionEvent.getSource() instanceof MyCardButton)
                        actionController.playTarget(id, ((MyCardButton) component).getId());
                    for (JButton b : getTargets(targets)) resetButton(b);
                    if (cardButton != null) cardButton.setBorder(null);
                    updateBothFields();
                    for (Component c : mainPlayGround.getComponents()) c.repaint();
                });
            }
        }
    }

    private MyCardButton getButtonInField(long id) {
        for (MyCardButton c : getAll()) if (c.getId() == id) return c;
        return null;
    }

    public void updateHandCards(Side side, ArrayList<GuiCard> cards, boolean allowance, boolean cardBacksVisible, int maxFieldSize) {
        int fieldHeight = getFieldHeight(side);
        int handHeight;
        if (side == Side.FRIENDLY) handHeight = friendlyHandY;
        else handHeight = enemyHandY;

        for (Component component : getFieldComponents(handHeight)) {
            mainPlayGround.remove(component);
            actionController.getClientGui().getCardButtons().remove(component);
        }
        int num = 0;
        for (GuiCard guiCard : cards) {
            MyCardButton cardButton;
            if (!cardBacksVisible) {
                cardButton = new MyCardButton(actionController, guiCard, 50, mainPlayGround, null, true, 140 + num * 50, handHeight);
                actionController.drawInformationOnCard(guiCard.getId());
                addSelectedCardListener(cardButton, guiCard.getId(), guiCard.getCardName());
            } else
                cardButton = new MyCardButton(actionController, null, 50, mainPlayGround, null, true, 140 + num * 50, handHeight);
            num++;
            if (allowance && haveEnoughMana(cardButton.getMana())) {
                cardButton.addClickListener(currentMana);
                ArrayList<MyCardButton> fieldComponents = getFieldComponents(fieldHeight);
                Rectangle bounds1 = cardButton.getBounds();
                cardButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (fieldComponents.size() == maxFieldSize && cardButton.getType() == Card.type.MINION)
                            JOptionPane.showMessageDialog(null, "you can't have more than "+maxFieldSize+" minions in your field");
                        cardButton.setCardSize(100);
                        screenX = e.getXOnScreen();
                        screenY = e.getYOnScreen();
                        myX = cardButton.getX();
                        myY = cardButton.getY();
                    }
                });
                cardButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (Math.abs(cardButton.getY() - handHeight) >= 10) {
                            if (cardButton.getType() == Card.type.SPELL) {
                                mainPlayGround.remove(cardButton);
                                audioPlayer.playQuick("spell.wav");
                            }
                            if (cardButton.getType() == Card.type.MINION) {
                                audioPlayer.playQuick("Whip Crack-SoundBible.com-330576409.wav");
                                int x = cardButton.getX();
                                if (fieldComponents.size() == 0) cardButton.setBounds(140, fieldHeight, 100, 138);
                                else {
                                    fieldComponents.sort(MyCardButton::compareTo);
                                    Collections.sort(fieldComponents);
                                    if (cardButton.getX() > (fieldComponents.size() - 1) * 100 + 140) {
                                        cardButton.setBounds(fieldComponents.size() * 100 + 140, fieldHeight, 100, 138);
                                    } else {
                                        for (Component component : fieldComponents) {
                                            if (component.getX() >= x) {
                                                final Rectangle bounds = component.getBounds();
                                                cardButton.setBounds(bounds);
                                                component.setBounds(component.getX() + 100, fieldHeight, 100, 138);
//                                            if (component.getX() >= x) {
//
                                            }
//                                                for (Component component1 : fieldComponents) {
//                                                    if (component1.getX() >= x)
//                                                        component1.setBounds(component1.getX()+100,fieldHeight,component1.getWidth(),component1.getHeight());
////                                                        simpleMove.animate((JComponent) component1, new Point(component1.getX() + 100, fieldHeight), 1000, 1000);
////                                                    try {
////                                                        Thread.sleep(100);
////                                                    } catch (InterruptedException interruptedException) {
////                                                        interruptedException.printStackTrace();
////                                                    }
//                                                }
//                                                cardButton.setBounds(bounds);
//                                                fieldComponents.add(cardButton);
//                                                Collections.sort(fieldComponents);
//                                                break;
//                                            }
                                        }

                                    }
                                }
                                fieldComponents.add(cardButton);
                            }
                            afterHandToField(guiCard.getId());
                        } else {
                            cardButton.setCardSize(50);
                            cardButton.setBounds(bounds1);
                        }
                    }
                });
                cardButton.addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        int deltaX = e.getXOnScreen() - screenX;
                        int deltaY = e.getYOnScreen() - screenY;
                        if (myX + deltaX >= 0 && myY + deltaY >= 0 && myX + deltaX <= 1200 && myY + deltaY <= 700)
                            cardButton.setLocation(myX + deltaX, myY + deltaY);
                    }
                });
            }
        }
        mainPlayGround.repaint();
        revalidate();
    }

    public void afterHandToField(long id) {
        actionController.initialPlay(id);
        updateBothFields();
        actionController.drawPlayChanges();
        actionController.initialMoveTargeting(id);
        updateHands();
        updateBothFields();
    }

    public void updateHands() {
        actionController.drawPlayChanges();
        actionController.updateHandCards(Side.FRIENDLY);
        actionController.updateHandCards(Side.ENEMY);

    }

    public void updateFieldCards(Side side, ArrayList<GuiCard> cards, boolean allowance, boolean tauntExist) {
        int fieldHeight = getFieldHeight(side);
        for (Component component : getFieldComponents(fieldHeight)) {
            mainPlayGround.remove(component);
            actionController.getClientGui().getCardButtons().remove(component);
        }
        int i = 0;
        for (GuiCard guiCard : cards) {
            MyCardButton cardButton = new MyCardButton(actionController, guiCard, 100, mainPlayGround, null, true, 140 + i * 100, fieldHeight);
            actionController.drawInformationOnCard(guiCard.getId());
            i++;
            addSelectedCardListener(cardButton, guiCard.getId(), guiCard.getCardName());
            if (allowance) {
                if (cardButton.canAttack() && getFieldComponents(getOppositeFieldY(fieldHeight)).size() != 0) {
                    cardButton.setBorder(BorderFactory.createBevelBorder(1, Color.ORANGE, Color.LIGHT_GRAY));
                    cardButton.addActionListener(e -> {
                        for (MyCardButton enemyCardButton : getFieldComponents(getOppositeFieldY(fieldHeight))) {
                            if (!tauntExist || enemyCardButton.isTaunt()) {
                                enemyCardButton.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLACK));
                                enemyCardButton.addActionListener(actionEvent -> {
                                    actionController.attack(guiCard.getId(), enemyCardButton.getId());
                                    audioPlayer.playQuick("attack.wav");
                                    resetButton(hero2);
                                    resetButton(hero1);
                                    updateBothFields();
                                });
                                if (!tauntExist) setHeroAsTarget(side, cardButton);
                            }
                        }
                    });
                }
            }
        }
        for (Component component : mainPlayGround.getComponents()) component.repaint();
        mainPlayGround.repaint();
        revalidate();
    }

    private void removeDeadOnes() {
        for (MyCardButton c : getAll()) if (c.getHp() <= 0 && c.getHp() > -30) mainPlayGround.remove(c);
    }

    private Side getOppositeSide() {
        return getCurrentSide().getOpposite();
    }

    private Side getCurrentSide() {
        if (switchTimes % 2 == 0) return Side.FRIENDLY;
        return Side.ENEMY;
    }

    private void resetButton(JButton button) {
        removeListeners(button);
        button.setBorder(null);
    }

    private void setHeroAsTarget(Side side, MyCardButton cardButton) {
        if (side == Side.ENEMY) {
            resetButton(hero2);
            hero1.setBorderPainted(true);
            hero1.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLACK));
            hero1.addActionListener(actionEvent -> {
                //todo
                actionController.attack(cardButton.getId(),null);
                resetButton(cardButton);
                resetButton(hero1);
            });
        } else {
            resetButton(hero1);
            hero2.setBorderPainted(true);
            hero2.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLACK));
            hero2.addActionListener(actionEvent -> {
                //todo
                actionController.attack(cardButton.getId(),null);
//                boardController.attack(attacker, boardController.getEnemyPlayer().getPlayersChoosedHero());
                resetButton(cardButton);
                resetButton(hero2);
            });
        }
    }

    private ArrayList<MyCardButton> getFieldComponents(int height) {
        ArrayList<MyCardButton> result = new ArrayList<>();
        for (Component component : mainPlayGround.getComponents())
            if (component.getY() == height) result.add((MyCardButton) component);
        result.sort(MyCardButton::compareTo);
        Collections.sort(result);
        return result;
    }

    private ArrayList<JButton> getTargets(ArrayList<Target> targets) {
        ArrayList<JButton> result = new ArrayList<>();
        for (Target target : targets) {
            if (target == Target.FRIENDLY_MINION) result.addAll(getFieldComponents(friendlyFieldY));
            else if (target == Target.FRIENDLY_HERO) result.add(hero1);
            else if (target == Target.ENEMY_MINION) result.addAll(getFieldComponents(enemyFieldY));
            else if (target == Target.ENEMY_HERO) result.add(hero2);
        }
        return result;
    }

    public void updateMana() {
        setVisible(enemyManas, Math.min(enemyMana, 10));
        setVisible(friendlyManas, Math.min(friendlyMana, 10));
        if (switchTimes % 2 == 0) currentMana = friendlyMana;
        else currentMana = enemyMana;
    }

    public void setVisible(JLabel[] labels, int i) {
        if (i < 0) i = 0;
        for (int j = 0; j < i; j++) labels[j].setVisible(true);
        for (int j = i; j < labels.length; j++) labels[j].setVisible(false);
    }

    public void setChanges(int friendlyHeroHp, int enemyHeroHp, int friendlyDeckSize, int enemyDeckSize, int friendlyMana, int enemyMana, String time) {
        this.friendlyHeroHp = friendlyHeroHp;
        this.enemyHeroHp = enemyHeroHp;
        this.friendlyDeckSize = friendlyDeckSize;
        this.enemyDeckSize = enemyDeckSize;
        this.friendlyMana = friendlyMana;
        this.enemyMana = enemyMana;
        this.time = time;
        repaint();
        revalidate();
    }

    public void updateBothFields() {
        actionController.drawPlayChanges();
        actionController.updateFieldCards(getOppositeSide(), getFieldComponents(getFieldHeight(getOppositeSide())));
        actionController.updateFieldCards(getCurrentSide(), getFieldComponents(getFieldHeight(getCurrentSide())));
    }

    private int getFieldHeight(Side side) {
        if (side == Side.FRIENDLY) return friendlyFieldY;
        return enemyFieldY;
    }


    private ArrayList<MyCardButton> getAll() {
        ArrayList<MyCardButton> all = new ArrayList<>();
        all.addAll(getFieldComponents(friendlyFieldY));
        all.addAll(getFieldComponents(enemyFieldY));
        return all;
    }

    public void setUpHeroPowers(Side side, boolean allowance, boolean hasEnoughMana, boolean hasTarget) {
        ActionListener powerListener = actionEvent -> {
            actionController.playHeroPower();
            if (hasTarget) {
                for (MyCardButton cardButton : getAll()) {
                    cardButton.setBorder(BorderFactory.createBevelBorder(1, Color.BLUE, Color.GRAY));
                    cardButton.addActionListener(actionEvent1 -> {
                        actionController.playTargetedPower(cardButton.getId());
                        for (MyCardButton cardButton1 : getAll()) resetButton(cardButton1);
                        updateBothFields();
                    });
                }
                resetButton(power1);
                resetButton(power2);
            }
        };
        if (allowance && hasEnoughMana && side == Side.FRIENDLY) {
            resetButton(power2);
            power1.setBorderPainted(true);
            power1.setBorder(BorderFactory.createBevelBorder(1, Color.MAGENTA, Color.GRAY));
            power1.addActionListener(powerListener);
        } else if (allowance && hasEnoughMana && side == Side.ENEMY) {
            resetButton(power1);
            power2.setBorderPainted(true);
            power2.setBorder(BorderFactory.createBevelBorder(1, Color.MAGENTA, Color.GRAY));
            power2.addActionListener(powerListener);
        }
    }

    public void addSwitch() {
        switchTimes++;
    }


//    @Override
//    public void actionPerformed(ActionEvent actionEvent) {
//        actionController.log(((JButton) actionEvent.getSource()).getText() + " button clicked - Collection");
//        //
//        if (actionEvent.getSource() == newGame) {
//            int ans = JOptionPane.showConfirmDialog(null, "are you sure you want to start a new game ?", "warning", JOptionPane.YES_NO_OPTION);
//            if (ans == JOptionPane.YES_OPTION)
//                actionController.getClientGui().goToPanel("play");
//        }
//    }

//    private MyCardButton getComponentInGame(long id) {
//        for (Component cardButton : mainPlayGround.getComponents()) {
//            if (cardButton instanceof MyCardButton && ((MyCardButton) cardButton).getId() == (id))
//                return (MyCardButton) cardButton;
//        }
//        return null;
//    }

}