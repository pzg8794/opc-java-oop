/*
 *  Created:      10/07/2012
 *  Last Changed: 10/07/2012
 *  
 *  HashSet.java 
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */


/**
 * This program creates a class called HashSet
 * It implements the AnotherHashSet interface, and inherits the class LinkedList
 * This has methods to add an element to the hash set,remove an element
 * It also has methods to check if hash table contains an object, or if it is empty
 * It has method to calculate the size of the hash set as well as resize it.
 * 
 * @author Piter Garcia
 * @author Sindhu Srinivasan
 */

public class HashSet extends HashCode implements AnotherHashSet {
 

	int size= 0;
	int capacity = 10;
	float loadFactor= ((capacity-1)/(capacity+1));
 
	public static void main( String args[] ){
  

	}

 
	   //This method adds an object to the hash table.If the capacity of hash set is 
	//exhausted,it doubles the size.This also returns the capacity of the hash table
 
	public boolean add(Object elt) {
		if(listSize() >= capacity){
			
		 	return reSize(elt);
		}
		else
			return adds(elt);
	}

 
									 //Checks if an object exists in the hash table
	public boolean contains(Object obj) {
  
		return (get(obj) == -1);  
	}
 

 
	public void clear() {								    //Clears the hash table
		this.clearList();
	}

 
 
	public boolean isEmpty() {					//Checks if the hash table is empty
 
		return (size() == 0);
	}

 
 
	public boolean remove(Object obj) {		// Removes the element at the specified 
	 												  //position in this hash table
 
		if(get(obj) == -1 ){				  // if the index is out of range, exit
			return false;
		}
		else{
			remove(get(obj));
			return true;
		}				//listCount--; // decrement the number of elements variable
	}



	public int size() {							 // Getting size of your hash table
  
		return listSize();
	}
	
	public boolean reSize(Object elt) {	  // Resizing array after limit is exceeded
		
		System.out.println("Sorry,but you have exceeded the capacity of your " +
				"HashTable of " + capacity + " objects \n");

		System.out.println("Resizing your HashTable \n");
		capacity = 2*capacity;

		System.out.println("Your capacity is now " + capacity + "\n");
		adds(elt);
		return true;
	}
	
}