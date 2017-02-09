/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import java.util.*;
import java.io.*;
import static java.lang.Boolean.*;
/**
 *
 * @author Jay
 */
public class LinkedList {

    /**
     * @param args the command line arguments
     */
            // create our list of nodes
    static ArrayList<Node> nodes = new ArrayList();

    public static class Node  {
        Node prev = null;
        Node next = null;
        int payload;
        
        public Node(int i) {
            payload = i;
        }
        // add a node immediately following me
        public void addNode(Node n) {
           if (next != null) {
               n.next = next;
               next.prev = n;
               next = n;
           } 
           this.next = n;
           
           // insert an error here
           if (n.payload == 7) {
               n.next = this;
            }
        }
    }
   
    public static void printNode(Node n) {
        System.out.print(n.payload + " ");
        if (n.next != null) {
               printNode(n.next);
        }
    }
    public static Boolean checkNodes(Node n) {
        System.out.println("Checking for loops. n.payload = " + n.payload + " ");
           
        if (nodes.contains(n)) {
            System.out.println(" Error - node is already in the list");
            System.out.println("Node " + n.payload + " is in a loop");
            return true;
        } else if (n.next != null) {
               // add N to our collection
               nodes.add(n);
               checkNodes(n.next);
        }
        return false;
    }
 
    public static void main(String[] args) {
        // TODO code application logic here
        String input = "5 2 7";
        Scanner sc = new Scanner(input);
        Node head = new Node(sc.nextInt());
        Node last = head;
        while(sc.hasNextInt()) {
            Node n = new Node(sc.nextInt());
            last.addNode(n);
            last = last.next;
        }

        Boolean loop = checkNodes(head);
        
        //printNode(head);
        System.out.println(" ");

    }
    
}
