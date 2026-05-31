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
 * This program creates a class called HashCode.
 * Java's hashSet has been implemented using linked list.
 * We can add elements, clear the linked list, check for specific elements
 * remove specified elements,check if list is empty, retreive the size of the list
 * 
 * @author Piter Garcia
 * @author Sindhu Srinivasan
 */
public class HashCode{
 
	protected static Node head;			// reference to the head node
	private static int listCount;

	
	public HashCode(){				  		  // HashCode constructor
 
		  // this is an empty list, so the reference to the head node
								 // is set to a new node with no data
		head = new Node(null);
		listCount = 0;
	}

		
	public void clearList(){				   // Clears the HashCode

		  // this is an empty list, so the reference to the head node
								 // is set to a new node with no data
		head = new Node(null);
		listCount = 0;
	}

	// Adds objects to the linked list.
	public boolean adds(Object data){ 
	   // post: appends the specified element to the end of this list
	
		Node temp = new Node(data);
		Node current = head;
		   // starting at the head node, crawl to the end of the list
		if(get(data) == -1){
			while(current.getNext() != null){
				current = current.getNext();
			}
			  // the last node's "next" reference set to our new node
			current.setNext(temp);
			 listCount++;// increment the number of elements variable
			 return true;
		}
		else{
			return false;
		}
	}

	    // Inserts the specified element at the specified position in this list
	public void add(Object data, int index){
		index = Math.abs(data.hashCode() % 10);
		Node temp = new Node(data);
		Node current = head;
		
				// crawl to the requested index or the last element in the list,
				// whichever comes first
		for(int i = 0; i < index && current.getNext() != null; i++){

			current = current.getNext();
		}
	// set the new node's next-node reference to this node's next-node reference
		temp.setNext(current.getNext());
					  // now set this node's next-node reference to the new node
		current.setNext(temp);
		listCount++;				// increment the number of elements variable
	}

					//Removes the element at the specified position in this list
	public boolean removes(Object obj){
										   // if the index is out of range, exit
		if(get(obj) == -1 ){
			return false;
		}
		else{
			removes(get(obj));
			return true;
		}
	}				 //listCount--; // decrement the number of elements variable
  
				   // Returns the element at the specified position in this list
	public static int get(Object obj){

		Node current = head.getNext();
		for(int i = 0; i < listCount; i++){
			//System.out.println(current.getData());
			if(current.getData() == obj)
				return i;

			current = current.getNext();
		}
		return -1;
	}


					// Returns the element at the specified position in this list
	public Object get(int index){
													 // index must be 1 or higher
		if(index <= 0)
			return null;

		Node current = head.getNext();
		for(int i = 1; i < index; i++){
			if(current.getNext() == null)
				return null;

			current = current.getNext();
		}
		return current.getData();
	}

					//  Removes the element at the specified position in this list.
	public static boolean remove(int index){
		// if the index is out of range, exit
		if(index < 0 || index > listSize())
			return false;

		Node current = head;
		for(int i = 0; i < index; i++){

			if(current.getNext() == null)
				return false;

			current = current.getNext();
		}
		current.setNext(current.getNext().getNext());
		listCount--; // decrement the number of elements variable
		return true;
	}



					// Returns the number of elements in this list
	public static int listSize(){
		return listCount;
	}

	public String toString(){
		Node current = head.getNext();
		String output = "";
		while(current != null){
			output += "[" + current.getData().toString() + "]";
			current = current.getNext();
		}
		return output;
	}

	
	
	class Node{
						// reference to the next node in the chain,
									  // or null if there isn't one
		Node next;
		// data carried by this node, could be of any type you need
		Object data;

												
		public Node(Object _data){				// Node constructor
			next = null;
			data = _data;
		}

						  // another Node constructor if we want to
									// specify the node to point to
		public Node(Object _data, Node _next){
			next = _next;
			data = _data;
		}

						// these methods should be self-explanatory
		public Object getData(){
			return data;
		}

		public void setData(Object _data){
			data = _data;
		}

		public Node getNext(){
			return next;
		}

		public void setNext(Node _next){
			next = _next;
		}
 	}
  

 	public static void main( String args[] ){

 	}
}