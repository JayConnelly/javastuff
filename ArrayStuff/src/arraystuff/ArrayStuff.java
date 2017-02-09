/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arraystuff;
import java.lang.reflect.Array;
import java.util.*;
/**
 *
 * @author Jay
 */
public class ArrayStuff {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String input = "6 1 2 3 4 5 6";
        Scanner sc = new Scanner(input);

        ArrayList<Integer> al = new ArrayList();
        int num = sc.nextInt(); //number of elements
        for (int i=0;i<num;i++) {
            // read the numbers
            al.add(sc.nextInt());
        }
        for (int i=num-1;i>=0;i--) {
            // read the numbers
            System.out.print(al.get(i) + " ");
        }
        
    }
    
}
