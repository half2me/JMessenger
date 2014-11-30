package com.shadev.chat;

import com.shadev.gui.ConnectionWindow;
import com.shadev.gui.MainWindow;
import com.shadev.net.ClientConnectionManager;
import com.shadev.net.Connection;
import com.shadev.net.ServerConnectionManager;

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
        System.out.println("CEH: started!");
        this.connections = new ArrayList<Connection>();
        this.isConnected = false;
    }

    public void registerGui(MainWindow mainWindow){
        System.out.println("CEH: GUI registered!");
        this.mainWindow = mainWindow;
    }

    public void registerConnectionGui(ConnectionWindow connectionWindow){
        System.out.println("CEH: ConnectionGUI registered");
        this.connectionWindow = connectionWindow;
    }

    public void msgReceived(String s){
        System.out.println("CEH (Got):" + s);
        this.mainWindow.getMessage(s);
    }

    public void registerConnection(Connection connection){
        if(!this.connections.contains(connection)){
            this.connections.add(connection);
            System.out.println("CEH: Connection registered!");
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
        System.out.println("CEH: Outgoing Connection failed");
        if(connectionWindow != null){
            connectionWindow.eventConnectionFailed();
        }
    }

    public void eventDisconnected(Connection c){
        this.isConnected = false;
        System.out.println("CEH: Connection lost...");
        try {
            c.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainWindow.close();
        if(connectionWindow != null){
            connectionWindow.open("Disconnected...");
        }
    }

    public void eventConnectionSuccess(Connection c){
        if(this.isConnected) return;
        this.isConnected = true;
        System.out.println("CEH: Connection successful!");
        if(connectionWindow != null){
            connectionWindow.close();
        }
        this.mainWindow.init(c);
        Thread watcher = new Thread(new Watcher(c, this));
        watcher.start();
    }
}
