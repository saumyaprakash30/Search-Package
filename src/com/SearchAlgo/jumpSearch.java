package com.SearchAlgo;



import javax.naming.InsufficientResourcesException;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Semaphore;

public class jumpSearch extends Thread {

    private ArrayList<Integer> data;
    private int tofind;
    private JTextPane textArea;
    private int arraypart;
    private JTextField tfResult;
    private int found =1;
    private int secElement;
    Semaphore sem;


    public jumpSearch(ArrayList<Integer> data, int tofind, int arraypart,JTextPane textArea, JTextField tfResult,int secElement,Semaphore sem) {
        this.data = data;
        this.tofind = tofind;
        this.textArea = textArea;
        this.arraypart = arraypart;
        this.tfResult = tfResult;
        this.secElement = secElement;
        this.sem = sem;



    }

    @Override
    public void run() {

        ///////
        StyledDocument doc = textArea.getStyledDocument();

        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setForeground(center, Color.blue);
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(center,18);
//        StyleConstants.setSpaceAbove(center,20);
        ///////

//        textArea.setText("Thread - "+(arraypart+1));

        try {
            StyleConstants.setUnderline(center,true);
            doc.insertString(doc.getLength(),"Thread - "+(arraypart+1),center);
            doc.setParagraphAttributes(doc.getLength(), 1, center, false);
            StyleConstants.setUnderline(center,false);
        }catch (Exception e)
        {

        }
        String res = "";
        int n =data.size();
        int prev=0;int step = (int)Math.floor(Math.sqrt(n));
        String arr="\nInitialization: prev = "+prev+", step = \u221A"+data.size()+" = "+step+", Tofind = "+tofind+"\n";
        try {
            StyleConstants.setForeground(center,Color.red);
            StyleConstants.setFontSize(center,12);
            StyleConstants.setBold(center,true);
            doc.insertString(doc.getLength(),arr,center);
            doc.setParagraphAttributes(doc.getLength(), 1, center, false);
            StyleConstants.setFontSize(center,18);
            StyleConstants.setBold(center,false);
        }catch (Exception e) {}
        for(int i=0;i<data.size();i++){
            arr=(data.get(i))+"   ";
            try {
                if(i==prev) StyleConstants.setForeground(center,new  Color(102,204,0));
                else StyleConstants.setForeground(center,Color.black);
                doc.insertString(doc.getLength(),arr,center);
                doc.setParagraphAttributes(doc.getLength(), 1, center, false);

            }catch (Exception e){}

        }




        if(tofind<data.get(0))
        {
            String overJump = "First element > Tofind\nSo stop searching";

            try{
                doc.insertString(doc.getLength(),overJump,center);
                doc.setParagraphAttributes(doc.getLength(), 1, center, false);
            }catch (Exception e){}

            return;
        }
        while(data.get(Math.min(step,n)-1)< tofind)
        {
            System.out.println("ff"+arraypart);
            prev =step;
//            System.out.println(prev+"step: "+step);
            step+=(int)Math.floor(Math.sqrt(n));
//            System.out.println("stop"+step+" "+prev);
            if(prev>=n){
//                System.out.println("strop");
                found =0;
                try {
                    StyleConstants.setFontSize(center,14);
                    String msg = "\nTake jump of "+(step-prev) +" steps";
                    StyleConstants.setForeground(center, Color.black);
                    doc.insertString(doc.getLength(),msg,center);
                    doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                    StyleConstants.setFontSize(center,18);
                }catch (Exception e){}
                for(int i=0;i<data.size();i++){
                    arr=(data.get(i))+"   ";
                    try {
                        if(i==prev) StyleConstants.setForeground(center,Color.blue);
                        else StyleConstants.setForeground(center,Color.black);
                        if(i==0) doc.insertString(doc.getLength(),"\n"+arr,center);
                        else doc.insertString(doc.getLength(),arr,center);
                        doc.setParagraphAttributes(doc.getLength(), 1, center, false);

                    }catch (Exception e){}

                }
                //
                String overJump="";
                if(prev==n-1) overJump= "\nSince (last_element) != Tofind\n Not present in array\n";
                else overJump= "\nSince it jumped outside the array!\nSo "+tofind+" not present in array\n";
                try{
                    doc.insertString(doc.getLength(),overJump,center);
                    doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                }catch (Exception e){}
//                break;
                return;
            }

            try {
                //0 1 2 5 8 7 4 5 8 9 6 6 6 9 6 2 2
                StyleConstants.setFontSize(center,14);
                String msg = "\nTake jump of "+(step-prev) +" steps";
                StyleConstants.setForeground(center,Color.black);
                doc.insertString(doc.getLength(),msg,center);
                doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                StyleConstants.setFontSize(center,18);

            }catch (Exception e){}
            for(int i=0;i<data.size();i++){
                arr=(data.get(i))+"   ";
                try {
                    if(i==prev) StyleConstants.setForeground(center,new  Color(102,204,0));
                    else StyleConstants.setForeground(center,Color.black);
                    if(i==0) doc.insertString(doc.getLength(),"\n"+arr,center);
                    else doc.insertString(doc.getLength(),arr,center);
                    doc.setParagraphAttributes(doc.getLength(), 1, center, false);

                }catch (Exception e){}

            }
            if(tofind!=data.get(prev)){

                arr="\nNow prev = "+prev+" & "+"Tofind > arr[prev]\nJump "+(step-prev)+" step(s)\n";
                try {
                    StyleConstants.setForeground(center,Color.red);
                    StyleConstants.setFontSize(center,12);

                    doc.insertString(doc.getLength(),arr,center);
                    doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                    StyleConstants.setFontSize(center,18);
                }catch (Exception e) {}
            }
        }
        System.out.println(arraypart+"prev"+prev);
        if(found!=0)
        {
            if(!data.get(prev).equals(tofind)){
                for(int i=0;i<data.size();i++) {
                    arr = (data.get(i)) + "   ";
                    try {
                        if (i == step) StyleConstants.setForeground(center, Color.red);
                        else StyleConstants.setForeground(center, Color.black);
                        if (i == 0) doc.insertString(doc.getLength(), "\n" + arr, center);
                        else doc.insertString(doc.getLength(), arr, center);
                        doc.setParagraphAttributes(doc.getLength(), 1, center, false);

                    } catch (Exception e) { }
                }
                try {
                    arr = "\nJump back to previous location!\n";
                    doc.insertString(doc.getLength(), arr, center);
                    doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                }catch (Exception e){}
            }

            String st = "\nNow we will move 1 step at a time!";
            try {
                StyleConstants.setForeground(center,Color.black);
                StyleConstants.setFontSize(center,14);
                doc.insertString(doc.getLength(),st,center);
                doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                StyleConstants.setFontSize(center,18);
            }catch (Exception e) {}
            while(data.get(prev)<tofind)
            {
                System.out.println("ss"+arraypart);
                prev++;
                if(prev == Math.min(step,n))
                {
                    found=0;
//                    break;
                    return;
                }
                st = "\nJump 1 step\n";
                try {
                    StyleConstants.setForeground(center,Color.black);
                    StyleConstants.setFontSize(center,14);
                    doc.insertString(doc.getLength(),st,center);
                    doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                    StyleConstants.setFontSize(center,18);
                }catch (Exception e) {}
                for(int i=0;i<data.size();i++) {
                    arr = (data.get(i)) + "   ";
                    try {
                        if (i == prev) StyleConstants.setForeground(center, new Color(102, 204, 0));
                        else StyleConstants.setForeground(center, Color.black);
                        if (i == 0) doc.insertString(doc.getLength(), "\n" + arr, center);
                        else doc.insertString(doc.getLength(), arr, center);
                        doc.setParagraphAttributes(doc.getLength(), 1, center, false);

                    } catch (Exception e) { }
                }


            }
        }
//        System.out.println("sss ");
        if(found!=0)
            while(prev<n)
            {
                System.out.println("tt"+arraypart);
                if(data.get(prev).equals(tofind))
                {
                    String st = "\nFound at "+prev+"\nJump 1 step";
                    try {
                        StyleConstants.setForeground(center,Color.black);
                        StyleConstants.setFontSize(center,14);
                        doc.insertString(doc.getLength(),st,center);
                        doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                        StyleConstants.setFontSize(center,18);
                    }catch (Exception e) {}



                    System.out.println(arraypart+"found"+(arraypart*secElement+prev+1));
                    res=Integer.toString((arraypart*secElement+prev));
//
                    try
                    {
                        sem.acquire();
                        tfResult.setText(tfResult.getText()+res+" ");
                        sem.release();
                    }catch (Exception e){}

                }
                prev++;
                if(data.get(prev-1).equals(tofind)){
                    for(int i=0;i<data.size();i++) {
                        arr = (data.get(i)) + "   ";
                        try {
                            if (data.get(i)==tofind && prev>i) StyleConstants.setForeground(center, Color.blue);
                            else if(data.get(i).equals(tofind) && prev==i) StyleConstants.setForeground(center, Color.green);
                            else StyleConstants.setForeground(center, Color.black);
                            if (i == 0) doc.insertString(doc.getLength(), "\n" + arr, center);
                            else doc.insertString(doc.getLength(), arr, center);
                            doc.setParagraphAttributes(doc.getLength(), 1, center, false);

                        } catch (Exception e) { }
                    }
                }

            }
//0 0 0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 4 4 4 4 4 4 5 5 5 5 5 5

        System.out.println("end"+arraypart);
        return;

    }

}


