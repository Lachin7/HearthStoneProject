package request_response.response;

import client.ClientGui;
import client.gui.panels.subPanels.ChatPanel;
import client.gui.panels.subPanels.WatchListPanel;

public class SetUpChatAndWatch extends Response {
    @Override
    public void execute(ClientGui clientGui) {
        ChatPanel chatPanel = new ChatPanel(clientGui.getPlayPanel().getActionController());
        chatPanel.setBounds(1,70,180,400);
        clientGui.getPlayPanel().setChatPanel(chatPanel);
        clientGui.getPlayPanel().add(chatPanel);

        WatchListPanel watchListPanel = new WatchListPanel(clientGui.getPlayPanel().getActionController());
        watchListPanel.setBounds(1050,50,150,300);
        clientGui.getPlayPanel().setWatchListPanel(watchListPanel);
        clientGui.getPlayPanel().add(watchListPanel);
    }
}
