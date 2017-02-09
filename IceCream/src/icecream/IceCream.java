/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icecream;
import java.util.*;
import java.io.*;

/**
 *
 * @author Jay
 */
public class IceCream {

    /**
     * @param args the command line arguments
     */
    static int numTrips = 0;
    //static int budget;
    static ArrayList<Integer> costs; // filled out once we start a new trip 
    
    public static void getTripData(Scanner sc, ArrayList<Integer> costs, int budget) throws FileNotFoundException {
        int numItems = sc.nextInt();
        // creat the arraylist
        
        for (int c = 0; c<numItems;c++) {
            costs.add(sc.nextInt());
        }
 
    }
    
    public static void solveEquation(ArrayList<Integer> costs, int budget, int[] items) {
        int spent = 0;
        
        for (int c1=0; c1 < costs.size(); c1 ++) {
            // now see if any of the others add up to budget
            for (int c2=c1+1; c2 < costs.size(); c2 ++) {
                spent = costs.get(c1) + costs.get(c2);
                if (spent == budget ) {
                    items[0] = (c1 < c2) ? c1 + 1 : c2 + 1;
                    items[1] = (c1 < c2) ? c2 + 1 : c1 + 1;
                    //System.out.println("Found solution, item 1 = " + items[0] +  " item 2 = " + items[1]);
                    
                    return;
                }
            }
            
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        int budget=0;
        int[] items = new int[2];
        File f = new File("data.txt");
        Scanner sc = new Scanner(f);
        numTrips = sc.nextInt();
        for (int t=0;t<numTrips;t++) {
            costs = new ArrayList();
            budget = sc.nextInt();
            getTripData(sc, costs, budget);
            solveEquation(costs, budget, items);
            System.out.println(items[0] + " " + items[1]);
        }
    }
    
}
