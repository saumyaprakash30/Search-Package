package com.SearchAlgo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class exposearch extends Thread {
    private ArrayList<Integer> data;
    private int tofind;
    private JTextPane textAreaarr; //printing area
    private JTextPane textAreaspecific;
    private JTextField tfResult; // final list field
    private int sizeofarr ;
    private int [] arr;
    private int [] temparr;
    private int beg;
    private int end;
    private int t_num;
    private ArrayList<Integer> arr_visual;
    //    private int noofthreads;
    public static int foundatindex=Integer.MIN_VALUE;

    private void arrayprinter(int arr [],int beg,int end)
    {
        String pr = "The array being operated on by this thread is ";
        for(int i = 0 ; i < arr.length;i++)
        {
            pr= pr+" "+String.valueOf(arr[i])+" ";
        }
        pr=pr+"\n\n";
        textAreaspecific.setText(textAreaspecific.getText() +pr);
    }


    public exposearch(int [] arr, int tofind, JTextPane textArea, int beg ,int end, JTextField tfResult,int noofthreads,int tnum) {
            this.tofind = tofind;
            this.textAreaspecific = textArea;
            this.tfResult = tfResult;
            this.arr = arr;
            this.beg = beg;
            this.end = end;
            this.temparr = new int[end-beg+1];
            this.t_num=tnum;
            for(int i = 0 ; i<end-beg+1;i++)
            {
                temparr[i]=arr[beg+i];
            }
        this.arr_visual = new ArrayList<Integer>(temparr.length);
        }

        public int[] other_index(int index)
        {
            int i= index;
            System.out.println(index+" in other func");
            while(arr[i]==arr[index] && i<arr.length)
            {
                i++;
                if(i>=arr.length){break;}
            }
            System.out.println(index+" in other func");
            int rightmostindex = i-1;
            i = index;
            System.out.println(index+" in other func");
            while(arr[i]==arr[index] && i>=0)
            {
                i--;
                if(i<0){break;}
            }
            System.out.println(index+" in other func");
            int leftmostindex = i+1;
            int [] indices = new int[]{leftmostindex,rightmostindex};
            return indices ;

        }
        //1 2 3 4 5 6 7 8 9 10 11 12 13
        //recieve into array the required part
        //print received numbs
        //print beg and fib end of arr search
        //seach the required
        //print going left right or found
        @Override
        public void run()
        {
            int z ;
            arrayprinter(temparr,0, temparr.length);
            z=exponentialSearch(temparr,tofind,end-beg+1);
            Drawing_exponential dr = new Drawing_exponential(temparr,tofind,arr_visual,t_num);
            if(z<0){z=Integer.MIN_VALUE;}
            else{foundatindex=z+beg;}
        }
    public int exponentialSearch(int arr[],
                                 int x, int n)
    {
        // If x is present at firt location itself
        arr_visual.add(0);
        textAreaspecific.setText(textAreaspecific.getText()+"Step 1. Comparing "+x+" with array value"+ arr[0]+"\n\n");
        if (arr[0] == x)
        {textAreaspecific.setText(textAreaspecific.getText()+" Found! returning! "+"\n\n");
        return 0;}

        // Find range for binary search by
        // repeated doubling
        textAreaspecific.setText(textAreaspecific.getText()+"Step 2.Not found ! ,so now finding index by repeated doubling "+"\n\n");
        int i = 1;
        while (i < n && arr[i] <= x) {
            i = i * 2;
            arr_visual.add(i);
        }
        textAreaspecific.setText(textAreaspecific.getText()+"Repeated doubling index is " + i+"\n\n");
        // Call binary search for the found range.
        textAreaspecific.setText(textAreaspecific.getText()+"Calling binary search from (Repeated doubling index)/2 ie. "+i/2+" to "+ Math.min(i, n)+"\n\n");
        int f =Arrays.binarySearch(arr, i/2,
                Math.min(i, n), x);
        if(f>-1)
        arr_visual.add(f);
        return  f;
    }
}

