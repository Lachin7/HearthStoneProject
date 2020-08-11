package server.controller.modes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import resLoader.ConfigLoader;
import server.ClientHandler;
import server.models.Cards.Card;
import server.models.Heroes.Mage;
import server.models.Player;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeckReader extends Online {

    private ConfigLoader configLoader;

    public DeckReader(ClientHandler clientHandler) {
        super(clientHandler);
        configLoader = new ConfigLoader("deckReaderConfig");
    }

    public void setAsFirst(){
        resetPlayer(friendlyPlayer);
        friendlyPlayer.setChoosedHero(new Mage());
        friendlyPlayer.setDeckCardsInGame(getCards(configLoader.readStringList("friend")));
    }

    public void setAsSecond(){
        resetPlayer(friendlyPlayer);
        friendlyPlayer.setChoosedHero(new Mage());
        friendlyPlayer.setDeckCardsInGame(getCards(configLoader.readStringList("enemy")));
    }

    public ArrayList<Card> getCards(List<String> list) {
        ArrayList<Card> result = new ArrayList<>();
        for (String name : list) result.add(cardController.createCard(name));
        return result;
    }

    @Override
    protected void setPlayers() {
    }

    @Override
    protected void initialDeckToHand(Player player) {
        for (int i = 0; i < 3; i++) {
            player.getHandsCards().add(player.getDeckCardsInGame().get(0));
            player.getDeckCardsInGame().remove(0);
        }
    }

    @Override
    protected Card shuffleAndGetCard() {
        return getCurrentPlayer().getHandsCards().get(0);
    }

//    @SneakyThrows
//    public DeckReader creatDeckReaderFromJson(String deckReaderAddress) {
//        Gson gson = new GsonBuilder().create();
//        FileReader fileReader = new FileReader(deckReaderAddress);
//        DeckReader deckReader = gson.fromJson(fileReader, DeckReader.class);
//        fileReader.close();
//        return deckReader;
//    }
//
//    public static void jsonFileMakerForDeckReader(DeckReader deckReader) throws IOException {
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
//        FileWriter fileWriter = new FileWriter("./src/main/resources/config/deckReader.json");
//        gson.toJson(deckReader, fileWriter);
//        fileWriter.close();
//    }



}
