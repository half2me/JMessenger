package com.shadev.net;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;

/**
 * Created by halftome on 2014.11.21..
 */
public class ConnectionContext {
    private ServerSocket serverSocket;
    private Socket socket;
    private PrintWriter out;
}
