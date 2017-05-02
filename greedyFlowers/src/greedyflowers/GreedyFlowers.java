/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedyflowers;
import java.util.*;

/**
 *
 * @author Jay
 */
public class GreedyFlowers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
     // Scanner in = new Scanner(System.in);
     Scanner in = new Scanner("3 1 2 5 6");
      
      int N, K;
      N = in.nextInt();
      K = in.nextInt();
      
      Integer C[] = new Integer[N];
      for(int i=0; i<N; i++){
         C[i] = in.nextInt();
      }
      
      // create a comparator
      Comparator<Integer> comp = Collections.reverseOrder();

      // sorting array with reverse order using comparator
      Arrays.sort(C, comp);

      // now have the people buy the most expensive first
        int k = 0;
        int cost = 0;
        int adder = 0;
        for (int i = 0; i < N; i++) {
          cost = cost + (1 + adder) * C[i];
          if (++k == K) {
              adder +=1;
              k = 0;
          }
        }

        System.out.println( cost );
      
    }
    
}
