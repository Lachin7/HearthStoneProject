package gui.panels;

import com.google.gson.annotations.Expose;
import controller.modes.AI;
import controller.util.EndTurnThread;
import controller.util.QuestRewardMaker;
import gui.GameFrame;
import gui.animation.SimpleMove;
import models.Cards.Minion;
import models.Cards.Target;
import models.Cards.spells.questAndReward.QuestAndReward;
import models.Character;
import resLoader.MyAudioPlayer;
import models.Cards.Card;
import controller.*;
import gui.myComponents.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;

public class PlayPanel extends MyPanel implements ActionListener {

    @Expose private MyButton endTurn, events, newGame, hero1, hero2, power1, power2, questRewardsFriendly, questRewardsEnemy;
    @Expose private MyCardButton selectedCard, weapon1, weapon2;
    @Expose private JLabel[] friendlyManas, enemyManas;
    @Expose private JLabel timeRemaining;
    @Expose private final MyPanel mainPlayGround;
    @Expose private MyPanel discoverPanel;
    @Expose private final BoardController boardController;
    @Expose private int activeTurn = 0, animationTims =0;
    @Expose private final MyAudioPlayer audioPlayer;
    @Expose private final SimpleMove simpleMove;
    @Expose private EndTurnThread endTurnThread;
    @Expose private final Object monitor = new Object();
    @Expose private int friendlyFieldY, enemyFieldY, friendlyHandY, enemyHandY;
    @Expose private volatile int screenX = 0, screenY=0, myX = 0, myY = 0;

    public PlayPanel(BoardController boardController) {
       this.setLayout(null);
       this.setPreferredSize(new Dimension(configLoader.readInteger("mainFrameWidth"),configLoader.readInteger("mainFrameHeight")));
       this.backGroundFile = Controller.getInstance().getMainPlayer().getPlayBackGround();
       audioPlayer = MyAudioPlayer.getInstance();
       this.boardController = boardController;
        simpleMove = new SimpleMove();

       mainPlayGround = new MyPanel(null,false,null,this);
       mainPlayGround.setBounds(100,0,960,700);
        loadConfig();
        setButtons();
        setHeroes();
        setManas();
        addActionListenerToPower();
        updateMana();

       audioPlayer.playMainMusic("PlayGound.wav");
       updateHandCards(friendlyHandY, this.boardController.getFriendlyHandCards(),activeTurn);
       updateHandCards(enemyHandY, this.boardController.getEnemyHandCards(),activeTurn+1);

       endTurnThread = new EndTurnThread(this);
       endTurnThread.start();
   }

    private void setButtons(){
       events = new MyButton("Events", "pinkCrystal100.png", this, this);
       events.setBounds(1080, 300, events.getWidth(), events.getHeight());
       endTurn = new MyButton("", "endTurn.png", this, this);
       endTurn.setBounds(953, 304, endTurn.getWidth(), endTurn.getHeight());
       newGame = new MyButton("new Game","pinkCrystal100.png",this,this);
       newGame.setBounds(1080,150,newGame.getWidth(),newGame.getHeight());
       questRewardsEnemy = new MyButton("QuestRewrds","pinkCrystal100.png", this, this);
       questRewardsEnemy.setBounds(25,80,questRewardsEnemy.getWidth(),questRewardsEnemy.getHeight());
       questRewardsFriendly = new MyButton("QuestRewrds","pinkCrystal100.png",this, this);
       questRewardsFriendly.setBounds(25,500,questRewardsFriendly.getWidth(),questRewardsFriendly.getHeight());
       customComponent.exit(this,1080,200);
       customComponent.backToMenuButton(this,1080,250,this);
       selectedCard = new MyCardButton("",150,this);
       selectedCard.setBounds(1050,380, 150,207);
       timeRemaining = new JLabel();
       timeRemaining.setBounds(100,300,100,100);
       this.add(timeRemaining);

   }

    private void loadConfig(){
       friendlyFieldY = configLoader.readInteger("friendlyFieldY");
       enemyFieldY = configLoader.readInteger("enemyFieldY");
       friendlyHandY = configLoader.readInteger("friendlyHandY");
       enemyHandY =configLoader.readInteger("enemyHandY");
   }

    private void setHeroes(){
        hero1 = new MyButton("","hero pics/"+boardController.getFriendlyPlayer().getPlayersChoosedHero().toString().toLowerCase()+"Icon.png", mainPlayGround, this,140,150);
        hero1.setBounds(431,460,143,151);
        power1 = new MyButton("","hero pics/"+boardController.getFriendlyPlayer().getPlayersChoosedHero().toString().toLowerCase()+"HeroPower.png", mainPlayGround, this,100,102);
        power1.setBounds(564,490,100,102);

        hero2 = new MyButton("","hero pics/mageIcon.png", mainPlayGround, this,140,150);
        hero2.setBounds(431,60,143,151);
        power2 = new MyButton("","hero pics/mageHeroPower.png", mainPlayGround, this,100,102);
        power2.setBounds(564,80,100,102);
    }

    private void setManas(){
       friendlyManas = new JLabel[10];
       for (int i = 0; i < 10; i++) {
           friendlyManas[i] = new JLabel(new ImageIcon(imageLoader.loadImage("mana.png").getScaledInstance(19, 18, Image.SCALE_SMOOTH)));
           this.add(friendlyManas[i]);
           friendlyManas[i].setBounds(831 + (21 * i), 642, 19, 18);
           friendlyManas[i].setVisible(false);
       }
       enemyManas = new JLabel[10];
        for (int i = 0; i < 10; i++) {
            enemyManas[i] = new JLabel(new ImageIcon(imageLoader.loadImage("mana.png").getScaledInstance(19, 18, Image.SCALE_SMOOTH)));
            this.add(enemyManas[i]);
            enemyManas[i].setBounds(831 + (21 * i), 42, 19, 18);
            enemyManas[i].setVisible(false);
        }
   }

