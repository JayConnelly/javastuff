/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hourglass;

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Jay
 */
public class Hourglass {

    /**
     */
     public static int[][] a = new int[6][6];
     
     public static int sumHour( int row, int col) {
        // spass in top left of Array
        
        //First sum the top row
        int sum = a[row][col] + a[row][col+1]+a[row][col+2];
        sum += a[row+1][col+1];
        sum += a[row+2][col] + a[row+2][col+1] + a[row+2][col+2];
        return(sum);
    }
     
     public static void fillArray() throws FileNotFoundException {
         File f = new File("data2.txt");
         Scanner sc = new Scanner(f);
         for (int row=0;row<6;row++) {
             for (int col=0;col<6;col++) {
                 a[row][col] = sc.nextInt();
             }
         }
     }
     
    public static void main(String[] args)  {
        
        // TODO code application logic here
        int bestSum = -100;
        int thisSum;
        
         try {
             fillArray();
         } catch (FileNotFoundException ex) {
             Logger.getLogger(Hourglass.class.getName()).log(Level.SEVERE, null, ex);
             return;
         }
       
        // Intitialize points of the hourglass
       for (int row=0;row<4;row++) {
           for (int col = 0; col<4;col++) {
               thisSum = sumHour(row,col);
               bestSum = (thisSum > bestSum) ? thisSum : bestSum;
 //              System.out.println(" Row = " + row + " col = " + col + " best = " + bestSum);
           }
       } 
       System.out.println(bestSum);
    }
        
        
        //for each of the starting points of the ourglass, sum them up
        
}
