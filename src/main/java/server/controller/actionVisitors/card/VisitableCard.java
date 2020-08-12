package server.controller.actionVisitors.card;

import server.controller.Board.BoardController;
import server.models.Character;


public interface VisitableCard {
     void accept(CardVisitor cardVisitor, Character target, BoardController boardController);
}
