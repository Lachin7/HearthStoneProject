package server.controller;

import server.models.Player;
import server.ClientHandler;

import javax.swing.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;


public class PlayerController {

    private ClientHandler clientHandler;
    public PlayerController(ClientHandler clientHandler){
        this.clientHandler = clientHandler;
    }

    public String getFriendlyPlayersInfo(){
       return  "player name : " + clientHandler.getMainPlayer().getName()+"\n"+
               "Player ID : " + clientHandler.getMainPlayer().getID()+"\n"+
               "player Coins : "+ clientHandler.getMainPlayer().getCoins()+"\n";

    }

    public String deleteThePlayer(String password) {
        String message = "";
        if(!clientHandler.getMainPlayer().getPassword().equals(getHashedPassword(password))) message = "incorrect password!";
        else if(!password.equals("")){
            clientHandler.getServer().getDataBase().delete(clientHandler.getMainPlayer());
            clientHandler.log("PLAYER_DELETED_AT : " +  new SimpleDateFormat(" yyyy/MM/dd HH:mm:ss").format(new Date()) + "\n");
            System.exit(0);
        }
        return message;
    }

    public String getHashedPassword(String playerPassword){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(playerPassword.getBytes());
            byte[] byteData = md.digest();
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

    public void makePlayerWinner(Player player){
        player.addWin();
        player.getDeck().setWinGamesPlayed(player.getDeck().getWinGamesPlayed()+1);
        player.getDeck().setAllGamesPlayed(player.getDeck().getAllGamesPlayed()+1);
        player.getDeck().setCups(player.getDeck().getCups()+1);
    }

    public void makePlayerLoser(Player player){
        player.addLoses();
        player.getDeck().setLoses(player.getDeck().getLoses()+1);
        player.getDeck().setAllGamesPlayed(player.getDeck().getAllGamesPlayed()+1);
        player.getDeck().setCups(player.getDeck().getCups()-1);
    }



//    File file = new File("src/logs/"+clientHandler.getMainPlayer().getName()+"-"+clientHandler.getMainPlayer().getID()+".log");
//    File temp = File.createTempFile("temp-file-name", ".log");
//    BufferedReader br = new BufferedReader(new FileReader( file));
//    PrintWriter pw =  new PrintWriter(new FileWriter( temp ));
//    String line;
//    int lineCount = 0;
//            while ((line = br.readLine()) != null) {
//        pw.println(line);
//        if(lineCount==3){
//            pw.println("PLAYER_DELETED_AT : " +  new SimpleDateFormat(" yyyy/MM/dd HH:mm:ss").format(new Date()) + "\n");
//        }
//        lineCount++;
//    }
//            br.close();
//            pw.close();
//            file.delete();
//            temp.renameTo(file);

}
