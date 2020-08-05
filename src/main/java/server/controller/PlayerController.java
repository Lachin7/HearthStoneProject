package server.controller;

import server.ClientHandler;

import javax.swing.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.*;

import static JSON.jsonForPlayers.jsonForPlayers.*;

public class PlayerController {

    private final Logger PlayerLOGGER ;
    private ClientHandler clientHandler;
    public PlayerController(ClientHandler clientHandler){
        this.clientHandler = clientHandler;
        PlayerLOGGER = Logger.getLogger("PlayerLog");
    }

    public String getFriendlyPlayersInfo(){
       return  "player name : " + clientHandler.getMainPlayer().getName()+"\n"+
               "Player ID : " + clientHandler.getMainPlayer().getID()+"\n"+
               "player Coins : "+ clientHandler.getMainPlayer().getCoins()+"\n";

    }

    public void deleteThePlayer() throws IOException {
        String password = JOptionPane.showInputDialog("if your sure of deleting your account enter you password :");
        if(!clientHandler.getMainPlayer().getPassword().equals(getHashedPassword(password))){
            JOptionPane.showMessageDialog(null,"incorrect password!");
        }
        else if(!password.equals("")){
            /** adding deleted to the log file in line 4 */
            File file = new File("src/logs/"+clientHandler.getMainPlayer().getName()+"-"+clientHandler.getMainPlayer().getID()+".log");
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

            getPlayerFiles(clientHandler.getMainPlayer().getName()).deleteOnExit();
            System.exit(0);
        }
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

    public Logger getPlayerLOGGER() {
        return PlayerLOGGER;
    }

}
