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
        if(c.in == null) System.out.println("c is null");
        while(true){
            if(c.in.hasNext()){
                String s = c.in.nextLine();
                System.out.println(s);
                e.msgReceived(s);
            }
        }
    }
}
