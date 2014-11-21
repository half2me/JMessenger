package com.shadev.net;

import java.io.IOException;

/**
 * Created by halftome on 2014.11.21..
 */
public class Client extends Net{

    private String host;
    private int port;

    public Client(){
        this.host = "localhost";
        this.port = 9000;
    }

    public Client(String host){
        this.host = host;
        this.port = 9000;
    }

    public Client(String host, int port){
        this.host = host;
        this.port = port;
    }
    @Override
    public void run() {
        try {
            connect(this.host, this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
