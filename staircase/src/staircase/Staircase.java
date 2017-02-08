/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staircase;
import java.util.*;
import java.io.*;
        
/**
 *
 * @author Jay
 */
public class Staircase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Scanner sc = new Scanner(System.in);
        int num = 6; //sc.nextInt();
        for (int row = 0;row <num ;row++) {
            for (int col = 0;col<num; col++) {
                if (col >= (num-row)-1) {
                    System.out.print("#");
                } else {
                    System.out.print("-");
                }
            }
            System.out.print("\n");
        }
        
    }
    
}
