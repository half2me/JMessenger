package com.shadev.chat;

import com.shadev.gui.MainWindow;
import com.shadev.net.Connection;

/**
 * This class monitors the connection for incoming messages.
 * When it receives a message it calls the appropriate method in the Event Handler
 */
public class Watcher implements Runnable{
    private Connection c;
    private ChatEventHandler e;

    /**
     * @param c The connection object to watch
     * @param e The event handler to use
     */
    public Watcher(Connection c, ChatEventHandler e){
        this.c = c;
        this.e = e;
    }

    /**
     * The runnable method
     */
    @Override
    public void run() {
        System.out.println("Watcher Thread Started...");
        while(true){
            if(c.in.hasNext()){
                String s = c.in.nextLine();
                System.out.println(s);
                e.msgReceived(s);
            }

            if(c.out.checkError()){
                e.eventDisconnected(c);
                System.out.println("Watcher Thread Terminated...");
                return;
            }
        }
    }
}
