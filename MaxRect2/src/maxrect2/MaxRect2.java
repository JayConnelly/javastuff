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
    static protected class BldData {
        private int ref;
        private int span;
        public BldData(int r, int s) {
            ref = r;
            span = s;
        }
        public int getRef() { return ref; }
        public int getSpan() { return span; }
        public void incRef(int i) { ref = ref + i; span += i; }
        public void incSpan(int i) { span = span + i; }

        @Override
        public String toString() {
            return("[" + ref + "," + span + "]");
        }
        
    }
    static int max = -1;  // max value so far - must be >= 0
    static int num = 0;  // number of buildings 
    static ArrayList<Integer> a = new ArrayList<>();   // array of all the buildings
    static Map<Integer,BldData> m = new LinkedHashMap();  // hashmap of all active sizes
    
    
    static void getData(ArrayList<Integer> a) {
        Scanner sc = new Scanner("2 2 5 6 4 5 1 3 2 1");
        num = 0;
        int next;
        while (sc.hasNext()) {
            num++;
            next = sc.nextInt();
            a.add(next);
        }
        //System.out.println(a);
    }
   
    public static void main(String[] args) {
        // TODO code application logic here
        Integer b;
        Integer tmp = 0;
        getData(a);
        Stack stk = new Stack();

        for (int i = 0; i < num; i++) { // now loop through the buildings
            b = a.get(i); // get the next building height

           BldData bldData;     
            // see if this is new
            if (m.isEmpty() || ! m.containsKey(b)) { // then we just add it
                bldData = new BldData(1,1);
                m.put(b,bldData);
                //System.out.println(" " + m.get(b).getRef()); 
            } else {  // found a duplicate  
                //System.out.print(" incrementing ref count for " + b + " to " );
                bldData =  (BldData) m.get(b);
                bldData.incRef(1);
                //System.out.println( m.get(b).getRef());
            }
            //System.out.println(" Map now looks like: " + m.toString());
           
            // now loop through the active set to figure out what to inc and what to retire
            Set set = m.entrySet();
            Iterator itr =  set.iterator();
            while (itr.hasNext()) {
                Map.Entry next = (Map.Entry)itr.next();
                // System.out.println(" Processing " + next.toString() + " Map = " + m.toString());
                if ( (Integer) next.getKey() < b) {  // if key is less than what we are adding, increment span
                    bldData =  (BldData) m.get( (Integer) next.getKey());
                    bldData.incSpan(1);
                    //System.out.println("New span count for " + next.getKey() + " = " +  m.get( (Integer) next.getKey()).getSpan());
                    
                } else if ( (Integer) next.getKey() > b) {
                    // retire next
                    Integer key = (Integer) next.getKey();
                    bldData = (BldData) next.getValue();
                    if ((key * bldData.getSpan()) > max) max = key* bldData.getSpan();
                    //System.out.print(" retiring " + next.toString() + " Max = " + max);
                    stk.push(key);
                          
                   // System.out.println(" pushed  " + key + " map = " + m.toString()) ;

                }
            } // end while
            
 
            // now clean up and deal with deletes
            int d = 0; // delete item
            int minValue = Integer.MAX_VALUE;
            int tt=0;
            int size = stk.size();
            
            //System.out.println(" Stack = " + stk.toString() + "size = " + stk.size());
            
            // now deal with the ones we retired
            for (int t=0; t< size; t++) {
                
                d = (Integer) stk.pop();
                //System.out.println(" popped " + d + " map = " + m.toString() );
                if (d < minValue) {
                    tt =  m.get(d).getSpan(); // get the number of entries for the minimum
                    //System.out.println(" found new minimum value of " + d + " with span of " + tt);
                    minValue = d;
                }
                //System.out.println(" Deleting " + d);
                BldData remove = m.remove( d);
                if (remove == null) {
                    System.out.println(" Remove of " + d + " == null");
                }
            }
          
            if (tt > 0) {
                BldData bData = m.get(b);
                if ((bData != null) && (bData.getRef() <= 1)) {
                    bData.incSpan(tt);
                    m.put( b, bData);
                    //System.out.println(" bumped " + b + " to " + m.get(b) + " Map = " + m.toString() );
                } 
            }
            //System.out.println(" Max == " + max);
         
        }
   
        
        // now deal with the still active ones
        Set set = m.entrySet();        
        Iterator itr =  set.iterator();
        while (itr.hasNext()) {
            Map.Entry me = (Map.Entry) itr.next();
            Integer key = (Integer) me.getKey();
            Integer value = ((BldData) me.getValue()).getSpan();
            Integer tmp1 = key *  value;
            if (tmp1 > max) max = tmp1;
           // System.out.println(" closing out " + key + " value = " + value + " max = " + max);
            
        }
        System.out.println(max);
    }        
}
