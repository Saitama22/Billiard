package com.company;

import org.w3c.dom.events.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Maket extends JFrame {
    Maket(){
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1000, 500));
        BillardPanel bilp = new BillardPanel();
        this.setContentPane(bilp);
        Mouse m= new Mouse(bilp);
        this.getContentPane().addMouseListener(m);
        this.getContentPane().addMouseMotionListener(m);
        pack();
        setVisible(true);
    }
    public void act(Event event){

    }
}
