package com.SearchAlgo;



import javax.naming.InsufficientResourcesException;
import javax.swing.*;
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

        textArea.setText("Thread - "+(arraypart+1));
        String res = "";
        int n =data.size();
        int step = (int)Math.floor(Math.sqrt(n));
        int prev=0;
        if(tofind<data.get(0))
            return;
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
//                break;
                return;
            }
        }
        System.out.println(arraypart+"prev"+prev);
        if(found!=0)
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
            }
//        System.out.println("sss ");
        if(found!=0)
            while(prev<n)
            {
                System.out.println("tt"+arraypart);
                if(data.get(prev).equals(tofind))
                {
                    System.out.println(arraypart+"found"+(arraypart*secElement+prev+1));
                    res=Integer.toString((arraypart*secElement+prev+1));
//
                    try
                    {
                        sem.acquire();
                        tfResult.setText(tfResult.getText()+res+" ");
                        sem.release();
                    }catch (Exception e)
                    {

                    }

                }
                prev++;
            }


        System.out.println("end"+arraypart);
        return;

    }

}
