package server;

import client.Client;
import lombok.Getter;
import request_response.JsonRequestMaker;
import com.google.gson.Gson;
import request_response.response.Response;
import request_response.response.StartOnlineGame;
import server.controller.*;
import models.Player;
import server.controller.modes.WatchGame;
import server.controller.util.PlayerComparator;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class ClientHandler implements Runnable , Comparable<ClientHandler> {

    private Server server;
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private Gson gson;
    private Player mainPlayer;
    private final Logger PlayerLOGGER ;
    private JsonRequestMaker jsonRequestMaker;
    private PlayerController playerController;
    private CardController cardController;
    private BoardController boardController;
    @Getter
    private ClientHandler enemy;

    public ClientHandler(Server server, Socket socket){
        this.server =server;
        this.socket = socket;
        gson = new Gson();
        PlayerLOGGER = Logger.getLogger("PlayerLog");
        jsonRequestMaker = new JsonRequestMaker();
        playerController = new PlayerController(this);
        cardController = new CardController(this);
    }
    @Override
    public void run() {
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
            String[] requests;
            String requestName = "";
            String json = "";
            while (in.hasNextLine()){
                 requests = in.nextLine().split("split");
                 requestName = requests[0];
                 json = requests[1];
                 jsonRequestMaker.makeRequest(requestName,json).execute(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendResponse(String responseName, Response response){
        out.println(responseName + "split" + gson.toJson(response));
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public Player getMainPlayer() {
        return mainPlayer;
    }

    public void setMainPlayer(Player mainPlayer) {
        this.mainPlayer = mainPlayer;
    }

    public Logger getPlayerLOGGER() {
        return PlayerLOGGER;
    }

    public server.controller.CardController getCardController() {
        return cardController;
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    public Server getServer() {
        return server;
    }

    public void startOnlineGame(ClientHandler enemy) {
        sendResponse("StartOnlineGame",new StartOnlineGame());
        boardController.setEnemyPlayer(enemy.getMainPlayer());
        this.enemy = enemy;
    }

    public void setUpWatch(ClientHandler c1, ClientHandler c2){
        this.boardController = new WatchGame(c1,c2);
    }


    @Override
    public int compareTo(ClientHandler clientHandler) {
        return new PlayerComparator().getPlayersSetUpComparator().compare(this.getMainPlayer(),clientHandler.getMainPlayer());
    }
}
