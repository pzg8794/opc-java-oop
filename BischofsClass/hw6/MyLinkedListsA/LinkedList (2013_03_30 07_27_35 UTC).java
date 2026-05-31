/*
 *  Created:      10/14/2012
 *  Last Changed: 10/14/2012
 *  
 *  LinkedList.java 
 * 
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

// Import statements are placed here
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * This program creates a class called LinkedList that implements interface Iterable.
 * It has methods to add object, add object at a particular index position,
 * add in the first position, last position, implement an iterator in ascending 
 * and descending order, get the object in the first and last position
 * iterator that gets an element at specified index in list, remove an object, its first occurence
 * second occurence, convert the list to an array.
 * 
 * @author Piter Garcia
 * @author Sindhu Srinivasan
 */
public class LinkedList implements Iterable{
 
 Node head = null;    //Creates the head node
 Node tail = null;    //Creates the tail node
 Node temp = null;    //Creates a temporary node
 protected int counter = 0;   //initializes an int counter
 LinkedList objArray;        //creates an array of type linked list     
 
 //Creates a constructor for LinkedList class.
 public LinkedList(){
  
 }
 
  /**
  * @Function: size()
  *
  * @Description:  returns the size of the linked list.
  *
  * @param
  */
 public int size(){
  return counter;
 }
 
 public static void main( String args[] )
 {
 
 }
 

 
 /*******************************************************
 *
 *  The Iterator class
 *
 ********************************************************/
 
    /**
    * Get the least recently inserted item in the queue.
    * Does not alter the queue.
    * @return the least recently inserted item in the queue.
    * @exception UnderflowException if the queue is empty.
    */ 
 
 
  /**
  * @Function: addElem()
  * Checks if the index is 0, if yes, the new node that is added
  * becomes the head. else the element is added the index specified.
  * 
  * @param index Designates the index at which object has to be added.
  * @param Object Designates the object which has to be added.
  */
 
 public Object addElem(int index, Object e){
  if(index == 0)
  {
   Node newNode = new Node();
   newNode.elem = e;
   newNode.next = head;
   head = newNode;
   this.counter++;
  }
  else
  {
   temp = head;
   for (int i=0; i<index-1 ; i++)
   {
     temp = temp.next;
   }
   Node newNode = new Node();
   newNode.next = temp.next;
   newNode.elem = e;
   temp.next = newNode;
   this.counter++;
  }

  return e;
 }
 
 
  /**
  * @Function: addFirst2()
  * Adds the object to the first position in the list.
  *
  * @param Object Designates the object which has to be added.
  */
 public void addFirst2( Object elem){
        head = new Node(elem, head);
        this.counter++;
     }
 
  
  /**
  * @Function: addLast2()
  * Adds the object to the last position in the list.
  * If the list is empty, the object becomes the head.
  *
  * @param Object Designates the object which has to be added.
  */
     public void addLast2(Object item)
     {
        if( head == null)
           addFirst2(item);
        else
        {
           Node tmp = head;
           while(tmp.next != null) 
            tmp = tmp.next;

           tmp.next = new Node(item, null);
        }
        counter++;
     }
     
     
  /**
  * @Function: contains()
  * checks if an object is present in the list.
  * it returns true, if the object is present, else false.
  *
  * @param Object Designates the object which has to be added.
  * @return returns true of false depending on whether the object is found/not.
  */
      public boolean contains(Object x)
      {
         for(Object tmp : this)
            if(tmp.equals(x)) return true;

         return false;
      }
     
  /**
  * @Function: sort()
  * checks if an object is present in the list.
  * it returns true, if the object is present, else false.
  *
  * @param Object Designates the object which has to be added.
  * @return returns true of false depending on whether the object is found/not.
  */
       
  public void sort( LinkedList test){
  
     int i = 0;
       
     while( i != test.counter){
      
      System.out.print(" " + test.get(i));
      i++;
     }
  }
  
  
  
