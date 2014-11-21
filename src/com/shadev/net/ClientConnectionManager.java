package com.shadev.net;

import java.io.IOException;

/**
 * Created by Benjamin on 2014.11.21..
 */
public class ClientConnectionManager implements Runnable{
    private Connection c;
    private String hostname;
    private int port;

    public ClientConnectionManager(Connection c, String hostname, int port){
        this.c = c;
        this.hostname = hostname;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            c.connect(this.hostname, this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
