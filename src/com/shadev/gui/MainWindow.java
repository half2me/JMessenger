package com.shadev.gui;

import com.shadev.chat.ChatEventHandler;
import com.shadev.net.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * The main GUI for the chat interface
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

    /**
     * @param chatEventHandler The event handler to use
     */
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

    /**
     * Initialize the window
     * @param c Connection object to use
     */
    public void init(Connection c){
        this.connection = c;
        mainFrame.setTitle("JMessenger - " + c.getRole());
        mainFrame.setVisible(true);
    }

    /**
     * Sends a message
     * @param s The string to send
     */
    public void sendMessage(String s){
        chatHistory.append("[Me]: " + s + '\n');
        this.connection.sendMessage(s);
        typebox.setText("");
        chatHistory.updateUI();
        JScrollBar vertical = chatHistoryPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }

    /**
     * Loads a message that has been received into the chatHistory
     * and displays it on screen
     * @param s The message
     */
    public void getMessage(String s){
        chatHistory.append("[Partner]: " + s + '\n');
        chatHistory.updateUI();
        JScrollBar vertical = chatHistoryPane.getVerticalScrollBar();
        vertical.setValue(vertical.getMaximum());
    }

    /**
     * Closes the GUI
     * and clears the chat history
     */
    public void close() {
        mainFrame.setVisible(false);
        chatHistory.removeAll();
    }
}