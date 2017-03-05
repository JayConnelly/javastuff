/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runningmedianheap;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Jay
 */
public class RunningMedianHeap {

    /**
     * @param args the command line arguments
     */
    
    static final int CAPACITY = 1000;
    static int[] heap = new int[CAPACITY];
    static int size = 0;
    static int next = 1;
    
    public void ensureCapacity() {
        //make certain we have enough capacity
        if (size >= heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
    }
    
    static public boolean hasLeft(int i) {return( i*2+1 < size);};
    static public boolean hasRight(int i) {return( i*2+2 < size);};
    static public boolean hasParent(int i) {return( (i/2 + i%2 - 1) >= 0);};
    
    static public int getLeft(int i) {return( i*2+1);};
    static public int getRight(int i) {return( i*2+2);};
    static public int getParent(int i) {return( i/2 + i%2 - 1);};
    
    static public void swap(int a, int b) {
        System.out.printf(" Swappng item %d at Index %d with item %d at Index %d \n  ", heap[a],a,heap[b],b);
        int temp = heap[b];
        heap[b] = heap[a];
        heap[a] = temp;
        printHeap(size);
    }
    
    static public void add(int num) {
        System.out.printf(" Adding %d at index %d \n",num, size);
        heap[size++] = num;
        printHeap(size);
    }
    
    static void printHeap(int size) {
        System.out.println(" print heap - size = " + size);
        for (int i=0;i<size;i++) {
            System.out.printf(" [%d] ", heap[i]);
        }
        System.out.println("*");
    }
    static public void heapifyUp() {
   
        int index = size - 1;
        while (hasParent(index)) {
            if ( heap[getParent(index)] > heap[index]) {
                swap(getParent(index), index);
                index = getParent(index);   
            } else {
                break;
            }
        }
    }
   static public int getMinChild(int index) {
       // returns the minimum child or parent if no children
       int minChild = -1;
            if (hasLeft(index) && hasRight(index)) {
                minChild = getLeft(index) > getRight(index) ? getLeft(index) : getRight(index);
            } else if (hasLeft(index)) {
                minChild = getLeft(index);
            } else if (hasParent(index)) {
                System.out.printf(" using parent value %d at index %d for average \n", heap[getParent(index)], getParent(index));
                minChild = getMinChild(getParent(index));
            } else {
                System.out.printf(" help - im lost getting minimum child for index  %d  \n", index);
            }
            System.out.println(" minChild returning " + minChild);
            return(minChild);
   } 
    
    static public float getMedian() {
        int midPoint = size / 2;
        int minChild = 0;
        if ( (size % 2) == 1) {
         
            int best = getMinChild(getParent(midPoint));
               System.out.println(" Odd number of elements - returning " + heap[best]);
            return (heap[best]);
        } else {
            // now we are in teh middle either
            //   midpoint + minchi
            minChild = getMinChild(midPoint);   
            System.out.printf(" Midpoint = %d, midPoint value = %d minchild = %d minchildValue = %d \n",
                    midPoint, heap[midPoint], minChild, heap[minChild]);
            return( ((float) heap[midPoint] + heap[minChild]) / 2);
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner sc = new Scanner("6 12 4 5 3 8 7");
        int numEls = sc.nextInt();
        for (int i=0;i<numEls;i++) {
            int nextVal = sc.nextInt();
            add(nextVal);
            printHeap(size);
            heapifyUp();
            //printHeap(size);
            //System.out.println(" ");
            float medianValue = getMedian();
            System.out.printf(" Median Value is %.1f\n\n",medianValue);
        }
            
    }
    
}
