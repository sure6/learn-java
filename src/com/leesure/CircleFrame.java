package com.leesure;

import javax.swing.*;
import java.awt.*;

public class CircleFrame extends JFrame {

    public void paint(Graphics g){
        g.drawString("circle", 20,20);
        int x0=getSize().width/2;
        int x1=getSize().height/2;

        lable1:for (int i=0; i<getSize().height/2;i+=10){
            g.setColor(getRamdomColor());
            g.drawOval(x0-i,x1-i/2,i*2,i*2);
        }
    }

    private Color getRamdomColor(){
        return new Color((int)Math.random()*255,(int)Math.random()*255,(int)Math.random()*255);
    }

    public static void main(String[] args) {
        CircleFrame cf=new CircleFrame();
        cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cf.setSize(600,600);
        cf.setVisible(true);
    }
}
