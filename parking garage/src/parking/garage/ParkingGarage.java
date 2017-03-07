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
    static final String CAR = "C";          // input format for car (would use integers in production)
    static final String  MOTORCYCLE = "M";  // input format for motorcycle (The letters helped with debug)
    
    static final int CARSIZE = 3;           // width of car 
    static final int  MOTORCYCLESIZE = 1;   // width of motorcycle

    static class Spots {    // 2 types of slots - motorcycle and car
        int numSpots = 0;
        int numCars = 0;    // need these for exit
        int numMotorCycles = 0;
        int getNumCars() { return (numCars); }
        int getNumMotorCycles() { return (numMotorCycles); }
        void setSpots(int spots) { numSpots = spots; }
    }
    
    static class MSpots extends Spots {     // motorcycle parking spots
        static int numBlocked = 0;
         boolean isSpaceForCar() { return ((numSpots - numBlocked) >= CARSIZE); }
         boolean isSpaceForMotorCycle() { return (numSpots  >= MOTORCYCLESIZE); }
 
         void leaving(Vehicle v) {  // vehicle leaving motorcycle spots 
            if (v.isCar()) {
                numCars--;
                numSpots += 3;
                numBlocked -= 2;
            } else {   // motorcycle is leaving
                numMotorCycles--;
                numSpots++;
            }
        }

        boolean tryToPark(Vehicle v) {   //parking in motorcycle spots
             if (v.isCar() && isSpaceForCar()) {
                numBlocked += 2;    // blocks 2 additional spaces for cars
                numCars++;
                numSpots -= 3;
                return true;
           } else if ( v.isMotorCycle() && isSpaceForMotorCycle()) {
               numBlocked +=2;
               numMotorCycles++;
               numSpots--;
               return true;
           } else {
               return false;
           }               
        }
    }
    
    static class CSpots extends Spots {
         boolean isSpaceForCar() { return (numSpots > 0); }
         boolean isSpaceForMotorCycle() { return (numSpots > 0); }

         void leaving(Vehicle v) { 
            numSpots++;
            if (v.isCar()) {
                numCars--;
            } else {   // motorcycle is leaving
                numMotorCycles--;
            }
        }
         
        boolean tryToPark(Vehicle v) {  // park in teh car spot
           if (numSpots > 0) {                         // parking in  car spot
                numSpots--;        // must have taken a car slot
                if (v.isCar()) { numCars++; }
                if (v.isMotorCycle()) { numMotorCycles++; }
                return (true);
            } else {
               return false;
           }
        }
    }

    // instantiate the different classes for spot
    static MSpots motorCycleSpots = new MSpots();
    static CSpots carSpots = new CSpots();
    

    // The vehicle class represents either a motorcycle or a car.
    protected static class Vehicle {
        private int size = 0;       // 1 for notorcycle, 3 for car
        private String name;        // printable name based on type
        
        boolean parking = false;    // keep track of what this vehicle wants to do
        boolean leaving = false;
        
        String getName() { return(name); }
        boolean isCar() { return( (size == CARSIZE));}
        boolean isMotorCycle() { return( (size == MOTORCYCLESIZE));}
        boolean tryingToPark() { return( parking);}        
        boolean isLeaving() { return( leaving );}

        boolean leave() {    
            if (isCar()) {
                // try to leave from car spot
                if (carSpots.getNumCars() > 0 ) {    // means there is a car parked in cars
                    carSpots.leaving(this);
                } else if (motorCycleSpots.getNumCars() > 0) {  // must be leaiving from mortor cycles
                    motorCycleSpots.leaving(this);
                } else {
                    System.out.println(" Error car leaving.  No cars parked ");
                    return false;  
                }
            } else {  // must be a motorcycle leaving
                if (motorCycleSpots.getNumMotorCycles() > 0 ) {    // motor cycles leave from motorcycle spots first
                    motorCycleSpots.leaving(this);
                } else if (carSpots.getNumMotorCycles() > 0) {  // else leave from car slots
                    carSpots.leaving(this);
                } else {
                    System.out.println(" Error motorcycle leaving.  No motorcycles parked ");
                    return false;
                }
            }
            return true;
        }
                
        boolean canPark() {    // see if we can park this vehicle
            if (isCar()) {      // cars park in motorcycle first or car slot 2nd
                return (motorCycleSpots.tryToPark(this) || carSpots.tryToPark(this));
            } else {            // motorcycles park in car spots first then motorcycles if avauilable
                return (carSpots.tryToPark(this) || motorCycleSpots.tryToPark(this));
            }
        }
                    
        Vehicle( String vString) {
            if (vString.charAt(0)== '+') {     // trying to park a new v
                parking = true;
            } else if (vString.charAt(0)== '-') {  // v is leaving
                leaving = true;
            }
        
            switch (vString.substring(1)) {
                case CAR:
                    name = CAR;
                    size = 3;  
                    break;
                case MOTORCYCLE:
                    name = MOTORCYCLE;
                    size = 1;
                    break;
                default:
                    System.out.println(" Invalid vehicle type " + vString);
            }
        }
    }
     
    public static void main(String[] args) throws FileNotFoundException {
        
        File f = new File("data.txt");
        Scanner sc = new Scanner(f);
        
        carSpots.setSpots(sc.nextInt());
        motorCycleSpots.setSpots (sc.nextInt());
                
        while ( sc.hasNext() ) {   // keep going while vehicles are coming
            Vehicle v  = new Vehicle((String) sc.next());
            if (v.tryingToPark()) {  // separate parking from leaving
                if (v.canPark()) {
                    System.out.println("Welcome " + v.getName());                  
                } else {
                    System.out.println("Sorry - all full " + v.getName());
             }
            } else  if (v.isLeaving()) {  // must be leaving
                boolean success = v.leave();
                if (success) { 
                    System.out.println("Have a nice day " + v.getName());
                }
            } else {
                System.out.println("Invalid Input" + v.getName());
            }
        }
    }
}

