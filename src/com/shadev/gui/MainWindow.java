package com.shadev.gui;

import com.shadev.chat.ChatEventHandler;
import com.shadev.net.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Created by Benjamin on 2014.11.20..
 */
public class MainWindow {

    private Connection connection;
    private JFrame mainFrame;
    private JPanel mainPanel;

    private JScrollPane chatHistoryPane;
    private JTextArea chatHistory;

    private JPanel typePanel;
    private JTextArea typebox;
    private JButton send;
    private JLabel msgLabel;
    private ChatEventHandler chatEventHandler;

    public MainWindow(ChatEventHandler chatEventHandler){
        this.chatEventHandler = chatEventHandler;
        chatEventHandler.registerGui(this);
        mainFrame = new JFrame("JMessenger");
        mainPanel = new JPanel(new BorderLayout());

        chatHistory = new JTextArea();
        chatHistoryPane = new JScrollPane(chatHistory);

        typePanel = new JPanel(new BorderLayout());
        msgLabel = new JLabel("Type a message");
        typebox = new JTextArea(3, 1);
        typebox.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == '\n'){
                    sendMessage(typebox.getText());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        send = new JButton("Send");
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage(typebox.getText());
            }
        });
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
    }

    public void init(Connection c){
        this.connection = c;
        mainFrame.setTitle("JMessenger - " + c.getRole());
        mainFrame.setVisible(true);
    }

    public void sendMessage(String s){
        chatHistory.append("[Me]: " + s + '\n');
        this.connection.sendMessage(s);
        typebox.setText("");
        chatHistory.updateUI();
    }

    public void getMessage(String s){
        chatHistory.append("[You]: " + s + '\n');
        chatHistory.updateUI();
    }

    public void close(){
        mainFrame.setVisible(false);
        chatHistory.removeAll();
    }
}
