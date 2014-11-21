package com.shadev.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by halftome on 2014.11.20..
 */
public abstract class Net extends Thread{
    private ServerSocket serverSocket;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Queue<String> outQueue;
    private Queue<String> inQueue;

    public void listen(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server: Listening on " + serverSocket.getLocalPort() + "...");
        while(true){
            socket = serverSocket.accept();
            System.out.println("Server: Connection accepted from " + socket.getRemoteSocketAddress());
            init();
            break;
        }

        while(true){
            receive();
        }
    }

    public void connect(String s, int port) throws IOException {
        System.out.println("Client: Connecting to " + s + "...");
        socket = new Socket(s, port);
        System.out.println("Client: Connected to " + socket.getRemoteSocketAddress());
        init();
    }



    private void init() throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        inQueue = new LinkedList<String>();
        outQueue = new LinkedList<String>();
    }

    public void send(String s) throws IOException {
        System.out.println("Sending " + s);
        out.println(s);
    }

    public String receive() throws IOException {
        return in.readLine();
    }

    @Override
    public abstract void run();
}


