/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parking.garage;
import java.util.*;
import java.io.*;


/**
 *
 * @author Jay
 */
public class ParkingGarage {
    static final int CAR = 0;
    static final int MOTORCYCLE = 1;
    static int numCarSlots = -1;
    static int numMotorSlots = -1;    
    static int numCarSpots = -1;
    static int numMotorSpots = -1;
    static int type = -1;
    static boolean allowed;
    
     
    public static void main(String[] args) throws FileNotFoundException {
        
        File f = new File("data.txt");
        Scanner sc = new Scanner(f);
        
        numCarSpots = sc.nextInt();
        numMotorSpots = sc.nextInt();
        allowed = true;
        
        while ( sc.hasNext() && ((numMotorSpots > 0) || (numCarSpots > 0) )) {
            allowed = true;   // set default then unwind if not possible
            type = sc.nextInt();
          
            if (type == CAR) {   // its a car
                if (numMotorSpots  > 3) {
                    numMotorSpots  -= 3;  // worse case - assume they parked in the motorcycle spots
                } else if (numCarSpots > 0) {
                    numCarSpots -= 1;        // must have taken a car slot
                } else {                  // not enough car slots
                    allowed = false;
                } 
            } else if (type == MOTORCYCLE )  {
                if (numCarSpots >= 1) {
                    numCarSpots -= 1;  // worse case he parks in a car spot
                } else if (numMotorSpots > 0) {
                    numMotorSpots -= 1;   // has to take a motorcycle slot
                } else {
                    allowed = false;
                }
            }
    
            if (allowed) {
                System.out.println("Welcome " + type);
            } else {
                System.out.println("Sorry - all full " + type);
            }
        }
    }
}

