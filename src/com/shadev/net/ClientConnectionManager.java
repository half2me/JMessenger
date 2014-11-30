package com.shadev.net;

import com.shadev.chat.ChatEventHandler;

import java.io.IOException;

/**
 * This takes care of the client's connection in a Runnable way
 */
public class ClientConnectionManager implements Runnable{
    private Connection c;
    private String hostname;
    private int port;
    private ChatEventHandler chatEventHandler;

    /**
     * @param c The client's connection object
     * @param hostname hostname of the server to connect to
     * @param port port number of the server
     * @param chatEventHandler the event handler to use
     */
    public ClientConnectionManager(Connection c, String hostname, int port, ChatEventHandler chatEventHandler){
        this.c = c;
        this.hostname = hostname;
        this.port = port;
        this.chatEventHandler = chatEventHandler;
    }

    /**
     * The runnable method
     */
    @Override
    public void run() {
        System.out.println("CCM Thread running");
        try {
            c.connect(this.hostname, this.port);
        } catch (IOException e) {
            chatEventHandler.eventConnectionFailed();
            System.out.println("CCM Thread terminated! (Exception)");
        }
        System.out.println("CCM Thread terminated!");
    }
}
