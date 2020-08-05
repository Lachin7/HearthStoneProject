package request_response.request;

import gui.myComponents.MyCardButton;
import models.Cards.Card;
import models.Cards.Minion;
import models.Cards.Weapon;
import server.ClientHandler;
import server.controller.BoardController;

public class DrawInformationOnCard extends Request {
    private long id;
    private MyCardButton cardB;
    public DrawInformationOnCard(MyCardButton cardB, long id) {
        this.id = id;
        this.cardB = cardB;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        Card card = clientHandler.getCardController().getCardWithId(id);
        int hp = -30,attack = -1,durability = -30;
        boolean hasShield = false, hasTaunt = false, isInGame= false, canAttack = false;
        if (card instanceof Minion) {
            hp = ((Minion) card).getHP();
            attack = ((Minion) card).getAttack();
            hasShield = ((Minion) card).hasDivineShield();
            hasTaunt = ((Minion) card).hasTaunt();
            canAttack = true;
        }
        if (card instanceof Weapon){
            attack = ((Weapon) card).getAttack();
            durability = ((Weapon) card).getDurability();
        }
//        if (clientHandler.getBoardController()!=null){
//            BoardController boardController = clientHandler.getBoardController();
//            for (Card card1 : boardController.getFriendlyPlayer().getDeckCardsInGame())
//                if (card1.getId() == id) {
//                    isInGame = true;
//                    break;
//                }
//            for (Card card1 : boardController.getEnemyPlayer().getDeckCardsInGame())
//                if (card1.getId() == id) {
//                    isInGame = true;
//                    break;
//                }
//            for (Card card1 : boardController.getFriendlyPlayer().getHandsCards())
//                if (card1.getId() == id) {
//                    isInGame = true;
//                    break;
//                }
//            for (Card card1 : boardController.getEnemyPlayer().getDeckCardsInGame())
//                if (card1.getId() == id) {
//                    isInGame = true;
//                    break;
//                }
//            for (Card card1 : boardController.getFriendlyPlayer().getFieldCardsInGame())
//                if (card1.getId() == id) {
//                    isInGame = true;
//                    break;
//                }
//            for (Card card1 : boardController.getEnemyPlayer().getDeckCardsInGame())
//                if (card1.getId() == id) {
//                    isInGame = true;
//                    break;
//                }
//
//            isInGame = true;
//        }

        clientHandler.sendResponse("DrawInformationOnCard", new request_response.response.DrawInformationOnCard(cardB,card.getManaCost(),hp,attack,durability,clientHandler.getCardController().isLocked(card),hasShield,hasTaunt,canAttack,card.getType()));

    }
}
