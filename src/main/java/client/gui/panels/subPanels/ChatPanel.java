package client.gui.panels.subPanels;

import client.actionController.PlayActionController;
import client.gui.myComponents.MyButton;
import client.gui.myComponents.MyPanel;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class ChatPanel extends MyPanel {

    private JTextField message;
    private JTextPane chatArea;
    private SimpleAttributeSet attributeSet;
    private Document doc;

    public ChatPanel(PlayActionController actionController) {
        this.setPreferredSize(new Dimension(configLoader.readInteger("chatPanelWidth"), configLoader.readInteger("chatPanelHeight")));
        this.setLayout(null);
        this.grayBackground = true;
        message = new JTextField("", 50);
        message.setBounds(0, 370, 150, 30);
        this.add(message);
        new MyButton("", "sendButton.png", this, actionEvent -> {
            newChatMessage(new Color(65, 35, 111), "You", message.getText());
            actionController.sendChatMessage(message.getText());
            message.setText("");
        }, 150, 370);
        chatArea = new JTextPane();
        chatArea.setBackground(new Color(115, 115, 243, 154));
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBounds(0, 0, 180, 370);
        this.add(scrollPane);
        doc = chatArea.getStyledDocument();
    }

    @SneakyThrows
    public void newChatMessage(Color color, String sender, String message) {
        attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);
        StyleConstants.setForeground(attributeSet, color);
        doc.insertString(doc.getLength(), sender + " : ", attributeSet);
        attributeSet = new SimpleAttributeSet();
        StyleConstants.setItalic(attributeSet, true);
        StyleConstants.setForeground(attributeSet, new Color(21, 9, 42));
        doc.insertString(doc.getLength(), message + "\n", attributeSet);
    }
}
