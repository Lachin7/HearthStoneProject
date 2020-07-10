package controller.actionVisitors;

import controller.BoardController;
import models.Character;

public interface VisitableCard {
    public void accept(CardVisitor cardVisitor, Character target, BoardController boardController);
}
