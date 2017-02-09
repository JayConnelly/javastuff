/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testbed;

/**
 *
 * @author Jay
 */
public class TestBed {

    /**
     * @param args the command line arguments
     */
    public static void getNewInts(int[] a) {
        a[0] = 3;
        a[1] = 4;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        int[] arr = new int[2];
        arr[0] = 1;
        arr[1] = 2;
        getNewInts(arr);
        System.out.println(" i1 = " + arr[0] + " i2 = " + arr[1]);
    }
    
}
