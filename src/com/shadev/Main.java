package com.shadev;

import com.shadev.chat.Watcher;
import com.shadev.gui.MainWindow;
import com.shadev.net.ClientConnectionManager;
import com.shadev.net.Connection;
import com.shadev.net.ServerConnectionManager;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Make a server:
        Connection server = new Connection();
        Thread serverConnectionManager = new Thread(new ServerConnectionManager(server, 9000));
        serverConnectionManager.start();

        // Make a client
        Connection client = new Connection();
        Thread clientConnectionManager = new Thread(new ClientConnectionManager(client, "localhost", 9000));
        clientConnectionManager.start();

        // Register Watchers
        clientConnectionManager.join();
        Thread serverWatcher = new Thread(new Watcher(server));
        serverWatcher.start();
        Thread clientWatcher = new Thread(new Watcher(client));
        clientWatcher.start();

        MainWindow mainWindow = new MainWindow(client);
    }
}
