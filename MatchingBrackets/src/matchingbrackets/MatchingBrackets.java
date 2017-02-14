/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matchingbrackets;
import java.util.*;
import java.io.*;
import java.lang.*;


/**
 *
 * @author Jay
 */
public class MatchingBrackets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Stack<Character> s = new Stack();
        String open = "([{";
        String close = ")]}";
        
        
        File f = new File("data.txt");
        Scanner sc = new Scanner(f);
        char p;
        
        
        int numStrings = sc.nextInt();
       // System.out.println("numstrings = " + numStrings);
        String str;
    
        for (int i = 0; i < numStrings; i++) {
            boolean matches = true;
            str = sc.next();
            //System.out.println("string = " + str);
            for (int c = 0; c<str.length(); c++) {
                Character current = str.charAt(c);
               // System.out.printf("char at position %d = %c ",c,current);

                String curString = current.toString();
                if (open.contains(curString)) {  //open paren
                    s.push(current);
                 //   System.out.println("Pushing " + current);

                }
                else { // must be a close
                    Iterator itr = s.iterator();
                    if (! s.empty()) {
                        p = s.pop();
                    } else {
                        matches = false;
                        //c = str.length();
                        continue;
                    }
                   // System.out.println("Popped " + current);
                    // pop must match current character
                    switch (current) {
                        case ')':
                            if (p != '(') matches = false;
                            break;
                        case ']':
                            if (p != '[') matches = false;
                            break;
                        case '}':
                            if (p != '{') matches = false;
                            break;    
                    }
                }
            }
            if (matches) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
    
}
