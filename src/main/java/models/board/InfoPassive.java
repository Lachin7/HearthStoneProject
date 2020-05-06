package models.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public enum InfoPassive {

    TwiceDraw("Twice draw","you can draw 2 cards each turn"),
    OffCards("Off cards","all of your cards will cost one mana less"),
    ManaJump("Mana jump","you will start with one mana instead of zero"),
    FreePower("Free power","your hero power will cost one mana less and you can use it twice each turn"),
    PotionOfVitality("Potion of Vitality","Double your starting Health."),
    Warriors("Warriors","if your minion dies, your hero will gain one defence"),
    Zombie("Zombie","your hero power changes and will cost zero!");

    private String name, explanation;
    InfoPassive(String name, String explanation){
        this.name = name;
        this.explanation = explanation;
    }

    public static ArrayList<InfoPassive> getRandomPassives(int num){
        ArrayList<InfoPassive> passives = new ArrayList<>(Arrays.asList(InfoPassive.values()));
        Random random = new Random();
        for (int i = 0; i < passives.size()-num ; i++) passives.remove(random.nextInt(passives.size()));
        return passives;
    }

    public String getName() {
        return name;
    }

    public String getExplanation() {
        return explanation;
    }
}
