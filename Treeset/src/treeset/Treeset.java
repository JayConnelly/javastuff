/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treeset;
import java.util.*;

/**
 *
 * @author Jay
 */
public class Treeset {

    /**
     * @param args the command line arguments
     */
    public static String findDupes(String input) {
        // TODO code application logic here
        Scanner sc = new Scanner(input);
        TreeSet ts = new TreeSet();
        while (sc.hasNext()) {
            String key = sc.next();
            if ( ! ts.add(key)) { // if we can't add it it is because its already there
               return(key);
            }
        }
        return(null);
    }
    
    public static void main(String args[]) {
        String str = findDupes("Dog Cat Dogs Bear Cat");
        System.out.println(str);
    }
}
