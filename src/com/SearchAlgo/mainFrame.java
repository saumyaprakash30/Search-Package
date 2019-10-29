package com.SearchAlgo;

import javax.swing.*;
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
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea textArea4;

    public mainFrame() {
        JScrollPane j = new JScrollPane(taRes1);





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
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setContentPane(new mainFrame().panel1);
        frame.setSize(600,500);
        frame.setVisible(true);
    }



}
