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
            boolean parked = false;
            if (v.isCar() && isSpaceForCar()) {
                numBlocked += 2;    // blocks 2 additional spaces for cars
                numCars++;
                numSpots -= 3;
                parked = true;
            } else if ( v.isMotorCycle() && isSpaceForMotorCycle()) {
               numBlocked +=2;
               numMotorCycles++;
               numSpots--;
               parked = true;
            }
            //System.out.println(" try to park in MC Spot returning " + parked);
            return parked;
        }
    }
    
    static class CSpots extends Spots {  //this is for all the car spots
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
         
        boolean tryToPark(Vehicle v) {  // park in the car spot
            boolean parked = false;
            if (numSpots > 0) {                         // parking in  car spot
                numSpots--;        // must have taken a car slot
                if (v.isCar()) { numCars++; }
                if (v.isMotorCycle()) { numMotorCycles++; }
                parked = true;
            }
            //System.out.println(" try to park in car spot returning " + parked);
            return parked;
        }
    }

    // instantiate the different classes for spot
    static MSpots motorCycleSpots = new MSpots();
    static CSpots carSpots = new CSpots();
 
    static ArrayList mcArr = new ArrayList();
    static ArrayList carArr = new ArrayList();
    
    // read in all the configs.  format:  first line is number of configs.  Each config is a line starting with an "M" or a "C" and followed by the number of slots
    static int getConfigs(Scanner sc) {
        // get number of configurations
        int numConfigs = sc.nextInt();
        for (int i = 0; i< numConfigs; i++) {
            String type =  sc.next();
            if (type.equals(CAR)) {
                CSpots cs = new CSpots();
                cs.setSpots(sc.nextInt());
                carArr.add(cs);
            } else if (type.equals(MOTORCYCLE)) {
                MSpots ms = new MSpots();
                ms.setSpots(sc.nextInt());
                mcArr.add(ms);
            }
        }
        System.out.printf(" ParkingGarage.getConfigs found %d configs, carSize = %d, mcSize = %d \n", numConfigs, carArr.size(), mcArr.size());
        return(numConfigs);
    }


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
        
        boolean tryToLeaveCarSpot(Vehicle v) {
            boolean left = false;
            for (int i = 0; i < carArr.size(); i++) {
                 // try to leave from car spot
                CSpots cSpots = (CSpots) carArr.get(i);
                if (( v.isCar() && (cSpots.getNumCars() > 0)) || ( v.isMotorCycle() && (cSpots.getNumMotorCycles() > 0))) {    // means there is a car parked in cars
                    cSpots.leaving(this);
                    left = true;
                    break;
                } 
            }
            //if (left == false) { System.out.printf(" TTLC: Error - vehicle %s tried to leave car slots but none were parked \n",getName());}
            //System.out.println(" vehicle.tryToLeaveCarSpot returning " + left);
            return left;
        }
        
        boolean tryToLeaveMotorCycleSpot(Vehicle v) {
            boolean left = false;
            for (int i = 0; i < mcArr.size(); i++) {
                 // try to leave from car spot
                MSpots mSpots = (MSpots) mcArr.get(i);
                if (( v.isCar() && (mSpots.getNumCars() > 0)) || ( v.isMotorCycle() && (mSpots.getNumMotorCycles() > 0)))  { 
                    mSpots.leaving(this);
                    left = true;
                    break;
                }   
            }
           // if (left == false) { System.out.printf(" TTLMC: Error - vehicle %s tried to leave MC slots but none were parked \n", getName());}
           // System.out.println(" vehicle.tryToLeaveMotorcycleSlot returning " + left);
            return left;
        }
                
              
        boolean leave() {
            boolean left = false;
            if (isCar()) {
                left = tryToLeaveCarSpot(this) || tryToLeaveMotorCycleSpot(this);
            } else {  // must be a motorcycle leaving - motorcycles leave from motorcycle spots first
                left =  tryToLeaveMotorCycleSpot(this) || tryToLeaveCarSpot(this);
            }
            if (left == false) { System.out.printf(" vehicle.leave: Error - vehicle %s tried to leave car slots but none were parked \n", getName());}       
            //System.out.println(" vehicle.leave returning " + left);
            return left;
        }
        
        boolean tryToParkInMotorCycleSlot(Vehicle v) {
            boolean parked = false;
            for (int i = 0; i < mcArr.size(); i++) {
                MSpots mcSpots = (MSpots) mcArr.get(i);
                if ( mcSpots.tryToPark(this)) {
                        parked = true;
                        break;
                }
            }
            //System.out.println(" TTPM returning " + parked);
            return (parked);
        }
        
        boolean tryToParkInCarSlot(Vehicle v) {
            boolean parked = false;
            for (int i = 0; i < carArr.size(); i++) {
                CSpots cSpots = (CSpots) carArr.get(i);
                if ( cSpots.tryToPark(this)) {
                        parked = true;
                        break;
                }
            }
            //System.out.println(" TTPC returning " + parked);
            return(parked);
        }
        
        boolean canPark() {    // see if we can park this vehicle
            if (isCar()) {      // cars park in motorcycle first or car slot 2nd
                return( tryToParkInMotorCycleSlot(this) || tryToParkInCarSlot(this));
            } else {            // motorcycles park in car spots first then motorcycles if avauilable
                return( tryToParkInCarSlot(this) || tryToParkInMotorCycleSlot(this));
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
        
        int numConfigs = getConfigs(sc);        
                
        while ( sc.hasNext() ) {   // keep going while vehicles are coming
            Vehicle v  = new Vehicle((String) sc.next());
            if (v.tryingToPark()) {  // separate parking from leaving
                if (v.canPark()) {  // self parking vehicles :)
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

