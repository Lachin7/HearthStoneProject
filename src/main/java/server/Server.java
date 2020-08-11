package server;

import javafx.util.Pair;
import lombok.Getter;
import lombok.SneakyThrows;
import request_response.request.ChooseGameSetUps;
import request_response.response.AskForPermissionToWatch;
import request_response.response.Message;
import request_response.response.ShowChatMessage;
import resLoader.ConfigLoader;
import resLoader.database.DataBase;
import server.models.Cards.Card;
import server.models.Player;
import server.models.util.MyPair;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server extends Thread {

    private int port;
//    private String host;
    private ConfigLoader configLoader;
    private ServerSocket serverSocket;
    @Getter
    private ArrayList<ClientHandler> waitingList;
    @Getter
    private HashMap<MyPair<ClientHandler, ClientHandler>, HashMap<ClientHandler, Integer>> runningGames;
    @Getter
    private HashMap<MyPair<ClientHandler, ClientHandler>,Integer> waitingForLaunch;
    private ArrayList<ClientHandler> allClientHandlers;
    @Getter
    private ArrayList<Card> allProducedCards;
    @Getter
    private DataBase dataBase;
    private boolean isRunning = true;


    public Server() {
        configLoader = new ConfigLoader("serverConfig");
        port = configLoader.readInteger("port");
//        host = configLoader.readString("host");

        waitingList = new ArrayList<>();
        runningGames = new HashMap<>();
        waitingForLaunch = new HashMap<>();
        allProducedCards = new ArrayList<>();
        allClientHandlers = new ArrayList<>();
        dataBase = new DataBase(this);
    }

    @Override
    public void run() {
        while (isRunning) {
            try{
                serverSocket = new ServerSocket(port);
                while(isRunning){
                    Socket socket = serverSocket.accept();
                    ClientHandler clientHandler = new ClientHandler(this, socket);
                    clientHandler.start();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @SneakyThrows
    public void requestOnlineGame(ClientHandler clientHandler) {
        waitingList.add(clientHandler);
        System.out.println(waitingList.size());
        if (waitingList.size() > 1) {
//            waitingList.sort(ClientHandler::compareTo);
//            ClientHandler c1 = waitingList.get(0),c2 = waitingList.get(1);
            MyPair<ClientHandler,ClientHandler> pair = new MyPair<>(waitingList.get(0),waitingList.get(1));
            waitingForLaunch.put(pair,0);
            waitingList.get(0).startOnlineGame(waitingList.get(1));
            waitingList.get(1).startOnlineGame(waitingList.get(0));
            runningGames.put(pair,new HashMap<>());
            waitingList.remove(0);
            waitingList.remove(0);
        }
    }

    private void setUpSuitAbles(){
        if (waitingList.size() > 1) {
            waitingList.sort(ClientHandler::compareTo);
            MyPair<ClientHandler,ClientHandler> pair = new MyPair<>(waitingList.get(0),waitingList.get(1));
            waitingForLaunch.put(pair,0);
            waitingList.get(0).startOnlineGame(waitingList.get(1));
            waitingList.get(1).startOnlineGame(waitingList.get(0));
            runningGames.put(pair,new HashMap<>());
            waitingList.remove(0);
            waitingList.remove(0);
            if(waitingList.size()>1)setUpSuitAbles();
        }
    }

    public MyPair<ClientHandler, ClientHandler> getClients(ClientHandler clientHandler) {
        for (Map.Entry<MyPair<ClientHandler, ClientHandler>, HashMap<ClientHandler, Integer>> entry : runningGames.entrySet())
            if (entry.getKey().getKey() == clientHandler || entry.getKey().getValue() == clientHandler)
                return entry.getKey();
        return null;
    }

    public HashMap<ClientHandler, Integer> getWatchers(ClientHandler clientHandler) {
        return runningGames.get(getClients(clientHandler));
    }

    public List<Player> getAllPlayers() {
        return dataBase.fetchAll(Player.class);
    }

    public void declareChosenMatchToWatch(String key, String value, ClientHandler viewer) {
        for (Map.Entry<MyPair<ClientHandler, ClientHandler>, HashMap<ClientHandler, Integer>> entry : runningGames.entrySet()) {
            if (entry.getKey().getKey().getMainPlayer().getName().equals(key) && entry.getKey().getValue().getMainPlayer().getName().equals(value)) {
                entry.getValue().put(viewer, 0);
                runningGames.put(entry.getKey(), entry.getValue());
                entry.getKey().getKey().sendResponse("AskForPermissionToWatch", new AskForPermissionToWatch(viewer.getMainPlayer().getName()));
                entry.getKey().getValue().sendResponse("AskForPermissionToWatch", new AskForPermissionToWatch(viewer.getMainPlayer().getName()));
                break;
            }
        }
    }

    public void watchAgreement(ClientHandler clientHandler, String viewer) {
        for (Map.Entry<MyPair<ClientHandler, ClientHandler>, HashMap<ClientHandler, Integer>> entry : runningGames.entrySet()) {
            if (entry.getKey().getKey() == clientHandler || entry.getKey().getValue() == clientHandler) {
                for (Map.Entry<ClientHandler, Integer> v : entry.getValue().entrySet()) {
                    if (v.getKey().getMainPlayer().getName().equals(viewer)) {
                        entry.getValue().put(v.getKey(), v.getValue() + 1);
                        runningGames.put(entry.getKey(), entry.getValue());
                        if (v.getValue()  % 2 == 0) {
                            v.getKey().setUpWatch(entry.getKey().getKey(), entry.getKey().getValue());
                            entry.getKey().getKey().addViewer(v.getKey().getMainPlayer().getName());
                            entry.getKey().getValue().addViewer(v.getKey().getMainPlayer().getName());
                        }
                    }
                }
            }
        }
    }

    public ClientHandler getOpponent(ClientHandler clientHandler) {
        MyPair<ClientHandler, ClientHandler> clientHandlerPair = getClients(clientHandler);
        if (clientHandlerPair.getKey() != clientHandler) return clientHandlerPair.getKey();
        else return clientHandlerPair.getValue();
    }

    public void sendMessage(ClientHandler clientHandler, String text) {
        getOpponent(clientHandler).sendResponse("ShowChatMessage", new ShowChatMessage(clientHandler.getMainPlayer().getName(), text));
        for (Map.Entry<ClientHandler, Integer> entry : getWatchers(clientHandler).entrySet())
            if (entry.getValue() != 0 && entry.getValue() % 2 == 0)
                entry.getKey().sendResponse("ShowChatMessage", new ShowChatMessage(clientHandler.getMainPlayer().getName(), text));
    }

    public void handleHibernateException() {
        System.out.println("hibernate exception");
        for(ClientHandler clientHandler: allClientHandlers) clientHandler.sendResponse("Message",new Message("connection to data base failed"));
    }
}
