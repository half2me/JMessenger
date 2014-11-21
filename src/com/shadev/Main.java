package com.shadev;

import com.shadev.net.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args){
        //Gui mainWindow = new Gui();
        Server server = new Server();
        Client client = new Client();

        // Start server
        server.start();

        // Connect to server
        client.start();

        // Send test msg
        try {
            client.send("Haliho");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
