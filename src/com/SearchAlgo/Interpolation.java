package com.SearchAlgo;
import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.*;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Interpolation extends Thread{
    //Semaphore sem;
    private int beg, end;
    private  int key, arr[];
    private JTextPane textArea;
    private int arraypart;
    private JTextField field;
    public  Scanner s = new Scanner(System.in);

    public Interpolation(int beg, int end,int arraypart, int key, int[] arr,JTextPane textArea,JTextField field) {
        this.beg = beg;
        this.end = end;
        this.key = key;
        this.arraypart = arraypart;
        this.arr = arr;
        this.textArea=textArea;
        this.field=field;
    }

    //    public Interpolation(int beg, int end) {
//        this.beg = beg;
//        this.end = end;
////        Thread thread = new Thread(this);
////        thread.start();
//    }
//    public static void initializer() {
//        System.out.println("Enter the " + n + " elements ");
//        arr = new int[n];
//        for(int i = 0; i<n; i++) {
//            arr[i] = s.nextInt();
//        }
//
//        System.out.println("Enter the key");
//        key = s.nextInt();
//    }
    public void run()
    {
        StyledDocument doc = textArea.getStyledDocument();

        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setForeground(center, Color.blue);
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(center,18);
        try {
            StyleConstants.setUnderline(center,true);
            doc.insertString(doc.getLength(),"Thread - "+(arraypart+1),center);
            doc.setParagraphAttributes(doc.getLength(), 1, center, false);
            StyleConstants.setUnderline(center,false);
        }catch (Exception e)
        {

        }
        String temp="";
        temp="\n"+temp+"\n";
        try {
            StyleConstants.setForeground(center,Color.red);
            StyleConstants.setFontSize(center,16);
            StyleConstants.setBold(center,true);
            doc.insertString(doc.getLength(),"\n\nBeg = "+beg+"\tEnd = "+end+"\tTo find = "+key+"\n",center);
            doc.setParagraphAttributes(doc.getLength(), 1, center, false);
            StyleConstants.setFontSize(center,18);
            StyleConstants.setBold(center,false);
        }catch (Exception e) {}
        for(int i=beg;i<=end;i++) {
            temp =arr[i]+"  ";
        try {
               StyleConstants.setForeground(center,Color.black);
                doc.insertString(doc.getLength(),temp,center);
                doc.setParagraphAttributes(doc.getLength(), 1, center, false);
            }catch (Exception e){}}
        if(key<arr[beg])
        {
            String overJump = "\nFirst element > To find\nSo stop searching";

            try{
                doc.insertString(doc.getLength(),overJump,center);
                doc.setParagraphAttributes(doc.getLength(), 1, center, false);
            }catch (Exception e){}

            return;
        }
        if(key>arr[end])
        {
            String overJump = "\nLast element < To find\nSo stop searching";

            try{
                doc.insertString(doc.getLength(),overJump,center);
                doc.setParagraphAttributes(doc.getLength(), 1, center, false);
            }catch (Exception e){}

            return;
        }
        try
        {
            int check=-1;
            while (beg<= end && key >= arr[beg] && key <= arr[end])
            {

                if (beg == end)
                {
                    if (arr[beg] == key) {
                        //System.out.println("Found at: " + beg);
//                        if(field.getText().equals("Not Found !"))
//                            field.setText(beg+" ");
//                        else
                        String found = "\nFound at "+beg+"\nStop searching.\n";

                        try{
                            StyleConstants.setForeground(center, Color.black);
                            doc.insertString(doc.getLength(),found,center);
                            doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                        }catch (Exception e){}
                            field.setText(field.getText()+" "+beg+" ");



                    }
                    break;
                }
                if(arr[beg]!=arr[end]) {
                    int pos = beg + (((end - beg) / (arr[end] - arr[beg])) * (key - arr[beg]));
                    try{
                        StyleConstants.setForeground(center, Color.red);
                        StyleConstants.setFontSize(center,16);
                        doc.insertString(doc.getLength(),"\npos = "+pos+"\n",center);
                        doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                        StyleConstants.setFontSize(center,18);
                        for(int jj=beg;jj<=end;jj++) {
                            temp =arr[jj]+"  ";
                            try {
                                if(jj==pos) StyleConstants.setForeground(center,new  Color(102,204,0));
                                else StyleConstants.setForeground(center,Color.black);
                                doc.insertString(doc.getLength(),temp,center);
                                doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                                StyleConstants.setFontSize(center,18);
                            }catch (Exception e){}}

                    }catch (Exception e){}
                    if (arr[pos] == key) {
//                        //System.out.println("Found at: " + pos);
//                        if(field.getText().equals("Not Found !"))
//                            field.setText(pos+" ");
//                        else
                        try{
                            StyleConstants.setForeground(center, Color.green);
                            doc.insertString(doc.getLength(),"\nFound at "+pos+"\n",center);
                            doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                            StyleConstants.setFontSize(center,18);

                        }catch (Exception e){}
                        field.setText(field.getText() + " " + pos + " ");

                        check = pos;
                        break;
//                            end=end-1;
//                            beg=beg+1;
                    }
                    if (arr[pos] < key) {
                        beg = pos + 1;
                        try {
                            StyleConstants.setForeground(center,Color.blue);
                            StyleConstants.setFontSize(center,18);
                            StyleConstants.setBold(center,true);
                            doc.insertString(doc.getLength(),"\nElement at pos < To find\nChanging beg\n",center);
                            doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                            StyleConstants.setFontSize(center,18);
                            StyleConstants.setBold(center,false);
                        }catch (Exception e) {}

                    }
                    else //if(arr[pos] > key)
                    {
                        end = pos - 1;
                        try {
                            StyleConstants.setForeground(center,Color.blue);
                            StyleConstants.setFontSize(center,18);
                            StyleConstants.setBold(center,true);
                            doc.insertString(doc.getLength(),"\nElement at pos > To find\nChanging end\n",center);
                            doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                            StyleConstants.setFontSize(center,18);
                            StyleConstants.setBold(center,false);
                        }catch (Exception e) {}
                    }
                    try {
                        StyleConstants.setForeground(center,Color.red);
                        StyleConstants.setFontSize(center,16);
                        StyleConstants.setBold(center,true);
                        doc.insertString(doc.getLength(),"\nBeg = "+beg+"\tEnd = "+end+"\n",center);
                        doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                        StyleConstants.setFontSize(center,18);
                        StyleConstants.setBold(center,false);
                    }catch (Exception e) {}

                }
                if(beg<arr.length && end>=0 && arr[beg]==arr[end] && arr[beg]==key && beg!=end )
                {

                    for(int i=beg;i<=end;i++) {
                        try{
                            StyleConstants.setForeground(center, Color.green);
                            doc.insertString(doc.getLength(),"\nFound at "+i+"\n",center);
                            doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                            StyleConstants.setFontSize(center,18);

                        }catch (Exception e){}

                        field.setText(field.getText() + " " + i + " ");
                    }

                    break;
                }
            }
            if(check!=-1){
                int h=0,g=0;
                try {
                    StyleConstants.setForeground(center,Color.red);
                    StyleConstants.setFontSize(center,16);
                    StyleConstants.setBold(center,true);
                    doc.insertString(doc.getLength(),"\nNow we will move one step at a time",center);
                    doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                    StyleConstants.setFontSize(center,18);
                    StyleConstants.setBold(center,false);
                }catch (Exception e) {}
            for(int i=check+1;i<=end;i++)
            {
                if(key==arr[i])
                {
                    h=1;
                    try{
                        StyleConstants.setForeground(center, Color.green);
                        doc.insertString(doc.getLength(),"\nFound at "+i+"\n",center);
                        doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                        StyleConstants.setFontSize(center,18);

                    }catch (Exception e){}
                field.setText(field.getText() + " " + i + " ");
                }
            }
            for(int i=check-1;i>=beg;i--)
            {
                if(key==arr[i])
                {
                    g=1;
                    try{
                        StyleConstants.setForeground(center, Color.green);
                        doc.insertString(doc.getLength(),"\nFound at "+i+"\n",center);
                        doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                        StyleConstants.setFontSize(center,18);

                    }catch (Exception e){}
                    field.setText(field.getText() + " " + i + " ");
                }
            }
            if(h==0)
            {
                try{
                    StyleConstants.setForeground(center, Color.black);
                    doc.insertString(doc.getLength(),"\nNo further presence of the key.\n",center);
                    doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                    StyleConstants.setFontSize(center,18);
                 return;
                }catch (Exception e){}
            }
                if(g==0)
                {
                    try{
                        StyleConstants.setForeground(center, Color.black);
                        doc.insertString(doc.getLength(),"\nNo further presence of the key.\n",center);
                        doc.setParagraphAttributes(doc.getLength(), 1, center, false);
                        StyleConstants.setFontSize(center,18);
                        return;

                    }catch (Exception e){}
                }
            }

        }
        catch (Exception e)
        {
            System.out.println ("Exception is caught");
        }
    }

//    public static void main(String[] args)
//    {
//        System.out.println("Enter number of elements");
//        n = s.nextInt();
//        initializer();
//        Arrays.sort(arr);
//        System.out.println("Number of threads: ");
//        int no_t = s.nextInt();
//        System.out.println(Arrays.toString(arr));
//        int k =n/no_t;
//        int lo,hi;
//        int i;
//        for(i=0;i<no_t;i++)
//        {
//            lo = i*k;
//            if(i==no_t-1)
//                hi = n-1;
//            else
//                hi = i*k + k-1;
//            new Interpolation(lo,hi);
//        }
////        if(flag !=1)
////        {
////            System.out.println("What a waste of time!");
////        }
//    }
}