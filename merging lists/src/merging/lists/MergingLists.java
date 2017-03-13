/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merging.lists;
import java.util.*;
import java.io.*;

/**
 *
 * @author Jay
 */
public class MergingLists {
    static class Query {
        String type = " ";
        int p1 = 0;
        int p2 = 0;
    }
    
    static Query getQuery(Scanner sc) {
        //System.out.println(" Scanning for new query");
        Query q = new Query();
        q.type = (String) sc.next();
        q.p1 = sc.nextInt();
        if (q.type.equals("M")) {
            q.p2 = sc.nextInt();
        }
        //System.out.printf("Found query %s %d %d \n", q.type, q.p1,q.p2);

        return q;
    }
    
    static ArrayList al = new ArrayList();
    
    static void mergeSets(int p1, int p2) {
        int i1 = -1;
        int i2 = -1;
        HashSet <Integer> s1 = null,s2 = null;
        
        for (int i=1; i < al.size(); i++) {
            //System.out.printf("looking into al %d for persons %d and %d \n",i,p1,p2);
            
            if (al.get(i) != null) {
                
                if ( ((HashSet <Integer>) al.get(i)).contains(p1)) {
                    // System.out.printf("found person %d and location %d \n",p1, i);
                   i1 = i;
                }
                
                if ( ((HashSet <Integer>) al.get(i)).contains(p2)) {
                    //System.out.printf("found person %d and location %d \n",p2, i);
                    i2 = i;
                }
            } else {
                //System.out.printf(" ArrayList [ %d ] = null \n", i);
            }
            if ( (i1 >=0 ) && (i2 >= 0) ) {
                 break;
            }
        }
        

        //System.out.printf("merging sets with %d into one with %d \n",i1, i2);
        if (i1 != i2) {
            ((HashSet <Integer>) al.get(i2)).addAll((HashSet <Integer>) al.get(i1));
        }
            //System.out.printf("deleting array list number %d with %d in it \n",i1, p1);
        
        al.set(i1,null);
    }
    
    static int findSetNum(int p) {
        // finds the set number a person is in
        int l = -1;
        
        for (int i=1; i < al.size(); i++) {
            if (al.get(i) != null) {
                if ( ((HashSet <Integer>) al.get(i)).contains(p)) {
                   l = i;
                   break;
                }
            }
        }

        //System.out.printf("found person %d in arraylist position %d \n",p, l);        
        return(l);
    }
    
    static void executeQuery(Query q) {
        if (q.type.equals("M")) {
            // merge the lists
            mergeSets(q.p1, q.p2);            
        } else {  // must be a query
            int i = findSetNum(q.p1);
            System.out.println(i);
        }
    }
    
    static void buildArrayList(int size) {
        al.add(null);  // stuff arraylist zero with nothing.  never used
        for (int i=1; i<=size;i++) {
            HashSet <Integer> hs = new HashSet<>();
            hs.add(i);
            al.add(hs);
            //System.out.printf(" Adding person %d to set %d \n", i,i);
        }
        //System.out.printf(" Arraylist size = %d \n", al.size());
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("data.txt");
        Scanner sc = new Scanner(f);
        
        // Read the number of users and number of queries
        int numU = sc.nextInt();
        int numQ = sc.nextInt();
        
        // create an araylist of sets
        buildArrayList(numU);
        
        // create a new set for each user and add to arraylisy
        // read the number of queries
        for (int i = 0; i < numQ; i++) {
            // read query
            Query q = getQuery(sc);
            
            // execute query
            executeQuery(q);
        }
    }
    
}
