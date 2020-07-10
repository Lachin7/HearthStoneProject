package controller;

import models.Cards.Card;
import gui.GameFrame;
import models.Deck;
import models.Heroes.Mage;
import models.Player;
import models.board.InfoPassive;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;

import static JSON.jsonForCards.jsonForCards.creatCardFromjson;
import static JSON.jsonForPlayers.jsonForPlayers.*;

public class PlayerController {

    private Logger PlayerLOGGER = Logger.getLogger("PlayerLog");

    public String SignIn(String userName, String pass) {
        String message = "";
        if (getPlayerFiles(userName) == null) message = "Error! There isn't an account in this name , Try again..";
                else {
            try {
                if(!jsonFileReader(userName).getPlayerPassword().equals(new Player().getHashedPassword(pass)))
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
            SignedUpPlayer.setPlayerPassword(SignedUpPlayer.getHashedPassword(CreatedPass));
            SignedUpPlayer.setPlayerCoins(50);
            SignedUpPlayer.setPlayerID(System.currentTimeMillis());
            SignedUpPlayer.setPlayersChoosedHero(new Mage());
            ArrayList<Card> optionalMageDeck = null;
            optionalMageDeck = (new ArrayList<Card>(Arrays.asList(creatCardFromjson("PharaohsBlessing"),creatCardFromjson("Polymorph"),creatCardFromjson("FriendlySmith"),creatCardFromjson("DreadScale"),creatCardFromjson("SwampKingDred"),creatCardFromjson("HighPriestAmet"),creatCardFromjson("Sathrovarr"),creatCardFromjson("SecurityRover"),creatCardFromjson("CurioCollector"),creatCardFromjson("StrengthInNumbers"),creatCardFromjson("LearnDraconic"),creatCardFromjson("ScrapDeadlyShot"),creatCardFromjson("BonechewerVanguard"),creatCardFromjson("BeamingSidekick"),creatCardFromjson("LostSpirit"),creatCardFromjson("Ratcatcher"),creatCardFromjson("ScavengingShivarra"),creatCardFromjson("LearnDraconic"),creatCardFromjson("FungalBruiser"),creatCardFromjson("RocketAugmerchant"),creatCardFromjson("PsycheSplit"),creatCardFromjson("CurioCollector"))));
            Deck deck = new Deck("optional Mage deck",optionalMageDeck);
            deck.setHero(Card.HeroClass.MAGE);
            SignedUpPlayer.setPlayersDeck(deck);
            SignedUpPlayer.setDecks(new ArrayList<>()) ;
            SignedUpPlayer.getDecks().add(SignedUpPlayer.getPlayersDeck());
            ArrayList<Card> optionalAllCards = new ArrayList<>();
            optionalAllCards.addAll(optionalMageDeck);
            optionalAllCards.add(creatCardFromjson("FrozenShadoweaver"));
            optionalAllCards.add(creatCardFromjson("CurioCollector"));
            optionalAllCards.add(creatCardFromjson("BeamingSidekick"));
            optionalAllCards.add(creatCardFromjson("Locust"));
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

//    public String deleteThePlayer(String CreatedName, String CreatedPass) throws IOException {
//        Boolean isvalid = false;
//        while (!isvalid) {
//            String pass = scanner.nextLine();
//            if (getHashedPassword(pass).equals(this.PlayerPassword)) {
//                isvalid = true;
//                /** adding deleted to the log file in line 4 */
//                File file = new File("src/logs/"+ getPlayerName()+"-"+getPlayerID()+".log");
//                File temp = File.createTempFile("temp-file-name", ".log");
//                BufferedReader br = new BufferedReader(new FileReader( file));
//                PrintWriter pw =  new PrintWriter(new FileWriter( temp ));
//                String line;
//                int lineCount = 0;
//                while ((line = br.readLine()) != null) {
//                    pw.println(line);
//                    if(lineCount==3){
//                        pw.println("PLAYER_DELETED_AT : " +  new SimpleDateFormat(" yyyy/MM/dd HH:mm:ss").format(new Date()) + "\n");
//                    }
//                    lineCount++;
//                }
//                br.close();
//                pw.close();
//                file.delete();
//                temp.renameTo(file);
//
//                getPlayerFiles(PlayerName).deleteOnExit();
//                System.exit(0);
//            } else {
//                System.out.println("Wrong password! ");
//            }
//        }
//    }

    public Logger getPlayerLOGGER() {
        return PlayerLOGGER;
    }

}
