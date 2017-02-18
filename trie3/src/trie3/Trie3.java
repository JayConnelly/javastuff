/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trie3;
import java.util.*;
import java.io.*;

/**
 *
 * @author Jay
 */
public class Trie3 {

    /**
     * @param args the command line arguments
     */
    
    static  Node head;
       
    protected static class Node {
        //HashMap<Character,Node> children = new HashMap<>();
        Node[] children = new Node[26];
        boolean isWord = false;
        int numChildWords = 0;
       
        public Node() {
            Arrays.fill(children, null);
           // for (int i=0;i<26;i++) { children[i] = null; }
        }
       
            static final int A = (int) 'a';
       
       // protected static class CharInt {
        public int translate(char c) {
                return((int) c - A);
        }
        

        private void add(String s, int idx) {
            
            Node next;
        numChildWords++;  // added a new child - will be a word down there somewhere
        
            if ( idx < s.length()) {
                //System.out.println("S not mt - Adding " + s.substring(idx));
           
                Character c = s.charAt(idx);
                next = children[translate(c)];
                if (next != null) {
                    // send the substring down the chain
                    next.add(s, ++idx);
                } else { // next == null
                    // now more children in the string

                    //System.out.println(" Adding char " + c + " numChildwords at this node now = " + numChildWords);
                    next = new Node();
                    children[translate(c)] = next;
                    
                    //System.out.println(" calling next.add with  " + s.substring(idx + 1));               

                    next.add(s, ++idx);
                }
            } else {
                // S is null.  This is the end of a word
                //System.out.println(" isWord = true");
                //numChildWords++;  // added a new child
                isWord = true;
            }
        }
        
        private int findRemaining() {
           return(numChildWords);
          /*  int words =  0;
   
            if (isWord) { words++; }

            for (int i = 0; i<26; i++) {
                if (children[i] != null) {
                    words = words + children[i].findRemaining();
                }
            }
            System.out.println(" findRemaining returning " + words + " numChildWords = " + numChildWords);
            return (words);
         */
        }
    
   
         protected int findAll(String s, int idx) {
            
            int words = 0;
            Node next;
            
            if ( idx < s.length()){
                //System.out.println("Looking for " + s.substring(idx));
                next = children[translate(s.charAt(idx))];
 
                if (next != null) {
                    words = words + next.findAll(s, ++idx);        
                } 
            } else {
                //System.out.println("did not find character at position " + idx + " calling find remaining");
                words = words + findRemaining();
            }
            //System.out.println(" find all returning " + words);
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
 
        for (int i = 0 ; i < numOps; i++)  { 
            nextOp = (String) sc.next();
            
            if ( nextOp.charAt(0) == 'a' ) {
                head.add(sc.next(), 0);
            } else {
                numWords = head.findAll(sc.next(), 0);
               System.out.println( numWords );
            } 
        }
    }
    
}
