package server.models.Heroes;

import server.controller.Board.BoardController;
import server.controller.actionVisitors.heroPower.HeroPowerVisitor;
import server.controller.actionVisitors.heroPower.VisitableHeroPower;
import server.models.Character;

public enum HeroPower implements VisitableHeroPower {
    MageHPower(true,2){
        @Override
        public void accept(HeroPowerVisitor heroPowerVisitor, Character target, BoardController boardController) {
            heroPowerVisitor.visitMageHeroPower(target,boardController);
        }
    },
    RogueHPower (false,3){
        @Override
        public void accept(HeroPowerVisitor heroPowerVisitor,Character target,BoardController boardController) {
            heroPowerVisitor.visitRougeHeroPower(target,boardController);
        }
    },
    WarlockHeroPower (false,0){
        @Override
        public void accept(HeroPowerVisitor heroPowerVisitor,Character target,BoardController boardController) {
            heroPowerVisitor.visitWarlockHeroPower(target,boardController);
        }
    },
    HunterHPower(false,0){
        @Override
        public void accept(HeroPowerVisitor heroPowerVisitor,Character target,BoardController boardController) {
            heroPowerVisitor.visitHunterHeroPower(target,boardController);
        }
    },
    PriestHPower(true,2) {
        @Override
        public void accept(HeroPowerVisitor heroPowerVisitor,Character target,BoardController boardController) {
            heroPowerVisitor.visitPriestHeroPower(target,boardController);
        }
    };

    private boolean hasTarget ;
    private int mana;
    HeroPower(boolean hasTarget, int mana){
        this.hasTarget = hasTarget;
        this.mana = mana;
    }

    public boolean isHasTarget() {
        return hasTarget;
    }

    public void setHasTarget(boolean hasTarget) {
        this.hasTarget = hasTarget;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
