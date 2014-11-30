package com.shadev.net;

import com.shadev.chat.ChatEventHandler;

import java.io.IOException;

/**
 * Created by Benjamin on 2014.11.21..
 */
public class ServerConnectionManager implements Runnable{
    private Connection c;
    private int port;
    private ChatEventHandler chatEventHandler;

    public ServerConnectionManager(Connection c, int port, ChatEventHandler chatEventHandler){
        this.c = c;
        this.port = port;
        this.chatEventHandler = chatEventHandler;
    }

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
