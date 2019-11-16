package com.SearchAlgo;

import javax.swing.*;
import java.util.ArrayList;

public class fibosearch extends Thread {
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
    //    private int noofthreads;
    public static int foundatindex=Integer.MIN_VALUE;

    private void arrayprinter(int arr [],int beg,int end)
    {
        String pr = "";
        for(int i = 0 ; i < arr.length-1;i++)
        {
            pr= pr+" "+String.valueOf(arr[i])+" ";
        }
        pr=pr+"\n";
        textAreaspecific.setText(textAreaspecific.getText() +pr);
    }


    public fibosearch(int [] arr, int tofind, JTextPane textArea, int beg ,int end, JTextField tfResult,int noofthreads,int tnum) {
        this.tofind = tofind;
        this.textAreaspecific = textArea;
        this.tfResult = tfResult;
        this.arr = arr;
        this.beg = beg;
        this.end = end;
        this.temparr = new int[end-beg+2];
//        this.noofthreads = noofthreads;
        for(int i = 0 ; i<end-beg+1;i++)
        {
            temparr[i]=arr[beg+i];
        }
//        Thread.currentThread().setName(String.valueOf(tnum));
//        String s = Thread.currentThread().getName();
//        if(Thread.currentThread().getName().equals("1"))
//        {
//            textAreaspecific=textAreaarr[0];
//        }
//        if(Thread.currentThread().getName().equals("2"))
//        {
//            textAreaspecific=textAreaarr[1];
//        }
//        if(Thread.currentThread().getName().equals("3"))
//        {
//            textAreaspecific=textAreaarr[2];
//        }
//        if(Thread.currentThread().getName().equals("4"))
//        {
//            textAreaspecific=textAreaarr[3];
//        }
    }
    public int[] other_index(int index)
    {
        int i= index;
        while(arr[i]==arr[index] && i<arr.length)
        {
            i++;
            if(i>=arr.length){break;}
        }
        int rightmostindex = i-1;
        i = index;
        while(arr[i]==arr[index] && i>=0)
        {
            i--;
            if(i<0){break;}
        }
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
        z=fibMonaccianSearch(temparr,tofind,end-beg+1);
        if(z+beg>=0 && z+beg>foundatindex){foundatindex=z+beg;}
    }
    public static int min(int x, int y)
    { return (x <= y)? x : y; }

    /* Returns index of x if present, else returns -1 */
    public int fibMonaccianSearch(int arr[],
                                  int x, int n) {

        try {
            textAreaspecific.setText(textAreaspecific.getText() +"Current array being processed \n");

            arrayprinter(arr, 0, n - 1);
        } catch (NullPointerException c) {
            System.out.println("extra thread");
        } finally {


            /* Initialize fibonacci numbers */
            int fibMMm2 = 0; // (m-2)'th Fibonacci No.
            int fibMMm1 = 1; // (m-1)'th Fibonacci No.
            int fibM = fibMMm2 + fibMMm1; // m'th Fibonacci

        /* fibM is going to store the smallest
        Fibonacci Number greater than or equal to n */
            while (fibM < n) {
                fibMMm2 = fibMMm1;
                fibMMm1 = fibM;
                fibM = fibMMm2 + fibMMm1;
            }
            try {
                textAreaspecific.setText(textAreaspecific.getText() +"Fibonacci number that is just greater than array_size is "+fibM+" \n");
            } catch (NullPointerException c) {
                System.out.println("fib num print");
            }
            // Marks the eliminated range from front
            int offset = -1;

        /* while there are elements to be inspected.
        Note that we compare arr[fibMm2] with x.
        When fibM becomes 1, fibMm2 becomes 0 */
            while (fibM > 1) {
                // Check if fibMm2 is a valid location
                int i = min(offset + fibMMm2, n - 1);

            /* If x is greater than the value at
            index fibMm2, cut the subarray array
            from offset to i */
                try {
                    textAreaspecific.setText(textAreaspecific.getText() +"Desired Fibonnaci index checked of the local array "+i+" \n");
                } catch (NullPointerException c) {
                    System.out.println("fib num print");
                }
                if (arr[i] < x) {
                    try {
                        textAreaspecific.setText(textAreaspecific.getText() +arr[i]+" was being checked but it is less than the number to find "+x+" \n");
                    } catch (NullPointerException c) {
                        System.out.println("fib num print");
                    }
                    fibM = fibMMm1;
                    fibMMm1 = fibMMm2;
                    fibMMm2 = fibM - fibMMm1;
                    offset = i;
                }

            /* If x is greater than the value at index
            fibMm2, cut the subarray after i+1 */
                else if (arr[i] > x) {
                    try {
                        textAreaspecific.setText(textAreaspecific.getText() +arr[i]+" was being checked but it is greater than the number to find "+x+" \n");
                    } catch (NullPointerException c) {
                        System.out.println("fib num print");
                    }
                    fibM = fibMMm2;
                    fibMMm1 = fibMMm1 - fibMMm2;
                    fibMMm2 = fibM - fibMMm1;
                }

                /* element found. return index */

                else {
                    try {
                        textAreaspecific.setText(textAreaspecific.getText() +"Yay! found at! index " + i + " of the local array \n");
                    } catch (NullPointerException c) {
                        System.out.println("fib num print");
                    }

                    return i;}
            }

            /* comparing the last element with x */
            if (fibMMm1 == 1 && arr[offset + 1] == x)
                return offset + 1;

            /*element not found. return -1 */
            return Integer.MIN_VALUE + 50;
        }
    }
}

