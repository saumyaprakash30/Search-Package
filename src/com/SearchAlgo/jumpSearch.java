package com.SearchAlgo;

import org.omg.CORBA.INTERNAL;

import javax.naming.InsufficientResourcesException;
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
    private int secElement;



    private String illustration="";


    public jumpSearch(ArrayList<Integer> data, int tofind, int arraypart,JTextArea textArea, JTextField tfResult,int secElement) {
        this.data = data;
        this.tofind = tofind;
        this.textArea = textArea;
        this.arraypart = arraypart;
        this.tfResult = tfResult;
        this.secElement = secElement;



    }

    @Override
    public void run() {

        illustration+="\tThread - " + Integer.toString(arraypart)+"\n";
        //textArea.setText(illustration);
        String res = "";
        int n =data.size();
        int step = (int)Math.floor(Math.sqrt(n));
        int prev=0;
        if(tofind<data.get(0))
            return;
        while(data.get(Math.min(step,n)-1)< tofind)
        {

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
                if(data.get(prev).equals(tofind))
                {
                    System.out.println(arraypart+"found"+(arraypart*secElement+prev+1));
                    res=Integer.toString((arraypart*secElement+prev+1));
                    tfResult.setText(tfResult.getText()+" "+res);
                    prev++;
                }


        return;
//        System.out.println("end"+arraypart);

    }
    public String getIllustration() {
        return illustration;
    }
}
