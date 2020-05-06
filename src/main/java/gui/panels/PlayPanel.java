package gui.panels;

import gui.Constants.GuiCons;
import gui.GameFrame;
import gui.ResLoader;
import gui.MyAudioPlayer;
import models.Cards.Card;
import controller.*;
import gui.myComponents.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;

public class PlayPanel extends MyPanel implements ActionListener {

    private MyButton endTurn, events, playCard, newGame;
    private JLabel hero1, hero2, power1, power2, selectedCard;
    private int heroHP2 = 30;
    private JLabel[] manas;
    private JPanel handCards, fieldCards, enemyHandCards;
    private ResLoader resLoader;
    private BoardController boardController;
    private PlayerController playerController;
    private CustomComponent customComponent;
    private Boolean firstDraw = true, gameStarted;
    private MyAudioPlayer audioPlayer;

   public PlayPanel() {
       this.setLayout(null);
       this.setPreferredSize(new Dimension(GuiCons.getWidth(),GuiCons.getHeight()));
       this.backGroundFile = Controller.getInstance().getCurrentPlayer().getPlayBackGround();
       resLoader = new ResLoader();
       customComponent = new CustomComponent();
       audioPlayer = MyAudioPlayer.getInstance();
       playerController = new PlayerController();
       playerController.checkForNewGame();

       setHeroes();
       setCardsShowCase();
       boardController = new BoardController();

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

       selectedCard = new JLabel();
       selectedCard.setBounds(1050,380, 150,207);
       selectedCard.setOpaque(false);
       this.add(selectedCard);

       manas = new JLabel[10];
       for (int i = 0; i < 10; i++) {
           manas[i] = new JLabel(new ImageIcon(resLoader.imageLoader("mana.png").getScaledInstance(19, 18, Image.SCALE_SMOOTH)));
           this.add(manas[i]);
           manas[i].setBounds(831 + (21 * i), 642, 19, 18);
           manas[i].setVisible(false);
       }
       audioPlayer.playMainMusic("PlayGound.wav");
      if(Controller.getInstance().getCurrentPlayer().getMakeNewGame()==null||Controller.getInstance().getCurrentPlayer().getMakeNewGame()) drawCardToHands();
      else {
          updateFieldCards();
          updateHandCards();
      }
   updateMana();
   }

   private void setHeroes(){
       hero1 =  new JLabel(new ImageIcon(resLoader.imageLoader("hero pics/"+Controller.getInstance().getCurrentPlayer().getPlayersDeck().getHero().toString().toLowerCase()+"Icon.png").getScaledInstance(140,150,Image.SCALE_SMOOTH)));
       hero1.setBounds(531,460,143,151); hero1.setOpaque(false);
       this.add(hero1);
       power1 = new JLabel(new ImageIcon(resLoader.imageLoader("hero pics/"+Controller.getInstance().getCurrentPlayer().getPlayersDeck().getHero().toString().toLowerCase()+"HeroPower.png").getScaledInstance(100,102,Image.SCALE_SMOOTH)));
       power1.setBounds(664,490,100,102);
       this.add(power1);

       hero2 =  new JLabel(new ImageIcon(resLoader.imageLoader("hero pics/mageIcon.png").getScaledInstance(140,150,Image.SCALE_SMOOTH)));
       hero2.setBounds(531,60,143,151);hero2.setOpaque(false);
       this.add(hero2);
       power2 = new JLabel(new ImageIcon(resLoader.imageLoader("hero pics/mageHeroPower.png").getScaledInstance(100,102,Image.SCALE_SMOOTH)));
       power2.setBounds(664,80,100,102);
       this.add(power2);
   }

   private void setCardsShowCase(){
       handCards = new JPanel();
       handCards.setBounds(210,610,710,70);
       this.add(handCards);
       handCards.setOpaque(false);

       enemyHandCards = new JPanel();
       enemyHandCards.setBounds(210,10,710,80);
       this.add(enemyHandCards);
       enemyHandCards.setOpaque(false);
       for (int i = 0; i < 5; i++) {
           MyCardButton myCardButton = new MyCardButton(null,50,enemyHandCards);
       }

       fieldCards = new JPanel();
       fieldCards.setBounds(240,290,710,176);
       this.add(fieldCards);
       fieldCards.setOpaque(false);
   }