   @Override
   public void paintComponent(Graphics g){
       super.paintComponent(g);
       Graphics2D g2d = (Graphics2D) g;
       g2d.drawImage(imageLoader.loadImage("heroHealth.png").getScaledInstance(30,42,Image.SCALE_SMOOTH),657,575,null);
       g2d.drawImage(imageLoader.loadImage("heroHealth.png").getScaledInstance(30,42,Image.SCALE_SMOOTH),657,170,null);
       g2d.drawImage(imageLoader.loadImage("weapons.png").getScaledInstance(35,35,Image.SCALE_SMOOTH),500,570,null);
       g2d.drawImage(imageLoader.loadImage("weapons.png").getScaledInstance(35,35,Image.SCALE_SMOOTH),500,170,null);
       g2d.drawString(boardController.getFriendlyPlayer().getPlayersChoosedHero().getHP()+"",664,605);
       g2d.drawString(boardController.getEnemyPlayer().getPlayersChoosedHero().getHP()+"",664,200);
       g2d.setColor(Color.WHITE);
       g2d.setFont(new Font("Areil",Font.BOLD,14));
       g2d.drawString(boardController.getFriendlyPlayer().getCurrentMana()+"/10",780,654);
       g2d.drawString(boardController.getEnemyPlayer().getCurrentMana()+"/10",780,42);
       g2d.drawString(boardController.getFriendlyPlayer().getDeckCardsInGame().size()+"",1020,500);
       g2d.drawString(boardController.getEnemyPlayer().getDeckCardsInGame().size()+"",1020,300);
       if(boardController.getFriendlyQuestRewards().size()!=0) drawQuestReward(g,boardController.getFriendlyQuestRewards(),650);
       if(boardController.getEnemyQuestRewards().size()!=0) drawQuestReward(g,boardController.getEnemyQuestRewards(),300);
       if(boardController.getFriendlyPlayer().getPlayersChoosedHero().getWeapon()!=null){
           weapon1 = new MyCardButton(boardController.getFriendlyPlayer().getPlayersChoosedHero().getWeapon().getName(),80,this);
           weapon1.setBounds(350,550,weapon1.getWidth(),weapon1.getHeight());
       }
       if(boardController.getEnemyPlayer().getPlayersChoosedHero().getWeapon()!=null){
           weapon2 = new MyCardButton(boardController.getEnemyPlayer().getPlayersChoosedHero().getWeapon().getName(),80,this);
           weapon2.setBounds(350,100,weapon2.getWidth(),weapon2.getHeight());
       }
       updateMana();
   }

   private void drawQuestReward(Graphics g,ArrayList<QuestRewardMaker> questRewardMakers, int height){
       int i = 0;
       for(QuestRewardMaker questRewardMaker : questRewardMakers){
           if(boardController.getFriendlyQuestRewards().contains(questRewardMaker)){
               g.drawString(questRewardMaker.getName(),50,height-i*50);
               g.drawString(questRewardMaker.getPercent()+"",25,height-i*50);
           }
           else {
               g.drawString(questRewardMaker.getPercent()+"",25,height+i*50);
               g.drawString(questRewardMaker.getName(),50,height+i*50);
           }
           i++;
       }
   }

    private boolean haveEnoughMana(int cost){
        return (cost<=getCurrentMana());
    }

