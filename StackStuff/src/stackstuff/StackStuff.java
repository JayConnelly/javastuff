/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackstuff;
import java.util.*;
import java.io.*;

/**
 *
 * @author Jay
 */
public class StackStuff {

    /**
     * @param args the command line arguments
     */
   public static long findMax(Stack s) {
        Iterator si = s.iterator();
        long max = Long.MIN_VALUE;
        long v = 0;
        while (si.hasNext()){
            v = (long) si.next();
            if (v > max) max = v;
         }
        return(max);
   }
   
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Stack<Long> s = new Stack();
   
        File f = new File("data1.txt");
        Scanner sc = new Scanner(f);
        
        long numLines = sc.nextInt();
        long value = 0; 
        int op = 0;
        long max = Long.MIN_VALUE;
        
        for (int i = 0; i < numLines; i++) {
            // read the op
            if ( !sc.hasNext()) {
                continue;
            }
            op = sc.nextInt();
            switch (op) {
                case 1: // This is the push command
                    value = sc.nextInt();
                    if (value > max) max = value;
                    s.push(value);
                    break;
                case 2: // delete element at top of stack;
                    value = (long) s.pop();
                    if (value == max) {
                        max = findMax(s);
                    }
                    break;
                case 3:  // print the largest value
                    System.out.println(max);
            }
        }
    }
}
