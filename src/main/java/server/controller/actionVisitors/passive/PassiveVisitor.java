package server.controller.actionVisitors.passive;

import server.controller.Board.BoardController;
import server.models.Player;

public interface PassiveVisitor {
    void visitTwiceDraw(Player player, BoardController boardController);
    void visitOffCards(Player player, BoardController boardController);
    void visitWarriors(Player player, BoardController boardController);
    void visitNurse(Player player, BoardController boardController);
    void visitManaJump(Player player, BoardController boardController);
    void visitPotionOfVitality(Player player, BoardController boardController);
}
