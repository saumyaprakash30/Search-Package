package com.SearchAlgo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JTextArea taRes2;
    private JTextArea taRes4;
    private JTextArea taRes3;
    private JTextField tfResult;

    public mainFrame() {





        btnJump.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> a = new ArrayList<Integer>();
                int tofind =0;
                String arrayElement = tfArray.getText();
                String [] elements = arrayElement.split(" ");
                for(int i=0;i<elements.length;i++)
                {
//                   a[i]=Integer.parseInt(elements[i]) ;
//                    System.out.println(elements[i]);
                    a.add(Integer.parseInt(elements[i]));
                }
                for(int i=0;i<a.size();i++)
                    System.out.println(a.get(i));


                jumpSearch j1 = new jumpSearch(a,tofind,0,taRes1,tfResult);
                j1.start();


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
