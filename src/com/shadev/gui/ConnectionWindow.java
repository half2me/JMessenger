package com.shadev.gui;

import com.shadev.chat.ChatEventHandler;
import com.shadev.net.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The GUI for the Connection Windows
 */
public class ConnectionWindow {
    private JFrame mainFrame;
    private JPanel mainPanel;

    private JLabel connectionInfo;
    private JLabel connectionInfoText;
    private JLabel connectLabel;
    private JLabel ipLabel;
    private JLabel portLabel;

    private JComboBox ip;
    private JTextField port;

    private JButton connectButton;

    private JLabel status;

    private DefaultComboBoxModel<String> recentHostnames;

    /**
     * @param server The server's connection object
     * @param client The client's connection object
     * @param hand The event handler
     */
    public ConnectionWindow(final Connection server, final Connection client, final ChatEventHandler hand){
        this.recentHostnames = new DefaultComboBoxModel<String>();
        loadRecentHostnames();

        hand.registerConnectionGui(this);
        mainFrame = new JFrame("JMessenger - Connect");
        mainPanel = new JPanel(new FlowLayout());

        connectionInfo = new JLabel("Server listening on: ");
        connectionInfoText = new JLabel(server.serverSocket.getLocalSocketAddress().toString());
        connectLabel = new JLabel("Connect to Server: ");
        ipLabel = new JLabel("IP: ");
        portLabel = new JLabel("Port: ");
        ip = new JComboBox(recentHostnames);
        ip.setEditable(true);
        port = new JTextField(5);
        connectButton = new JButton("Connect");
        status = new JLabel("");

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                status.setText("Connecting...");
                connectButton.setEnabled(false);
                recentHostnames.addElement(ip.getSelectedItem().toString());
                hand.connect(ip.getSelectedItem().toString(), port.getText(), client);
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

    /**
     * Notify the user of a failed connection attempt
     */
    public void eventConnectionFailed(){
        this.status.setText("Failed to Connect!");
        this.connectButton.setEnabled(true);
    }

    /**
     * Closes the GUI and saves the Recent hostname list
     */
    public void close(){
        mainFrame.setVisible(false);
        saveRecentHostnames();
    }

    /**
     * Opens the GUI
     */
    public void open(){
        mainFrame.setVisible(true);
    }

    /**
     * Opens the GUI
     * @param s The status message to show to the user
     */
    public void open(String s){
        mainFrame.setVisible(true);
        status.setText(s);
        this.connectButton.setEnabled(true);
    }

    /**
     * Loads the recently used hostnames from recent.dat
     */
    private void loadRecentHostnames(){
        try {
            FileInputStream f = new FileInputStream("recent.dat");
            ObjectInputStream in = new ObjectInputStream(f);
            recentHostnames = (DefaultComboBoxModel<String>) in.readObject();
            in.close();
            f.close();
            System.out.println("Deserialized data...");
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the recently used hostnames to recent.dat
     */
    private void saveRecentHostnames(){
        try
        {
            FileOutputStream f = new FileOutputStream("recent.dat");
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(recentHostnames);
            out.close();
            f.close();
            System.out.println("Serialized data...");
        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }
}
