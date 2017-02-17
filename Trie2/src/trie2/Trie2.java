/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trie2;
import java.util.*;
import java.io.*;

/**
 *
 * @author Jay
 */
public class Trie2 {

    /**
     * @param args the command line arguments
     */
    
    static  Node head;
       
    protected static class Node {
        //HashMap<Character,Node> children = new HashMap<>();
        Node[] children = new Node[26];
        boolean isWord = false;
       
        public Node() {
            Arrays.fill(children, null);
           // for (int i=0;i<26;i++) { children[i] = null; }
        }
       
            static final int A = (int) 'a';
       
       // protected static class CharInt {
        public int translate(char c) {
                return((int) c - A);
        }
        

        protected void add(String s) {
            
            Node next;

            if ( ! s.isEmpty()) {
                //System.out.println("S not mt - Adding " + s);
                Character c = s.charAt(0);
                next = children[translate(c)];
                if (next != null) {
                    // send the substring down the chain
                    next.add(s.substring(1));
                } else {
                    // now more children in the string
                    //System.out.println(" did not find char " + s.charAt(0) + " creating new node and adding to hash");               
                    next = new Node();
                    children[translate(c)] = next;
                    //System.out.println(" calling next.add with  " + s.substring(1, s.length()) );               

                    next.add(s.substring(1));
                }
            } else {
                // S is null.  This is the end of a word
                //System.out.println(" isWord = true");
                isWord = true;
            }
        }
        
        protected int findRemaining() {
            int words =  0;
   
            if (isWord) { words++;  }

            for (int i = 0; i<26; i++) {
                if (children[i] != null) {
                    words = words + children[i].findRemaining();
                }
            }
            return (words);
        }
        
         protected int findAll(String s) {
            
            int words = 0;
            Node next;
            
            if ( ! s.isEmpty()) {
                //System.out.println("Looking for " + s);
                next = children[translate(s.charAt(0))];
 
                if (next != null) {
                    words = words + next.findAll(s.substring(1));        
                } 
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
        
        File f = new File("data.txt");
        Scanner sc = new Scanner(f);
        int numOps = sc.nextInt();
 
        for (int i = 0 ; i < numOps; i++)  { 
            nextOp = (String) sc.next();
            
            if ( "add".equals(nextOp) ) {
                head.add(sc.next());
            } else if ("find".equals(nextOp)) {
                numWords = head.findAll(sc.next());
                System.out.println( numWords );
            } 
        }
    }
    
}
