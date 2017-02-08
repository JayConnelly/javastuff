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
   
   protected String name = "no name";
   private String sound = "Grrr";
   private int weight;
   private boolean hasOwner = false;
   private byte age;
   
   protected static int numberOfAnimals = 0;
   
   static Scanner userInput = new Scanner(System.in);
   
     public void setSound(String s) {
               this.sound = s;
       }

       public String getSound() {
               return "Grrrrr";
       }
       
       public String getName() {
               return name;
       }
   public Animal() {
       numberOfAnimals++;
//       System.out.println("In Animal Constructor for " + name + " num animals = " +  numberOfAnimals);
      

   }
   
    public static void main(String[] args) {
        // TODO code application logic here
        Cat myCat = new Cat("kitty");
        Dog myDog = new Dog("fido");
        Animal[] animals = { myCat, myDog};
        
//        Animal[] animals = new Animal[2];
//        animals[0] = myCat;
//        animals[1] = myDog;
        for (Animal a : animals) {
            System.out.println("XFOR: Animal with name "  + a.getName() + " says " + a.getSound());
        
        }
        
       ArrayList<Animal> al = new ArrayList<>();
       al.add(myCat);
       al.add(myDog);
       Animal b; 
       Iterator itr = al.iterator();
       while (itr.hasNext()) {
            b = (Animal) itr.next();
            System.out.println("ITR: Animal with name "  + b.getName() + " says " + b.getSound());
        }
       
    }  
};
