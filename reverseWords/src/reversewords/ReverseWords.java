/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversewords;

/**
 *
 * @author Jay
 */
public class ReverseWords {

    /**
     * @param args the command line arguments
     */
    static String myStr = "Now is the new time again";
    
    public static void reverseWords(String myStr) {
        String[] splitStr = myStr.split(" ");
        String newStr = "";
     
        for (int i=0; i< splitStr.length; i++) {
            newStr = newStr + " " + splitStr[splitStr.length-i-1];
        }
        System.out.println(" The new array is: " + newStr);
    }
    
     public static int reverseDigits(Integer myNum) {
        Integer ret = 0;
        while (myNum > 0) {
            ret = (ret *10) + (myNum % 10);
            myNum = myNum / 10;
        }
     
        System.out.println(" The new number is: " + ret);
        return(ret);
     }
     
    public static void main(String[] args) {
        // TODO code application logic here
    
        reverseWords(myStr);
        reverseDigits(123);
    }
    
}
