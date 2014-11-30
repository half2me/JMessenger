package com.shadev;

import com.shadev.chat.ChatEventHandler;
import com.shadev.chat.Watcher;
import com.shadev.gui.ConnectionWindow;
import com.shadev.gui.MainWindow;
import com.shadev.net.ClientConnectionManager;
import com.shadev.net.Connection;
import com.shadev.net.ServerConnectionManager;

import java.io.IOException;

/**
 * @author Tamási Benjamin
 * JMessenger - Szoftlab3 NHF
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Set params
        int port = 9005;
        try{
            port = Integer.parseInt(args[1]);
        } catch
                (Exception e){
        }

        // Event Handler
        ChatEventHandler hand = new ChatEventHandler();

        // Make a server:
        Connection server = new Connection(hand);
        Thread serverConnectionManager = new Thread(new ServerConnectionManager(server, port, hand));
        serverConnectionManager.start();

        // Make a client
        Connection client = new Connection(hand);

        // GUI
        ConnectionWindow cw = new ConnectionWindow(server, client, hand);
        MainWindow mainWindow = new MainWindow(hand);
    }
}