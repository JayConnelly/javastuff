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
public class Dog extends Animal {
   public Dog(String newName) {
       super();
       name = newName;
       System.out.println("In dog Constructor for " + this.name + " num animals = " +  numberOfAnimals);
   }
}
