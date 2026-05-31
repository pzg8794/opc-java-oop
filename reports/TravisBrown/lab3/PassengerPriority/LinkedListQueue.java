/*
 *  LinkedListQueue.java 
 * 
 * Version:
 *     $Id: LinkedListQueue.java,v 1.1 2013/06/19 02:43:14 pzg8794 Exp $
 *
 * Revisions:
 *     $Log: LinkedListQueue.java,v $
 *     Revision 1.1  2013/06/19 02:43:14  pzg8794
 *     Lab3!
 *
 */

import java.util.ArrayList;
import java.util.NoSuchElementException;


/**
 * This program creates a class called LinkedListQueue that implements the
 * interface PriorityQueue to add elements, Passengers in this case, to the
 * head of the list according to its priority.
 * 
 * @author Piter Garcia
 */
public class LinkedListQueue<T extends Comparable<T> > implements PriorityQueue<T> {


	/*
	 * Creates the head node
	 */
	private Node head = null;  

	/*
	 * Creates the tail node
	 */
	private Node tail = null;

	/*
	 * Creates a temporary node
	 */
	private Node temp = null;    	

	/*
	 * initializes an int counter
	 */
	private int counter = 0;  

	/**
	 * Inner class use to create a node for each element added into a list
	 * with reference to a particular data.
	 * @author piter garcia
	 *
	 */
	public class Node{

		/*
		 * element variable access.
		 */
		private T elem;

		/*
		 * next and previous node variable access.
		 */
		private Node next, previous;



		/**
		 * 
		 * @param item, element to be added into the node.
		 * @param head, node created.
		 */
		public Node( T item, Node head){
			next = head;
			elem =  item;
		}



		/**
		 * sets the data of a node.
		 * @param data, info to store.
		 */
		public Node(T data) {
			elem = data;
		}



		/**
		 * default node constructor
		 */
		public Node() {
			elem = null;
			next = null;
		}



		/**
		 * clears node and data.
		 */
		public void clear(){
			elem = null;
			next = null;
		}

	}



	/**
	 * Default constructor for the queue.
	 */
	public LinkedListQueue(){

	}



	/**
	 * Copy an entire array list to a new list.
	 */
	public LinkedListQueue(ArrayList<T> test) {
		
		for( T t: test)
			this.insert(t);
	}



	/**
	 * All methods are being tested in the main method.
	 * @param args, ignored
	 */
	public static void main( String args[] ){


		LinkedListQueue<Passenger> list = new LinkedListQueue<Passenger>();
		list.insert(new Passenger("Piter", 'A', 45));
		list.insert(new Passenger("Tom", 'B', 35));
		list.insert(new Passenger("Juan", 'C', 25));
		list.insert(new Passenger("Pablo", 'A', 15));
		list.insert(new Passenger("Jason", 'B', 5));
		list.insert(new Passenger("Luis", 'C', 45));
		list.insert(new Passenger("Carlos", 'A', 35));

		list.print(list);

		list.remove(new Passenger("Juan", 'C', 25));

		System.out.println(list.size());
		list.print(list);

		System.out.println("\n" + list.peek() + "\n");

		list.enqueue(new Passenger("FIRST", 'A', 35));
		list.print(list);

		System.out.println("\n" + list.indexOf(new 
				Passenger("Pablo", 'A', 15)));
		System.out.println("\n" + list.getLast());
		System.out.println("\n" + list.dequeue() + "\n");

		list.print(list);
		System.out.println("\n" + list.remove(3) + "\n");
		list.print(list);

		list.addLast(new Passenger("FIRST", 'A', 35));
		list.print(list);

		list.print(list);

		LinkedListQueue<Passenger> list1 = new LinkedListQueue<Passenger>();
		list1.insert(new Passenger("Piter", 'A', 45));
		list1.insert(new Passenger("Tom", 'B', 35));
		list1.insert(new Passenger("Juan", 'C', 25));
		list1.insert(new Passenger("Pablo", 'A', 15));
		list1.insert(new Passenger("Jason", 'B', 5));
		list1.insert(new Passenger("Luis", 'C', 45));
		list1.insert(new Passenger("Carlos", 'A', 35));

		list1.print(list1);
	}



	/**
	 * Adds an element to the list, in no special order.
	 * 
	 * @return true if element was added, false if not.
	 * @exception UnderflowException if the queue is empty.
	 */
	public boolean insert(T elem){
		Node prev = null;
		Node current = this.head;
		while (current != null && (current.elem.compareTo(elem)==-1)){
			prev = current;
			current = current.next;
		}

		Node temp = new Node(elem);
		if (prev == null){
			temp.next = this.head;
			this.head = temp;
		}
		else{
			temp.next = current;
			prev.next = temp;
		} 

		this.counter++;
		return true;
	}



	/**
	 * @Function: addLast()
	 * Adds the element to the last position of the list.
	 * If the list is empty it becomes the head.
	 *
	 * @param T, element to be added in the list.
	 */
	public void addLast(T item){
		if( head == null)
			enqueue(item);
		else{
			Node tmp = head;
			while(tmp.next != null) 
				tmp = tmp.next;

			tmp.next = new Node(item, null);
		}
		counter++;
	}



