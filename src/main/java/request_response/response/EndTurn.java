package request_response.response;

import client.ClientGui;
import client.gui.panels.PlayPanel;
import resLoader.MyAudioPlayer;

public class EndTurn extends Response{
    @Override
    public void execute(ClientGui clientGui) {
        MyAudioPlayer audioPlayer = clientGui.getAudioPlayer();
        PlayPanel playPanel = clientGui.getPlayPanel();
        audioPlayer.playQuick("poker-chips-daniel_simon.wav");
        audioPlayer.playQuick("drawCard.wav");
        playPanel.addSwitch();
        playPanel.updateHands();
        playPanel.updateBothFields();
        playPanel.getActionController().setUpHeroPowers();
    }
}
