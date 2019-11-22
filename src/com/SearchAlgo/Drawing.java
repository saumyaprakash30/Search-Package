package com.SearchAlgo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;


import static java.lang.Math.abs;
import static java.lang.Thread.sleep;

public class Drawing extends JFrame  {
    public static  int CANVAS_WIDTH  ;
    public static  int CANVAS_HEIGHT ;
    public int []a;
    public int tofind;
    private int sleeping=1;
    // Declare an instance of the drawing canvas,
    // which is an inner class called DrawCanvas extending javax.swing.JPanel.
    private DrawCanvas canvas;

    public int getSleeping() {
        return sleeping;
    }


    // Constructor to set up the GUI components and event handlers
    public Drawing(int []a,int tofind) {


        this.a=a;
        this.tofind=tofind;
        canvas = new DrawCanvas();    // Construct the drawing canvas
        JScrollPane pane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        pane.getViewport().add(canvas);
        CANVAS_WIDTH = a.length*35;
        if(CANVAS_WIDTH<300)
            CANVAS_WIDTH = 300;
        CANVAS_HEIGHT = 500;
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));


        // Set the Drawing JPanel as the JFrame's content-pane
        Container cp = getContentPane();
        cp.add(pane);
        // or "setContentPane(canvas);"

//        setDefaultCloseOperation(EXIT_ON_CLOSE);   // Handle the CLOSE button
        pack();              // Either pack() the components; or setSize()
        setTitle("......");  // "super" JFrame sets the title
        setVisible(true);    // "super" JFrame show
    }



    /**
     * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
     */
    private class DrawCanvas extends JPanel  {

        int n ;
        private int p1 = 20, q1 = 200, r1= 30, s1 = 30;
        int initp = 20+r1/2,initq = (q1+s1),d=0,k=0;
        int currp=initp,currq=initq;
        int step = (int)Math.sqrt(a.length);
        int prev=0,sstep,found =1;
        int countStep=0,countSmallStep=0;
        int coutfound=0;
        int goback=1;
        int foundat;
        // Override paintComponent to perform your own painting
            public DrawCanvas()
            {
                n= a.length;
                sstep = (int)Math.floor(Math.sqrt(n));
                System.out.println(n+"[[[]]]"+sstep);
                try{

                    while(a[Math.min(sstep,n)-1]< tofind)
                    {
                        prev = sstep;
                        sstep+=(int)Math.floor(Math.sqrt(n));
                        System.out.println(sstep);
                        if(prev>=n) {
                            found = 0;
                            break;
                        }
                        countStep++;
                    }

                    if(found!=0)
                    {
                        while(a[prev]<tofind)
                        {
                            prev++;
                            if(prev == Math.min(sstep,n))
                            {
                                found=0;
                                break;

                            }
                            countSmallStep++;
                        }

                    }

                    if(found!=0){
                        while(prev<n){
                            if(a[prev]==tofind){

                            }else break;
                            prev++;
                            coutfound++;
                        }
                    }
                    foundat = countStep*step+countSmallStep;

                }catch (Exception e){
                    System.out.println(n+"[[[-"+tofind  +"-]]]"+sstep);
                    for (int i=0;i<n;i++)
                        System.out.println("::--"+a[i]);
                    System.out.println(sstep+" ::::::::::"+n);
                }

                System.out.println(countStep+" .."+countSmallStep+".."+coutfound+"s"+foundat);
                Timer t = new Timer(15,new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        repaint();
                    }
                });
                t.start();
            }


        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            int p = 20, q = 200, r = 30, s = 30;

            g.drawString("Step Size = \u221A"+a.length+" = "+step,(r*a.length)/2,150);
            g.drawString("To find = "+tofind,(r*a.length)/2,180);




            if(countStep>=0){
                g.fillOval(currp+d,currq+k,10,10);
                if (d<(r*step)/2)
                    k++;
                else k--;
                if(d==step*r)
                {
                    d=0;
                    currp=currp+step*r;
                    countStep--;
                }
                else d++;

            }
            else if((countSmallStep)>=0){
                if(goback==1){
                    g.fillOval(currp+d,currq+k,10,10);
                    if(abs(d)<abs((r*step)/2)) k++;
                    else k--;
                    if(abs(d)==step*r){
                        d=0;
                        currp=currp-r*step;
                        goback=0;
                    }else{
                        d--;
                    }

                }
                else{
                    g.fillOval(currp+d,currq+k,10,10);
                    if (d<(r)/2)
                        k++;
                    else k--;
                    if(d==r)
                    {
                        d=0;
                        currp=currp+r;
                        countSmallStep--;
                    }
                    else d++;

                }
            }
            else if(coutfound-1>0){
                g.fillOval(currp+d,currq+k,10,10);
                if (d<(r)/2)
                    k++;
                else k--;
                if(d==r)
                {
                    d=0;
                    currp=currp+r;
                    coutfound--;
                    foundat++;
                }
                else d++;

            }
            for (int i = 0; i < a.length; i++) {
                g.setColor(Color.black);
                g.drawRect(p, q, r, s);
                g.setColor(Color.blue);
                if (a[i] == tofind && foundat>=i)
                {
                    g.setColor(Color.RED);

                }
                g.drawString(Integer.toString(a[i]), (p + r / 2), q + 2 * s / 3);
                p += r;
            }




                

