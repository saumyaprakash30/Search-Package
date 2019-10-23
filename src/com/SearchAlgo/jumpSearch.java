package com.SearchAlgo;

import javax.swing.*;
import java.awt.*;

public class jumpSearch extends Thread {

    private  int [] data;
    private int tofind;
    private JTextArea textArea;
    private int arraypart;
    private JTextField tfResult;


    public jumpSearch(int [] data, int tofind, int arraypart,JTextArea textArea, JTextField tfResult) {
        this.data = data;
        this.tofind = tofind;
        this.textArea = textArea;
        this.arraypart = arraypart;
        this.tfResult = tfResult;
    }

    @Override
    public void run() {


       


    }
}
