package com.shadev.net;

import com.shadev.chat.ChatEventHandler;

import java.io.IOException;

/**
 * This takes care of the server's connection in a Runnable way
 */
public class ServerConnectionManager implements Runnable{
    private Connection c;
    private int port;
    private ChatEventHandler chatEventHandler;

    /**
     * @param c the server's connection object
     * @param port the port to listen on
     * @param chatEventHandler the event handler to use
     */
    public ServerConnectionManager(Connection c, int port, ChatEventHandler chatEventHandler){
        this.c = c;
        this.port = port;
        this.chatEventHandler = chatEventHandler;
    }

    /**
     * The runnable method
     */
    @Override
    public void run() {
        System.out.println("SCM running");
        try {
            c.listen(this.port);
        } catch (IOException e) {
            System.out.println("SCM terminated! (Exception)");
            e.printStackTrace();
        }
        System.out.println("SCM terminated!");
    }
}
