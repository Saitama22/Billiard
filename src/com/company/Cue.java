package com.company;

import java.awt.*;

public class Cue {
    private int x1, x2, y1, y2;
    public void setDim1(int x, int y){
        x1=x;
        y1=y;
    }
    public void setDim2(int x, int y){
        x2=x;
        y2=y;
    }
    public int[] getDim1(){
        int[] ints = {x1,y1};
        return ints;
    }
    public int[] getDim2(){
        int[] ints = {x2,y2};
        return ints;
    }
    public void draw(Graphics g){
        g.setColor(Color.black);
        g.drawLine(x1,y1,x2,y2);
    }
}
