package com.shadev.gui;

import com.shadev.net.Connection;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


/**
 * Created by Benjamin on 2014.11.20..
 */
public class MainWindow {

    private Connection connection;
    private JFrame mainFrame;
    private JPanel mainPanel;

    private JScrollPane chatHistoryPane;
    private JPanel chatHistory;

    private JPanel typePanel;
    private JTextArea typebox;
    private JButton send;
    private JLabel msgLabel;

    public MainWindow(Connection c){
        this.connection = c;
        mainFrame = new JFrame("JMessenger");
        mainPanel = new JPanel(new BorderLayout());

        chatHistory = new JPanel();
        chatHistory.setLayout(new BoxLayout(chatHistory, BoxLayout.Y_AXIS));
        chatHistory.setAlignmentY(0f);
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
        mainFrame.setVisible(true);
    }

    public void sendMessage(String s){
        typebox.setText("");
        JPanel jp = (new JPanel( new BorderLayout() ));
        JTextField tf = new JTextField(s);
        tf.setBorder(null);
        tf.setEditable(false);
        tf.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        jp.add(tf, BorderLayout.EAST);
        jp.setAlignmentY(Component.TOP_ALIGNMENT);
        chatHistory.add(jp);
        this.connection.out.println(s);
        typebox.setText("");
    }
}