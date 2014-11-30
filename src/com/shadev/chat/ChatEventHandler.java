package com.shadev.chat;

import com.shadev.gui.ConnectionWindow;
import com.shadev.gui.MainWindow;
import com.shadev.net.ClientConnectionManager;
import com.shadev.net.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Benjamin on 2014.11.21..
 */
public class ChatEventHandler {
    private ConnectionWindow connectionWindow;
    private MainWindow mainWindow;
    private Collection<Connection> connections;
    private boolean isConnected;

    public ChatEventHandler(){
        this.connections = new ArrayList<Connection>();
        this.isConnected = false;
    }

    public void registerGui(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void registerConnectionGui(ConnectionWindow connectionWindow){
        this.connectionWindow = connectionWindow;
    }

    public void msgReceived(String s){
        this.mainWindow.getMessage(s);
    }

    public void registerConnection(Connection connection){
        if(!this.connections.contains(connection)){
            this.connections.add(connection);
        }
    }

    public void connect(String hostname, String port, Connection connection){
        if(port.isEmpty()){
            eventConnectionFailed();
            return;
        }
        Thread clientConnectionManager = new Thread(new ClientConnectionManager(connection, hostname, Integer.parseInt(port), this));
        clientConnectionManager.start();
    }

    public void eventConnectionFailed(){
        System.out.println("Outgoing Connection failed");
        if(connectionWindow != null){
            connectionWindow.eventConnectionFailed();
        }
    }

    public void eventConnectionSuccess(Connection c){
        if(this.isConnected) return;
        this.isConnected = true;
        System.out.println("Connection successful!");
        if(connectionWindow != null){
            connectionWindow.close();
        }
        this.mainWindow.init(c);
        Thread watcher = new Thread(new Watcher(c, this));
        watcher.start();
    }
}
