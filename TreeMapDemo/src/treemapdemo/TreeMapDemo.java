/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treemapdemo;
import java.util.*;

/**
 *
 * @author Jay
 */
public class TreeMapDemo {

    /**
     * @param args the command line arguments
     */

        
   public static void main(String args[]) {
      // Create a hash map
      TreeMap tm = new TreeMap();
      
      // Put elements to the map
      tm.put("Zara", 3434.34);
      tm.put("Mahnaz", 123);
      tm.put("Ayan", 'a');
      tm.put("Daisy", "abcd");
      tm.put("Qadir", -19.08);
      
      // Get a set of the entries
      Set set = tm.entrySet();
      
      // Get an iterator
      Iterator i = set.iterator();
      
      // Display elements
      while(i.hasNext()) {
         Map.Entry me = (Map.Entry)i.next();
         System.out.print(me.getKey() + ": ");
         System.out.println(me.getValue());
      }
      System.out.println();
     
      // Deposit 1000 into Zara's account
      double balance = (double)tm.get("Zara");
      tm.put("Zara", balance + 1000);
      System.out.println("Zara's new balance: " + tm.get("Zara"));
      
      
   }
}
