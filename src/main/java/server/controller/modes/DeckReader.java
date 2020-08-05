package server.controller.modes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.board.Side;
import server.ClientHandler;
import server.controller.BoardController;
import models.Cards.Card;
import models.Heroes.Mage;
import models.Player;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DeckReader extends BoardController {

    private final ArrayList<String> friend,enemy;
    private final Player first, second ;

    public DeckReader(ClientHandler clientHandler, Player first, Player second) {
        super(clientHandler);
        friend = new ArrayList<>();
        enemy = new ArrayList<>();
        this.first = first;
        this.second =second;
    }

    public ArrayList<Card> getCards(ArrayList <String> arrayList){
        ArrayList<Card> result = new ArrayList<>();
        for(String name : arrayList) result.add(cardController.createCard(name));
        return result;
    }

    public ArrayList<String> getFriend() { return friend; }
    public ArrayList<String> getEnemy() { return enemy; }

    @Override
    protected void setPlayers() {
       DeckReader deckReader = creatDeckReaderFromJson("./src/main/resources/deckReaderFiles/deckReader.json");
       chooseMainAsFriend();
       resetPlayer(enemyPlayer);
       first.setDeckCardsInGame(getCards(deckReader.getFriend()));
       first.setChoosedHero(new Mage());
       second.setDeckCardsInGame(getCards(deckReader.getEnemy()));
       second.setChoosedHero(new Mage());
       setUpSpecialPowers(first);setUpSpecialPowers(second);
    }

    @Override
    protected void initialDeckToHand(Player player) {
        for (int i = 0; i < 3 ; i++) {
            player.getHandsCards().add(player.getDeckCardsInGame().get(0));
            player.getDeckCardsInGame().remove(0);
        }
    }

    public DeckReader creatDeckReaderFromJson(String deckReaderAddress) {
        Gson gson = new GsonBuilder().create();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(deckReaderAddress);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DeckReader deckReader = gson.fromJson(fileReader, DeckReader.class);
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deckReader;
    }

    public static void jsonFileMakerForDeckReader(DeckReader deckReader) throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(Card.class, new JsonAdapter<>());
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        FileWriter fileWriter = new FileWriter("./src/main/resources/config/deckReader.json");
        gson.toJson(deckReader, fileWriter);
        fileWriter.close();
    }

    @Override
    protected Card shuffleAndGetCard() {
       return getCurrentPlayer().getHandsCards().get(0);
    }

    @Override
    public void checkGameOver() {

    }

    @Override
    public boolean getAllowance(Side side) {
        return side == Side.FRIENDLY;
    }
}
