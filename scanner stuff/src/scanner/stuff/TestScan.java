/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  scanner.stuff;;

import java.util.*;
import java.io.*;
/**
 *
 * @author Jay
 */
public class TestScan {
    public void scanThis(String s) {
        Scanner sc = new Scanner(s);
        System.out.printf("using printf  %d\n", sc.nextInt());
        /* System.out.println("Number is " + sc.nextInt());
        System.out.println("Number is " + sc.next());
        System.out.println("Number is " + sc.next());
        System.out.println("Number is " + sc.next());
*/
        float f = (float) 1.23;
        int i = 4;
        double d = 2.3456789;
        System.out.println("done");
        
    }
}
