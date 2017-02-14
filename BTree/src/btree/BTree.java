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
    public static Node root; 
    
    public  static class Node {
        // attributes and values
        int payload = -1;
        Node left;
        Node right;
        int level = 0;
        static int maxLeft = 0;
        static int maxRight = 0;
        
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
        
        public int getDepth(Node n) {
            int leftDepth = 0;
            int rightDepth = 0;
            if (n.left != null) {
                leftDepth = getDepth(n.left);
            } else {
                leftDepth = n.level;
            }
        
            if (n.right != null) {
                rightDepth = getDepth(n.right) + rightDepth;
            } else {
                rightDepth = n.level;
            }
            System.out.println(" depth for node " + n.payload + " right = " + rightDepth + " Left = " + leftDepth);
            System.out.println(" returning " + Integer.max(leftDepth,rightDepth));
            return(Integer.max(leftDepth, rightDepth));
        }
        
        
/*
    public void balanceTree(Node n) {
            int rightDepth;
            if (right != null) {
                rightDepth = getDepth(n.right)
            } else rightDepth = 0;
            
            if (left != null) {
                leftDepth = getDepth(n.right)
            } else leftDepth = 0;
            
            System.out.println("node " + payload + " right depth = " + leftDepth + " right depth = " + rightDepth);
        }
  */          
            
        
        public void addNode(int i) {
            // first find the right spot
            
            System.out.println("adding node " + i);
            if (i < this.payload) {
                
                if (this.left == null) {
                    setLeft(new Node(i));
                    this.left.level = this.level + 1;
                    System.out.println(" Adding left node for " + i + " Level = " + left.level);
                } else {
                    this.left.addNode(i);
                }
            } else {  // i> payload
                if (right == null) {
                    setRight(new Node(i));
                    right.level = level + 1;
                    System.out.println(" Adding right node for " + i + " Level = " + right.level );
                } else {
                    this.right.addNode(i);
                }
            }
            
            // now level them
 //          int depth = getDepth(root);
 //          System.out.println(" Depth = " + depth);
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
        String input = "3 2 4 5 6";
        Scanner sc = new Scanner(input);
        root = new Node(sc.nextInt());
        while(sc.hasNextInt()) {
            root.addNode(sc.nextInt());
        }
        root.printNode();
        System.out.println(" ");
    }
    
}
