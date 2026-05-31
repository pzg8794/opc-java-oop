/*
 *  Created:      10/14/2012
 *  Last Changed: 10/14/2012
 *  
 *  PartialLinkedList.java 
 * 
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
import java.util.Iterator;
import java.util.ListIterator;

/**
 * This program creates an interface called PartialLinkedList.
 * It has methods to add object, add object at a particular index position,
 * add in the first position, last position, implement an iterator in ascending 
 * and descending order, get the object in the first and last position
 * iterator that gets an element at specified index in list, remove an object, its first occurence
 * second occurence, convert the list to an array.
 * 
 * @author Piter Garcia
 * @author Sindhu Srinivasan
 */
 public interface PartialLinkedList{
    
    /**
    * Adds an object to the list. the objects is added
    * to the last position in the list.
    * @param  object onject to be added to the list
    * @return boolean returns true or false depending on whether object as been added/not.
    */
    boolean add(Object e);
            
     /**
    * Adds an object to the list. the objects is added
    * to the specified index position in the list.
    * @param  object onject to be added to the list
    * @param  index index at which object is added to the list
    * @return boolean returns true or false depending on whether object as been added/not.
    */
    void add(int index, Object element);
               
   /**
    * Adds an object to the list. the objects is added
    * to the first position in the list.
    * @param  object object to be added to the list
    */
    void addFirst(Object e);
         
         
         
                
   /**
    * Adds an object to the list. the objects is added
    * to the last position in the list.
    * @param  object object to be added to the list
    */
    void addLast(Object e);
            
    
               
   /**
    * Gets the object in the last position in the list 
    * 
    * @return  object object added to the list
    */
    Object getLast();
    
    
    
                
   /**
    * Gets the object in the first position in the list 
    * 
    * @return  object object added to the list
    */
    Object getFirst();
            
    
 /**  
* This method implements the iterator for the list.
* the iterator has the function of traversing 
* the linked list.the descending iterator traverses the list backwards
* 
* @return returns the iterator object
*/ 

    Iterator descendingIterator();
    
    
    
    
 /**  
* This method implements the iterator for the list.
* the iterator has the function of traversing 
* the linked list., and get the object at a specified index
* @param index the index at which the object is to be found
* @return returns the iterator object
*/ 
    Object listIterator(int index);
    
    
    
   /**
  * Get the least recently inserted item in the queue.
  * Does not alter the queue. if the element is the head, the next node
  * is assigned as the head, and the current node is removed.
  * 
  * @param   obj the object of the list is taken as the argument
  * @return  least recently inserted item in the queue.
  * @exception UnderflowException if the queue is empty.
  */
    boolean remove(Object o);
    
    
    
    /**
    * Return and remove the least recently inserted occurence of 
    * an item from the queue.
    * @param the object which has to be removed
    * @return the least recently inserted item in the queue.
    * @exception UnderflowException if the queue is empty.
    */
    boolean removeLastOccurrence(Object o);
    
   
    /**
    * Return and remove the first occurence of 
    * an item from the queue.
    * @param the object which has to be removed
    * @return the least recently inserted item in the queue.
    * @exception UnderflowException if the queue is empty.
    */
    boolean removeFirstOccurrence(Object o);

    
    
    /**
    * Test if the queue is logically empty.
    @return true if empty, false otherwise.
    */
    boolean isEmpty( );

}