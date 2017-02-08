/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner.stuff;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jay
 */
public class PlusMinus {

       
    public void countit() {
        int positive = 0;
        int negative = 0;
        int zeros = 0;
        int num = 0;
        
       Scanner s = null;
        File f = new File("scandata.txt");
        try {
            s = new Scanner(f);
         } catch (FileNotFoundException ex) {
              Logger.getLogger(PlusMinus.class.getName()).log(Level.SEVERE, null, ex);
         }
        if (s != null) {
            num = s.nextInt();
            String tmp = s.nextLine();
           // tmp = s.nextLine();
            
            System.out.println("number = " + num + "  string = " + tmp);
            for ( int i = 0; i<num;i++) {
                int next = s.nextInt();
                System.out.println("got value :" + next);
                if (next > 0) positive++;
                else if (next < 0) negative++;
                else zeros++;
            }
        }
        double pRatio = (double) positive / num;
        double nRatio = (double) negative / num;
        double zRatio = (double) zeros / num;
        
        System.out.println((double) positive / num);
        System.out.println((double) negative / num);
        System.out.println((double) zeros / num);
    }
}
