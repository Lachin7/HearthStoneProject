package client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import request_response.JsonResponseMaker;
import request_response.request.Request;
import request_response.response.Response;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientNetwork extends Thread {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private Gson gson;
    private JsonResponseMaker jsonResponseMaker;
    private ClientGui clientGui;

    public ClientNetwork(String host, int port, ClientGui clientGui) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.enableComplexMapKeySerialization();
        gson = gsonBuilder.create();
        jsonResponseMaker = new JsonResponseMaker();
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            clientGui.getConnectionPanel().getError().setText("can not connect to server!");
        }
        this.clientGui = clientGui;
    }

    @Override
    public void run() {
        String[] responses;
        String responseName,json;
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
            while (in.hasNextLine()) {
                responses = in.nextLine().split("split");
                responseName = responses[0];
                json = responses[1];
                if (!responseName.equals("ping")) {
                    Response response = jsonResponseMaker.makeResponse(responseName, json);
                    clientGui.executeResponse(response);
                }
            }
        } catch (Exception e) {
            System.out.println("cant connect");
            JOptionPane.showMessageDialog(null, "connection is lost");
        }
    }

    public void sendRequest(String requestName, Request request) {
       if (request!=null)out.println(requestName + "split" + gson.toJson(request));
    }
}
