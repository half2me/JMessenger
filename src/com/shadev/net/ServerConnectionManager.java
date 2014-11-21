package com.shadev.net;

import java.io.IOException;

/**
 * Created by Benjamin on 2014.11.21..
 */
public class ServerConnectionManager implements Runnable{
    private Connection c;
    private int port;

    public ServerConnectionManager(Connection c, int port){
        this.c = c;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            c.listen(this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
