package controller;

import models.Cards.Card;
import gui.GameFrame;
import models.Deck;
import models.Heroes.Mage;
import models.Player;

import javax.swing.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;

import static JSON.jsonForCards.jsonForCards.creatCardFromjson;
import static JSON.jsonForPlayers.jsonForPlayers.*;

public class PlayerController {

    private Logger PlayerLOGGER ;
    private CardController cardController;
    public PlayerController(){
        PlayerLOGGER = Logger.getLogger("PlayerLog");
        cardController = new CardController();
    }

    public String SignIn(String userName, String pass) {
        String message = "";
        if (getPlayerFiles(userName) == null) message = "Error! There isn't an account in this name , Try again..";
                else {
            try {
                if(!jsonFileReader(userName).getPlayerPassword().equals(getHashedPassword(pass)))
                    message = "Error! Wrong password , Try again..";
                else {
            Player player = null;
            try {
                player = jsonFileReader(userName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.setSignedUp(true);
            Controller.getInstance().setMainPlayer(player);
                    LogManager.getLogManager().reset();
            FileHandler fileHandler = null;
            try {
                fileHandler = new FileHandler("./src/main/java/logs/" + player.getPlayerName() + "-" + player.getPlayerID() + ".log", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileHandler.setFormatter(new SimpleFormatter());
                    PlayerLOGGER.addHandler(fileHandler);
                    PlayerLOGGER.info("USER  : " + player.getPlayerName() + "\nSigned_In AT :" +
                            new SimpleDateFormat(" yyyy/MM/dd HH:mm:ss").format(new Date()) + "\nPASSWORD : " + player.getPlayerPassword() + "\n");
                    GameFrame.getInstance().goToPanel("mainMenu");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    public String SignUp(String CreatedName, String CreatedPass){
        String message = " ";

        if(getPlayerFiles(CreatedName)!=null) message ="Error! Sorry this user name is taken, Try something else..";
        else if(CreatedName.length()<3) message = "Error! your username should contain more than 2 Characters , try again";
        else if(CreatedPass.length()<3) message = "Error! your password should contain more than 2 Characters , try again";
        else {
            Player SignedUpPlayer = new Player();
            SignedUpPlayer.setPlayerName(CreatedName);
            SignedUpPlayer.setPlayerPassword(getHashedPassword(CreatedPass));
            SignedUpPlayer.setPlayerCoins(50);
            SignedUpPlayer.setPlayerID(System.currentTimeMillis());
            SignedUpPlayer.setPlayersChoosedHero(new Mage());
            ArrayList<Card> optionalMageDeck = null;
//            optionalMageDeck = (new ArrayList<Card>(Arrays.asList(cardController.createCardWRef("PharaohsBlessing"),cardController.createCardWRef("Polymorph"),cardController.createCardWRef("FriendlySmith"),cardController.createCardWRef("DreadScale"),cardController.createCardWRef("SwampKingDred"),cardController.createCardWRef("HighPriestAmet"),cardController.createCardWRef("Sathrovarr"),cardController.createCardWRef("SecurityRover"),cardController.createCardWRef("CurioCollector"),cardController.createCardWRef("StrengthInNumbers"),cardController.createCardWRef("LearnDraconic"),cardController.createCardWRef("DreadScale"),cardController.createCardWRef("DreadScale"),cardController.createCardWRef("BeamingSidekick"),cardController.createCardWRef("LostSpirit"),cardController.createCardWRef("Ratcatcher"),cardController.createCardWRef("ScavengingShivarra"),cardController.createCardWRef("LearnDraconic"),cardController.createCardWRef("FungalBruiser"),cardController.createCardWRef("RocketAugmerchant"),cardController.createCardWRef("PsycheSplit"),cardController.createCardWRef("CurioCollector"))));
            optionalMageDeck = (new ArrayList<Card>(Arrays.asList(cardController.createCard("FriendlySmith"),cardController.createCard("FriendlySmith"),cardController.createCard("PharaohsBlessing"),cardController.createCard("DreadScale"),cardController.createCard("Sathrovarr"),cardController.createCard("Sathrovarr"),cardController.createCard("Sathrovarr"),cardController.createCard("SecurityRover"),cardController.createCard("SecurityRover"),cardController.createCard("SecurityRover"),cardController.createCard("SwampKingDred"),cardController.createCard("HighPriestAmet"),cardController.createCard("DreadScale"),cardController.createCard("HighPriestAmet"),cardController.createCard("LostSpirit"),cardController.createCard("Ratcatcher"),cardController.createCard("ScavengingShivarra"),cardController.createCard("LearnDraconic"),cardController.createCard("FungalBruiser"),cardController.createCard("RocketAugmerchant"),cardController.createCard("PsycheSplit"),cardController.createCard("CurioCollector"))));

            Deck deck = new Deck("optional Mage deck",optionalMageDeck);
            deck.setHero(Card.HeroClass.MAGE);
            SignedUpPlayer.setPlayersDeck(deck);
            SignedUpPlayer.setDecks(new ArrayList<>()) ;
            SignedUpPlayer.getDecks().add(SignedUpPlayer.getPlayersDeck());
            ArrayList<Card> optionalAllCards = new ArrayList<>();
            optionalAllCards.addAll(optionalMageDeck);
//            optionalAllCards.add(creatCardFromjson("FrozenShadoweaver"));
//            optionalAllCards.add(creatCardFromjson("CurioCollector"));
//            optionalAllCards.add(creatCardFromjson("BeamingSidekick"));
//            optionalAllCards.add(creatCardFromjson("Locust"));
            SignedUpPlayer.setALLPlayersCards(optionalAllCards);
            SignedUpPlayer.getPlayersUnlockedHeroes().add(new Mage());
            try {
                jsonTofilePlayer(SignedUpPlayer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Controller.getInstance().setMainPlayer(SignedUpPlayer);
            LogManager.getLogManager().reset();
            FileHandler fileHandler = null;
            try {
                fileHandler = new FileHandler("./src/main/java/logs/"+ SignedUpPlayer.getPlayerName()+"-"+SignedUpPlayer.getPlayerID()+".log",true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileHandler.setFormatter(new SimpleFormatter());
            PlayerLOGGER.addHandler(fileHandler);
            PlayerLOGGER.info("USER  : " + SignedUpPlayer.getPlayerName() + "\nCREATED AT :" +
                    new SimpleDateFormat(" yyyy/MM/dd HH:mm:ss").format(new Date()) + "\nPASSWORD : " + SignedUpPlayer.getPlayerPassword() + "\n");
            message = "you are signed up successfully! BEGIN YOUR JOURNEY IN HEARTH STONE!!";
            GameFrame.getInstance().goToPanel("mainMenu");
        }
        return message;
    }

    public String getFriendlyPlayersInfo(){
       return  "player name : " + getFriendlyPlayer().getPlayerName()+"\n"+
               "Player ID : " + getFriendlyPlayer().getPlayerID()+"\n"+
               "player Coins : "+ getFriendlyPlayer().getPlayerCoins()+"\n";

    }

    private Player getFriendlyPlayer(){
        return Controller.getInstance().getMainPlayer();
    }

    public void deleteThePlayer() throws IOException {
        String password = JOptionPane.showInputDialog("if your sure of deleting your account enter you password :");
        if(!Controller.getInstance().getMainPlayer().getPlayerPassword().equals(getHashedPassword(password))){
            JOptionPane.showMessageDialog(null,"incorrect password!");
        }
        else if(!password.equals("")){
            /** adding deleted to the log file in line 4 */
            File file = new File("src/logs/"+Controller.getInstance().getMainPlayer().getPlayerName()+"-"+Controller.getInstance().getMainPlayer().getPlayerID()+".log");
            File temp = File.createTempFile("temp-file-name", ".log");
            BufferedReader br = new BufferedReader(new FileReader( file));
            PrintWriter pw =  new PrintWriter(new FileWriter( temp ));
            String line;
            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                pw.println(line);
                if(lineCount==3){
                    pw.println("PLAYER_DELETED_AT : " +  new SimpleDateFormat(" yyyy/MM/dd HH:mm:ss").format(new Date()) + "\n");
                }
                lineCount++;
            }
            br.close();
            pw.close();
            file.delete();
            temp.renameTo(file);

            getPlayerFiles(Controller.getInstance().getMainPlayer().getPlayerName()).deleteOnExit();
            System.exit(0);
        }
    }

    public String getHashedPassword(String playerPassword){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(playerPassword.getBytes());
            byte byteData[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length ; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Logger getPlayerLOGGER() {
        return PlayerLOGGER;
    }

}
