package request_response.request;

import server.models.Cards.Card;
import server.models.Cards.Minion;
import server.models.Cards.Weapon;
import server.ClientHandler;

public class DrawInformationOnCard extends Request {
    private Long id;

    public DrawInformationOnCard(Long id) {
        this.id = id;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        Card card = clientHandler.getCardController().getCardWithId(id);
        int hp = -30,attack = -30,durability = -30;
        boolean hasShield = false, hasTaunt = false, canAttack = false;
        if (card instanceof Minion) {
            hp = ((Minion) card).getHP();
            attack = ((Minion) card).getAttack();
            hasShield = ((Minion) card).hasDivineShield();
            hasTaunt = ((Minion) card).hasTaunt();
            canAttack = ((Minion) card).canAttack();
        }
        if (card instanceof Weapon){
            attack = ((Weapon) card).getAttack();
            durability = ((Weapon) card).getDurability();
        }
        clientHandler.sendResponse("DrawInformationOnCard", new request_response.response.DrawInformationOnCard(id,card.getManaCost(),hp,attack,durability,clientHandler.getCardController().isLocked(card),hasShield,hasTaunt,canAttack,card.getType()));

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

}
