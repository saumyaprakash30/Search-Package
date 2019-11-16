package com.SearchAlgo;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Semaphore;

public class mainFrame {
    private JTextField tfArray;
    private JTextField tfToFind;
    private JButton btnJump;
    private JButton btnFib;
    private JPanel panel1;
    private JPanel drawPanel;
    private JTextArea taRes1;
    private JTextField tfResult;

    private JTextField tfnot;
    private JPanel panel2;
    private JComboBox cbSearchType;
    private JButton searchButton;
    private JPanel panel3;
    private int noOfThread;
    JTextArea ta[];
    JScrollPane jsp[];


    public mainFrame() {


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = cbSearchType.getSelectedIndex();
                if(selected==0)
                {

                    //--------------------------jump Search ----------------------

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

                    Semaphore sem = new Semaphore(1);

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
                        jS[i] = new jumpSearch(temp,tofind,i,ta[i],tfResult,k,sem);
                        jS[i].start();

                    }

                    tfArray.setText("");
                    for(int i=0;i<a.size();i++)
                    {
                        tfArray.setText(tfArray.getText()+a.get(i)+" ");
                    }
                for(int i=0;i<4;i++)
                {
                    try
                    {
                        System.out.println("h");
                        jS[i].join();
                        System.out.println("h1"+i);
                    }
                    catch (Exception e1)
                    {
                        System.out.println(e1);
                    }
                }

                    if(tfResult.getText().equals(""))
                        tfResult.setText("Not Found !");

                    int []arr = new int[a.size()];
                    for(int i=0;i<a.size();i++)
                        arr[i]=a.get(i);

                    new Drawing(arr,tofind);
                }

                else if(selected==1)
                {
                    /////////
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
                    ////////

                    ArrayList<Integer> a = new ArrayList<Integer>();
                    int tofind =Integer.parseInt(tfToFind.getText());
                    String arrayElement = tfArray.getText();
                    String [] elements = arrayElement.split(" ");

                    for(int i=0;i<elements.length;i++)
                    {
                        a.add(Integer.parseInt(elements[i]));
                    }
                    Collections.sort(a);
                    int [] arr = new int[a.size()];
                    for(int i = 0 ; i < a.size(); i ++)
                    {
                        arr[i]=a.get(i);
                    }
                    //array acheived
                    int previdx=0;
                    int sizearrpart = (arr.length)/noOfThread;
                    int r;
                    fibosearch fbarr [] = new fibosearch[noOfThread+1];
                    JTextArea txtareaarr[] = new JTextArea[noOfThread];

//                txtareaarr[0]=taRes1;
//                txtareaarr[1]=taRes2;
//                txtareaarr[2]=taRes3;
//                txtareaarr[3]=taRes4;
                    int ta_counter=3;
                    for(r = 1; r < noOfThread ; r++)
                    {
                        fbarr[r-1] = new fibosearch(arr,tofind,ta[r-1],previdx,r*sizearrpart-1,tfResult,noOfThread,r);
                        fbarr[r-1].start();
                        previdx = r*sizearrpart ;
                    }

                    fbarr[noOfThread] = new fibosearch(arr,tofind,ta[ta.length-1],previdx,arr.length-1,tfResult,noOfThread,r);
                    fbarr[noOfThread].start();
                    for(int l = 0 ; l<=noOfThread ;l++)
                    {
                        try {
                            fbarr[l].join();
                        }
                        catch (Exception ex)
                        {

                        }
                    }

                    fibosearch dummy = new fibosearch(arr,tofind,ta[ta.length-1],previdx,arr.length-1,tfResult,noOfThread,r);
                    // get other indexes
                    if(dummy.foundatindex>-1){
                        int arx[] = dummy.other_index(dummy.foundatindex);
                        tfResult.setText("i was found at index "+arx[0]+" <-to-> "+arx[1]);
                        dummy.foundatindex=Integer.MIN_VALUE;

                    }
                    else {
                        tfResult.setText("i was not found sorry:");
                    }
                    tfArray.setText("");
                    for(int i=0;i<a.size();i++)
                    {
                        tfArray.setText(tfArray.getText()+a.get(i)+" ");
                    }
                    System.gc();

                }
                else if(selected==2)
                {
                    /////////
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
                    ////////

                    ArrayList<Integer> a = new ArrayList<Integer>();
                    int tofind =Integer.parseInt(tfToFind.getText());
                    String arrayElement = tfArray.getText();
                    String [] elements = arrayElement.split(" ");

                    for(int i=0;i<elements.length;i++)
                    {
                        a.add(Integer.parseInt(elements[i]));
                    }
                    Collections.sort(a);
                    int [] arr = new int[a.size()];
                    for(int i = 0 ; i < a.size(); i ++)
                    {
                        arr[i]=a.get(i);
                    }
                    //array acheived
                    int previdx=0;
                    int sizearrpart = (arr.length)/noOfThread;
                    int r;
                    exposearch exparr [] = new exposearch[noOfThread+1];
                    JTextArea txtareaarr[] = new JTextArea[noOfThread];

//                txtareaarr[0]=taRes1;
//                txtareaarr[1]=taRes2;
//                txtareaarr[2]=taRes3;
//                txtareaarr[3]=taRes4;
                    int ta_counter=3;
                    for(r = 1; r < noOfThread ; r++)
                    {
                        exparr[r-1] = new exposearch(arr,tofind,ta[r-1],previdx,r*sizearrpart-1,tfResult,noOfThread,r);
                        exparr[r-1].start();
                        previdx = r*sizearrpart ;
                    }

                    exparr[noOfThread] = new exposearch(arr,tofind,ta[ta.length-1],previdx,arr.length-1,tfResult,noOfThread,r);
                    exparr[noOfThread].start();
                    for(int l = 0 ; l<=noOfThread ;l++)
                    {
                        try {
                            exparr[l].join();
                        }
                        catch (Exception ex)
                        {

                        }
                    }
                    exposearch dummy = new exposearch(arr,tofind,ta[ta.length-1],previdx,arr.length-1,tfResult,noOfThread,r);
                    // get other indexes
                    if(dummy.foundatindex>-1){
                        int arx[] = dummy.other_index(dummy.foundatindex);
                        tfResult.setText("i was found at index "+arx[0]+" <-to-> "+arx[1]);
                        dummy.foundatindex=Integer.MIN_VALUE;

                    }
                    else {
                        tfResult.setText("i was not found sorry:");
                    }
                    tfArray.setText("");
                    for(int i=0;i<a.size();i++)
                    {
                        tfArray.setText(tfArray.getText()+a.get(i)+" ");
                    }
                    System.gc();


                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setContentPane(new mainFrame().panel1);
        frame.setSize(600,500);
        frame.setMinimumSize(new Dimension(500,300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }



}
