/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btree;

import java.util.*;
import java.io.*;

/**
 *
 * @author Jay
 */
public class BTree {

    /**
     * @param args the command line arguments
     */
    public  static class Node {
        // attributes and values
        int payload = -1;
        Node left;
        Node right;
        
        //Now the methods
        public Node(int i) {
            this.payload = i;
            left = null;
            right = null;
        }
        
        public void setLeft(Node n) {
            System.out.println(" setting left node");
            left = n;
        }

        public Node getLeft() {
            return left;
        }
        public void setRight(Node n) {
            System.out.println(" setting right node");
            right = n;
        }
        public Node getRight() {
            return right;
        }
        public void addNode(int i) {
            // first find the right spot
            System.out.println("adding node " + i);
            if (i < this.payload) {
                System.out.println("going left.  i =  " + i + " mypayload = " + this.payload);
                
                if (this.left == null) {
                    setLeft(new Node(i));
                } else {
                    this.left.addNode(i);
                }
            } else {  // i> payload
                System.out.println("going right.  i =  " + i + " mypayload = " + this.payload);
                if (this.right == null) {
                    setRight(new Node(i));
                } else {
                    this.right.addNode(i);
                }
            }
        }
        
        public void printNode() {
            if (left != null) {
                left.printNode();
            }
            
            System.out.print(payload + " ");

            if (right != null) {
                right.printNode();
            }
            
        }

    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        String input = "5 2 7 4 -8 9 6 3 16 -2";
        Scanner sc = new Scanner(input);
        Node root = new Node(sc.nextInt());
        while(sc.hasNextInt()) {
            root.addNode(sc.nextInt());
        }
        root.printNode();
        System.out.println(" ");
    }
    
}
