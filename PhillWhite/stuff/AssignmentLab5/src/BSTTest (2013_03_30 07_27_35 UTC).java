/*
 *  Created:      11/30/2012
 *  Last Changed: 12/04/2012
 *  
 *  BSTT.java 
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */


import java.util.Arrays;
import java.util.Set;



/**
* This program tests Binary Search Tree Class (BinSet)
* This is used to add the keys of elements added from the user to the Array.
* This keys are added and sorted in a binary search tree.
* 
* Piter Garcia ID = 296009929, PZG8794
* Wander Bravo ID = 110006833, WMB1306

* @author      Piter Garcia
* @author 	   Wander Bravo
*/ 
public class BSTTest {

	 @SuppressWarnings("unused")
	public static void main(String[] arguments) {
		 
	    	System.out.println("Testing BinarySearchTreeSet Class -- \nConstructor");
	    	Set<Integer> set = new BinSet<Integer>(Arrays.asList(1,3));
	    	System.out.println(" -- \nDisplaying");
			System.out.println(set.toString());
		
			System.out.println("\nTesting BinarySearchTreeSet Class -- \nAdding");
			set.add(2);
			System.out.println(" -- \nDisplaying");
			System.out.println(set.toString());
			
			System.out.println("\nTesting BinarySearchTreeSet Class -- \nClear");
			set.clear();
			System.out.println(" -- \nDisplaying");
			System.out.println(set.toString());
			
			System.out.println("\nTesting BinarySearchTreeSet Class -- \nAdding All");
			set.addAll(Arrays.asList(1,2,3));
			System.out.println(" -- \nDisplaying");
			System.out.println(set.toString());
			
			System.out.println("\nTesting BinarySearchTreeSet Class -- \nRemoving");
			set.remove(2);
			System.out.println(" -- \nDisplaying");
			System.out.println(set.toString());
			
			System.out.println("\nTesting BinarySearchTreeSet Class -- \nIterator");
			Integer[] a = {1,3};
			int j = 0;
			for (Integer i : set){
			    j++;
			}
			System.out.println(" -- Displaying");
			System.out.println(set.toString());
			
			System.out.println("Testing BinarySearchTreeSet Class -- RetailAll");
			set.retainAll(Arrays.asList(3,4));
			System.out.println(" -- Displaying");
			System.out.println(set.toString());
		
		 
		 
	 }
}
