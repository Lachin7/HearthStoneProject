package JSON.jsonForGame;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gui.panels.PlayPanel;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public class jsonForGame {

    public static void jsonFileMakerForGame(PlayPanel playPanel) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter("./src/main/java/JSON/jsonForGame/game.json" );
        gson.toJson(playPanel, fileWriter);
        fileWriter.close();
    }

    public static PlayPanel GameFromjson() throws IOException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        FileReader fileReader = new FileReader("./src/main/java/JSON/jsonForGame/game.json");
        PlayPanel playPanel = gson.fromJson(fileReader, PlayPanel.class);
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
