package com.SearchAlgo;
import java.util.Scanner;
import java.util.concurrent.*;
import javax.swing.*;
public class Interpolation extends Thread{
    //Semaphore sem;
    private int beg, end;
    private  int key, arr[];
    private JTextPane textArea;
    private JTextField field;
    public  Scanner s = new Scanner(System.in);

    public Interpolation(int beg, int end, int key, int[] arr,JTextPane textArea,JTextField field) {
        this.beg = beg;
        this.end = end;
        this.key = key;
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

                            field.setText(field.getText()+" "+beg+" ");



                    }
                    break;
                }
                if(arr[beg]!=arr[end]) {
                    int pos = beg + (((end - beg) / (arr[end] - arr[beg])) * (key - arr[beg]));
                    if (arr[pos] == key) {
//                        //System.out.println("Found at: " + pos);
//                        if(field.getText().equals("Not Found !"))
//                            field.setText(pos+" ");
//                        else

                        field.setText(field.getText() + " " + pos + " ");

                        check = pos;
                        break;
//                            end=end-1;
//                            beg=beg+1;
                    }
                    if (arr[pos] < key)
                        beg = pos + 1;

                    else //if(arr[pos] > key)
                        end = pos - 1;

                }
                if(beg<arr.length && end>=0 && arr[beg]==arr[end] && arr[beg]==key && beg!=end )
                {

                    for(int i=beg;i<=end;i++)
                        field.setText(field.getText() + " " + i + " ");

                    break;
                }
            }
            if(check!=-1){
            for(int i=check+1;i<=end;i++)
            {
                if(key==arr[i])
                {
                field.setText(field.getText() + " " + i + " ");
                }

            }
            for(int i=check-1;i>=beg;i--)
            {
                if(key==arr[i])
                {
                    field.setText(field.getText() + " " + i + " ");
                }
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