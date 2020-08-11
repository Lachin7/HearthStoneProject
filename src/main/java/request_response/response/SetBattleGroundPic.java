package request_response.response;

import client.ClientGui;

public class SetBattleGroundPic extends Response{
    private String fileName;

    public SetBattleGroundPic(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void execute(ClientGui clientGui) {
        clientGui.getPlayPanel().setBackGroundFile(fileName);
    }
}
