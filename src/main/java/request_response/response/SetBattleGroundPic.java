package request_response.response;

import client.Client;

public class SetBattleGroundPic extends Response{
    private String fileName;

    public SetBattleGroundPic(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void execute(Client client) {
        client.getPlayPanel().setBackGroundFile(fileName);
    }
}
