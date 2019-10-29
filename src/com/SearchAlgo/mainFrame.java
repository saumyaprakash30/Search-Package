package com.SearchAlgo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class mainFrame {
    private JTextField tfArray;
    private JTextField tfToFind;
    private JButton btnJump;
    private JButton btnMway;
    private JButton btnFib;
    private JButton btnExp;
    private JPanel panel1;
    private JPanel drawPanel;
    private JTextArea taRes1;
    private JTextField tfResult;
    private JPanel panel2;
    private JTextField tfnot;
    private int noOfThread;

    public mainFrame() {
        panel2.setLayout(new GridLayout(0,2));
        panel2.setAutoscrolls(true);
        String nof = tfnot.getText();
        System.out.println(nof);
        int tt = Integer.parseInt(nof);
        System.out.println("tt"+tt);
        JTextArea ta[] =new JTextArea[tt];
        JScrollPane jsp;
        for(int i=0;i<5;i++)
        {
            ta[i] = new JTextArea();
            jsp = new JScrollPane(ta[i]);
            panel2.add(ta[i]);

        }





//            JTextArea ta1 = new JTextArea();
//
//
////            jsp = new JScrollPane(panel2);
//            panel2.add(jsp);
//
//        JTextArea ta2 = new JTextArea();
//        jsp = new JScrollPane(ta2);
//        panel2.add(jsp);
//        JTextArea ta3 = new JTextArea();
//        jsp = new JScrollPane(ta3);
//        panel2.add(jsp);
//        JTextArea ta4 = new JTextArea();
//        jsp = new JScrollPane(ta4);
//        panel2.add(jsp);
//        JTextArea ta5 = new JTextArea();
//        jsp = new JScrollPane(ta5);
//        panel2.add(jsp);
//        JTextArea ta6 = new JTextArea();
//        jsp = new JScrollPane(ta6);
//        panel2.add(jsp);
//        JTextArea ta7 = new JTextArea();
//        jsp = new JScrollPane(ta7);
//        panel2.add(jsp);






        btnJump.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfResult.setText("");
                ArrayList<Integer> a = new ArrayList<Integer>();
                int tofind =Integer.parseInt(tfToFind.getText());
                String arrayElement = tfArray.getText();
                String [] elements = arrayElement.split(" ");
                for(int i=0;i<elements.length;i++)
                {
//                   a[i]=Integer.parseInt(elements[i]) ;
//                    System.out.println(elements[i]);
                    a.add(Integer.parseInt(elements[i]));
                }
                Collections.sort(a);
                for(int i=0;i<a.size();i++)
                    System.out.println(a.get(i));
                int k=a.size()/4;
                jumpSearch jS[];
                jS = new jumpSearch[4];
                for(int i=0;i<4;i++)
                {
                    int beg = i*k;
                    int end ;
                    if(i==3)
                        end = a.size()-1;
                    else end = i*k + k-1;
                    ArrayList<Integer> temp = new ArrayList<>();
                    for(int j=beg;j<=end;j++)
                    {

                        temp.add(a.get(j));
                    }

                    for(int j=0;j<temp.size();j++){
                        System.out.println(i+"temp"+temp.get(j));
                    }
                    jS[i] = new jumpSearch(temp,tofind,i,taRes1,tfResult,k);
                    jS[i].start();

                }

                tfArray.setText("");
                for(int i=0;i<a.size();i++)
                {
                    tfArray.setText(tfArray.getText()+a.get(i)+" ");
                }
//                for(int i=0;i<4;i++)
//                {
//                    try
//                    {
//                        System.out.println("h");
//                        jS[i].join();
//                        System.out.println("h1"+i);
//                    }
//                    catch (Exception e1)
//                    {
//                        System.out.println(e1);
//                    }
//                }

                if(tfResult.getText().equals(""))
                    tfResult.setText("Not Found !");

            }
        });
        btnExp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawPanel.add(new JTextArea());
            }
        });
        btnMway.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnFib.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setContentPane(new mainFrame().panel1);
        frame.setSize(600,500);
        frame.setVisible(true);
    }



}
