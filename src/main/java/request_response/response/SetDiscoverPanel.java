package request_response.response;

import client.Client;
import javafx.util.Pair;


public class SetDiscoverPanel extends Response {
    private Pair<Long, String> longStringPair,longStringPair1,longStringPair2;

    public SetDiscoverPanel(Pair<Long, String> longStringPair, Pair<Long, String> longStringPair1, Pair<Long, String> longStringPair2) {
        this.longStringPair = longStringPair;
        this.longStringPair1 = longStringPair1;
        this.longStringPair2 = longStringPair2;
    }

    @Override
    public void execute(Client client) {
        client.getPlayPanel().setDiscoverPanel(longStringPair,longStringPair2,longStringPair2);
    }
}
