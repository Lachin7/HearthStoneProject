package controller.actionVisitors.card;

import controller.BoardController;
import controller.actionVisitors.card.CardVisitor;
import models.Cards.Card;
import models.Character;


public interface VisitableCard {
     void accept(CardVisitor cardVisitor, Character target, BoardController boardController);
}
