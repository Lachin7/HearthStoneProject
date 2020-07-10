package controller.modes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.BoardController;
import models.Cards.Card;
import models.Heroes.Mage;
import models.Player;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DeckReader extends BoardController {

    private ArrayList<String> friend = new ArrayList<>() , enemy = new ArrayList<>();


    public ArrayList<Card> getCards(ArrayList <String> arrayList){
        ArrayList<Card> result = new ArrayList<>();
        for(String name : arrayList) result.add(cardController.creatCard(name));
        return result;
    }

    public ArrayList<String> getFriend() { return friend; }
    public ArrayList<String> getEnemy() { return enemy; }

    @Override
    protected void setPlayers() {
       DeckReader deckReader = creatDeckReaderFromJson("./src/main/resources/deckReaderFiles/deckReader.json");
       friendlyPlayer = new Player();
       friendlyPlayer.setDeckCardsInGame(getCards(deckReader.getFriend()));
       friendlyPlayer.setPlayersChoosedHero(new Mage());
       friendlyPlayer.setPlayerName("friendly player");
       enemyPlayer = new Player();
       enemyPlayer.setDeckCardsInGame(getCards(deckReader.getEnemy()));
       enemyPlayer.setPlayersChoosedHero(new Mage());
       friendlyPlayer.setPlayerName("enemy player");
       setUpSpecialPowers(friendlyPlayer);setUpSpecialPowers(enemyPlayer);
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

    @Override
    protected Card shuffleAndGetCard() {
       return getCurrentPlayer().getHandsCards().get(0);
    }
}
