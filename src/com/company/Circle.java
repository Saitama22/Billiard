package com.company;

import java.awt.*;

public class Circle{
    private double x, y;
    private int di=35;
    private int directX=1, directY=1;
    private Color col;
    private double disX=0,disY=0;
    double force;
    double dx, dy;
    public Color getCol() {
        return col;
    }

    public double getDisX() {
        return disX;
    }

    public double getDisY() {
        return disY;
    }
    Circle(){}
    Circle(int x,int y, Color color)    {
        col=color;
        this.x=x;
        this.y=y;
        dx=0;
        dy=0;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getDi() {
        return di;
    }
    public void setVecSpeed(double cueX, double cueY) {
        double len = Math.sqrt(cueX*cueX+cueY*cueY);
        disX=(cueX/len);
        disY=(cueY/len);
        directY=1;
        directX=1;
        force=len/100;
    }
    public static void exchange( Circle c1,Circle c2 ) {
//        c1.force=0.5;
//        c2.force=0.5;

        double dx = c1.x - c2.x;
        double dy = c1.y - c2.y;
        double d2 = Math.sqrt(dx * dx + dy * dy);
        dx/=d2;
        dy/=d2;

        double cosC1= c1.findcorn(dx,dy);
        double cosC2= c2.findcorn(dx,dy);


        dx = Math.abs(dx);
        dy = Math.abs(dy);
        double perC1=(90-cosC1)/90, perC2=(90-cosC2)/90;
        if (c1.x>c2.x){
            c1.disX+=dx*perC2;
            c2.disX-=dx*perC1;
        }
        else {
            c1.disX-=dx*perC2;
            c2.disX+=dx*perC1;
        }
        if (c1.y>c2.y){
            c1.disY+=dy*perC2;
            c2.disY-=dy*perC1;
        }
        else {
            c1.disY-=dy*perC2;
            c2.disY+=dy*perC1;
        }
        double tempf=c1.force;
        c1.force=c1.force/2+c2.force*perC2*0.9;
        c2.force=c2.force/2+tempf*perC1*0.9;

        c1.directX=1;
        c1.directY=1;
        c2.directX=1;
        c2.directY=1;

        double dis= dist(c1.x,c1.y,c2.x,c2.y);
        while (dist(c1.x,c1.y,c2.x,c2.y)<40)        {
            c1.x+=c1.disX;
            c1.y+=c1.disY;
            c2.x+=c2.disX;
            c2.y+=c2.disY;
            dis= dist(c1.x,c1.y,c2.x,c2.y);
        }
//        c2.disX+=dx*per;
//        c2.disY+=dy*per;
//        c1.disX-=dx*per;
//        c1.disY-=dy*per;
//        c1.move(1000,1000);
        //c2.move(1000,1000);

//        double hipc1=Math.sqrt(c1.disX* c1.disX+ c1.disY+ c1.disY);
//        double hipc2=Math.sqrt(c2.disX* c2.disX+ c2.disY+ c2.disY);
//        hipc1++;

        //double d2 = i.center.distanceSq(j.center);
//        if ( d2 < 1600 )  {
//        double kii, kji, kij, kjj;
//        kji = (dx * c1.disX + dy * c1.disY) / d2; // k of j due to i
//        kii = (dx * c1.disY - dy * c1.disX) / d2; // k of i due to i
//        kij = (dx * c2.disX + dy * c2.disY) / d2; // k of i due to j
//        kjj = (dx * c2.disY - dy * c2.disX) / d2; // k of j due to j
//
//        // set velocity of i
//        c1.disY = kij * dy + kii * dx;
//        c1.disX = kij * dx - kii * dy;
//
//        // set velocity of j
//        c2.disY = kji * dy + kjj * dx;
//        c2.disX = kji * dx - kjj * dy;
//        }
    }
    private static double dist(double x1,double y1,double x2,double y2){
        int catx=Math.abs((int)x1-(int)x2);
        int caty=Math.abs((int)y1-(int)y2);
        return Math.sqrt(catx*catx+caty*caty);
    }
    public double findcorn(double v1, double v2){
        if (disX==0&&disY==0)
            return 0;
        double corn=(v1*disX + v2*disY);
        if (corn!=0)
            corn/= Math.sqrt(disY*disY + disX* disX)*Math.sqrt(v2 * v2+v1 * v1);
        corn=Math.acos(corn);
        corn*=57.2958;
        if (corn>90)
            corn=180-corn;
        return corn;
    }

    public void touch(Circle cir){
        exchange(this, cir);
    }
    public void move(int MaxX,int MaxY){
        double tempX=x;
        double tempY=y;
        if (x<=0||x>=MaxX-di) {
            directX = -directX;
            force*=0.998;
            if (x<=0)
                x=1;
            else if(x>=MaxX-di)
                x=MaxX-di-1;
        }
        if (y<=0||y>=MaxY-di) {
            directY = -directY;
            force*=0.998;
            if (y<=0)
                y=1;
            else if(y>=MaxY-di)
                y=MaxY-di-1;
        }
        x+=disX*directX *force;
        y+=disY*directY *force;
        dx=x-tempX;
        dy=y-tempY;
        force*=0.998;
        if (Math.abs(force)<0.2)
            force=0;
    }
    public boolean moving() {
        if (force == 0)
            return false;
        else
            return true;
    }
    public void stop(){
        force=0;
        disX=0;
        disY=0;
    }
    public void updatedir(){
        directX=1;
        directY=1;
    }


}
