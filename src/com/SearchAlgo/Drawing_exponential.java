package com.SearchAlgo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Drawing_exponential extends JFrame  {
    public static  int CANVAS_WIDTH  ;
    public static  int CANVAS_HEIGHT ;
    public int []a;
    public int tofind;
    private ArrayList<Integer>idx_lst;
    private int sleeping=1;
    private int highlight;
    private int t_num;
    // Declare an instance of the drawing canvas,
    // which is an inner class called DrawCanvas extending javax.swing.JPanel.
    private DrawCanvas canvas;

    public int getSleeping() {
        return sleeping;
    }


    // Constructor to set up the GUI components and event handlers
    public Drawing_exponential(int []a, int tofind, ArrayList<Integer> idx_lst,int tnum) {

        this.a=a;
        this.tofind=tofind;
        this.idx_lst=idx_lst;
        this.highlight=-255;
        this.t_num=tnum;
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
        setLocation((900+(tnum-1)*CANVAS_WIDTH+20),0);
    }




    /**
     * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
     */

    private class DrawCanvas extends JPanel  {

        private int p1 = 20, q1 = 200, r1= 30, s1 = 30;
        int i =0;
        int initp = 20+r1/2,initq = q1+s1+20,d=0,k=0;
        // Override paintComponent to perform your own painting
        public DrawCanvas()
        {

            Timer t = new Timer(2500,new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    if(i<idx_lst.size()){highlight=idx_lst.get(i);repaint();i++;};
                }
            });
            t.start();
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int p = 20, q = 200, r = 30, s = 30;
            for (int i = 0; i < a.length; i++) {
                g.setColor(Color.black);
                g.drawRect(p, q, r, s);
                g.setColor(Color.blue);

                if (a[i] == tofind){
                    g.setColor(Color.RED);
                    g.drawOval(p,q,30,30);}

                if(highlight>=0 &&highlight<a.length){
                    if(a[i]==a[highlight] && a[i] == tofind)
                    {
                        g.setColor(Color.ORANGE);
                        g.fillOval(p, q, 30, 30);
                    }
                    else if (a[i]==a[highlight]) {
                        g.setColor(Color.BLACK);
                        g.drawOval(p, q, 30, 30);
                    }
                }
                if(a[i]==tofind)
                g.setColor(Color.RED);
                else
                    g.setColor(Color.blue);
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