   /**
  * Get the least recently inserted item in the queue.
  * Does not alter the queue.
  * @return the least recently inserted item in the queue.
  * @exception UnderflowException if the queue is empty.
  */ 
  public void sort2( LinkedList test){
   int i = test.counter -1;
       while( i != -1 ){
      
      System.out.print(" " + test.get(i));
      i--;
     }
  }
 
  
  
     /**
  * Get the least recently inserted item in the queue.
  * Does not alter the queue.
  * @return the least recently inserted item in the queue.
  * @exception UnderflowException if the queue is empty.
  */ 
  public void sort2(LinkedList test, Comparator c){
   int i = test.counter -1;
       
     while( i != -1){
      
      System.out.print(" " + test.get(i));
      i--;
     }
  }
  
     
  /**
  * @Function: toArray()
  * converts the linked list to an array.
  * @param item Designates the object of the linked list which has to be converted.
  * @return returns the objects converted as an array.
  */    
    public LinkedList toArray(LinkedList item)  
    {
          if( head == null)
             addFirst2(item);
          else
          {
             Node tmp = head;
             while(tmp.next != null) 
              tmp = tmp.next;

             tmp.next = new Node(item, null);
          }
          counter++;
          return item; 
          }
 
     
     public LinkedList reverse(){
         
      LinkedList list = new LinkedList();
         Node tmp = head;
         while(tmp != null){
              
          list.addFirst2( tmp.elem );
             tmp = tmp.next;
         }
         return list;
     }
     
     
     /**
     * @Function: toArray()
     * removes the first occurence of an object in list.
     * @param Designates the object of the linked list which has to be removed.
     * @return the removed item in the queue.
     */     
  public Object rmvFirstOccurrance(Object elem){
     temp = head; //start at the beginning of the list
     Node two = null;

     if(head.elem.equals(elem)){
         head = head.next;
         head.previous = null;
         counter--;
         return elem;
     }
     
    //if the element occurs in the tail of the list
     else if(tail.elem.equals(elem)){
         tail = tail.previous;
         tail.next = null; 
         counter--;
         return elem;
     }

    //while the elem hasn't been found but there is another node
     while(temp != null && !temp.elem.equals(elem)){
     two = temp; //have a reference to the element before the one to remove
     temp = temp.next; //in this method, temp will be the elem to remove
     }

  //if the element wasn't found, return null
     if(temp == null) return null;

     two.next = temp.next;
     Object spare = temp.elem; //return element
     temp = null;
     counter--; //decrement size
     return spare;  
  }
  
  
  
  /**
  * Get the least recently inserted item in the queue.
  * Does not alter the queue. if the element is the head, the next node
  * is assigned as the head, and the current node is removed.
  * 
  * @param   obj the object of the list is taken as the argument
  * @return  least recently inserted item in the queue.
  * @exception UnderflowException if the queue is empty.
  */ 
  public Object rmv(Object obj){
   
   if(head == null) throw new RuntimeException("cannot delete");

   if( head.elem.equals(obj) ){
    head = head.next;
    this.counter--;
    return obj;
   }

   Node cur  = head;
   Node prev = null;

   while(cur != null && !cur.elem.equals(obj) ){
    prev = cur;
    cur = cur.next;
   }

   if(cur == null) return obj;

   //delete cur node
   prev.next = cur.next;
   this.counter--;
   return obj;
  }
 
 
  /**
  * Get the least recently inserted item in the queue.
  * Does not alter the queue. if the element is the head, the next node
  * is assigned as the head, and the current node is removed.
  * 
  * @param   obj the object of the list is taken as the argument
  * @return  least recently inserted item in the queue.
  * @exception UnderflowException if the queue is empty.
  */ 
     public Object rmvFirst(){
       
      Object tmp = getFirst2();
      head = head.next;
      counter--;
      return tmp;
     }
  
     
   /**
  * removes an element in a specified index in the list
  * if the index is 0, the head is removed. the next node is 
  * assigned to the head.
  * 
  * @param  index the index of object in list is taken as the argument
  * @return  element element removed is returned.
  */ 
     public Object rmv(int index){
      assert(index >= 0 && index < counter); //force valid index
      temp = head; //start at the beginning of the list

      if(index == 0){
           Object elem = head.elem;
           head = head.next; 
           counter--;
           return elem;
      }

      else if(index == counter){
           Object elem = tail.elem;
           tail = tail.previous;
           counter--;
           return elem;
      }

      //iterate to the position before the index
      for(int i = 0; i < index-1; i++) temp = temp.next;
      Node two = temp.next;

   //set temp.next to point to the Node next to the Node to be removed
      temp.next = two.next; 
      Object elem = two.elem; //store the element to return
      two = null; //remove the node
      counter--; //decrement size
      return elem; //return the element at that position
   }
     
     
     /**
     * Get the least recently inserted item in the queue.
     * Does not alter the queue.
     * @return the least recently inserted item in the queue.
     */ 
     public Object getFirst2(){
       
      if(head == null) throw new NoSuchElementException();

      return head.elem;
     }
     
     
     
     
  /**
  * Get the least recently inserted item in the queue.
  * Does not alter the queue.
  * @return the least recently inserted item in the queue.
  */ 
     public Object getLast2(){
  if(head == null) throw new NoSuchElementException();
     Node tmp = head;
     while(tmp.next != null) 
      tmp = tmp.next;

       return tmp.elem;
     } 
    
    
     
