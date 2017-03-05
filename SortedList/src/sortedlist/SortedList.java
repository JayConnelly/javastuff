/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortedlist;
import java.io.*;
import java.util.*;

/**
 *
 * @author Jay
 */
public class SortedList {

    /**
     * @param args the command line arguments
     */
    public static void binaryAdd(LinkedList ll, int num, int start, int stop) {
        // first search == middle
        if (ll.isEmpty()) {
            ll.addFirst(num);
            return;
        }

        int pos = (stop - start) / 2 + start;
        //System.out.println(" pos = " + pos);
        int existing = (Integer) ll.get(pos);
        //System.out.printf("Binary Add, start = %d stop = %d pos = %d num = %d  Existing = %d ",start,stop,pos,num,existing);
        //System.out.println(ll);
        
        // if stop - start <1
        if ((stop-start) == 1) {
            // add it
            if ( (Integer) ll.get(start) > num) {
                //System.out.printf(" 1. ADDING %d at location %d \n",num,start);
                ll.add(start,num);
            } else {
                //System.out.printf(" 2. ADDING %d at location %d \n",num,stop);
                ll.add(stop,num);
            }
        } else if ((stop - start) == 2) {
            //System.out.printf(" 3. ADDING %d at location %d \n",num, stop-1);

            ll.add(stop-1,num);
        } else if ( existing < num) {
            //System.out.printf("A. calling myself with  num = %d start=%d stop=%d existing=%d \n",num,pos,stop,existing);

            binaryAdd(ll,num,pos,stop);
        } else if (existing >= num) {
            //System.out.printf(" B. calling myself with  num = %d start=%d stop=%d Existing=%d \n",num,start,pos,existing);
            binaryAdd(ll,num,start,pos);
        }
    }
    public static void add(LinkedList ll, int num) {
        // first find next number greater than us
        //System.out.printf("Adding element %d \n",num);
        if (ll.isEmpty()) {
            ll.addFirst(num);
            //System.out.printf("Adding first %d \n",num);
        } else {  // run through the array till we find something bigger
            // set Iterator at specified index
            Iterator itr = ll.listIterator(0);
            int i=0;
            while (itr.hasNext()) {
               i++;
               int nextNum = (Integer) itr.next();
               if ( nextNum > num) {
                   //System.out.printf("Adding Element %d, which is less than element %d found at location %d \n",num,ll.get(i-1),(i-1));
                   ll.add(i-1,num);
                   break;
               }else {
                   //System.out.printf(" Adding Element %d, >= than element %d found at location %d \n", num, ll.get(i-1), (i-1));
               }
            }
            // if we get here, ran off the end of the list
            if ( !itr.hasNext()) {
                //System.out.printf(" Adding Element %d, to end of list location %d \n", num,  i);
                
                ll.addLast(num);
            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
       LinkedList<Integer> ll = new LinkedList<>();
       File f = new File("data1.txt");
       Scanner sc = new Scanner(f);
       int numEls = sc.nextInt();
        double medianValue = 0;
        for (int i=0;i<numEls;i++) {
           int newNum = sc.nextInt();
           binaryAdd(ll,newNum, 0,ll.size());
           //add(ll,newNum);
    
       
           int medianPos = (i) / 2; // compensate for arrays starting at zero
       //System.out.printf("median pos = %d\n",medianPos);
            if (i % 2 == 0) {
                // answer is middle
                medianValue = ll.get(medianPos);
            } else {  // it was even
                medianValue = (float) (ll.get(medianPos) + ll.get(medianPos+1) ) / 2;   // note check for small lists
            }
           // System.out.println(ll);
            //System.out.printf("%.1f \n", medianValue);
            //System.out.println(ll);
       
       }
            System.out.printf("%.1f \n", medianValue);
       
       
                
    }
    
}
