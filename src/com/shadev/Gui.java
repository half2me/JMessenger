package com.shadev;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


/**
 * Created by Benjamin on 2014.11.20..
 */
public class Gui{

    private JFrame mainFrame;
    private JPanel mainPanel;

    private JScrollPane chatHistoryPane;
    private JPanel chatHistory;

    private JPanel typePanel;
    private JTextArea typebox;
    private JButton send;
    private JLabel msgLabel;

    public Gui(){
        mainFrame = new JFrame("JMessenger");
        mainPanel = new JPanel(new BorderLayout());

        chatHistory = new JPanel();
        chatHistory.setLayout(new BoxLayout(chatHistory, BoxLayout.Y_AXIS));
        chatHistory.setAlignmentY(0f);
        chatHistoryPane = new JScrollPane(chatHistory);

        typePanel = new JPanel(new BorderLayout());
        msgLabel = new JLabel("Type a message");
        typebox = new JTextArea(3, 1);
        send = new JButton("Send");
        typePanel.add(msgLabel, BorderLayout.NORTH);
        typePanel.add(typebox, BorderLayout.CENTER);
        typePanel.add(send, BorderLayout.EAST);

        mainPanel.add(chatHistoryPane, BorderLayout.CENTER);
        mainPanel.add(typePanel, BorderLayout.SOUTH);

        mainFrame.setSize(400,800);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        sendMessage("Hooya");
        sendMessage("Hooya2");
        sendMessage("Hooya");
        sendMessage("Hooya2");
    }

    public void sendMessage(String s){
        JPanel jp = (new JPanel( new BorderLayout() ));
        jp.add(new Label(s), BorderLayout.EAST);
        jp.setAlignmentY(Component.TOP_ALIGNMENT);
        chatHistory.add(jp);
    }
}
