package gui.panels;

import client.actionController.PlayActionController;
import gui.animation.SimpleMove;
import gui.myComponents.MyButton;
import gui.myComponents.MyCardButton;
import gui.myComponents.MyJLabel;
import gui.myComponents.MyPanel;
import gui.panels.subPanels.ChatPanel;
import javafx.util.Pair;
import lombok.Getter;
import models.Cards.Card;
import models.Cards.Target;
import models.board.Side;
import resLoader.MyAudioPlayer;
import server.controller.util.EndTurnThread;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PlayPanel extends MyPanel implements ActionListener {

    private MyButton endTurn, events, newGame, hero1, hero2, power1, power2, questRewardsFriendly, questRewardsEnemy;
    private MyCardButton selectedCard, weapon1, weapon2;
    private JLabel[] friendlyManas, enemyManas;
    private JLabel timeRemaining;
    private final MyPanel mainPlayGround;
    private MyPanel discoverPanel;
    private int activeTurn = 0, animationTims = 0;
    private final MyAudioPlayer audioPlayer;
    private final SimpleMove simpleMove;
    private EndTurnThread endTurnThread;
    @Getter
    private ChatPanel chatPanel;
    //    private final Object monitor = new Object();
    private int friendlyFieldY, enemyFieldY, friendlyHandY, enemyHandY;
    private volatile int screenX = 0, screenY = 0, myX = 0, myY = 0, currentMana = 0;
    private Graphics2D g2d;
    private PlayActionController actionController;

    public PlayPanel(PlayActionController actionController) {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(configLoader.readInteger("mainFrameWidth"), configLoader.readInteger("mainFrameHeight")));
        this.actionController = actionController;
        actionController.setBattleGroundPic();
        audioPlayer = MyAudioPlayer.getInstance();
        simpleMove = new SimpleMove();
        mainPlayGround = new MyPanel(null, false, null, this);
        mainPlayGround.setBounds(100, 0, 960, 700);
        loadConfig();
        setButtons();
        actionController.setHeroes();
        setManas();
        actionController.setUpHeroPowers();
        actionController.updateMana();
        actionController.updateHandCards(Side.FRIENDLY);
        actionController.updateHandCards(Side.ENEMY);
        endTurnThread = new EndTurnThread(this);
        endTurnThread.start();
    }

    private void setButtons() {
        events = new MyButton("Events", "pinkCrystal100.png", this, actionEvent -> actionController.showEvents(), 1080, 300);
        endTurn = new MyButton("", "endTurn.png", this, this, 953, 304);
        newGame = new MyButton("new Game", "pinkCrystal100.png", this, this, 1080, 150);
        questRewardsEnemy = new MyButton("QuestRewrds", "pinkCrystal100.png", this, this, 25, 80);
        questRewardsFriendly = new MyButton("QuestRewrds", "pinkCrystal100.png", this, this, 25, 500);
        customComponent.exit(this, 1080, 200);
        customComponent.backToMenuButton(this, 1080, 250, actionController);
        selectedCard = new MyCardButton(actionController, 150, "", 150, this, null, true);
        selectedCard.setBounds(1050, 380, 150, 207);
        timeRemaining = new MyJLabel("", Color.RED, 40, this, 100, 300, 100, 100);
    }

    private void loadConfig() {
        friendlyFieldY = configLoader.readInteger("friendlyFieldY");
        enemyFieldY = configLoader.readInteger("enemyFieldY");
        friendlyHandY = configLoader.readInteger("friendlyHandY");
        enemyHandY = configLoader.readInteger("enemyHandY");
    }

    public void setHeroes(String friendlyHero, String enemyHero) {
        hero1 = new MyButton("", "hero pics/" + friendlyHero.toLowerCase() + "Icon.png", mainPlayGround, this, 431, 460, 143, 151);
        power1 = new MyButton("", "hero pics/" + friendlyHero.toLowerCase() + "HeroPower.png", mainPlayGround, this, 564, 490, 100, 102);
        hero2 = new MyButton("", "hero pics/" + enemyHero.toLowerCase() + "Icon.png", mainPlayGround, this, 431, 60, 143, 151);
        power2 = new MyButton("", "hero pics/" + enemyHero.toLowerCase() + "HeroPower.png", mainPlayGround, this, 564, 80, 100, 102);
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
        this.g2d = g2d;
        g2d.drawImage(imageLoader.loadImage("heroHealth.png").getScaledInstance(30, 42, Image.SCALE_SMOOTH), 657, 575, null);
        g2d.drawImage(imageLoader.loadImage("heroHealth.png").getScaledInstance(30, 42, Image.SCALE_SMOOTH), 657, 170, null);
        g2d.drawImage(imageLoader.loadImage("weapons.png").getScaledInstance(35, 35, Image.SCALE_SMOOTH), 500, 570, null);
        g2d.drawImage(imageLoader.loadImage("weapons.png").getScaledInstance(35, 35, Image.SCALE_SMOOTH), 500, 170, null);
        actionController.drawPlayChanges();
        actionController.updateMana();
        removeDeadOnes();
    }

    public void drawChanges(int friendlyHeroHp, int enemyHeroHp, int friendlyDeckSize, int enemyDeckSize, int friendlyMana, int enemyMana) {
        g2d.drawString(friendlyHeroHp + "", 664, 605);
        g2d.drawString(enemyHeroHp + "", 664, 200);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Areil", Font.BOLD, 14));
        g2d.drawString(friendlyDeckSize + "", 1020, 500);
        g2d.drawString(enemyDeckSize + "", 1020, 300);
        g2d.drawString(friendlyMana + "", 780, 654);
        g2d.drawString(enemyMana + "", 780, 42);
        actionController.DrawQuestReward();
    }

    public void drawQuestReward(HashMap<String, Integer> friendlyQR, HashMap<String, Integer> enemyQR) {
        int i = 0, j = 0;
        for (Map.Entry<String, Integer> entry : friendlyQR.entrySet()) {
            g2d.drawString(entry.getKey(), 50, 650 - i * 50);
            g2d.drawString(entry.getValue() + "", 25, 650 - i * 50);
            i++;
        }
        for (Map.Entry<String, Integer> entry : enemyQR.entrySet()) {
            g2d.drawString(entry.getValue() + "", 25, 300 + j * 50);
            g2d.drawString(entry.getKey(), 50, 300 + j * 50);
            j++;
        }
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
                selectedCard = new MyCardButton(actionController, id, name, 150, null, null, true);
                selectedCard.setBounds(1050, 380, 150, 207);
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

    public void setDiscoverPanel(Pair<Long, String> c1, Pair<Long, String> c2, Pair<Long, String> c3) {
        discoverPanel = new MyPanel("Choose3CardBG.jpg", true, null, mainPlayGround);
        discoverPanel.setBounds(350, 250, 500, 200);
        ActionListener actionListener = actionEvent -> {
            actionController.setDiscovery(((MyCardButton) actionEvent.getSource()).getName());
            discoverPanel.setVisible(false);
        };
        MyCardButton card1 = new MyCardButton(actionController, c1.getKey(), c1.getValue(), 100, discoverPanel, actionListener, true);
        card1.setBounds(25, 50, card1.getWidth(), card1.getHeight());
        MyCardButton card2 = new MyCardButton(actionController, c1.getKey(), c1.getValue(), 100, discoverPanel, actionListener, true);
        card2.setBounds(175, 50, card1.getWidth(), card1.getHeight());
        MyCardButton card3 = new MyCardButton(actionController, c1.getKey(), c1.getValue(), 100, discoverPanel, actionListener, true);
        card3.setBounds(300, 50, card1.getWidth(), card1.getHeight());
    }

    public void initialMoveTargeting(ArrayList<Target> targets, long id) {
        updateBothFields();
        MyCardButton cardButton = getButtonInField(id);
        if (cardButton != null)
            cardButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.RED, Color.LIGHT_GRAY));
        for (JButton component : getTargets(targets)) {
            if (((MyCardButton) component).getId() != id) {
                component.setBorder(BorderFactory.createBevelBorder(0, Color.GREEN, Color.LIGHT_GRAY));
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

    public void updateHandCards(Side side, HashMap<Long, String> cards, boolean allowance) {
        int fieldHeight = getFieldHeight(side);
        int handHeight;
        if (side == Side.FRIENDLY) handHeight = friendlyHandY;
        else handHeight = enemyHandY;

        for (Component component : getFieldComponents(handHeight)) mainPlayGround.remove(component);
        int num = 0;
        for (Map.Entry<Long, String> entry : cards.entrySet()) {
            MyCardButton cardButton = new MyCardButton(actionController, entry.getKey(), entry.getValue(), 50, mainPlayGround, null, true);
            cardButton.setBounds(140 + num * cardButton.getWidth(), handHeight, 50, 69);
            num++;
            addSelectedCardListener(cardButton, entry.getKey(), entry.getValue());
            if (allowance && haveEnoughMana(cardButton.getMana())) {
                cardButton.addClickListener();
                ArrayList<MyCardButton> fieldComponents = getFieldComponents(fieldHeight);
                Rectangle bounds = cardButton.getBounds();
                cardButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (fieldComponents.size() == 7 && cardButton.getType() == Card.type.MINION)
                            JOptionPane.showMessageDialog(null, "you can't have more than 7 minions in your field");
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
                            if (cardButton.getType() == Card.type.SPELL) mainPlayGround.remove(cardButton);
                            if (cardButton.getType() == Card.type.MINION) {
                                int x = cardButton.getX();
                                boolean finallySettled = false;
                                if (fieldComponents.size() == 0) cardButton.setBounds(140, fieldHeight, 100, 138);
                                else {
                                    fieldComponents.sort(MyCardButton::compareTo);
                                    if (cardButton.getX() > (fieldComponents.size() - 1) * 100 + 140) {
                                        cardButton.setBounds(fieldComponents.size() * 100 + 140, fieldHeight, 100, 138);
                                    } else {
                                        for (Component component : fieldComponents) {
                                            if (component.getX() >= x) {
                                                final Rectangle bounds = component.getBounds();
                                                for (Component component1 : fieldComponents) {
                                                    if (component1.getX() >= x)
                                                        simpleMove.animate((JComponent) component1, new Point(component1.getX() + 100, fieldHeight), 1000, 1000);
                                                    try {
                                                        Thread.sleep(100);
                                                    } catch (InterruptedException interruptedException) {
                                                        interruptedException.printStackTrace();
                                                    }
                                                }
                                                cardButton.setBounds(bounds);
                                                fieldComponents.add(cardButton);
                                                Collections.sort(fieldComponents);
                                                break;
                                            }
                                        }
                                    }
                                }
                                fieldComponents.add(cardButton);
                            }
                            afterHandToField(entry.getKey());
                        } else {
                            cardButton.setCardSize(100);
                            cardButton.setBounds(bounds);
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
        actionController.initialMoveTargeting(id);
        updateHands();
    }

    public void updateHands() {
        actionController.updateHandCards(Side.FRIENDLY);
        actionController.updateHandCards(Side.ENEMY);
    }

    public void updateFieldCards(Side side, HashMap<Long, String> cards, boolean allowance, boolean tauntExist) {
        int fieldHeight = getFieldHeight(side);
        for (Component component : getFieldComponents(fieldHeight)) mainPlayGround.remove(component);
        int i = 0;
        for (Map.Entry<Long, String> entry : cards.entrySet()) {
            MyCardButton cardButton = new MyCardButton(actionController, entry.getKey(), entry.getValue(), 50, mainPlayGround, null, true);
            cardButton.setBounds(140 + i * 100, fieldHeight, 100, 137);
            addSelectedCardListener(cardButton, entry.getKey(), entry.getValue());
            i++;
            if (allowance) {
                if (cardButton.canAttack() && getFieldComponents(getOppositeFieldY(fieldHeight)).size() != 0) {
                    cardButton.setBorder(BorderFactory.createBevelBorder(0, Color.ORANGE, Color.LIGHT_GRAY));
                    cardButton.addActionListener(e -> {
                        for (MyCardButton enemyCardButton : getFieldComponents(getOppositeFieldY(fieldHeight))) {
                            if ((!tauntExist || cardButton.isTaunt())) {
                                enemyCardButton.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLACK));
                                enemyCardButton.addActionListener(actionEvent -> {
                                    actionController.attack(entry.getKey(), enemyCardButton.getId());
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
        if (activeTurn % 2 == 0) return Side.ENEMY;
        return Side.FRIENDLY;
    }

    private Side getCurrentSide() {
        if (activeTurn % 2 == 0) return Side.FRIENDLY;
        return Side.ENEMY;
    }

    private void resetButton(JButton button) {
        removeListeners(button);
        button.setBorderPainted(false);
    }

    private void setHeroAsTarget(Side side, MyCardButton cardButton) {
        if (side == Side.ENEMY) {
            resetButton(hero2);
            hero1.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLACK));
            hero1.setBorderPainted(true);
            hero1.addActionListener(actionEvent -> {
                //todo
//                actionController.attack(cardButton.getId(),null);
                resetButton(cardButton);
                resetButton(hero1);
            });
        } else {
            resetButton(hero1);
            hero2.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLACK));
            hero2.setBorderPainted(true);
            hero2.addActionListener(actionEvent -> {
                //todo
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

    public void updateMana(int friendlyMana, int enemyMana) {
        setVisible(getEnemyManas(), Math.min(friendlyMana, 10));
        setVisible(getFriendlyManas(), Math.min(enemyMana, 10));
        if (activeTurn % 2 == 0) currentMana = friendlyMana;
        else currentMana = enemyMana;
    }

    public void setVisible(JLabel[] labels, int i) {
        if (i < 0) i = 0;
        for (int j = 0; j < i; j++) labels[j].setVisible(true);
        for (int j = i; j < labels.length; j++) labels[j].setVisible(false);
    }

    public void updateBothFields() {
        actionController.updateFieldCards(getOppositeSide(), getFieldComponents(getFieldHeight(getOppositeSide())));
        actionController.updateFieldCards(getCurrentSide(), getFieldComponents(getFieldHeight(getCurrentSide())));
    }

    private int getFieldHeight(Side side) {
        if (side == Side.FRIENDLY) return friendlyFieldY;
        return enemyFieldY;
    }

    public void endTurn() {
        if (endTurnThread.isRunning()) endTurnThread.setRunning(false);
        activeTurn++;
        actionController.endTurn();
        audioPlayer.playQuick("poker-chips-daniel_simon.wav");
        actionController.updateMana();
        audioPlayer.playQuick("drawCard.wav");
        updateHands();
        updateBothFields();
        actionController.setUpHeroPowers();
        endTurnThread = new EndTurnThread(this);
        endTurnThread.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        actionController.log(((JButton) actionEvent.getSource()).getText() + " button clicked - Collection");
//        if (actionEvent.getSource() == questRewardsFriendly) {
//            String result = "";
//            for (QuestRewardMaker questAndReward : boardController.getFriendlyQuestRewards())
//                result += ("" + questAndReward.getName() + " :  " + questAndReward.getPercent() + "\n");
//            JOptionPane.showMessageDialog(null, result, boardController.getFriendlyPlayer().getPlayerName(), JOptionPane.INFORMATION_MESSAGE);
//        } else if (actionEvent.getSource() == questRewardsEnemy) {
//            String result = "";
//            for (QuestRewardMaker questAndReward : boardController.getEnemyQuestRewards())
//                result += ("" + questAndReward.getName() + " :  " + questAndReward.getPercent() + "\n");
//            JOptionPane.showMessageDialog(null, result, boardController.getEnemyPlayer().getPlayerName(), JOptionPane.INFORMATION_MESSAGE);
//        } else if (actionEvent.getSource() == endTurn) endTurn();

        //todo
        if (actionEvent.getSource() == newGame) {
            int ans = JOptionPane.showConfirmDialog(null, "are you sure you want to start a new game ?", "warning", JOptionPane.YES_NO_OPTION);
            if (ans == JOptionPane.YES_OPTION)
                actionController.getClient().goToPanel(actionController.getClient().getPlayPanel());
        }
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
            power2.setBorderPainted(false);
            power1.setBorderPainted(true);
            power1.setBorder(BorderFactory.createBevelBorder(1, Color.MAGENTA, Color.GRAY));
            power1.addActionListener(powerListener);
        } else if (allowance && hasEnoughMana && side == Side.ENEMY) {
            power1.setBorderPainted(false);
            power2.setBorderPainted(true);
            power2.setBorder(BorderFactory.createBevelBorder(1, Color.MAGENTA, Color.GRAY));
            power2.addActionListener(powerListener);
        }
    }

    public JLabel[] getFriendlyManas() {
        return friendlyManas;
    }

    public JLabel[] getEnemyManas() {
        return enemyManas;
    }

    public JLabel getTimeRemaining() {
        return timeRemaining;
    }

//    private MyCardButton getComponentInGame(long id) {
//        for (Component cardButton : mainPlayGround.getComponents()) {
//            if (cardButton instanceof MyCardButton && ((MyCardButton) cardButton).getId() == (id))
//                return (MyCardButton) cardButton;
//        }
//        return null;
//    }
//
//    if (boardController.getFriendlyQuestRewards().size() != 0)
//    drawQuestReward(g, boardController.getFriendlyQuestRewards(), 650);
//        if (boardController.getEnemyQuestRewards().size() != 0)
//    drawQuestReward(g, boardController.getEnemyQuestRewards(), 300);
//        if (boardController.getFriendlyPlayer().getPlayersChoosedHero().getWeapon() != null) {
//        weapon1 = new MyCardButton(boardController.getFriendlyPlayer().getPlayersChoosedHero().getWeapon().getName(), 80, this);
//        weapon1.setBounds(350, 550, weapon1.getWidth(), weapon1.getHeight());
//    }
//        if (boardController.getEnemyPlayer().getPlayersChoosedHero().getWeapon() != null) {
//        weapon2 = new MyCardButton(boardController.getEnemyPlayer().getPlayersChoosedHero().getWeapon().getName(), 80, this);
//        weapon2.setBounds(350, 100, weapon2.getWidth(), weapon2.getHeight());
//    }
}