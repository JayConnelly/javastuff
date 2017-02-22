/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parallelarrays;
import java.util.*;
import java.io.*;

/**
 *
 * @author Jay
 */
public class ParallelArrays {

    /**
     * @param args the command line arguments
     */


    static protected void printArray(long[] ar) {
        for (long i : ar) {
            System.out.println(i+" ");
        }
        System.out.println("");
    }
    
    static protected void execute(long[] ar,int a, int b, int v) {
        ar[a-1] += v;
        if (b < ar.length) ar[b] -= v;
    }  
    
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        File f = new File("data.txt");
        Scanner sc = new Scanner(f);
    
        int begin = 0;
        int end = 0;
        int value = 0;

        int arraySize = sc.nextInt();
        long[] ar = new long[arraySize];

        int numOps = sc.nextInt();
        for (int op = 0; op < numOps; op++) {
            begin = sc.nextInt();
            end = sc.nextInt();
            value = sc.nextInt();
            execute(ar, begin,end, value);
            //printArray(ar);
        } 

        long sum = 0;
        long max = 0;
        // now sum up the prefixes
        for (int i=0; i<ar.length;i++) {
            sum += ar[i];
            if (sum > max) max = sum;
        }
        
         System.out.println( max);
    }
}
