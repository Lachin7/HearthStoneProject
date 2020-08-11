package request_response.request;

import server.ClientHandler;

public class ChangeSetting extends Request {
    private String cardBack,field;
    public ChangeSetting(String cardBack, String field) {
        this.cardBack = cardBack;
        this.field = field;
    }

    @Override
    public void execute(ClientHandler clientHandler) {
        if (cardBack!=null)clientHandler.getMainPlayer().setCardSkin(cardBack);
        if (field!=null)clientHandler.getMainPlayer().setPlayBackGround(field);
    }
}
