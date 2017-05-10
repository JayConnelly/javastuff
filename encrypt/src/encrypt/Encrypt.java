/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encrypt;
import java.util.*;
import java.io.*;
        
/**
 *
 * @author Jay
 */
public class Encrypt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner("wclwfoznbmyycxvaxagjhtexdkwjqhlojykopldsxesbbnezqmixfpujbssrbfhlgubvfhpfliimvmnny");
        String in = sc.next();
        int l = in.length();
        System.out.printf("num chars = %d \n",l);
        double sqrt = Math.sqrt(l);
        System.out.println(sqrt);
        int rows = (int) Math.floor(sqrt);
        int cols = (int) Math.ceil(sqrt);
        System.out.printf("rows = %d cols = %d\n",rows,cols);
        if ((rows * cols) <= l) { 
            rows = cols;
        }
        
        Character[][] output = new Character [rows][cols]; 
        
    
        int sp = 0;
        for (int r = 0; r < rows; r++) {
             for (int c = 0; c < cols; c++) {
                if (sp < l) {
                    output[r][c] = in.charAt(sp++);
                } else {
                    output[r][c] = null;
                }
            }
        }
        
        
        // now print them out by column
        for (int c = 0; c < cols; c++) {
            for ( int r = 0; r < rows; r++) { 
                if (output[r][c] != null) {
                    System.out.print(output[r][c]);
                }
            }
            System.out.print(" ");
        }
        System.out.println();          
    }        
}
    
