package JSON.jsonForHeroes;

import models.Heroes.Hero;
import com.google.gson.*;
import java.io.*;


public class jsonForHeroes {

    public static void jsonFileMakerForHeroes(Hero hero) throws IOException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter("./src/main/java/JSON/jsonForHeroes/jsonFilesForHeroes/" + hero.toString() + ".json");
        gson.toJson(hero, fileWriter);
        fileWriter.close();
    }
    //jsonFileMakerForHeroes(new Mage()); jsonFileMakerForHeroes(new Rogue()); jsonFileMakerForHeroes(new Warlock());


}
