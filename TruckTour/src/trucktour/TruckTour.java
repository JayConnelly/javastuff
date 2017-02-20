/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trucktour;
import java.util.*;
import java.io.*;

/**
 *
 * @author Jay
 */
public class TruckTour {

    /**
     * @param args the command line arguments
     */
    static class PumpInfo {
        int gas = 0;
        int distance = 0;
        int excess = 0;
        public void setInfo(int g, int d) {
            gas = g;
            distance = d;
            excess = g-d;
        }
        public int getExcess() {
            return excess;
        }
    }
    
    static ArrayList<PumpInfo> al = new ArrayList<>();
    
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("data.txt");
        Scanner sc = new Scanner(f);
        int numPumps = sc.nextInt();
        //System.out.printf(" numpumps = %d \n",numPumps);
        
        int g = 0;
        int d = 0;
        PumpInfo p;
        
        for (int i = 0; i < numPumps; i++) {
            g = sc.nextInt();
            d = sc.nextInt();
            p = new PumpInfo();
            p.setInfo(g,d); 
            //System.out.printf(" pump # %d, Added gas = %d, distance = %d, excess = %d \n", i,g,d,p.getExcess());
            al.add(p);
        }
        
        // Now go through the list backwards
        int surplus = 0;
        int firstPump = numPumps;
        for (int i = (numPumps-1) ; i >= 0; i--) {
            surplus = surplus + al.get(i).excess;
            //System.out.printf(" surplus at pump %d is %d. Total surplus is %d \n", i,al.get(i).excess,surplus, surplus);
            if (surplus >= 0) {
                // we can get here
                firstPump = i;
                //System.out.printf("adding pump %d to the list \n ", i);
                surplus = 0;  // need to get here on their own.
            } else {
                //System.out.printf("Can't get home from pump %d \n", i);
            }
        }
        System.out.println(firstPump);
    }
}
   
