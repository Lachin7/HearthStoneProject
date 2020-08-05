package resLoader;

import models.Cards.Card;
import models.Cards.Minion;
import models.Heroes.Hero;
import resLoader.database.DataBase;

import java.util.ArrayList;
import java.util.List;

public class ModelLoader {

    private DataBase dataBase;
    private ArrayList<Card> defaultDeckCards;
    private Hero defaultHero;

    public ModelLoader(DataBase dataBase) {
        this.dataBase = dataBase;
        defaultDeckCards = new ArrayList<>();
    }

    public List<Card> getDefaultCards(){
        List<Card> result = new ArrayList<>();
        result.add(dataBase.fetch(Card.class,"BeamingSidekick"));
        result.add(dataBase.fetch(Card.class,"BeamingSidekick"));
        result.add(dataBase.fetch(Card.class,"BeamingSidekick"));
        result.add(dataBase.fetch(Card.class,"BeamingSidekick"));
        result.add(dataBase.fetch(Card.class,"DreadScale"));
        result.add(dataBase.fetch(Card.class,"DreadScale"));
        result.add(dataBase.fetch(Card.class,"DreadScale"));
        result.add(dataBase.fetch(Card.class,"DreadScale"));
        result.add(dataBase.fetch(Card.class,"Lifedrinker"));
        return result;
    }
}
