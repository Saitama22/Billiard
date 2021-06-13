package com.company;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class Mouse extends JPanel    implements MouseListener, MouseMotionListener
{
    BillardPanel bilPan;
    Cue cue;
    Mouse(BillardPanel bp){
        super();
        cue=new Cue();
        bilPan=bp;
    }

    public void mousePressed(MouseEvent e) {
        cue.setDim1(e.getX(),e.getY());
        cue.setDim2(e.getX(),e.getY());
    }

    public void mouseDragged(MouseEvent e) {
        bilPan.delCreCue(true);
        cue.setDim2(e.getX(),e.getY());
        bilPan.setCue(cue);
        bilPan.paint(bilPan.getGraphics());
    }

    public void mouseReleased(MouseEvent e) {
        bilPan.delCreCue(false);
        bilPan.repaint();
        if (bilPan.action()) {
            Thread thread = new Thread(bilPan);
            thread.start();
        }
    }

    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {


    }
    public void mouseMoved(MouseEvent e) {

    }
    public void mouseClicked(MouseEvent e) {

    }

}
