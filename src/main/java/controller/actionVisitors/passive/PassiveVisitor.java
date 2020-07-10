package controller.actionVisitors.passive;

import controller.BoardController;
import models.Player;

public interface PassiveVisitor {
    void visitTwiceDraw(Player player, BoardController boardController);
    void visitOffCards(Player player, BoardController boardController);
    void visitWarriors(Player player, BoardController boardController);
    void visitNurse(Player player, BoardController boardController);
    void visitManaJump(Player player, BoardController boardController);
    void visitPotionOfVitality(Player player, BoardController boardController);
}