////////////////////////////////////////////////////////////////
//            int p = 20, q = 200, r = 30, s = 30;
//            for (int i = 0; i < a.length; i++) {
//                g.setColor(Color.black);
//                g.drawRect(p, q, r, s);
//                g.setColor(Color.blue);
//                if (a[i] == tofind)
//                    g.setColor(Color.RED);
//                g.drawString(Integer.toString(a[i]), (p + r / 2), q + 2 * s / 3);
//                p += r;
//
//                //arrow
//
//                int initp = 20+r/2,initq = q+s+20;
//
////                    g.setColor(Color.BLUE);
////
////                    g.drawLine(initp,initq,initp,initq+30);
////                    g.drawLine(initp-10,initq+10,initp,initq);
////                    g.drawLine(initp,initq,initp+10,initq+10);
//
//                g.fillOval(initp,initq,5,5);
//
//                int step =(int) Math.floor(Math.sqrt(a.length));
//                int drawStep=step;
//                int n = a.length;int prev =0;int found =1;
//
//                int drawPrevX=initp,drawPrevY=initq;
//
//                while(a[Math.min(step,n)-1]< tofind)
//                {
//                    int xbeg = initp,ybeg = initq;
//                    drawPrevX = xbeg;drawPrevY=ybeg-s-40;
//
//                    while(xbeg<initp+drawStep*r)
//                    {
//                        System.out.println("h1");
//                        xbeg++;
//                        if((xbeg-initp)<(drawStep*r)/2)
//                            ybeg++;
//                        else
//                            ybeg--;
//                        g.fillOval(xbeg,ybeg,5,5);
//
////                        try
////                        {
////                            sleep(1);
////                        }catch (Exception e){
////                            System.out.println("errorrrr in paint");
////                        }
//                    }
//                    initp = xbeg;
//
//                    prev =step;
//                    step+=(int)Math.floor(Math.sqrt(n));
//                    if(prev>=n){
//                        found =0;
//                        break;
//                    }
//
//
//
//                }
//                int smallStep=drawPrevX+r;
//                g.fillOval(drawPrevX,drawPrevY,5,5);
//                int prevDrawX=drawPrevX;
//                while(a[(prev)]<tofind)
//                {
//                    System.out.println(drawPrevX+" ::::::" + drawPrevY);
//                    while(drawPrevX<prevDrawX+r)
//                    {
//                        System.out.println("h2");
//                        System.out.println(drawPrevX+" ::" + drawPrevY);
//                        drawPrevX++;
//
//                        if((drawPrevX-prevDrawX)<(r)/2)
//                            drawPrevY--;
//                        else
//                            drawPrevY++;
//                        g.fillOval(drawPrevX,drawPrevY,5,5);
//                    }
//                    prevDrawX = drawPrevX;
//                    prev++;
//                    if(prev == Math.min(step,n))
//                    {
//                        found=0;
//                        break;
//                    }
//                    System.out.println("smal "+smallStep);
////                    smallStep+=r;
//
//
//
//                }
//
//
//
//
////                while(initp<p)
////                {
////                    g.setColor(Color.BLUE);
////
////                    g.drawLine(initp,initq,initp,initq+30);
////                    g.drawLine(initp-10,initq+10,initp,initq);
////                    g.drawLine(initp,initq,initp+10,initq+10);
//////                    try
//////                    {
//////                        wait(5);
//////
//////                    }catch (Exception e)
//////                    {
//////
//////                    }
////
////                }
//
//
//
//
//
//
//            }

            sleeping=0;
        }
    }
}



//            setBackground(Color.BLACK);  // set background color for this JPanel

            // Your custom painting codes. For example,
            // Drawing primitive shapes
//            g.setColor(Color.YELLOW);    // set the drawing color
//            g.drawLine(30, 40, 100, 200);
//            g.drawOval(150, 180, 10, 10);
//            g.drawRect(200, 210, 20, 30);
//            g.setColor(Color.RED);       // change the drawing color
//            g.fillOval(300, 310, 30, 50);
//            g.fillRect(400, 350, 60, 50);
//            // Printing texts
//            g.setColor(Color.WHITE);
//            g.setFont(new Font("Monospaced", Font.PLAIN, 12));