package com.shadev.net;

import java.io.IOException;

/**
 * Created by halftome on 2014.11.21..
 */
public class Server extends Net{
    private int port;

    public Server(int port){
        this.port = port;
    }

    public Server(){
        this.port = 9000;
    }

    @Override
    public void run() {
        try {
            listen(this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
