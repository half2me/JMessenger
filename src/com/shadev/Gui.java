package com.shadev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Created by Benjamin on 2014.11.20..
 */
public class Gui{

    private JFrame mainFrame;
    private JPanel mainPanel;

    public Gui(){
        mainFrame = new JFrame("JMessenger");
        mainPanel = new JPanel(new FlowLayout());

        mainFrame.setSize(400,800);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        mainFrame.add(mainPanel);

        mainFrame.setVisible(true);

    }
}
