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
        
        
        File f = new File("data1.txt");
        Scanner sc = new Scanner(f);
        char p = ' ';
        
        
        int numStrings = sc.nextInt();
        System.out.println("numstrings = " + numStrings);
        String str;
        final int PAREN = 0;
        final int BRACE = 1;
        final int BRACKET = 2;

    
        for (int i = 0; i < numStrings; i++) {
            boolean matches = true;
            int[] isOpen = {0,0,0};
            str = sc.next();
            int c = 0;
            //System.out.println("string = " + str);
            do {
                
                Character current = str.charAt(c);
               // System.out.printf("char at position %d = %c ",c,current);

                String curString = current.toString();
                if (open.contains(curString)) {  //open paren
                     switch (current) {
                        case '(':
                            isOpen[PAREN]++;
                            break;
                        case '[':
                            isOpen[BRACKET]++;
                            break;
                        case '{':
                            isOpen[BRACE]++;
                            break;    
                    }
                    s.push(current);
                 //System.out.printf("Pushing %c Open Parns = %d Open brackets = %d, open braces = %d \n", current, isOpen[PAREN], isOpen[BRACKET], isOpen[BRACE]);

                }
                else { // must be a close
   // pushing a close.  early out test to see if there are opens
                     switch (current) {
                        case ')':
                            if ( --isOpen[PAREN] < 0) {
                              matches = false;
                            } 
                            break;
                        case ']':
                            if ( --isOpen[BRACKET] < 0) {
                                matches = false;
                                
                            } 
                            break;
                        case '}':
                            if ( --isOpen[BRACE] < 0) {
                                matches = false;
                                
                            } 
                            break;    
                    }
                    Iterator itr = s.iterator();
                    if (! s.empty()) {
                        p = s.pop();
                       //System.out.printf("Popped %c Open Parns = %d Open brackets = %d, open braces = %d \n", p, isOpen[PAREN], isOpen[BRACKET], isOpen[BRACE]);
                    } else {
                        matches = false;
                        //c = str.length();
                        
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
            c++;
            } while (matches && c<str.length());
                
            if (matches && isOpen[BRACKET]==0 && isOpen[BRACE]==0 && isOpen[PAREN]==0) {
                System.out.println("YES");
            } else {
                //System.out.printf(" Open Parns = %d Open brackets = %d, open braces = %d  ", isOpen[PAREN], isOpen[BRACKET], isOpen[BRACE]);
                System.out.println("NO");
                matches = true;
            }
        }
    }
    
}
