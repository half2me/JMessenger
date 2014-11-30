package com.shadev.net;

import com.shadev.chat.ChatEventHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.Scanner;

/**
 * The connection object used by most classes
 * It contains abstracted connection data
 */
public class Connection {
    public ServerSocket serverSocket;
    public Socket socket;
    public PrintWriter out;
    public Scanner in;
    private ChatEventHandler chatEventHandler;

    /**
     * @param chatEventHandler the event handler to use
     */
    public Connection(ChatEventHandler chatEventHandler){
        this.chatEventHandler = chatEventHandler;
        chatEventHandler.registerConnection(this);
    }

    /**
     * This initializes the connection object's writers
     * @throws IOException if it can't initialize for some reason
     */
    private void init() throws IOException {
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    /**
     * This will connect to server (client mode)
     * @param hostname the hostname of the server
     * @param port the port of the server
     * @throws IOException If we couldn't connect
     */
    public void connect(String hostname, int port) throws IOException {
        System.out.println("Connecting to: " + hostname + ":" + port);
        socket = new Socket(hostname, port);
        init();
        chatEventHandler.eventConnectionSuccess(this);
    }

    /**
     * Waits for incoming connections (Server mode)
     * @param port port to listen on
     * @throws IOException If the port was in use, or Socket could not be created
     */
    public void listen(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        System.out.println("Listening on " + port);
        this.socket = serverSocket.accept();
        System.out.println("Client connected: " + socket.getRemoteSocketAddress());
        init();
        chatEventHandler.eventConnectionSuccess(this);
    }

    /**
     * Sends a message through the socket
     * @param s the message to send
     */
    public void sendMessage(String s){
        out.println(s);
        System.out.println("SENT: " + s);
    }

    /**
     * Close off the connection
     * @throws IOException if the connection could not be closed
     */
    public void close() throws IOException {
        if(socket != null){
            System.out.println("Closing " + getRole() + "Socket");
            socket.close();
        }
    }

    /**
     * Get the operating mode of the connection object
     * @return Server or Client (operating mode)
     */
    public String getRole(){
        if(serverSocket != null){
            return "Server";
        }
        else return "Client";
    }
}
