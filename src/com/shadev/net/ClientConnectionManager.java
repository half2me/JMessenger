package com.shadev.net;

import com.shadev.chat.ChatEventHandler;

import java.io.IOException;

/**
 * Created by Benjamin on 2014.11.21..
 */
public class ClientConnectionManager implements Runnable{
    private Connection c;
    private String hostname;
    private int port;
    private ChatEventHandler chatEventHandler;

    public ClientConnectionManager(Connection c, String hostname, int port, ChatEventHandler chatEventHandler){
        this.c = c;
        this.hostname = hostname;
        this.port = port;
        this.chatEventHandler = chatEventHandler;
    }

    @Override
    public void run() {
        try {
            c.connect(this.hostname, this.port);
        } catch (IOException e) {
            chatEventHandler.eventConnectionFailed();
        }
    }
}
