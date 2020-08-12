package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import request_response.JsonRequestMaker;
import request_response.request.ChooseGameSetUps;
import request_response.request.ExitPlay;
import request_response.request.LaunchGame;
import request_response.response.AddViewer;
import request_response.response.GoToPanel;
import request_response.response.Response;
import server.controller.Board.BoardController;
import server.controller.CardController;
import server.controller.PlayerController;
import server.controller.Board.modes.DeckReader;
import server.controller.Board.modes.Online;
import server.controller.Board.modes.WatchGame;
import server.controller.util.PlayerComparator;
import server.models.Player;
import server.models.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Thread implements Comparable<ClientHandler> {

    private Server server;
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private Gson gson;
    @Getter
    @Setter
    private Player mainPlayer;
    private JsonRequestMaker jsonRequestMaker;
    @Getter
    private PlayerController playerController;
    @Getter
    private CardController cardController;
    @Getter
    @Setter
    private BoardController boardController;
    @Getter @Setter
    private ClientHandler enemy;
    private boolean isRunning;

    public ClientHandler(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.enableComplexMapKeySerialization();
        gson = gsonBuilder.create();
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonRequestMaker = new JsonRequestMaker();
        playerController = new PlayerController(this);
        cardController = new CardController(this);
//        ScheduledExecutorService ex = Executors.newSingleThreadScheduledExecutor();
//        ex.scheduleAtFixedRate(() -> {
//            System.out.println("enterd");
////                if (!socket.getInetAddress().isReachable(1000)) System.out.println("eerrrreooe");
//            //            if (out.checkError()) System.out.println("error");
//        }, 10, 20, TimeUnit.SECONDS);
        isRunning = true;

    }

    @Override
    public void run() {
        String[] requests;
        String requestName = "",json = "";
        while (isRunning) {
            try {
                requests = in.nextLine().split("split");
                requestName = requests[0];
                json = requests[1];
                jsonRequestMaker.makeRequest(requestName, json).execute(this);
            } catch (Exception e) {
                handleClientDisconnection();
            }
        }
    }

    public void sendResponse(String responseName, Response response) {
        out.println(responseName + "split" + gson.toJson(response));
    }

    private void handleClientDisconnection(){
        System.out.println("client  "+mainPlayer.getName() +"  disconnected");
        new ExitPlay().execute(this);
        log("client was disconnected and exited the game");
        isRunning = false;
    }

    public Server getServer() {
        return server;
    }

    public void startOnlineGame(ClientHandler enemy) {
        if (boardController instanceof DeckReader){
            if (server.getClients(this).getKey()==this)((DeckReader) boardController).setAsFirst();
            else ((DeckReader) boardController).setAsSecond();
        }
        new ChooseGameSetUps(boardController).execute(this);
        if (boardController instanceof Online) {
            boardController.setEnemyPlayer(enemy.getMainPlayer());
        }
        this.enemy = enemy;
    }

    public void setUpWatch(ClientHandler c1, ClientHandler c2) {
        this.boardController = new WatchGame(c1, c2);
        sendResponse("GoToPanel", new GoToPanel("play"));
    }


    @Override
    public int compareTo(ClientHandler clientHandler) {
        return new PlayerComparator().getPlayersSetUpComparator().compare( clientHandler.getMainPlayer(),this.getMainPlayer());
    }

    public void log(String log) {
        server.getDataBase().save(new Log(mainPlayer.getID(), log));
    }

    public void sendLaunchRequest() {
        if (server.getWaitingForLaunch().get(server.getClients(this)) > 0) {
            boardController.addSwitch();
            new LaunchGame(true).execute(this);
            new LaunchGame(true).execute(enemy);
        } else
            server.getWaitingForLaunch().put(server.getClients(this), server.getWaitingForLaunch().get(server.getClients(this)) + 1);
    }

    public void addViewer(String name) {
        sendResponse("AddViewer", new AddViewer(name));
    }


}
