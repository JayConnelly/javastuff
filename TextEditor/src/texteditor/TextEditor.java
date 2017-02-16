/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;
import java.util.*;
import java.io.*;

/**
 *
 * @author Jay
 */
public class TextEditor {

    /**
     * @param args the command line arguments
     */
    static Stack<Op> stk;
    static final int APPEND = 1;
    static final int DELETE = 2;
    static final int PRINT = 3;
    static final int UNDO = 4;
    static String s = "";
 
    static protected class Op {
        int operator = 0;
        private int numDel = -1;  // num chars to delete
        private int n = -1;       // print nth char
        private String str = "*";  // string to append
        private String deletedChars = "*";
        
        @Override
        public String toString() {
            return(" Op = " + operator +
                    " : numDel = " + numDel +
                    " : nth char = " + n +
                    " : append = " + str +
                    " : Deleted  = " + deletedChars +
                    " : s = " + s);
        }
        
        public int getOperator() { return operator; }
        
        public void getOp(Scanner sc) {  // read the op details from the scanner
            operator = sc.nextInt();
            switch (operator) {
                    case APPEND:  // append
                        str = sc.next();
                        break;
                    case DELETE: // delete last x characters
                        numDel = sc.nextInt();
                        break;
                    case PRINT: // print nth character
                        n = sc.nextInt();
                        break;
                    case UNDO: // undo the last operation
                        break;
            }
            //System.out.println("just got op " + this.toString());
        }
        
        public String undo(String s) {  // read the op details from the scanner
            //System.out.println("undoing op " + this.toString());
            switch (operator) {
                    case APPEND:  // append
                        s = s.substring(0, s.length() - str.length());
                        break;
                    case DELETE: // delete last x characters
                        s = s + deletedChars;
                        break;
                    default:
                        System.out.println("Error undoing op  " + this.toString());
            }
            return(s);
        }

        public String execute(Stack stk, String s) {  // read the op details from the scanner
           // System.out.println("Executing " + this.toString());

            Op lastOp;
            switch (operator) {
                    case APPEND:  // append
                        s = s + str;
                        break;
                    case DELETE: // delete last x characters
                        deletedChars = s.substring( (s.length()-numDel), s.length());
                        s = s.substring(0, (s.length() - numDel));
                        break;
                    case PRINT: // print nth character
                        System.out.println(s.charAt(n-1));
                        break;
                    case UNDO: // undo the last operation
                        lastOp = (Op) stk.pop();
                        s = lastOp.undo(s);
                        break;
            }
            return(s);
        }
                
    }
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        stk = new Stack();
        
        File f = new File("data.txt");
        Scanner sc = new Scanner(f);
 
        int numOps = sc.nextInt();
        //System.out.println("Number of operations = " + numOps);
    
        for (int i = 0; i < numOps; i++) {
             Op op = new Op();
             op.getOp(sc);      // read the operation
             s = op.execute(stk, s);      // execute the op
             if (op.getOperator() <= DELETE) {
                 stk.push(op);         // in case we have to rewind
             }
        }
    }
    
}
