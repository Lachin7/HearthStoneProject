package request_response.request;

import server.models.Cards.Card;
import server.models.Cards.Target;
import server.ClientHandler;

import java.util.ArrayList;

public class InitialMoveTargeting extends Request {
    private long id;
    public InitialMoveTargeting(long id) {
        this.id =id;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        Card card = clientHandler.getCardController().getCardWithId(id);
        if (card.getHasInitialMoveTarget())
            clientHandler.sendResponse("InitialMoveTargeting", new request_response.response.InitialMoveTargeting( new ArrayList<>(card.getTargets()), id));
    }
}