     ListIterator iterating(Node temp){
      ListIterator itr= new ListIterator();
      itr.element = temp.elem;
      
      return itr;
     }
     
     
     
     
     /*******************************************************
       *
       *  Other get functions
       *
       ********************************************************/
     /**
      * Get the least recently inserted item in the queue.
      * Does not alter the queue.
      * @return the least recently inserted item in the queue.
      */ 
     
     //returns first index of the given elem
     //returns -1 if elem not found
    public int get(Object elem){
     if(!contains(elem)){
      System.out.println("Sorry, but the object " + elem + " is not in the list");
      return -1;
     }
     else 
       return indexOf(elem);
    }

    public int indexOf(Object elem){
     temp = head; //start at the beginning of the list
     int i = 0; //create a counter field that isn't local to a loop

     //while we haven't found the elem we are looking for, keep looking
     for(; !(temp.elem).equals(elem) && temp != null; i++)
      temp = temp.next;
     if(i == size()) return -1; //if the elem wasn't found, return -1
      return i;   //otherwise, return the index
    }
 
   
    /**
     * gets the object at a specified index in list.
     * @param  index the index of the list at which object is ti be removed
     * @return the least recently inserted item in the queue.
     */
    public Object get(int index){
     //forces the index to be valid
    assert (index >= 0 && index < size());

    temp = head; //start at the head of the list
    
    //iterate to the correct node
    for(int i = 0; i < index; i++) temp = temp.next; 
     return temp.elem; //and return the corresponding element
    }
    
/**  
*  The Node class: creates a node with elem that denotes the data for a node
*  and next, which is a link to the next node in the list.
*  the Head denotes the head node
*/ 
public class Node{

 Object elem;
 Node next, previous;
   
 Node( Object item, Node head){
  next = head;
  elem =  item;
 }

 public void setNext(Node temp) {
  next = temp;
    
 }

 public Node(Object data) {
  elem = data;
 }
   
 public Node getNext() {

  return next;
 }
   
 Node() {
  elem = null;
  next = null;
 }
   
 public void clr(){
  elem = null;
  next = null;
 }
   
}

 /**  
* This method implements the iterator for the list.
* the iterator has the function of traversing 
* the linke list.
* @return returns the iterator object
*/ 

  public Iterator iterator(){
     return new ListIterator();
  }

    class ListIterator  implements Iterator{
     private Node nextNode;
     private Object element;
     
    public ListIterator(){
      nextNode = head;
     }
     
     public boolean hasNext(){
      return nextNode != null;
     }
     
     public Object listIterator( int index){
      
      
      return get(index);
     }

     public Object next(){
      if (!hasNext()) throw new NoSuchElementException();
      Object res = nextNode.elem;
      nextNode = nextNode.next;
      return res;
     }
     
     public void remove() {
 
     }
    }

 }
