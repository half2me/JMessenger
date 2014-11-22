package com.shadev.gui;

import com.shadev.chat.ChatEventHandler;
import com.shadev.net.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Created by Benjamin on 2014.11.21..
 */
public class ConnectionWindow {
    private JFrame mainFrame;
    private JPanel mainPanel;

    private JLabel connectionInfo;
    private JLabel connectionInfoText;
    private JLabel connectLabel;
    private JLabel ipLabel;
    private JLabel portLabel;

    private JTextField ip;
    private JTextField port;

    private JButton connectButton;

    private JLabel status;

    public ConnectionWindow(final Connection server, final Connection client, final ChatEventHandler hand){
        hand.registerConnectionGui(this);
        mainFrame = new JFrame("JMessenger - Connect");
        mainPanel = new JPanel(new FlowLayout());

        connectionInfo = new JLabel("Server listening on: ");
        connectionInfoText = new JLabel(server.serverSocket.getLocalSocketAddress().toString());
        connectLabel = new JLabel("Connect to Server: ");
        ipLabel = new JLabel("IP: ");
        portLabel = new JLabel("Port: ");
        ip = new JTextField(12);
        port = new JTextField(5);
        connectButton = new JButton("Connect");
        status = new JLabel("");

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                status.setText("Connecting...");
                connectButton.setEnabled(false);
                hand.connect(ip.getText(), port.getText(), client);
            }
        });

        mainPanel.add(connectionInfo);
        mainPanel.add(connectionInfoText);
        mainPanel.add(connectLabel);
        mainPanel.add(ipLabel);
        mainPanel.add(ip);
        mainPanel.add(portLabel);
        mainPanel.add(port);
        mainPanel.add(connectButton);
        mainPanel.add(status);


        mainFrame.setSize(370,150);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    public void eventConnectionFailed(){
        this.status.setText("Failed to Connect!");
        this.connectButton.setEnabled(true);
    }

    public void close(){
        mainFrame.setVisible(false);
    }

    public void open(){
        mainFrame.setVisible(true);
    }

    public void open(String s){
        mainFrame.setVisible(true);
        status.setText("Disconnected...");
    }
}
