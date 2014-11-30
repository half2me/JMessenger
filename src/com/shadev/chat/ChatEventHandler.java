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
 * The main event handler for the application.
 * Connections and GUIs are registered in the EH
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

    /**
     * This method should be called by the GUI to register itself
     * in the EH. This method is for the Main Window GUI
     * @param mainWindow the main window object
     */
    public void registerGui(MainWindow mainWindow){
        System.out.println("CEH: GUI registered!");
        this.mainWindow = mainWindow;
    }

    /**
     * This method should be called by the GUI to register itself
     * in the EH. This method is for the Connection Window GUI
     * @param connectionWindow the connection window object
     */
    public void registerConnectionGui(ConnectionWindow connectionWindow){
        System.out.println("CEH: ConnectionGUI registered");
        this.connectionWindow = connectionWindow;
    }

    /**
     * This method should be triggered when we get a message
     * This method is triggered by a Watcher
     * @param s The message we received
     */
    public void msgReceived(String s){
        System.out.println("CEH (Got):" + s);
        this.mainWindow.getMessage(s);
    }

    /**
     * This method should be run by the Connection object
     * to register itself in the EH.
     * @param connection The connection object to register
     */
    public void registerConnection(Connection connection){
        if(!this.connections.contains(connection)){
            this.connections.add(connection);
            System.out.println("CEH: Connection registered!");
        }
    }

    /**
     * This method initiates a connection to a server (Client mode)
     * @param hostname The hostname of the server
     * @param port The port of the server
     * @param connection The connection object to use for the connection
     */
    public void connect(String hostname, String port, Connection connection){
        if(port.isEmpty()){
            eventConnectionFailed();
            return;
        }
        Thread clientConnectionManager = new Thread(new ClientConnectionManager(connection, hostname, Integer.parseInt(port), this));
        clientConnectionManager.start();
    }

    /**
     * This method is called when a connection has failed
     */
    public void eventConnectionFailed(){
        System.out.println("CEH: Outgoing Connection failed");
        if(connectionWindow != null){
            connectionWindow.eventConnectionFailed();
        }
    }

    /**
     * This method is called when the Connection is terminated
     * @param c The connection object which has had its connection terminated
     */
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

    /**
     * This method is called when a connection succeeds
     * @param c The connection object which has successfully connected
     */
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
