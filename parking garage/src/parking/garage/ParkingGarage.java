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
    static final String CAR = "C";          // input format for car 
    static final String  MOTORCYCLE = "M";  // input format for motorcycle
    
    static final int CARSIZE = 3;           // width of car 
    static final int  MOTORCYCLESIZE = 1;   // width of motorcycle

    static int numCarSpots = -1;        // number of spots designated for cars
    static int numMotorSpots = -1;      // number of spots designated for motor cycles
    static int numBlocked = 0;        // worst mumber of motorCycle Spots blocked
    
    // next couple are only for parking in motorcycle slots. probably better names to be had
    static boolean isSpaceForCar() { return ((numMotorSpots - numBlocked) >= CARSIZE); }
    static boolean isSpaceForMotorCycle() { return (numMotorSpots  >= MOTORCYCLESIZE); }
    
    protected static class Vehicle {
        static int numVehicles = 0;
        private int size = 0;       // 1 for notorcycle, 3 for car
        private String name;        // printable name based on type
        String parkedAt;    // so we can unwind when it leaves

        void dump() { 
            if (parkedAt != null) {
                System.out.printf( "Vehicle %d is a %s parked in %s \n ", numVehicles, name, parkedAt);
            } else {
                System.out.printf( "Vehicle %d is a %s and is not parked\n ", numVehicles, name);

            }
         };
        
        String getName() { return(name); }
        
        boolean isCar() { return( (size == CARSIZE));}
        boolean isMotorCycle() { return( (size == MOTORCYCLESIZE));}
        
        
        boolean tryToPark(String typeOfSpot) {           // park the vehicle and adjust totals
            boolean allowed = false;
            if (typeOfSpot.equals(MOTORCYCLE)) {        // parking in a motorcycle spot
                //System.out.println(" Trying to parking in motorcycle space");
                if ( (isCar() && isSpaceForCar()) || (isMotorCycle() && isSpaceForMotorCycle()) )   {  
                    //System.out.println(" parking in motorcycle space");
                    numMotorSpots  -= size;      // remove actual spots used 
                    numBlocked += 2;    // blocks 2 additional spaces for cars
                    parkedAt = MOTORCYCLE;      // remember where we parked for when we leave
                    allowed = true;
                } 
            } else if (typeOfSpot.equals(CAR) && (numCarSpots > 0) ) {                         // parking in  car spot
                    //System.out.println(" parking in car space");
                    numCarSpots -= 1;        // must have taken a car slot
                    parkedAt = CAR;         // remember where we parked for when we leave
                    allowed = true;
            }
            
            //System.out.printf("parking in %s = %b ,  %b %b %b %b %d \n",typeOfSpot, allowed, isCar(),isSpaceForCar(), isMotorCycle(), isSpaceForMotorCycle(), numBlocked);
            //System.out.printf("motorspots =  %d,  blocked = %d \n" , numMotorSpots, numBlocked);
            //dump();
            return(allowed);
        }
                
        boolean canPark() {
            if (isCar()) {      // cars park in motorcycle first or car slot 2nd
                return (tryToPark(MOTORCYCLE) || tryToPark(CAR));
            } else {            // motorcycles park in car spots first then motorcycles if avauilable
                return (tryToPark(CAR) || tryToPark(MOTORCYCLE));
            }
        }
                    
        Vehicle( String vString) {
            switch (vString) {
                case CAR:
                    size = 3;
                    name = CAR;
                    break;
                case MOTORCYCLE:
                    size = 1;
                    name = MOTORCYCLE;
                    break;
                default:
                    System.out.println(" Invalid vehicle type " + vString);
            }
            numVehicles++;
        }
    }
     
    public static void main(String[] args) throws FileNotFoundException {
        
        File f = new File("data.txt");
        Scanner sc = new Scanner(f);
        
        numCarSpots = sc.nextInt();
        numMotorSpots = sc.nextInt();
                
        while ( sc.hasNext() ) {   // keep going while vehicles are coming
            Vehicle v  = new Vehicle((String) sc.next());
    
            if (v.canPark()) {
                System.out.println("Welcome " + v.getName());
            } else {
                System.out.println("Sorry - all full " + v.getName());
            }
        }
    }
}

