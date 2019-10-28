package com.SearchAlgo;

import org.omg.CORBA.INTERNAL;

import javax.swing.*;
import java.awt.*;
import java.time.Year;
import java.util.ArrayList;

public class jumpSearch extends Thread {

    private ArrayList<Integer> data;
    private int tofind;
    private JTextArea textArea;
    private int arraypart;
    private JTextField tfResult;
    private int found =1;


    public jumpSearch(ArrayList<Integer> data, int tofind, int arraypart,JTextArea textArea, JTextField tfResult) {
        this.data = data;
        this.tofind = tofind;
        this.textArea = textArea;
        this.arraypart = arraypart;
        this.tfResult = tfResult;


    }

    @Override
    public void run() {
        String res = "";
        int n =data.size();
        int step = (int)Math.floor(Math.sqrt(n));
        int prev=0;
        while(data.get(Math.min(step,n)-1)< tofind)
        {

            prev =step;
//            System.out.println(prev+"step: "+step);
            step+=(int)Math.floor(Math.sqrt(n));
//            System.out.println("stop"+step+" "+prev);
            if(prev>=n){
//                System.out.println("strop");
                found =0;
                break;
            }
        }
        System.out.println("prev"+prev);
        if(found!=0)
        while(data.get(prev)<tofind)
        {
            prev++;
            if(prev == Math.min(step,n))
            {
                found=0;
                break;
            }
        }
//        System.out.println("sss ");
        if(found!=0)
        if(data.get(prev).equals(tofind))
        {
            System.out.println("found"+(arraypart+prev+3));
            res+=Integer.toString((arraypart+prev+3));
            tfResult.setText(res);
        }




    }
}
