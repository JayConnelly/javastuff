/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

You have  types of coins available in infinite quantities where the value of each coin is given in the array . Can you determine the number of ways of making change for  units using the given types of coins? For example, if , and , we can make change for  units in three ways: , , and .

Given , , and , print the number of ways to make change for  units using any number of coins having the values given in .

Input Format

The first line contains two space-separated integers describing the respective values of  and . 
The second line contains  space-separated integers describing the respective values of  (the list of distinct coins available in infinite amounts).

Constraints

Each  is guaranteed to be distinct.
 */


package dynamic.programming.coins;
import java.util.*;
import java.io.*;

/**
 *
 * @author Jay
 */
 

public class DynamicProgrammingCoins {

    /**
     * @param args the command line arguments
     */
    static int coins[];
    static final int MAXCOINS = 51;
    static final int MAXVALUES = 251;
    static Long values[][] = new Long[MAXVALUES][MAXCOINS];
    static ArrayList al = new ArrayList();

    static int push(int coin) {
        al.add(coin);
        //System.out.println("added coin " + coin + "to position " + al.size());
        return al.size();
    }
    
    static void pop(int index) {
        //System.out.println("removed coin from index  " + index  + " array sie is  " + al.size());
        //System.out.println("removed coin  " + al.get(index-1)  + "from position " + al.size());
        al.remove(index - 1);
    }
  
    static Long solve(int value, int firstCoin) {
       Long r = 0L;
       int pos;
       int newVal;
       
       if (values[value][firstCoin] != -1L) {
        return values[value][firstCoin];
       }
     
       
       // else calculate it
       for (int i=firstCoin; i < coins.length; i++) {
           if (coins[i] > value) {  // no need to check any more coin       
              return r;
           } else if (coins[i] == value) {
               r++;
               return r;
           } else {
                r += solve(value - coins[i],i);
           }
       } 
        
        values[value][firstCoin] = r;
       return r;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
       for (int v=0; v< MAXVALUES; v++) {
           for (int c = 0; c < MAXCOINS; c++) {
            values[v][c] = -1L;
        }
       }
       
       File f = new File("data.txt");
       Scanner sc = new Scanner(f);
       int value = sc.nextInt();
       int numCoins = sc.nextInt();
       coins = new int[numCoins];
       for (int i=0; i < numCoins;i++) {
           coins[i] = sc.nextInt();
       }
       Arrays.sort(coins);

       Long result = solve(value,0);
       System.out.println(result);
           
    }
    
}
