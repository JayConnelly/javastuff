/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner.stuff;
import java.util.*;
/**
 *
 * @author Jay
 */

public class ScannerStuff {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        MyScanner ms = new MyScanner();
     //   ms.scanit( "1 fish 2 fish");
        
        PlusMinus pm = new PlusMinus();
    //    pm.countit();
        
        TestScan ts = new TestScan();
     //   ts.scanThis("1 2 this is a test 4 again" );
       
        boolean[] all = { false, true };
     for (boolean a : all) {
        for (boolean b: all) {
            boolean c = a ^ b;
       //     System.out.println(a + " ^ " + b + " = " + c);
        }
    }

    }
    
}
