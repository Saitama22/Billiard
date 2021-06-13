package com.company;

import javax.swing.*;
import java.awt.*;


public class BillardPanel extends JPanel implements Runnable{
    private Cue cue ;
    int CurCir;
    boolean paintCue=true;
    Circle[] circles=new Circle[16];

    BillardPanel(){
        super();
        setBackground(new Color( 0,100,0));
        circles[0]=new Circle(750,200,Color.WHITE);
        circles[1]=new Circle(250,200,Color.BLACK);
        circles[2]=new Circle(214,182,Color.BLUE);
        circles[3]=new Circle(214,218,Color.YELLOW);
        circles[4]=new Circle(178,200,Color.MAGENTA);
        circles[5]=new Circle(178,236,Color.CYAN);
        circles[6]=new Circle(178,164,Color.RED);
        circles[7]=new Circle(142,182,Color.PINK);
        circles[8]=new Circle(142,218,Color.gray);
        circles[9]=new Circle(142,254, new Color(226, 119, 15));
        circles[10]=new Circle(142,146,new Color(76, 6, 76));

        circles[11]=new Circle(106,200,new Color(190, 236, 82));
        circles[12]=new Circle(106,236,new Color(24, 255, 0));
        circles[13]=new Circle(106,272,new Color(0, 70, 144));
        circles[14]=new Circle(106,164,new Color(255, 0, 251));
        circles[15]=new Circle(106,128,new Color(12, 232, 236));

        CurCir=0;
        cue = new Cue();

    }
    public void delCreCue(boolean b){
        paintCue=b;
    }
    public void setCue(Cue cue1){
        cue=cue1;
    }
    @Override
    public void paint(Graphics g)   {
        super.paint(g);
//        g.setColor(Color.black);
//        int rad =100;
//        g.fillOval(0,0,rad,rad);
//        g.fillOval(-250,getHeight(),rad,rad);
//        g.fillOval(getWidth()/2,getHeight()-circles[0].getDi(),rad,rad);
//        g.fillOval(getWidth()/2,0,rad,rad);
//        g.fillOval(getWidth(),getHeight(),rad,rad);
//        g.fillOval(getWidth(),0,rad,rad);
       if (cue!=null&&paintCue)
            cue.draw(g);
        for (Circle circle : circles) {
            drawCir(g,circle);
        }
    }
    public void drawCir(Graphics g, Circle circle){
        g.setColor(circle.getCol());
        g.fillOval((int)circle.getX(),(int)circle.getY(),circle.getDi(),circle.getDi());
    }
    @Override
    public void run() {
        circles[0].setVecSpeed(cue.getDim1()[0]-cue.getDim2()[0],cue.getDim1()[1]-cue.getDim2()[1]);
        while (true){
            checkTouch();
            repaint();
            boolean allmove=false;
            for (Circle circle : circles) {
                circle.move(getWidth(), getHeight());
                if (!allmove)
                    allmove = circle.moving();
            }
            if (!allmove)
                break;
            try {
                Thread.sleep( 1 );
            }
            catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
        for (int i = 1; i < circles.length; i++) {
            circles[i].stop();
        }
    }
    private void checkTouch(){
        for (int j= 0; j < circles.length-1; j++) {
            for (int i = j+1; i < circles.length; i++) {
                if (dist(circles[j].getX(),circles[j].getY(),circles[i].getX(),circles[i].getY())<circles[j].getDi())
                    circles[i].touch(circles[j]);
            }
        }
    }
    private int dist(double x1,double y1,double x2,double y2){
        int catx=Math.abs((int)x1-(int)x2);
        int caty=Math.abs((int)y1-(int)y2);
        return (int)Math.sqrt(catx*catx+caty*caty);
    }

    public boolean action()    {

        if ((Math.abs(circles[0].getX()-cue.getDim1()[0])<circles[0].getDi()&&Math.abs(circles[0].getY()-cue.getDim1()[1])<circles[0].getDi())
        && (Math.abs(circles[0].getX()-cue.getDim2()[0])>circles[0].getDi()||Math.abs(circles[0].getY()-cue.getDim2()[1])>circles[0].getDi()))
            return true;
        else
            return false;
    }
}
