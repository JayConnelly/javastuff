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
public class MyScanner {
    public void scanit(String input) {
        // TODO code application logic here
        Scanner s = new Scanner(input).useDelimiter(" ");
        while (s.hasNext()){
            System.out.println(s.next());
        }
        
        s.close();
    }
}
