package com.shadev.chat;

import com.shadev.gui.MainWindow;
import com.shadev.net.Connection;

/**
 * Created by Benjamin on 2014.11.21..
 */
public class Watcher implements Runnable{
    private Connection c;
    private ChatEventHandler e;

    public Watcher(Connection c, ChatEventHandler e){
        this.c = c;
        this.e = e;
    }

    @Override
    public void run() {
        System.out.println("Watcher Thread Started...");
        while(true){
            if(c.in.hasNext()){
                String s = c.in.nextLine();
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
