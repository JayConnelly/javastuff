/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxrect;
import java.util.*;
import java.io.*;

/**
 *
 * @author Jay
 */
public class MaxRect {

    /**
     * @param args the command line arguments
     */
    static int max = -1;
    static int num = 0;
    static HashMap<Integer, Integer> hmap = new HashMap<>();
    static ArrayList<Integer> a = new ArrayList<>();   // array of all the buildings
    
    static void getData(ArrayList<Integer> a) {
        Scanner sc = new Scanner("3 4 5 3");
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
        int b;
        int tmp = 0;
        boolean unique = true;
        getData(a);
        for (int i = 0; i < num; i++) { // now loop through the buildings
            b = a.get(i); // get the next building height
            System.out.println(" got height of " + b);

            // handle the case if this is already there
            Integer prev = hmap.put((Integer) b, (Integer) 1);
            if (prev != null) {
                prev = hmap.put(b, prev + 1);
                System.out.println("\n duplicate " + b + " now has value of " + (prev+1));
            }
            
            // now clean out all the sizes greater than n (no longer in running set
            Set set = hmap.entrySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry)iterator.next();
                System.out.println(" " + mentry.toString()+ " ");
                if ((int) mentry.getKey() < b) { // size is still running
                    System.out.println(" Incrementing " + mentry.getKey() + " to " + ((Integer) mentry.getValue() + 1) );
                    hmap.put((Integer) mentry.getKey(), (Integer) mentry.getValue() + 1);

                } else if ((Integer) mentry.getKey() > b) {  //entry is greater than current.  need to stop string and test for max   
                    System.out.println(" retiring " + mentry.getKey());
                    
                     hmap.put(b, hmap.get(b) + 1);  //bump any running nodes less than the one we are retiring

                            
                    System.out.println(" bumping " + b + " to " + hmap.get(b));
                    
                    tmp = (Integer) mentry.getKey() * (Integer) mentry.getValue();
                    if ((Integer) tmp > max) max = tmp;  // found a longer string
                    hmap.remove( (Integer) mentry.getKey());
                } else {  //for the case where we are looking at ourself
                    System.out.println(" looking at myself " + b);
                    if (b > max) max = b;
                }
                //System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
                //System.out.println(mentry.getValue());
            }  
            
            System.out.println(" Max is " + max);
            
        
        } // end for
            // now go through all the sets still running when we quit
        Set set = hmap.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
           Map.Entry mentry = (Map.Entry)iterator.next();
           tmp = (Integer) mentry.getKey() * (Integer) mentry.getValue();
           if ((Integer) tmp > max) max = tmp;  // found a longer run
           System.out.println(" cleaning up for " + mentry.getKey() + " running is " + mentry.getValue() + " tmp = " + tmp + " max is " + max);
        }  
 
    }
}
