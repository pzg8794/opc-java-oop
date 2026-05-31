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
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;




/**
 * This program creates an interface called AnotherHashSet.
 * It has methods to add, clear, remove an element from a hash table.
 * It also has methods to check if hash table contains an object, or if it is empty.
 * It has method to calculate the size of the has set.
 * 
 * @author Piter Garcia
 * @author Sindhu Srinivasan
 */
public class PartialLinkedListImp  extends LinkedList implements PartialLinkedList {
 
 
 
 
    /**
    * Get the least recently inserted item in the queue.
    * Does not alter the queue.
    * @return the least recently inserted item in the queue.
    * @exception UnderflowException if the queue is empty.
    */
 public static void main( String args[] ){
  
 }
 
 
 
 /**
    * Get the least recently inserted item in the queue.
    * Does not alter the queue.
    * @return the least recently inserted item in the queue.
    * @exception UnderflowException if the queue is empty.
    */
 public Object getLast() {
     
  return getLast2();
 }
   
 
 
 /**
    * Get the least recently inserted item in the queue.
    * Does not alter the queue.
    * @return the least recently inserted item in the queue.
    * @exception UnderflowException if the queue is empty.
    */  
    public Object getFirst() {
       
  return getFirst2();
 }
    
    
    
    /**
    * Get the least recently inserted item in the queue.
    * Does not alter the queue.
    * @return the least recently inserted item in the queue.
    * @exception UnderflowException if the queue is empty.
    */
 public void clear() {
  Node t= new Node();
  t.clr();
 }

 
 
    /**
    * Get the least recently inserted item in the queue.
    * Does not alter the queue.
    * @return the least recently inserted item in the queue.
    * @exception UnderflowException if the queue is empty.
    */
 public void add(int index, Object element) {
  addElem(index, element);
  
 }

 
 
    /**
    * Get the least recently inserted item in the queue.
    * Does not alter the queue.
    * @return the least recently inserted item in the queue.
    * @exception UnderflowException if the queue is empty.
    */
    public Iterator descendingIterator() {
     
     int i = size() -1;
     
      while( i != -1 ){
     
     System.out.print(" " + get(i));
     i--;
    }
  return iterator();
 }
    
    
    /**
    * Get the least recently inserted item in the queue.
    * Does not alter the queue.
    * @return the least recently inserted item in the queue.
    * @exception UnderflowException if the queue is empty.
    */
    public Iterator ascendingIterator() {
     
   int i = 0;
      
    while( i != size()){
     
     System.out.print(" " + get(i));
     i++;
    }
    
  return iterator();
 }
 
 
    /**
    * Get the least recently inserted item in the queue.
    * Does not alter the queue.
     * @return 
    * @return the least recently inserted item in the queue.
    * @exception UnderflowException if the queue is empty.
    */
 public Object listIterator(int index) {
  
  
   //forces the index to be valid
     assert (index >= 0 && index < size());

     temp = head; //start at the head of the list
    
     //iterate to the correct node
     for(int i = 0; i < index; i++) temp = temp.next; 
     
    
     return  temp.elem;  //and return the corresponding element
 }

 

 /**
    * Get the least recently inserted item in the queue.
    * Does not alter the queue.
    * @return the least recently inserted item in the queue.
    * @exception UnderflowException if the queue is empty.
    */
 public boolean removeLastOccurrence(Object o) {
   addLast(o);
   System.out.println(rmv(o));
   return true;
 }

 
    
 /**
    * Get the least recently inserted item in the queue.
    * Does not alter the queue.
    * @return the least recently inserted item in the queue.
    * @exception UnderflowException if the queue is empty.
    */
 public boolean removeFirstOccurrence(Object o) {
  
 System.out.println(rmvFirstOccurrance(o));
 return true;
 
 }

 
 /**
 * Get the least recently inserted item in the queue.
 * Does not alter the queue.
 * @return the least recently inserted item in the queue.
 * @exception UnderflowException if the queue is empty.
 */
 public boolean add(Object elem){
  //if we don't have any elems in our LinkedList
  if (head == null) {
   head = new Node();
   tail = head;
   head.elem = elem;
   head.next = tail;
   counter++;
   return true;
  }
  else {
   tail.next = new Node(); //add a new node to the end of the list
   tail = tail.next; //set the tail pointer to that node
   tail.elem = elem; //set elem to be stored to the end node
   counter++;
   return true;
  }
 }
 
 
 
 /**
    * Get the least recently inserted item in the queue.
    * Does not alter the queue.
    * @return the least recently inserted item in the queue.
    * @exception UnderflowException if the queue is empty.
    */
    public void addFirst(Object e){
          
     addFirst2(e);
    }
    
    
    
    
    /**
    * Get the least recently inserted item in the queue.
    * Does not alter the queue.
    * @return the least recently inserted item in the queue.
    * @exception UnderflowException if the queue is empty.
    */
    public void addLast(Object e){
     
     addLast2(e);
     
    }
    
    
    
    /**
    * Get the least recently inserted item in the queue.
    * Does not alter the queue.
    * @return the least recently inserted item in the queue.
    * @exception UnderflowException if the queue is empty.
    */
 public boolean isEmpty() {
  
  return (size() == 0);
 }

 
 
    /**
    * Get the least recently inserted item in the queue.
    * Does not alter the queue.
    * @return the least recently inserted item in the queue.
    * @exception UnderflowException if the queue is empty.
    */
 public boolean remove(Object o) {
  
  if(contains(o) == true){
   System.out.println(" The object " + " is in the List");
   System.out.println( "The Object " + o + " has been removed: ");
    rmv(o);
    return true;
  }
  else{
   System.out.println("It's not in the List"); 
   return false;
  }
 }

}
