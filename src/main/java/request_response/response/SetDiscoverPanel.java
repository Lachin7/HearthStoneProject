package request_response.response;

import client.ClientGui;
import server.models.util.MyPair;


public class SetDiscoverPanel extends Response {
    private MyPair<Long, String> longStringPair,longStringPair1,longStringPair2;

    public SetDiscoverPanel(MyPair<Long, String> longStringPair, MyPair<Long, String> longStringPair1, MyPair<Long, String> longStringPair2) {
        this.longStringPair = longStringPair;
        this.longStringPair1 = longStringPair1;
        this.longStringPair2 = longStringPair2;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getPlayPanel().setDiscoverPanel(longStringPair,longStringPair2,longStringPair2);
        for (Long id : clientGui.getCardButtons().keySet())clientGui.getActionController().drawInformationOnCard(id);

    }
}
