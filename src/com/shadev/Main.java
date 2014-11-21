package com.shadev;

public class Main {

    public static void main(String[] args) {
        Gui mainWindow = new Gui();
	// write your code here
        try {
            System.out.println("Science bitch");
            Thread.sleep(500);
            System.out.println("Science bitch");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
