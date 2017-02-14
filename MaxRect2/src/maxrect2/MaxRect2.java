/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxrect2;
import java.util.*;
import java.io.*;

/**
 *
 * @author Jay
 */
public class MaxRect2 {

    /**
     * @param args the command line arguments
     */
    static int max = -1;
    static int num = 0;
    static ArrayList<Integer> running = new ArrayList<>(); // list of all active sizes
    static ArrayList<Integer> values = new ArrayList<>(); // list of all active sizes
    static ArrayList<Integer> a = new ArrayList<>();   // array of all the buildings
    
    static void getData(ArrayList<Integer> a) {
        Scanner sc = new Scanner("3 4 3 3");
        num = sc.nextInt();
        //System.out.println("num = " + num);
        int next;
        for (int i = 0; i < num; i++) {
            next = sc.nextInt();
            a.add(i,next);
        }
        //System.out.println(a);
    }
   
    public static void main(String[] args) {
        // TODO code application logic here
        Integer b;
        int tmp = 0;
        getData(a);
        Set<Integer> s = new HashSet();  // set of running floor sizes
        for (int i = 0; i < num; i++) { // now loop through the buildings
            b = a.get(i); // get the next building height
            System.out.println(" got height of " + b);
            
            if (running.contains(b)) {
                values.set(i, values.get(i)+1);  // increment the run
                System.out.println(" setting element " + b + " to " + values.get(i));
            } else {
                System.out.println(" adding element " + b);
                values.add(i,1);
                running.add(i,b);
            }
            System.out.println(" running " + running.toString() + "values " + values.toString());
        }  
 
    }
}
