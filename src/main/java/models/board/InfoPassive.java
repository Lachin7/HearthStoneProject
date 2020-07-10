package models.board;

import controller.BoardController;
import controller.actionVisitors.passive.InitialSetupsPassiveVisitor;
import controller.actionVisitors.passive.PassiveVisitor;
import controller.actionVisitors.passive.VisitablePassive;
import models.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public enum InfoPassive implements VisitablePassive {

    TwiceDraw("Twice draw","you can draw 2 cards each turn"){
        @Override
        public void accept(PassiveVisitor passiveVisitor, Player player, BoardController boardController) {
            passiveVisitor.visitTwiceDraw(player,boardController);
        }
    },
    OffCards("Off cards","all of your cards will cost one mana less") {
        @Override
        public void accept(PassiveVisitor passiveVisitor, Player player, BoardController boardController) {
            passiveVisitor.visitOffCards(player,boardController);
        }
    },
    ManaJump("Mana jump","you will start with one mana instead of zero") {
        @Override
        public void accept(PassiveVisitor passiveVisitor, Player player, BoardController boardController) {
            passiveVisitor.visitManaJump(player,boardController);
        }
    },
    Nurse("Nurse","at the end of each turn restores a minion health randomly") {
        @Override
        public void accept(PassiveVisitor passiveVisitor, Player player, BoardController boardController) {
            passiveVisitor.visitNurse(player,boardController);
        }
    },
    PotionOfVitality("Potion of Vitality","Double your starting Health.") {
        @Override
        public void accept(PassiveVisitor passiveVisitor, Player player, BoardController boardController) {
            passiveVisitor.visitPotionOfVitality(player,boardController);
        }
    },
    Warriors("Warriors","if your minion dies, your hero will gain one defence") {
        @Override
        public void accept(PassiveVisitor passiveVisitor, Player player, BoardController boardController) {
            passiveVisitor.visitWarriors(player,boardController);
        }
    };

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


//    ,
//    Zombie("Zombie","your hero power changes and will cost zero!") {
//        @Override
//        public void accept(InitialGameSetupsPassiveVisitor gameSetupsPassiveVisitor, Player player, BoardController boardController) {
//
//        }
//    }
    //    FreePower("Free power","your hero power will cost one mana less and you can use it twice each turn") {
//        @Override
//        public void accept(InitialGameSetupsPassiveVisitor gameSetupsPassiveVisitor, Player player, BoardController boardController) {
//        }
//    },


}
