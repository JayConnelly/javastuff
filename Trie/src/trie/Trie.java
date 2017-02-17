/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trie;
import java.util.*;
import java.io.*;

/**
 *
 * @author Jay
 */
public class Trie {

    /**
     * @param args the command line arguments
     */
    
    static  Node head;
       
    protected static class Node {
        HashMap<Character,Node> children;
        boolean isWord;
        
         public Node() {
            children = new HashMap<>();
            isWord = false;
        }
        
        protected void add(String s) {
            
            if ( ! s.isEmpty()) {
                //System.out.println("S not mt - Adding " + s);
                Node next = children.get(s.charAt(0));
                if (next != null) {
                    // send the substring down the chain
                    next.add(s.substring(1, s.length()));
                } else {
                    // now more children in the string
                    //System.out.println(" did not find char " + s.charAt(0) + " creating new node and adding to hash");               
                    next = new Node();
                    children.put(s.charAt(0), (Node) next);
                    //System.out.println(" calling next.add with  " + s.substring(1, s.length()) );               

                    next.add(s.substring(1, s.length()));
                }
            } else {
                // S is null.  This is the end of a word
                //System.out.println(" isWord = true");
                isWord = true;
            }
        }
        
        protected int findRemaining() {
            int words = 0;
              if (isWord) { words++;  }

              Iterator itr = children.entrySet().iterator();
            while (itr.hasNext()) {

                Map.Entry me = (Map.Entry) itr.next();
                Node next = (Node) me.getValue();
                //System.out.println(" hasNext " + me.getKey());
                words = words + next.findRemaining();
            }
            //System.out.println(" find remaining returning " + words);
            return (words);
        }
         protected int findAll(String s) {
            
            int words = 0;
                
            if ( ! s.isEmpty()) {
                //System.out.println("Looking for " + s);
                Node next = children.get(s.charAt(0));
 
                if (next != null) {
                    words = words + next.findAll(s.substring(1, s.length()));
                };
                
            } else {
                words = words + findRemaining();
            }
             return (words);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Try adding a new word
        int numWords = 0;
        String nextArg = "";
        String nextOp;
        head = new Node();
        
        File f = new File("data1.txt");
        Scanner sc = new Scanner(f);
        int numOps = sc.nextInt();
 
        for (int i = 0 ; i < numOps; i++)  { //while (sc.hasNext()) { //for (int i = 0; i < numOps; i++) {
            nextOp = (String) sc.next();
            nextArg = sc.next();
            
            if ( "add".equals(nextOp) ) {
                head.add(nextArg);
            } else if ("find".equals(nextOp)) {
                numWords = head.findAll(nextArg);
                System.out.println( numWords );
            } 
        }
    }
    
}
