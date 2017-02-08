/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animal;

/**
 *
 * @author Jay
 */
// import java.util.Scanner;
import java.util.*;

public class Animal {
   public static final double FAVNUM = 1.6180;
   
   protected String name;
   private int weight;
   private boolean hasOwner = false;
   private byte age;
   
   protected static int numberOfAnimals = 0;
   
   static Scanner userInput = new Scanner(System.in);
   

   
   public Animal() {
       numberOfAnimals++;
       System.out.println("In Animal Constructor for " + this.name + " num animals = " +  numberOfAnimals);
   }
   
    public static void main(String[] args) {
        // TODO code application logic here
        Cat myCat = new Cat("Meow");
        Dog myDog = new Dog("woof");
    }
}
