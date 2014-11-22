package com.shadev.net;

import com.shadev.chat.ChatEventHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by halftome on 2014.11.21..
 */
public class Connection {
    public ServerSocket serverSocket;
    public Socket socket;
    public PrintWriter out;
    public Scanner in;
    private ChatEventHandler chatEventHandler;

    public Connection(ChatEventHandler chatEventHandler){
        this.chatEventHandler = chatEventHandler;
        chatEventHandler.registerConnection(this);
    }

    private void init() throws IOException {
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void connect(String hostname, int port) throws IOException {
        System.out.println("Connecting to: " + hostname + ":" + port);
        socket = new Socket(hostname, port);
        init();
        chatEventHandler.eventConnectionSuccess(this);
    }

    public void listen(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        System.out.println("Listening on " + port);
        this.socket = serverSocket.accept();
        System.out.println("Client connected: " + socket.getRemoteSocketAddress());
        init();
        chatEventHandler.eventConnectionSuccess(this);
    }

    public void close() throws IOException {
        if(serverSocket != null){
            serverSocket.close();
        }
        if(socket != null){
            socket.close();
        }
    }
}
