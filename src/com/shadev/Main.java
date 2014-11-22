package com.shadev;

import com.shadev.chat.ChatEventHandler;
import com.shadev.chat.Watcher;
import com.shadev.gui.ConnectionWindow;
import com.shadev.gui.MainWindow;
import com.shadev.net.ClientConnectionManager;
import com.shadev.net.Connection;
import com.shadev.net.ServerConnectionManager;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Set params
        int port = 9000;
        if(args[1] != null){
            port = Integer.parseInt(args[1]);
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

        /*
        // Register Watchers

        Thread clientWatcher = new Thread(new Watcher(client, hand));
        clientWatcher.start();
        */


    }
}
