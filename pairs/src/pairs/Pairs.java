/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pairs;

/**
 *
 * @author Jay
 */
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

public class Pairs {

    /**
     * @param args the command line arguments
     */

    static int myPairs(int[] a,int k) {
      /* Complete this function */
        int ret = 0;
        Arrays.sort(a);
        Set<Integer> s = new HashSet<>();
        for (int i=0; i < a.length; i++) {
            s.add(a[i]+k);
            if ( s.contains(a[i]) ) {
                s.remove(a[i]);
                ret++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int res;
        
        //String n = in.nextLine();
        String n = "5 3";
        String[] n_split = n.split(" ");
        
        int _a_size = Integer.parseInt(n_split[0]);
        int _k = Integer.parseInt(n_split[1]);
        
        int[] _a = new int[_a_size];
        int _a_item;
        //String next = in.nextLine();
        String next = "1 5 3 4 2";
        String[] next_split = next.split(" ");
        
        for(int _a_i = 0; _a_i < _a_size; _a_i++) {
            _a_item = Integer.parseInt(next_split[_a_i]);
            _a[_a_i] = _a_item;
        }
        
        res = myPairs(_a,_k);
        System.out.println(res);
    }
}

