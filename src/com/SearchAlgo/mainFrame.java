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

    private JTextField tfnot;
    private JPanel panel2;
    private JPanel panel3;
    private int noOfThread;
    JTextArea ta[];
    JScrollPane jsp[];


    public mainFrame() {





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
                //----------thread pannel--------------
                panel2.removeAll();
                panel2.revalidate();
                panel2.repaint();
                panel2.setLayout(new GridLayout(0,2));
//                panel2.setAutoscrolls(true);
                String nof = tfnot.getText();
                System.out.println(nof);
                noOfThread = Integer.parseInt(nof);
                System.out.println("tt"+noOfThread);
                JTextPane ta[] =new JTextPane[noOfThread];
                JScrollPane jsp[] = new JScrollPane[noOfThread];
                for(int i=0;i<noOfThread;i++)
                {
                    ta[i] = new JTextPane();
                    ta[i].setMinimumSize(new Dimension(100,150));
                    jsp[i] = new JScrollPane(ta[i]);
//                    jsp[i].setMinimumSize(new Dimension(100,150));
                    panel2.add(jsp[i]);

                }
                panel2.revalidate();
                panel2.repaint();
                //----------tread panel end-----------

                tfResult.setText("");
                ArrayList<Integer> a = new ArrayList<Integer>();
                int tofind =Integer.parseInt(tfToFind.getText());
                String arrayElement = tfArray.getText();
                String [] elements = arrayElement.split(" ");
                for(int i=0;i<elements.length;i++)
                {
                    a.add(Integer.parseInt(elements[i]));
                }
                Collections.sort(a);
                for(int i=0;i<a.size();i++)
                    System.out.println(a.get(i));
                int k=a.size()/noOfThread;
                jumpSearch jS[];
                jS = new jumpSearch[noOfThread];
                for(int i=0;i<noOfThread;i++)
                {
                    int beg = i*k;
                    int end ;
                    if(i==noOfThread-1)
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
                    jS[i] = new jumpSearch(temp,tofind,i,ta[i],tfResult,k);
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
        frame.setMinimumSize(new Dimension(500,300));
        frame.setVisible(true);
    }



}
