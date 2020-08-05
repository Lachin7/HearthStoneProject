package gui.panels.subPanels;

import client.actionController.PlayActionController;
import gui.myComponents.MyButton;
import gui.myComponents.MyPanel;
import lombok.SneakyThrows;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel extends MyPanel {
    private PlayActionController actionController;
    private JTextField message;
    private MyButton send;
    private JTextPane chatArea;
    private SimpleAttributeSet attributeSet;
    private Document doc;
    public ChatPanel(PlayActionController actionController){
        this.setPreferredSize(new Dimension(configLoader.readInteger("chatPanelWidth"),configLoader.readInteger("chatPanelHeight")));
        this.setLayout(null);
//        this.backGroundFile = "rsz_wood_wallpapers_1080p_1.jpg";
        this.grayBackground = true;
        this.actionController = actionController;
        message = new JTextField("type here",50);
        message.setBounds(0,370,170,30);
        this.add(message);
        send = new MyButton("","sendButton.png" , this, actionEvent -> {
            newChatMessage(new Color(65, 35, 111),"You",message.getText());
            actionController.sendChatMessage(message.getText());},170,370);
        chatArea = new JTextPane();
        JScrollPane scrollPane = new JScrollPane( chatArea);
        scrollPane.setBounds(0,0,200,370);
        this.add(scrollPane);
        doc = chatArea.getStyledDocument();

    }
    @SneakyThrows
    public void newChatMessage(Color color ,String sender,String message){
        attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);
        StyleConstants.setForeground(attributeSet, color);
        doc.insertString(doc.getLength(),sender, attributeSet);
        attributeSet = new SimpleAttributeSet();
        StyleConstants.setItalic(attributeSet, true);
        StyleConstants.setForeground(attributeSet, new Color(21, 9, 42));
        doc.insertString(doc.getLength(),message+"\n", attributeSet);
    }
}
