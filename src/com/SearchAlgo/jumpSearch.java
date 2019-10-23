package com.SearchAlgo;

import org.omg.CORBA.INTERNAL;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class jumpSearch extends Thread {

    private ArrayList<Integer> data;
    private int tofind;
    private JTextArea textArea;
    private int arraypart;
    private JTextField tfResult;


    public jumpSearch(ArrayList<Integer> data, int tofind, int arraypart,JTextArea textArea, JTextField tfResult) {
        this.data = data;
        this.tofind = tofind;
        this.textArea = textArea;
        this.arraypart = arraypart;
        this.tfResult = tfResult;
    }

    @Override
    public void run() {
        for (int i=0;i<data.size();i++)
        {
                
        }





    }
}
