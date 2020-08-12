package server;

import lombok.Getter;
import lombok.SneakyThrows;
import request_response.response.AskForPermissionToWatch;
import request_response.response.GoToPanel;
import request_response.response.Message;
import request_response.response.ShowChatMessage;
import resLoader.ConfigLoader;
import resLoader.database.DataBase;
import server.controller.Board.modes.*;
import server.models.Cards.Card;
import server.models.Player;
import server.models.util.MyPair;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Server extends Thread {

    private int port;
    private ConfigLoader configLoader;
    private ServerSocket serverSocket;
    @Getter
    private ArrayList<ClientHandler> onlineWaitList, deckReaderWaitList, goldenTimeWaitList, oneShotWaitList, tavernBrawlWaitList, allClientHandlers;
    @Getter
    private HashMap<MyPair<ClientHandler, ClientHandler>, HashMap<ClientHandler, Integer>> runningGames;
    @Getter
    private HashMap<MyPair<ClientHandler, ClientHandler>, Integer> waitingForLaunch;
    @Getter
    private ArrayList<Card> allProducedCards;
    @Getter
    private DataBase dataBase;
    private boolean isRunning = true;


    public Server() {
        configLoader = new ConfigLoader("serverConfig");
        port = configLoader.readInteger("port");

        runningGames = new HashMap<>();
        waitingForLaunch = new HashMap<>();
        allProducedCards = new ArrayList<>();
        allClientHandlers = new ArrayList<>();

        onlineWaitList = new ArrayList<>();
        deckReaderWaitList = new ArrayList<>();
        goldenTimeWaitList = new ArrayList<>();
        oneShotWaitList = new ArrayList<>();
        tavernBrawlWaitList = new ArrayList<>();
        dataBase = new DataBase(this);

        ScheduledExecutorService ex = Executors.newSingleThreadScheduledExecutor();
        ex.scheduleAtFixedRate(() -> {
            setUpSuitAbles(onlineWaitList);
            setUpSuitAbles(deckReaderWaitList);
            setUpSuitAbles(oneShotWaitList);
            setUpSuitAbles(tavernBrawlWaitList);
            setUpSuitAbles(goldenTimeWaitList);
        }, 10, 20, TimeUnit.SECONDS);
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                serverSocket = new ServerSocket(port);
                while (isRunning) {
                    Socket socket = serverSocket.accept();
                    ClientHandler clientHandler = new ClientHandler(this, socket);
                    clientHandler.start();
                    allClientHandlers.add(clientHandler);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SneakyThrows
    public void requestOnlineGame(ClientHandler clientHandler) {
        if (clientHandler.getBoardController().getClass() == Online.class) onlineWaitList.add(clientHandler);
        else if (clientHandler.getBoardController().getClass() == DeckReader.class) deckReaderWaitList.add(clientHandler);
        else if (clientHandler.getBoardController().getClass() == OneShot.class) oneShotWaitList.add(clientHandler);
        else if (clientHandler.getBoardController().getClass() == TavernBrawl.class) tavernBrawlWaitList.add(clientHandler);
        else if (clientHandler.getBoardController().getClass() == GoldenTime.class) goldenTimeWaitList.add(clientHandler);
    }

    private void setUpSuitAbles(ArrayList<ClientHandler> waitingList) {
        if (waitingList.size() > 1) {
            waitingList.sort(ClientHandler::compareTo);
            MyPair<ClientHandler, ClientHandler> pair = new MyPair<>(waitingList.get(0), waitingList.get(1));
            waitingForLaunch.put(pair, 0);
            runningGames.put(pair, new HashMap<>());
            waitingList.get(0).startOnlineGame(waitingList.get(1));
            waitingList.get(1).startOnlineGame(waitingList.get(0));
            waitingList.remove(0);
            waitingList.remove(0);
            if (waitingList.size() > 1) setUpSuitAbles(waitingList);
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
                        if (v.getValue() % 2 == 0) {
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
        for (ClientHandler clientHandler : allClientHandlers) {
            clientHandler.sendResponse("Message", new Message("connection to data base failed"));
            clientHandler.sendResponse("GoToPanel", new GoToPanel("mainMenu"));
        }
    }
}
