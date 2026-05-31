import java.text.Collator;
import java.util.Comparator;
import java.util.Random;


import java.util.Comparator;
/*
 *  Created:      10/14/2012
 *  Last Changed: 10/14/2012
 *  
 *  AnotherHashSet.java 
 * 
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */





/**
 * This program creats a class that contains sort functions to sort
 * a linked list according to the value of its objects.
 * 
 * @author Piter Garcia
 * @author Sindhu Srinivasan
 * */
public class PartialCollectionsImp extends PartialLinkedListImp implements PartialCollections {

	

	public static void main( String args[] ){
		
	}
	
	
	
	
    /**
    * Sort the linked list from the head to the top of the list
    * 
    * @return a sorted list
    * @exception UnderflowException if the list is empty.
    */
	public void sort(PartialCollectionsImp  list) {
    	
    	int i = size() -1;
    	
  	  	while( i != -1 ){
  			
  			System.out.print(" " + get(i));
  			i--;
  		}
	}
	
	
	

    /**
    * Sort the linked list according to its value
    * @return a linked list according to its values.
    * @exception UnderflowException if the list is empty.
    */
	public void sort(LinkedList list, Comparator c) {
		 
		 int i = 0;
		 Node current = head;
		 Node previous = null;
		 
		 if (Integer.parseInt(current.elem.toString()) > Integer.parseInt(previous.elem.toString())){ 
			 
			 current = head.next;
			 previous = head;  
		 	}
		 		
		 //while (i <= count)
		 		
			while (current.next != null){ 
				
				if (Integer.parseInt(current.elem.toString()) > Integer.parseInt(current.next.elem.toString())){ 
					previous = current;
					current = current.next;
				}
		 	 }  
		 
			// count--;	
	}
	  
	// part of the sort function above.
	public int compare(String o1, String o2) {
        return Collator.getInstance().compare(o1, o2);
    }
			
				
	



    /**
    * Get the least recently inserted item in the queue.
    * Does not alter the queue.
    * @return the least recently inserted item in the queue.
    * @exception UnderflowException if the queue is empty.
    */
	public void shuffle(LinkedList list) {
		
		int i =list.counter - 1;
		Random randomObj = new Random();
		randomObj.nextInt();
		Object x =  null;
		while(i != 0) {
			
			x = list.get(randomObj.nextInt(i));
			list.addElem(i, x );
			remove(x);			
			i--;	
		
		}
		remove(0);
	}



	//returns the size of the lined list
	public int size() {
		return counter;
	}



	
	public void sort1(LinkedList list) {
		// TODO Auto-generated method stub
		
	}
}