package JSON.jsonForGame;


import JSON.JsonAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.BoardController;
import gui.panels.PlayPanel;
import models.Cards.Card;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public class jsonForGame {

    public static void jsonFileMakerForGame(BoardController playPanel) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(BoardController.class, new JsonAdapter<BoardController>());
//        gsonBuilder.registerTypeAdapter(Card.class, new JsonAdapter<Card>());
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter("./src/main/java/JSON/jsonForGame/game.json" );
        gson.toJson(playPanel,BoardController.class,fileWriter);
        fileWriter.close();
    }

    public static BoardController GameFromjson() throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(BoardController.class, new JsonAdapter<BoardController>());
//        gsonBuilder.registerTypeAdapter(Card.class, new JsonAdapter<Card>());
        Gson gson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        FileReader fileReader = new FileReader("./src/main/java/JSON/jsonForGame/game.json");
        BoardController playPanel = gson.fromJson(fileReader, BoardController.class);
        fileReader.close();
        return playPanel;
    }

    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter("src/JSON/jsonForGame/game.json" ,true);

    }

//    public static void main(String[] args) throws IOException {
//        jsonFileMakerForGame(gameCLI.getInstance());
//    }

    //    public static void jsonFileMakerForGame(gameCLI gameCLI) throws IOException {
//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
//        FileWriter fileWriter = new FileWriter("./src/main/java/JSON/jsonForGame/game.json" ,true);
//        gson.toJson(gameCLI, fileWriter);
//        fileWriter.close();
//    }
//
//    public static gameCLI GameFromjson() throws IOException {
//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
//        FileReader fileReader = new FileReader("./src/main/java/JSON/jsonForGame/game.json");
//        gameCLI gameCLI = gson.fromJson(fileReader, (Type) gameCLI.class);
//        fileReader.close();
//        return gameCLI;
//    }
}