   @Override
   public void paintComponent(Graphics g){
       super.paintComponent(g);
       Graphics2D g2d = (Graphics2D) g;
       g2d.drawImage(resLoader.imageLoader("heroHealth.png").getScaledInstance(30,42,Image.SCALE_SMOOTH),657,575,null);
       g2d.drawImage(resLoader.imageLoader("heroHealth.png").getScaledInstance(30,42,Image.SCALE_SMOOTH),657,170,null);
       g2d.drawImage(resLoader.imageLoader("weapons.png").getScaledInstance(35,35,Image.SCALE_SMOOTH),500,570,null);
       g2d.drawImage(resLoader.imageLoader("weapons.png").getScaledInstance(35,35,Image.SCALE_SMOOTH),500,170,null);
       g2d.drawString(boardController.setHeroHealth()+"",664,605);
       g2d.drawString(heroHP2+"",662,200);
       g2d.setColor(Color.WHITE);
       g2d.setFont(new Font("Areil",Font.BOLD,14));
       g2d.drawString(Controller.getInstance().getCurrentPlayer().getCurrentMana()+"/10",780,654);
       g2d.drawString(Controller.getInstance().getCurrentPlayer().getDeckCardsInGame().size()+"",1020,500);
   }

    public void drawCardToHands(){
        System.out.println(Controller.getInstance().getCurrentPlayer().getDeckCardsInGame().size());
       if(Controller.getInstance().getCurrentPlayer().getDeckCardsInGame().size()==0) {
           JOptionPane.showMessageDialog(null,"you don't have any cards in your deck\n your hero will lose a health:(");
           Controller.getInstance().getCurrentPlayer().getPlayersChoosedHero().setHP(Controller.getInstance().getCurrentPlayer().getPlayersChoosedHero().getHP()-1);
       }
       else if(handCards.getComponents().length== 12) JOptionPane.showMessageDialog(this,"maximum number of hand cards for a play is 12 , unfortunately you lost the coming card");
       else {
           try {
               boardController.drawCard(firstDraw);
               updateHandCards();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }

    private void updateHandCards(){
        handCards.removeAll();
        for (Card card : boardController.getPlayersHandCards()){
            MyCardButton cardButton = new MyCardButton(card.getName(), 50, handCards);
            cardButton.addActionListener(actionEvent -> {
                cardButton.addClickListener();
                selectedCard.setName(card.getName());
                System.out.println(card.getName());
                selectedCard.setIcon(new ImageIcon(resLoader.imageLoader("Cards/" + card.getName() + ".png").getScaledInstance(150, 207, Image.SCALE_SMOOTH)));
            });
        }
        handCards.repaint();
        revalidate();
    }

    private void updateFieldCards(){
       fieldCards.removeAll();
       for(Card card : boardController.getPlayerFieldCards()) new MyCardButton(card.getName(),100,fieldCards);
       fieldCards.repaint();
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
            firstDraw =false;
            updateMana();
            drawCardToHands();
            audioPlayer.playQuick("drawCard.wav");
            boardController.endTurn();
            audioPlayer.playQuick("poker-chips-daniel_simon.wav");
        }
        if(actionEvent.getSource()==playCard){
            if(selectedCard.getName()==null) JOptionPane.showMessageDialog(this,"you should choose a card from your hand first ");
            else if(!boardController.hasEnoughMana(selectedCard.getName())) JOptionPane.showMessageDialog(null,"you don't have enough mana left");
            else if(fieldCards.getComponents().length>=7) JOptionPane.showMessageDialog(this,"you can't have more than 7 minions in your field");
            else {
                try {
                    boardController.playCard(selectedCard.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                updateHandCards();
                updateFieldCards();
                selectedCard.setIcon(null);
                selectedCard.setName(null);
                updateMana();
            }
        }

        if(actionEvent.getSource()==newGame) GameFrame.getInstance().goToPanel("playPanel");
    }

    public JLabel[] getManas() {
        return manas;
    }

    public JPanel getFieldCards() {
        return fieldCards;
    }


    //    health1 = new JLabel("30", new ImageIcon(resLoader.imageLoader("heroHealth.png").getScaledInstance(30,42,Image.SCALE_SMOOTH)),JLabel.CENTER);
//    health2 = new JLabel("30", new ImageIcon(resLoader.imageLoader("heroHealth.png").getScaledInstance(30,42,Image.SCALE_SMOOTH)),JLabel.CENTER);
//       health1.setFont(new Font("Ariel",Font.PLAIN,1));health2.setFont(new Font("Ariel",Font.PLAIN,1));
//       health1.setBounds(647,570,30,42);health2.setBounds(645,160,30,42);
//       this.add(health1); this.add(health2);
//
//Controller.getInstance().getCurrentPlayer().getDeckCardsInGame()!=null&&Controller.getInstance().getCurrentPlayer().getDeckCardsInGame().size()!=0&&
}
