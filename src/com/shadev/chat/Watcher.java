package com.shadev.chat;

import com.shadev.net.Connection;

/**
 * Created by Benjamin on 2014.11.21..
 */
public class Watcher implements Runnable{
    private Connection c;

    public Watcher(Connection c){
        this.c = c;
    }

    @Override
    public void run() {
        while(true){
            if(c.in.hasNext()){
                System.out.println(c.in.nextLine());
            }
        }
    }
}
