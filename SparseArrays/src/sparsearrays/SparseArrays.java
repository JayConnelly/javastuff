/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sparsearrays;
import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

/**
 *
 * @author Jay
 */
public class SparseArrays {
    static final int MAX = 100;
    static String[] ca = new String[MAX]; // content array
    static String[] qa = new String[MAX]; // query array
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        File f = new File("data.txt");
        Scanner sc = new Scanner(f);
        
        // read the number of string
        int numStrings = sc.nextInt();
//        System.out.println("numstrings = " + numStrings);
        for (int i=0;i<numStrings;i++) {
            ca[i] = sc.next();
        }
        
        int numQ = sc.nextInt();
//        System.out.println("num queries = " + numQ);
        for (int i=0;i<numQ;i++) {
            qa[i] = sc.next();
        }

        int matches = 0;
        
        for(int q=0; q < numQ; q++) {  //loop through all queries
            // now loop through all the array elements
            for (int a=0; a < numStrings; a++) {
                String query = qa[q];
                String content = ca[a];
                int idx = 0;
                while (idx < content.length() ) {
                    idx = content.indexOf(query, idx);
                    if (idx < 0) {
//                        System.out.println("no more matches. query = " + query + " Conent = " + content + " idx = " + idx);
                        break;
                    } else {
 //                       System.out.println(" found match. query = " + query + " Conent = " + content + " idx = " + idx);
                        matches ++;
                        idx++;
                    }
                }
                    
            }
        }
    
        System.out.println("Total matches = " + matches);
    }
}