    private void addToPanel(Component component){
        this.add(component);
    }
    void deleteFromPanel(MyCardButton selectedCard){
        this.remove(selectedCard);
    }
    private void addSelectedCardListener(JButton button, Card card){
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                deleteFromPanel(selectedCard);
                selectedCard = new MyCardButton(card,150,GameFrame.getInstance().getPlayPanel());
                selectedCard.setBounds(1050,380, 150,207);
                addToPanel(selectedCard);
            }
        });
    }

    private int getOppositeFieldY(int height){
        if(height==friendlyFieldY)return enemyFieldY;
        else return friendlyFieldY;
    }

    private void removeListeners(JButton component){
        ActionListener[] actionListeners =  component.getActionListeners();
        MouseListener[] mouseListeners = component.getMouseListeners();
        for(ActionListener actionListener : actionListeners)component.removeActionListener(actionListener);
        for(MouseListener mouseListener : mouseListeners) component.removeMouseListener(mouseListener);
    }

    public void setDiscoverPanel(String card1Name, String card2Name, String card3Name){
        discoverPanel = new MyPanel("Choose3CardBG.jpg",true,null,mainPlayGround);
        discoverPanel.setBounds(350,250,500,200);
        ActionListener actionListener = actionEvent -> {
            boardController.setDiscovery(((MyCardButton)actionEvent.getSource()).getName());
            discoverPanel.setVisible(false);
        };
        MyCardButton card1 = new MyCardButton(card1Name,100,discoverPanel,actionListener);
        card1.setBounds(25,50,card1.getWidth(),card1.getHeight());
        MyCardButton card2 = new MyCardButton(card2Name,100,discoverPanel,actionListener);
        card2.setBounds(175,50,card1.getWidth(),card1.getHeight());
        MyCardButton card3 = new MyCardButton(card3Name,100,discoverPanel,actionListener);
        card3.setBounds(300,50,card1.getWidth(),card1.getHeight());
        card1.addActionListener(actionListener);card2.addActionListener(actionListener);card3.addActionListener(actionListener);
    }

    public void addToField(Card card, int turn){
        int fieldHeight;
        if(turn%2==0) fieldHeight = friendlyFieldY;
        else fieldHeight = enemyFieldY;
        MyCardButton cardButton = new MyCardButton(card,100,mainPlayGround);
        cardButton.setBounds(getFieldComponents(fieldHeight).size() * 100 + 145, fieldHeight, 100, 138);
    }

    public void initialMoveTargeting(Card card, MyCardButton cardButton) {
        updateFieldCards(friendlyFieldY,1);
        updateFieldCards(enemyFieldY,1);
        if(cardButton!=null)cardButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.RED, Color.LIGHT_GRAY));
        for (JButton component : getTargets(card)) {
            if (((MyCardButton) component).getId()!= cardButton.getId()) {
            component.setBorder(BorderFactory.createBevelBorder(0, Color.GREEN, Color.LIGHT_GRAY));
                component.addActionListener(actionEvent -> {
                    if (actionEvent.getSource() instanceof MyCardButton)
                        boardController.playTarget(card, ((MyCardButton) component).getId());
//                    else boardController.playTarget(card, component.getName());
                    for (JButton component1 : getTargets(card)) {
                        component1.setBorder(null);
                        removeListeners(component1);
                    }
                    cardButton.setBorder(null);
                    updateBothFields();
                    for (Component componentw : mainPlayGround.getComponents()) componentw.repaint();
                });
            }
        }
    }

    private void updateHandCards(int handHeight , ArrayList<Card> cards, int turn) {
        for (Component component : getFieldComponents(handHeight)) {
            mainPlayGround.remove(component);
        }
        int num = 0;
        for (Card card : cards) {
            MyCardButton cardButton = new MyCardButton(card, 50, mainPlayGround);
            cardButton.setBounds(140 + num * cardButton.getWidth(), handHeight, 50, 69);
            num++;
            addSelectedCardListener(cardButton,card);
            if (turn % 2 == 0 && haveEnoughMana(card.getManaCost())) {
                cardButton.addClickListener();
                ArrayList<MyCardButton> fieldComponents;
                int fieldHeight;
                if (handHeight == friendlyHandY) {
                    fieldComponents = getFieldComponents(friendlyFieldY);
                    fieldHeight = friendlyFieldY;
                } else {
                    fieldComponents = getFieldComponents(enemyFieldY);
                    fieldHeight = enemyFieldY;
                }
                Rectangle bounds = cardButton.getBounds();
                cardButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (fieldComponents.size() == 7 && card instanceof Minion) JOptionPane.showMessageDialog(null, "you can't have more than 7 minions in your field");
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
                            if (card.getType() == Card.type.SPELL) mainPlayGround.remove(cardButton);
                            if (card.getType() == Card.type.MINION) {
                                int x = cardButton.getX();
                                boolean finallySettled = false;
                                if (fieldComponents.size() == 0) cardButton.setBounds(140, fieldHeight, 100, 138);
                                else { fieldComponents.sort(MyCardButton::compareTo);
                                        if (cardButton.getX() > (fieldComponents.size()-1)*100+140) {
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
                            afterHandToDeck(card,cardButton);
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
                        if(myX + deltaX>=0 && myY + deltaY>=0 && myX + deltaX <= 1200 && myY + deltaY<=700) cardButton.setLocation(myX + deltaX, myY + deltaY);
                    }
                });
            }
        }
        mainPlayGround.repaint();
        revalidate();
    }

    public void afterHandToDeck(Card card, MyCardButton cardButton){
        boardController.initialPlay(card);
        updateFieldCards(getOppositeFieldY(getCurrentField()), 1);
        updateFieldCards(getCurrentField(), 0);
        if (card.getHasInitialMoveTarget()) initialMoveTargeting(card, cardButton);
       updateHands();
    }

    public void updateHands(){
        if(activeTurn%2==0)updateHandCards(friendlyHandY,boardController.getFriendlyHandCards(),0);
        else updateHandCards(enemyHandY,boardController.getEnemyHandCards(),0);
    }

    private void updateFieldCards(int fieldHeight, int turn){
        boardController.syncFriendlyFieldComponents(getFieldComponents(friendlyFieldY));
        if(!(boardController instanceof AI))boardController.syncEnemyFieldComponents(getFieldComponents(enemyFieldY));
        for (Component component : getFieldComponents(fieldHeight)) mainPlayGround.remove(component);
        ArrayList<Minion> cards ;
        if(fieldHeight==friendlyFieldY) cards = boardController.getFriendlyFieldCards();
        else cards = boardController.getEnemyFieldCards();
        int i=0;
        for(Minion minion : cards){
            MyCardButton cardButton = new MyCardButton(minion,100,mainPlayGround);
            cardButton.setBounds(140+i*100,fieldHeight,100,137);
            addSelectedCardListener(cardButton,minion);
            i++;
            if(turn%2==0){
                if(minion.canAttack() && getFieldComponents(getOppositeFieldY(fieldHeight)).size()!=0) {
                    cardButton.setBorder(BorderFactory.createBevelBorder(0, Color.ORANGE, Color.LIGHT_GRAY));
                    cardButton.addActionListener(e -> {
                        for (MyCardButton enemyCardButton : getFieldComponents(getOppositeFieldY(fieldHeight))) {
                            if ((!boardController.tauntExist(activeTurn)) || (((Minion) boardController.getCharacterOfTarget(enemyCardButton.getId())).hasTaunt())) {
                                enemyCardButton.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLACK));
                                enemyCardButton.addActionListener(actionEvent -> {
                                    boardController.attack(minion, boardController.getCharacterOfTarget(enemyCardButton.getId()));
                                    minion.setCanAttack(false);
                                    removeListeners(hero2);hero2.setBorderPainted(false);
                                    removeListeners(hero1);hero1.setBorderPainted(false);
                                    updateFieldCards(getOppositeFieldY(fieldHeight), turn);
                                    updateFieldCards(fieldHeight, turn);
                                });
                                if(!boardController.tauntExist(activeTurn))setHeroAsTarget(minion,cardButton);
                            }
                        }
                    });
                }
            }
        }
        for(Component component : mainPlayGround.getComponents())component.repaint();
        mainPlayGround.repaint();
        revalidate();
    }

    private void setHeroAsTarget(Minion attacker,MyCardButton cardButton){
        if(activeTurn%2==1){
            removeListeners(hero2);
            hero1.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLACK));
            hero1.setBorderPainted(true);
            hero1.addActionListener(actionEvent -> {
                boardController.attack(attacker,boardController.getFriendlyPlayer().getPlayersChoosedHero());
                removeListeners(cardButton);
                hero1.removeActionListener(this);
                hero1.setBorderPainted(false);
            });
        }
        else {
            removeListeners(hero1);
            hero2.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLACK));
            hero2.setBorderPainted(true);
            hero2.addActionListener(actionEvent -> {
                boardController.attack(attacker,boardController.getEnemyPlayer().getPlayersChoosedHero());
                removeListeners(cardButton);
                hero2.removeActionListener(this);
                hero2.setBorderPainted(false);
            });
        }
    }

    private ArrayList<MyCardButton> getFieldComponents(int height){
        ArrayList<MyCardButton> result = new ArrayList<>();
       for(Component component : mainPlayGround.getComponents()){
           if(component.getY()== height) {
               result.add((MyCardButton) component);
           }
       }
        result.sort(MyCardButton::compareTo);
        return  result;
    }

    private ArrayList <JButton> getTargets(Card card){
        ArrayList <JButton> result = new ArrayList<>();
       for(Target target : card.getTargets()) {
           if(target==Target.FRIENDLY_MINION) result.addAll(getFieldComponents(friendlyFieldY));
           if(target==Target.FRIENDLY_HERO) result.add(hero1);
           if(target==Target.ENEMY_MINION)result.addAll(getFieldComponents(enemyFieldY));
           if(target==Target.ENEMY_HERO)result.add(hero2);
       }
       return result;
    }

    public void updateMana(){
        setVisible(getEnemyManas(),boardController.getEnemyPlayer().getCurrentMana());
        setVisible(getFriendlyManas(), boardController.getFriendlyPlayer().getCurrentMana());
    }

    public void setVisible(JLabel[] labels, int i){
        if(i<0) i=0;
        for (int j = 0; j < i; j++) {
            labels[j].setVisible(true);
        }
        for (int j = i; j < labels.length ; j++) {
            labels[j].setVisible(false);
        }
    }

    private int getCurrentMana(){
        return boardController.getCurrentPlayer().getCurrentMana();
    }

    public void updateBothFields(){
        if(activeTurn%2==0) {
            updateFieldCards(enemyFieldY, activeTurn + 1);
            updateFieldCards(friendlyFieldY, activeTurn);
        }
        else {
            updateFieldCards(enemyFieldY, activeTurn);
            updateFieldCards(friendlyFieldY, activeTurn + 1);
        }
    }

    public void endTurn() {
        if(endTurnThread.isRunning())endTurnThread.setRunning(false);
        activeTurn++;
        boardController.endTurn();
        audioPlayer.playQuick("poker-chips-daniel_simon.wav");
        updateMana();
        audioPlayer.playQuick("drawCard.wav");
        updateHandCards(friendlyHandY, boardController.getFriendlyHandCards(),activeTurn);
        updateHandCards(enemyHandY, boardController.getEnemyHandCards(),activeTurn+1);
        updateBothFields();
        addActionListenerToPower();
        endTurnThread = new EndTurnThread(this);
        endTurnThread.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO,((JButton)actionEvent.getSource()).getText()+" button clicked - Collection");
        if(actionEvent.getSource()==events) {
            try {
                JOptionPane.showMessageDialog(null, Files.readString(boardController.getEventsLog().toPath(), StandardCharsets.US_ASCII), "Events in this game",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(actionEvent.getSource()==questRewardsFriendly){
            String result = "";
            for(QuestRewardMaker questAndReward : boardController.getFriendlyQuestRewards()) result += (""+questAndReward.getName()+ " :  "+ questAndReward.getPercent() + "\n");
            JOptionPane.showMessageDialog(null, result, boardController.getFriendlyPlayer().getPlayerName(), JOptionPane.INFORMATION_MESSAGE);
        }
        else if(actionEvent.getSource()==questRewardsEnemy){
            String result = "";
            for(QuestRewardMaker questAndReward : boardController.getEnemyQuestRewards()) result += (""+questAndReward.getName()+ " :  "+ questAndReward.getPercent() + "\n");
            JOptionPane.showMessageDialog(null, result, boardController.getEnemyPlayer().getPlayerName(), JOptionPane.INFORMATION_MESSAGE);
        }
        else if(actionEvent.getSource()==endTurn)endTurn();
        else if(actionEvent.getSource()==newGame) GameFrame.getInstance().goToPanel("playPanel");
    }

    private int getCurrentField(){
        if(activeTurn%2==0)return friendlyFieldY;
        return enemyFieldY;
    }

    private ArrayList<MyCardButton> getAll(){
        ArrayList<MyCardButton> all = new ArrayList<>();
        all.addAll(getFieldComponents(friendlyFieldY));
        all.addAll(getFieldComponents(enemyFieldY));
        return all;
    }

    private void addActionListenerToPower(){
        ActionListener powerListener = actionEvent -> {
            boardController.playHeroPower();
            for (MyCardButton cardButton : getAll()){
                cardButton.setBorder(BorderFactory.createBevelBorder(1, Color.BLUE, Color.GRAY));
                cardButton.addActionListener(actionEvent1 -> {
                    boardController.playTargetetPower(cardButton.getId());
                    for (MyCardButton cardButton1: getAll())removeListeners(cardButton1);
                    updateBothFields();
                });
            }
            power1.removeActionListener(this);power1.setBorderPainted(false);
            power2.removeActionListener(this);power2.setBorderPainted(false);
        };
        if(activeTurn%2==0){
            if(boardController.hasManaForPower()) {
                power2.setBorderPainted(false);
                power1.setBorderPainted(true);
                power1.setBorder(BorderFactory.createBevelBorder(1, Color.MAGENTA, Color.GRAY));
                power1.addActionListener(powerListener);
            }
        }
        else {
            if (boardController.hasManaForPower()&&!(boardController instanceof AI)) {
                power1.setBorderPainted(false);
                power2.setBorderPainted(true);
                power2.setBorder(BorderFactory.createBevelBorder(1, Color.MAGENTA, Color.GRAY));
                power2.addActionListener(powerListener);
            }
        }
    }

    public JLabel[] getFriendlyManas() {
        return friendlyManas;
    }
    public JLabel[] getEnemyManas() {
        return enemyManas;
    }

    public JLabel getTimeRemaining(){
        return timeRemaining;
    }

    public Object getMonitor(){
        return monitor;
    }


    public void setAnimationTims(int animationTims) {
        this.animationTims = animationTims;
    }

    public int getAnimationTimes(){
        return animationTims;
    }

    private MyCardButton getComponentInGame(long id){
        for(Component cardButton : mainPlayGround.getComponents()){
            if(cardButton instanceof MyCardButton &&((MyCardButton)cardButton).getId()==(id))return (MyCardButton) cardButton;
        }
        return null;
    }

    public BoardController getBoardController() {
        return boardController;
    }

    //                                    for (Component component : fieldComponents) {
//                                        if (component.getX() >= x) {
//                                            final Rectangle bounds = component.getBounds();
//                                            setTimes(fieldComponents.size());
//                                            for (Component component1 : fieldComponents) {
//                                                if (component1.getX() >= x) simpleMove.animate(GameFrame.getInstance().getPlayPanel(),(JComponent) component1, new Point(component1.getX() + 100, fieldHeight),1000,100);
//                                            }
//                                            cardButton.setBounds(bounds);
//                                            finallySettled = true;
//                                            fieldComponents.add(cardButton);
//                                            Collections.sort(fieldComponents);
//                                            break;
//                                        }
//                                    }
//                                                    if (!finallySettled) cardButton.setBounds(fieldComponents.size() * 100 + 140, fieldHeight, 100, 138);


//                                                    final Rectangle bounds = component.getBounds();
//                                                    setTimes(fieldComponents.size());
//                                                    ArrayList<MyCardButton> buttons = new ArrayList<>();
//
//                                                    for (Component component1 : fieldComponents) {
//                                                        if (component1.getX() >= x)
//                                                            buttons.add((MyCardButton) component1);
//                                                    }
////                                                    simpleMove = new SimpleMove(monitor, buttons);
////                                                    simpleMove.start();
////                                                    try {
////                                                        monitor.wait();
////                                                    } catch (InterruptedException interruptedException) {
////                                                        interruptedException.printStackTrace();
////                                                    }
//                                                    cardButton.setBounds(bounds);
//                                                    finallySettled = true;
//                                                    fieldComponents.add(cardButton);
//                                                    Collections.sort(fieldComponents);
//                                                    break


//    private void setAttackTargets(Character attacker, boolean damgeOnly){
//        for (MyCardButton enemyCardButton : getFieldComponents(getOppositeFieldY(getCurrentField()))) {
//            if ((!boardController.tauntExist(activeTurn)) || (((Minion) boardController.getCharacterOfTarget(enemyCardButton.getId())).hasTaunt())) {
//                enemyCardButton.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.BLACK));
//                enemyCardButton.addActionListener(actionEvent -> {
//                   if(!damgeOnly) boardController.attack(attacker, (Minion) boardController.getCharacterOfTarget(enemyCardButton.getId()));
//                   else boardController.changeMinion();
//                    updateFieldCards(getOppositeFieldY(getCurrentField()), 1);
//                    updateFieldCards(getCurrentField(), 0);
//                });
//            }
//        }
//    }

    //    private class HandToFieldMouseListener implements MouseListener , MouseMotionListener{
//
//        MyCardButton cardButton;
//        ArrayList<MyCardButton> fieldComponents;
//        int handHight, fieldHeight, turn;
//        Rectangle bounds;
//        Card card;
//        ArrayList<Card> cards;
//        private volatile int screenX = 0, screenY=0, myX = 0, myY = 0;
//
//        HandToFieldMouseListener(MyCardButton cardButton, ArrayList<MyCardButton> fieldComponents, int height, Card card, ArrayList<Card> cards, int turn){
//            this.cardButton = cardButton;
//            this.fieldComponents = fieldComponents;
//            this.handHight = height;
//            this.turn=turn;
//            this.card = card;
//            this.cards = cards;
//            this.bounds = cardButton.getBounds();
//        }
//
//
//        @Override
//        public void mouseClicked(MouseEvent e) {
//
//        }
//
//        @Override
//        public void mousePressed(MouseEvent e) {
//            if (fieldComponents.size() == 7) JOptionPane.showMessageDialog(null, "you can't have more than 7 minions in your field");
//            cardButton.setCardSize(100);
//            screenX = e.getXOnScreen();
//            screenY = e.getYOnScreen();
//            myX = cardButton.getX();
//            myY = cardButton.getY();
//        }
//
//        @Override
//        public void mouseReleased(MouseEvent mouseEvent) {
//            if (Math.abs(cardButton.getY() - handHight) >= 10) {
//                if (card.getType() == Card.type.MINION) {
//
//                    int x = cardButton.getX();
//                    boolean finallySettled = false;
//                    if (fieldComponents.size() == 0) cardButton.setBounds(140, fieldHeight, 100, 138);
//                    else {
//                        fieldComponents.sort(MyCardButton::compareTo);
//                        for (Component component : fieldComponents) {
//                            if (component.getX() >= x) {
//                                final Rectangle bounds = component.getBounds();
//                                for (Component component1 : fieldComponents) {
//                                    if (component1.getX() >= x)
//                                        simpleMove.animate((JComponent) component1, new Point(component1.getX() + 100, fieldHeight), 10, 10);
//                                }
//                                cardButton.setBounds(bounds);
//                                finallySettled = true;
//                                fieldComponents.add(cardButton);
//                                Collections.sort(fieldComponents);
//                                break;
//                            }
//                        }
//                        if (!finallySettled)
//                            cardButton.setBounds(fieldComponents.size() * 100 + 145, fieldHeight, 100, 138);
//                    }
//                }
//                initialMoveTargeting(card, cardButton);
//                if (card.getType() == Card.type.SPELL) {
//                    cardButton.setBounds(400, 300, 100, 138);
//                    mainPlayGround.remove(cardButton);
//                }
//                if (card.getType() == Card.type.WEAPON) {
//                    if (handHight == friendlyFieldY)
//                        cardButton.setBounds(331, 60, cardButton.getWidth(), cardButton.getHeight());
//                    else
//                        cardButton.setBounds(331, 360, cardButton.getWidth(), cardButton.getHeight());
//                    //todo
////                                       if(height==friendlyHandY) new MyButton("","");
////                                       else cardButton.setBounds();
//                }
//                updateHandCards(handHight, cards, turn);
////                                    boardController.playCard(card.getName());
//                updateMana();
////                                if(getCurrentMana()==0)updateHandCards(height,cards,turn+1);
//            } else {
//                cardButton.setCardSize(100);
//                cardButton.setBounds(bounds);
//            }
//        }
//
//        @Override
//        public void mouseEntered(MouseEvent mouseEvent) {
//
//
//        }
//
//        @Override
//        public void mouseExited(MouseEvent mouseEvent) {
//
//        }
//
//        @Override
//        public void mouseDragged(MouseEvent e) {
//            int deltaX = e.getXOnScreen() - screenX;
//            int deltaY = e.getYOnScreen() - screenY;
////todo check not to exceed the page
//            cardButton.setLocation(myX + deltaX, myY + deltaY);
//        }
//
//        @Override
//        public void mouseMoved(MouseEvent mouseEvent) {
//
//        }
//    }




    //    health1 = new JLabel("30", new ImageIcon(resLoader.imageLoader("heroHealth.png").getScaledInstance(30,42,Image.SCALE_SMOOTH)),JLabel.CENTER);
//    health2 = new JLabel("30", new ImageIcon(resLoader.imageLoader("heroHealth.png").getScaledInstance(30,42,Image.SCALE_SMOOTH)),JLabel.CENTER);
//       health1.setFont(new Font("Ariel",Font.PLAIN,1));health2.setFont(new Font("Ariel",Font.PLAIN,1));
//       health1.setBounds(647,570,30,42);health2.setBounds(645,160,30,42);
//       this.add(health1); this.add(health2);
//
//Controller.getInstance().getCurrentPlayer().getDeckCardsInGame()!=null&&Controller.getInstance().getCurrentPlayer().getDeckCardsInGame().size()!=0&&
}

//    private void chooseTarget(JButton attacker, ArrayList <JButton> targets) {
//        attacker.setBorder(BorderFactory.createBevelBorder(1,Color.GREEN,Color.LIGHT_GRAY));
//        for(JButton component : targets){
//            component.setBorder(BorderFactory.createBevelBorder(1,Color.BLUE,Color.LIGHT_GRAY));
//            component.addActionListener(actionEvent -> {
//
//            });
//        }
//    }


/* private MyButton endTurn, events, playCard, newGame;
    private JButton hero1, hero2, power1, power2, selectedCard;
    private int heroHP2 = 30;
    private JLabel[] manas;
    private JPanel friendlyHandCards, friendlyFieldCards, enemyHandCards, enemyFieldCards;
    private BoardController boardController;
    private int activeTurn = 0;
    private CustomComponent customComponent;
    private MyAudioPlayer audioPlayer;

   public PlayPanel() {
       this.setLayout(null);
       this.setPreferredSize(new Dimension(configLoader.readInteger("mainFrameWidth"),configLoader.readInteger("mainFrameHeight")));
       this.backGroundFile = Controller.getInstance().getCurrentPlayer().getPlayBackGround();
       customComponent = new CustomComponent();
       audioPlayer = MyAudioPlayer.getInstance();
       boardController = new BoardController();

       setHeroes();
       setCardsShowCase();

//       Controller.getInstance().setCurrentPlayer(Controller.getInstance().getFriendlyPlayer());

       events = new MyButton("Events", "pinkCrystal100.png", this, this);
       events.setBounds(1080, 300, events.getWidth(), events.getHeight());

       endTurn = new MyButton("", "endTurn.png", this, this);
       endTurn.setBounds(953, 304, endTurn.getWidth(), endTurn.getHeight());

       playCard = new MyButton("PlayCard", "pinkCrystal100.png", this, this);
       playCard.setBounds(1080, 610, playCard.getWidth(), playCard.getHeight());

       newGame = new MyButton("new Game","pinkCrystal100.png",this,this);
       newGame.setBounds(1080,150,newGame.getWidth(),newGame.getHeight());

       customComponent.exit(this,1080,200);
       customComponent.backToMenuButton(this,1080,250);

       selectedCard = new JButton();
       selectedCard.setBounds(1050,380, 150,207);
       selectedCard.setOpaque(false);
       this.add(selectedCard);

       setManas();

       audioPlayer.playMainMusic("PlayGound.wav");

//          updateFieldCards();
       updateHandCards(friendlyHandCards, boardController.getFriendlyHandCards(), activeTurn);
       updateHandCards(enemyHandCards, boardController.getEnemyHandCards(), activeTurn+1);
       updateMana();
   }

   private void setManas(){
       manas = new JLabel[10];
       for (int i = 0; i < 10; i++) {
           manas[i] = new JLabel(new ImageIcon(imageLoader.loadImage("mana.png").getScaledInstance(19, 18, Image.SCALE_SMOOTH)));
           this.add(manas[i]);
           manas[i].setBounds(831 + (21 * i), 642, 19, 18);
           manas[i].setVisible(false);
       }
   }

   private void setHeroes(){
       hero1 =  new JButton(new ImageIcon(imageLoader.loadImage("hero pics/"+Controller.getInstance().getFriendlyPlayer().getPlayersDeck().getHero().toString().toLowerCase()+"Icon.png").getScaledInstance(140,150,Image.SCALE_SMOOTH)));
       hero1.setBounds(531,460,143,151); hero1.setOpaque(false);
       this.add(hero1);
       power1 = new JButton(new ImageIcon(imageLoader.loadImage("hero pics/"+Controller.getInstance().getFriendlyPlayer().getPlayersDeck().getHero().toString().toLowerCase()+"HeroPower.png").getScaledInstance(100,102,Image.SCALE_SMOOTH)));
       power1.setBounds(664,490,100,102);
       this.add(power1);

       hero2 =  new JButton(new ImageIcon(imageLoader.loadImage("hero pics/mageIcon.png").getScaledInstance(140,150,Image.SCALE_SMOOTH)));
       hero2.setBounds(531,60,143,151);hero2.setOpaque(false);
       this.add(hero2);
       power2 = new JButton(new ImageIcon(imageLoader.loadImage("hero pics/mageHeroPower.png").getScaledInstance(100,102,Image.SCALE_SMOOTH)));
       power2.setBounds(664,80,100,102);
       this.add(power2);
   }

   private void setCardsShowCase(){
       friendlyHandCards = new JPanel();
       friendlyHandCards.setBounds(210,610,710,70);
       this.add(friendlyHandCards);
       friendlyHandCards.setOpaque(false);

       enemyHandCards = new JPanel();
       enemyHandCards.setBounds(210,10,710,80);
       this.add(enemyHandCards);
       enemyHandCards.setOpaque(false);

       friendlyFieldCards = new JPanel();
       friendlyFieldCards.setBounds(240,290,710,176);
       this.add(friendlyFieldCards);
       friendlyFieldCards.setOpaque(false);

       enemyFieldCards = new JPanel();
       enemyFieldCards.setBounds(240,290,710,176);
       this.add(enemyFieldCards);
       enemyFieldCards.setOpaque(false);
   }

   @Override
   public void paintComponent(Graphics g){
       super.paintComponent(g);
       Graphics2D g2d = (Graphics2D) g;
       g2d.drawImage(imageLoader.loadImage("heroHealth.png").getScaledInstance(30,42,Image.SCALE_SMOOTH),657,575,null);
       g2d.drawImage(imageLoader.loadImage("heroHealth.png").getScaledInstance(30,42,Image.SCALE_SMOOTH),657,170,null);
       g2d.drawImage(imageLoader.loadImage("weapons.png").getScaledInstance(35,35,Image.SCALE_SMOOTH),500,570,null);
       g2d.drawImage(imageLoader.loadImage("weapons.png").getScaledInstance(35,35,Image.SCALE_SMOOTH),500,170,null);
       g2d.drawString(boardController.getHeroHealth()+"",664,605);
       g2d.drawString(heroHP2+"",662,200);
       g2d.setColor(Color.WHITE);
       g2d.setFont(new Font("Areil",Font.BOLD,14));
       g2d.drawString(Controller.getInstance().getFriendlyPlayer().getCurrentMana()+"/10",780,654);
       g2d.drawString(Controller.getInstance().getFriendlyPlayer().getDeckCardsInGame().size()+"",1020,500);
   }

    private void updateHandCards(JPanel panel , ArrayList<Card> cards, int on){
        panel.removeAll();
        if(on%2!=0) for (int i = 0; i < cards.size() ; i++) new MyCardButton(null,50,panel);
        else {
            for (Card card : cards) {
                MyCardButton cardButton = new MyCardButton(card.getName(), 50, friendlyHandCards);
                cardButton.addActionListener(actionEvent -> {
                    cardButton.addClickListener();
                    selectedCard.setName(card.getName());
                    selectedCard.setIcon(new ImageIcon(imageLoader.loadImage("Cards/" + card.getName() + ".png").getScaledInstance(150, 207, Image.SCALE_SMOOTH)));
                });
            }
        }
        panel.repaint();
        revalidate();
    }

    private void updateFieldCards(JPanel currentField ,JPanel opponentField , ArrayList<Minion> minions, int on){
       // mituni esm card haro beja xodeshun pas bedi
        currentField.removeAll();
       for(Minion minion : minions) {
           MyCardButton cardButton = new MyCardButton(minion.getName(),100,currentField);
           if(on%2==0 && minion.canAttack()) {
               cardButton.setBorder(BorderFactory.createEtchedBorder(Color.GREEN,Color.BLACK));
//               if(boardController.enemyHasTaunt())
               cardButton.addMouseListener(new MouseAdapter() {
                   @Override
                   public void mouseClicked(MouseEvent e) {
                       for(Component component : opponentField.getComponents()) {
                           ((JButton)component).setBorder(BorderFactory.createEtchedBorder(Color.ORANGE,Color.BLACK));
                           component.addMouseListener(new MouseAdapter() {
                               @Override
                               public void mouseClicked(MouseEvent e) {
                                   boardController.attack(minion,);
                               }
                           });
                       }
                   }
               });
           }
       }
        currentField.repaint();
       revalidate();
    }


    public void updateMana(){
        boardController.setVisible(getManas(),Controller.getInstance().getCurrentPlayer().getCurrentMana());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Controller.getInstance().getPlayerController().getPlayerLOGGER().log(Level.INFO,((JButton)actionEvent.getSource()).getText()+" button clicked - Collection");
        if(actionEvent.getSource()==events) {
            try {
                JOptionPane.showMessageDialog(null, Files.readString(boardController.getEventsLog().toPath(), StandardCharsets.US_ASCII), "Events in this game",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(actionEvent.getSource()==endTurn){
            activeTurn++;
            boardController.endTurn();
            audioPlayer.playQuick("poker-chips-daniel_simon.wav");
            updateMana();
            audioPlayer.playQuick("drawCard.wav");
            updateHandCards(friendlyHandCards, boardController.getFriendlyHandCards(), activeTurn);
            updateHandCards(enemyHandCards, boardController.getEnemyHandCards(), activeTurn+1);
        }
        if(actionEvent.getSource()==playCard){
            if(selectedCard.getName()==null) JOptionPane.showMessageDialog(this,"you should choose a card from your hand first ");
            else if(!boardController.hasEnoughMana(selectedCard.getName())) JOptionPane.showMessageDialog(null,"you don't have enough mana left");
            else if(friendlyFieldCards.getComponents().length>=7) JOptionPane.showMessageDialog(this,"you can't have more than 7 minions in your field");
            else {
                boardController.playCard(selectedCard.getName());
//                updateHandCards();
//                updateFieldCards();
                selectedCard.setIcon(null);
                selectedCard.setName(null);
                updateMana();
            }
        }
        if (actionEvent.getSource()==power1) boardController.playHeroPower();

        if(actionEvent.getSource()==newGame) GameFrame.getInstance().goToPanel("playPanel");
    }

    public JLabel[] getManas() {
        return manas;
    }

    public JPanel getFieldCards() {
        return friendlyFieldCards;
    }*/



//        if(on%2!=0) for (int i = 0; i < cards.size() ; i++) {
//            MyCardButton cardButton = new MyCardButton(null,50,mainPlayGround);
//            cardButton.setBounds(40 + num * cardButton.getWidth(),height,50,69);
//            num++;
//        }


//    private void updateFieldCards(JPanel currentField ,JPanel opponentField , ArrayList<Minion> minions, int on){
//        // mituni esm card haro beja xodeshun pas bedi
//        currentField.removeAll();
//        for(Minion minion : minions) {
//            MyCardButton cardButton = new MyCardButton(minion.getName(),100,currentField);
//            if(on%2==0 && minion.canAttack()) {
//                cardButton.setBorder(BorderFactory.createEtchedBorder(Color.GREEN,Color.BLACK));
////               if(boardController.enemyHasTaunt())
//                cardButton.addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mouseClicked(MouseEvent e) {
//                        for(Component component : opponentField.getComponents()) {
//                            ((JButton)component).setBorder(BorderFactory.createEtchedBorder(Color.ORANGE,Color.BLACK));
//                            component.addMouseListener(new MouseAdapter() {
//                                @Override
//                                public void mouseClicked(MouseEvent e) {
////                                   boardController.attack(minion,);
//                                }
//                            });
//                        }
//                    }
//                });
//            }
//        }
//        currentField.repaint();
//        revalidate();
//    }

//    boardController.syncFriendlyFieldComponents(getFieldComponents(friendlyFieldY));
//        boardController.syncEnemyFieldComponents(getFieldComponents(enemyFieldY));
//        for (Component component : getFieldComponents(fieldHeight)) {
//        mainPlayGround.remove(component);
//    }
//    ArrayList<Minion> cards ;
//        if(fieldHeight==friendlyFieldY) cards = boardController.getFriendlyFieldCards();
//        else cards = boardController.getEnemyFieldCards();
//    int i=0;
//        for(Minion card : cards){
//        MyCardButton cardButton = new MyCardButton(card,100,mainPlayGround);
//        cardButton.setBounds(140+i*100,fieldHeight,100,137);
//        addSelectedCardListener(cardButton,card);
//        i++;
//        if(turn%2==0){
//            for(Minion minion : cards){
//                if(minion.canAttack() && getFieldComponents(getOppositeFieldY(fieldHeight)).size()!=0){
//                    getComponentInGame(minion.getId()).setBorder(BorderFactory.createBevelBorder(1, Color.GREEN, Color.LIGHT_GRAY));
//                    for(MyCardButton enemyCardButton : getFieldComponents(getOppositeFieldY(fieldHeight))){
//                        if((!boardController.tauntExist(activeTurn))||
//                                (((Minion)boardController.getCharacterOfTarget(enemyCardButton.getId()+"")).hasTaunt())){
//                            cardButton.setBorder(BorderFactory.createEtchedBorder(Color.BLUE,Color.BLACK));
//                            cardButton.addActionListener(actionEvent -> {
//                                boardController.attack(minion, (Minion) boardController.getCharacterOfTarget(cardButton.getName()));
//                                minion.setCanAttack(false);
//                                updateFieldCards(getOppositeFieldY(fieldHeight),turn);
//                                updateFieldCards(fieldHeight,turn);
//                            });
//                        }
//                    }
//                }
//            }
//        }