	/**
	 * @Function: toArray()
	 * converts the linked list to an array.
	 * @param item, Queue to convert to an array.
	 * @return returns T[], ArrayList of T elements.
	 */    
	public ArrayList<T> toArray(LinkedListQueue<T> item)  {
		ArrayList<T> array = new ArrayList<T>(item.size());

		Node current = this.head;
		while (current != null){
			array.add(current.elem);
			current = current.next;
		}
		return array; 
	}



	/**
	 * Get the least recently inserted item in the queue.
	 *  
	 * @param   obj, element to be removed.
	 * @return  T, element removed from the queue.
	 * @exception UnderflowException if the queue is empty.
	 */ 
	public T remove(T obj){

		if(head == null) 
			throw new RuntimeException("Queue is empty");

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
	 * removes first element in the queue
	 * @return remove(this.peek()), returns removed element.
	 */
	public T pop(){
		return remove(this.peek());
	}


	
	/**
	 * Gets the last element in the queue. Does not alter the queue.
	 * 
	 * @return the last element in the queue.
	 */ 
	public T getLast(){
		if(head == null)
			throw new NoSuchElementException();

		Node tmp = head;
		while(tmp.next != null) 
			tmp = tmp.next;

		return tmp.elem;
	} 



	/**
	 * Returns the index of the input element.
	 * 
	 * @param elem, the element provided
	 * @return i, index location of the input element in the list.
	 */
	public int indexOf(T elem){

		if(!contains(elem)){
			System.out.println("There is No Such Element");
			return -1;
		}

		temp = head; 
		int i = 0; 

		for(; !(temp.elem).equals(elem) && temp != null; i++)
			temp = temp.next;

		if(i == size()) return -1; 

		return i;   
	}



	/**
	 * removes an element in a specified index in the list
	 * if the index is 0, the head is removed and shifted to next node.
	 * 
	 * @param  index, index of the element to remove.
	 * @return  element, element removed.
	 */ 
	public T remove(int index){
		temp = head; //start at the beginning of the list

		if(index == 0){
			T elem = head.elem;
			head = head.next; 
			counter--;
			return elem;
		}
		else if(index == counter){
			T elem = tail.elem;
			tail = tail.previous;
			counter--;
			return elem;
		}

		//iterate to the position before the index
		for(int i = 0; i < index-1; i++) temp = temp.next;
		Node two = temp.next;

		//set temp.next to point to the Node next to the Node to be removed
		temp.next = two.next; 
		T elem = two.elem; //store the element to return
		two = null; //remove the node
		counter--; //decrement size
		return elem; //return the element at that position
	}



	/**
	 * Returns the size of the queue.
	 */
	public int size(){
		return counter;
	}



	/**
	 * checks if the element x is in the queue.
	 *
	 * @param T, element to be checked. 
	 * @return returns true/false whether x is found/not.
	 */
	public boolean contains(T x){
		Node current = this.head;
		while (current != null){
			if(current.elem.compareTo(x)== 0)
				return true;

			current = current.next;
		}
		return false;
	}



	/**
	 * Returns a list of the opposite order to the actual list.
	 * @return list, an opposite ordder list.
	 */
	public LinkedListQueue<T> reverse(){

		LinkedListQueue<T> list = new LinkedListQueue<T>();
		Node tmp = head;
		while(tmp != null){

			list.enqueue( tmp.elem );
			tmp = tmp.next;
		}
		return list;
	}



	/**
	 * Gets the first element of the queue.
	 * Does not alter the queue.
	 * @return head.elem, first element of the queue.
	 */ 
	public T peek(){

		if(head == null) 
			throw new NoSuchElementException();
		return head.elem;
	}



	/**
	 * gets the T at a specified index in list.
	 * @param  index, index of the element to be obtain.
	 * @return T, the element in the input index.
	 */
	public T get(int index){

		//start at the head of the list
		temp = head; 

		//iterate to the correct node
		for(int i = 0; i < index; i++) 
			temp = temp.next; 

		return temp.elem; 
		//and return the corresponding element
	}



	/**
	 * Checks if the list is empty or not.
	 * returns boolean, true if is empty, false if not.
	 */
	@Override
	public boolean isEmpty() {
		return (counter == 0);
	}



	/**
	 * Adds an element according to its priority.
	 *
	 * @param elem, element to be added.
	 */
	@Override
	public void enqueue(T elem) {
		insert(elem);
	}


	/**
	 * Adds an element to the head of the queue.
	 *
	 * @param elem, element to be added.
	 */
	public void addFirst(T elem){
		head = new Node(elem, head);
		this.counter++;
	}



	/**
	 * Removes the first element of the queue.
	 * 
	 * @return  T, element removed.
	 */ 
	@Override
	public T dequeue() {
		if( this.isEmpty())
			return null;

		T tmp = peek();
		head = head.next;
		counter--;
		return tmp;
	}



	/**
	 * Clears the queue.
	 */
	public void clear() {
		head = null;
		tail = null;
		temp = null;
	}



	/**
	 * print the entire queue.
	 * @param list, actual list.
	 */
	public void print(LinkedListQueue<Passenger> list){

		System.out.println("\n");
		Node tmp = head;
		while(tmp.next != null) {
			System.out.println(tmp.elem);
			tmp = tmp.next;
		}
	}


	/**
	 * print the entire queue as a set.
	 * @param list, actual list.
	 */
	public String toString(){
		String temp = "[";
		int i=0;

		while(i != this.size()-1){
			temp += this.get(i)+", ";
			i++;
		}
		temp += this.get(i) + "]";


		return  temp;
	}

}
