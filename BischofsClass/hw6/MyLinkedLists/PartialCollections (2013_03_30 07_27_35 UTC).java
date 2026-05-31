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
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * This program creates an interface called PartialColletions.
 * This inteface has the functions to sort a linked list in 
 * different ways, or according to the value of its object.
 * 
 * @author Piter Garcia
 * @author Sindhu Srinivasan
*/
public interface PartialCollections  {
    
	/**
    *sort the linked list from head to tail.
    * @return a sorted list.
    * @exception UnderflowException if the list is empty.
    */
	void sort1(LinkedList list);
    
	
    /**
    * sort the linked list according the vale of its object
    * @return a sorted list according to its values
    * @exception UnderflowException if the list is empty.
    */
	void sort(LinkedList list, Comparator<?> c);
    
	
	
    /**
    * unsorts the linked list, mixing its objects in different 
    * possitions.
    * @return an unsorted list.
    * @exception UnderflowException if the list is empty.
    */
	void shuffle(LinkedList list);
}