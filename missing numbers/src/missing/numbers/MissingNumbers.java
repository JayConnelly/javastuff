/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package missing.numbers;
import java.util.*;
import java.io.*;

/**
 *
 * @author Jay
 */
public class MissingNumbers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        File f = new File("data.txt");
        Scanner sc = new Scanner(f);
        int aSize = sc.nextInt();
        int[] a = new int[aSize];
        for (int i=0; i< aSize;i++) {
            a[i] = sc.nextInt();
        }
        
        int bSize = sc.nextInt();
        int[] b = new int[bSize];
        for (int i=0; i< bSize;i++) {
            b[i] = sc.nextInt();
        }

        int missing = 0;
        Set<Integer> s = new TreeSet<>();
        
        Arrays.sort(b);
        Arrays.sort(a);
        int j = 0;
        for (int i=0; i < b.length; i++) {
            //System.out.printf("i = %d, j = %d \n", i, j);
            if ( (j > (a.length - 1)) || b[i] != a[j]) {
                //System.out.printf("Found missing number %d\n", b[i]);
                missing ++;
                s.add(b[i]);
            } else {
                //System.out.printf(" Both have number %d \n ", b[i]);
                j++;
            }    
        }
        
        for (int i : s) {
            System.out.printf("%d ",i);
        }
        System.out.println("");
    }
}
