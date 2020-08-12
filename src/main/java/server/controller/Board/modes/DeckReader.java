package server.controller.Board.modes;

import resLoader.ConfigLoader;
import server.ClientHandler;
import server.models.Cards.Card;
import server.models.Heroes.Mage;
import server.models.Player;

import java.util.ArrayList;
import java.util.List;

public class DeckReader extends Online {

    private ConfigLoader configLoader;
    private Player first, second;

    public DeckReader(ClientHandler clientHandler) {
        super(clientHandler);
        configLoader = new ConfigLoader("deckReaderConfig");
//        first = new Player();
//        second = new Player();
//        setPlayer(first,"friendly");
//        setPlayer(second,"enemy");
    }

//    private void setPlayer(Player player, String deck) {
//        player.setChoosedHero(new Mage());
//        player.setDeckCardsInGame(getCards(configLoader.readStringList(deck)));
//        initialDeckToHand(player);
//    }

    public void setAsFirst(){
        friendlyPlayer = clientHandler.getMainPlayer();
        reset(friendlyPlayer);
        friendlyPlayer.setDeckCardsInGame(getCards(configLoader.readStringList("friendly")));
        initialDeckToHand(friendlyPlayer);
    }

    public void setAsSecond(){
        friendlyPlayer = clientHandler.getMainPlayer();
        reset(friendlyPlayer);
        friendlyPlayer.setDeckCardsInGame(getCards(configLoader.readStringList("enemy")));
        initialDeckToHand(friendlyPlayer);
    }

    public ArrayList<Card> getCards(List<String> list) {
        ArrayList<Card> result = new ArrayList<>();
        for (String name : list) result.add(cardController.createCard(name));
        return result;
    }

    @Override
    public void initialDeckToHand(Player player) {
        for (int i = 0; i < 3; i++) {
            player.getHandsCards().add(player.getDeckCardsInGame().get(0));
            player.getDeckCardsInGame().remove(0);
        }
    }

    @Override
    public void defineThread() {
        super.defineThread();
    }

    @Override
    public void setPlayers() {
        friendlyPlayer = clientHandler.getMainPlayer();

    }
    @Override
    protected Card shuffleAndGetCard() {
        return getCurrentPlayer().getDeckCardsInGame().get(0);
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
