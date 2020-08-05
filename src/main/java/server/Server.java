package server;

import client.Client;
import javafx.util.Pair;
import lombok.Getter;
import models.Player;
import request_response.response.AskForPermissionToWatch;
import resLoader.ConfigLoader;
import resLoader.ModelLoader;
import resLoader.database.DataBase;
import server.controller.util.PlayerComparator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server extends Thread{

    private int port;
    private String host;
    private ConfigLoader configLoader;
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> waitingList;
    @Getter
    private HashMap<Pair<ClientHandler,ClientHandler>,HashMap<ClientHandler,Integer>> runningGames;
    @Getter
    private DataBase dataBase;
    @Getter
    private ModelLoader modelLoader;


    public Server(){
        configLoader = new ConfigLoader("serverConfig");
        port = configLoader.readInteger("port");
        host = configLoader.readString("host");
        try {
            serverSocket  = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        waitingList = new ArrayList<>();
        runningGames = new HashMap<>();
        dataBase =new DataBase();
        modelLoader = new ModelLoader(dataBase);
    }

    @Override
    public void run() {
        while (true) {
            Socket socket;
            try {
                socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(this, socket);
                clientHandler.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getPort() {
        return port;
    }

    public synchronized void requestOnlineGame(ClientHandler clientHandler) {
        waitingList.add(clientHandler);
        if (waitingList.size() > 1) {
            waitingList.sort(ClientHandler::compareTo);
            waitingList.get(0).startOnlineGame(waitingList.get(1));
            waitingList.get(1).startOnlineGame(waitingList.get(0));
            waitingList.remove(0);
            waitingList.remove(0);
        }
    }

    public Pair<ClientHandler, ClientHandler> getClients(ClientHandler clientHandler){
        for (Map.Entry<Pair<ClientHandler,ClientHandler>,HashMap<ClientHandler,Integer>> entry : runningGames.entrySet())if (entry.getKey().getKey()==clientHandler||entry.getKey().getValue()==clientHandler)return entry.getKey();
        return null;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public List<Player> getAllPlayers() {
        return dataBase.fetchAll(Player.class);
    }

    public void declareChosenMatchToWatch(String key, String value, ClientHandler viewer) {
        for (Map.Entry<Pair<ClientHandler,ClientHandler>,HashMap<ClientHandler,Integer>> entry : runningGames.entrySet()) {
            if (entry.getKey().getKey().getMainPlayer().getName().equals(key) && entry.getKey().getValue().getMainPlayer().getName().equals(value)) {
                entry.getValue().put(viewer,0);
                runningGames.put(entry.getKey(),entry.getValue());
                entry.getKey().getKey().sendResponse("AskForPermissionToWatch", new AskForPermissionToWatch(viewer.getMainPlayer().getName()));
                entry.getKey().getValue().sendResponse("AskForPermissionToWatch", new AskForPermissionToWatch(viewer.getMainPlayer().getName()));
            }
        }
    }

    public void watchAgreement(ClientHandler clientHandler, String viewer) {
        for (Map.Entry<Pair<ClientHandler,ClientHandler>,HashMap<ClientHandler,Integer>> entry : runningGames.entrySet()){
            if (entry.getKey().getKey()==clientHandler||entry.getKey().getValue()==clientHandler){
                for (Map.Entry<ClientHandler,Integer> v : entry.getValue().entrySet()){
                    if (v.getKey().getMainPlayer().getName().equals(viewer)){
                        if ((v.getValue()+1 )%2==0) v.getKey().setUpWatch(entry.getKey().getKey(),entry.getKey().getValue());
                        entry.getValue().put(v.getKey(),v.getValue()+1);
                        runningGames.put(entry.getKey(),entry.getValue());
                    }
                }
            }
        }
    }

}
