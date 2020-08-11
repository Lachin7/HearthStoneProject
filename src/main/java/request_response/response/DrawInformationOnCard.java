package request_response.response;

import client.ClientGui;
import client.gui.myComponents.GuiCard;
import client.gui.myComponents.MyCardButton;
import server.models.Cards.Card;

public class DrawInformationOnCard extends Response{
    private Long id;
    private GuiCard guiCard;

    public DrawInformationOnCard(Long id,GuiCard guiCard) {
        this.guiCard = guiCard;
        this.id = id;
    }

    @Override
    public void execute(ClientGui clientGui) {
        for (MyCardButton cardButton : clientGui.getCardButtons().values())if (cardButton.getId()==id)
            cardButton.drawInformationOnCard(guiCard);

    }
}
