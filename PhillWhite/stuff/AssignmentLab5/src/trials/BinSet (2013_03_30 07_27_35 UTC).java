/*
 *  Created:      11/30/2012
 *  Last Changed: 12/04/2012
 *  
 *  BinSet.java 
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */
import java.util.*;


/**
* This program creates a Binary Search Tree Class (BinSet)
* This is used to add the keys of elements added from the user to the Array.
* This keys are added and sorted in a binary search tree.
* 
* Piter Garcia ID = 296009929, PZG8794
* Wander Bravo ID = 110006833, WMB1306
* 
* @author  Piter Garcia
* @author  Wander Bravo
*/ 
public class BinSet<E extends Comparable<? super E>> extends AbstractSet<E> {

    private ArrayList<E> s = new ArrayList<E>();

    
    
    
    /** 
    * constructor to build a node with no subtrees
    * @param the value to be stored by this node
    */
    public BinSet(){}

    
    
    
    /** 
    * constructor to build a node with no subtrees
    * @param the value to be stored by this node
    */
    public BinSet(Collection<? extends E> c){
	// YOUR CODE GOES HERE
    	E temp;
    	s.addAll(c);
    	for(int k=0;k<s.size();k++)
    	{
    		int i=0,j=0;
	    	while(j<s.size()-1)
	    	{
		    	if( s.get(i).compareTo( s.get(i+1) )>0 )
		    	{
			    	temp=s.get(i);
			    	s.remove(i);
			    	i++;
			    	s.add(i,temp);
		    	}
		    	j++;
	    	}
    	}    	
    }
    
    
    
    
    
    /**
     * This function search of a key in the binary search tree.
     * It returns the key of where the element is located.
     *
     * @param   returns the key of the object.   
     *
     */
    @SuppressWarnings("unused")
	private int binarySearch(E e){
	// YOUR CODE GOES HERE
    	int lo = 0;
    	int hi = s.size()- 1;
    	int i=0;    	    	
    	
    	while (lo <= hi) {
		// 
		int mid = lo + (hi - lo) / 2;
		if(e.compareTo(s.get(mid))<0){
			hi = mid - 1;
		}
		else if (e.compareTo(s.get(mid))>0){
			lo = mid + 1;
		}
		else{
			System.out.println("TEST MID VALUE:"+mid+"\n");
			return mid;
		}
    	}
    	return -1;
    }

    // comments
    public boolean add(E e) {    	
	// YOUR CODE GOES HERE
    	if(this.binarySearch(e)<0)
    	{
	    	for(int i=0;i<s.size();i++)
	    	{		    	
		    	if(i==0)
		    	{
			    	if(e.compareTo(s.get(i))>0&&e.compareTo(s.get(i+1))<=0)
			    	{		    	
			    		s.add(i+1,e);		    	
			    		return true;
			    	}
		    	}
		    	else
		    	{		    
			    	if(e.compareTo(s.get(i-1))>0&&e.compareTo(s.get(i))<=0)
			    	{			    
				    	s.add(i-1,e);			    
				    	return true;
			    	}
		    	}
	    	}
    	}
    	return false;
    }



    
    
    
    /**
    * This function add the key of an item to a the binary search tree
    *
    * @param  adds the keys (int) to the tree
    *
    */
    public boolean addAll(Collection<? extends E> c) {
	// YOUR CODE GOES HERE
    	int i=0;
    	Iterator<? extends E> itr = (Iterator<? extends E>)c.iterator();
    	while(itr.hasNext())
    	{
    		s.add(itr.next());
    		i=1;
    	}
    	if(i==0)
    		return false;    	
    	return true;
    }



    
    
    
    /**
    *This function is adding a set of items kyes to the binary search tree
    *
    * @param    It returns true if 
    */
    public void clear() {
	// YOUR CODE GOES HERE
    	s.clear();
    }

    
    
    
    
    
    /**
    * This function checks if the key of an object is in the binary search tree
    *
    * @param It returns true if the key is in the binary search tree otherwise
    * it returns false.  
    *
    */
    public boolean contains(Object o) {
	// YOUR CODE GOES HERE
    	return s.contains(o);
    }



    
    /**
    *This function checks if all keys of the given set of objects is in the 
    *binary search tree.
    *
    * @param it returns true if the keys are in the tree and false if even
    * one of the keys is not in the binary search tree
    *
    */
    public boolean containsAll(Collection<?> c) {
	// YOUR CODE GOES HERE
    	Iterator<?> itr = (Iterator<?>)c.iterator();
    	while(itr.hasNext())
    	{
    		if(!this.contains(itr.next()))
    			return false;
    	}
    	return true;
    }


    
    
    
    
    /**
    *This function checks if the binary search tree is empty
    *
    * @param message The String form of the message.
    * 
    */
    public boolean isEmpty() {
	// YOUR CODE GOES HERE
    	return s.isEmpty();
    }
          


    
    
    /**
    *This function iterates through the binary search tree
    *
    * @param it returns an iterator that iterates the tree
    *
    */
    public Iterator<E> iterator() {
	// YOUR CODE GOES HERE
    	Iterator<E> itr = (Iterator<E>) s.iterator();    	
    	return itr;
    }
    


    
    
    
    /**
    * This function deletes a key from the binary search tree
    * 
    * @param it returns true if the key was removed and false
    * if the key was not removed or it is not in the binary 
    * search tree
    *
    */
    @SuppressWarnings("unchecked")
	public boolean remove(Object o) {
	// YOUR CODE GOES HERE    	    
    	if(s.contains(o))
    	{
    		int i =binarySearch((E) o);
    		s.remove(i);
    		return true;
    	}
    	return false;
    }
     


    
    
    
    /**
    * This function intersects the given set with the actual binary search three
    * keys and it modifies the tree by replacing all values for the intersecting
    * values
    *
    * @param  modifies the binary search tree with the intersecting values
    *
    */
	public boolean retainAll(Collection<?> c) {
	// YOUR CODE GOES HERE
    	Iterator<E> itr = (Iterator<E>)this.iterator();    	
    	boolean modified=false;
    	while(itr.hasNext())
    	{    	
    		E obj = (E)itr.next();
    		if(!c.contains(obj))
    		{    			    	
    			remove(obj);    			
    			modified = true;
    		}
    	}	    	
		return modified;    			    	
    }
    



	
	
    /**
    * This function keys track of the size of the binary search tree
    *
    * @param it returns the actual size of the tree 
    *
    */
	public int size() {
	// YOUR CODE GOES HERE
    	return s.size();
    }
     


	
	
	   /**
	    * This function save all the keys in the binary search tree into an array.
	    *
	    * @param it returns an array with the keys of the tree
	    *
	    */
    public Object[] toArray() {
	// YOUR CODE GOES HERE    	
    	return s.toArray();
    }
     
    


    
    
    /**
     * This function adds the keys from the binary search tree to an array a.
     * If the array size is not big enough it resize it by creating a new memory
     * space for the given array, with enough size to save the keys
     * 
     * @param  it returns a copy of the keys in the binary tree.
     *
     */
    @SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
	// YOUR CODE GOES HERE
    	int index = 0;
   	 
    	Iterator<?> itr = this.iterator();
    	 if( ! (a.length >= this.size())){    		 
    		 a = (T[]) new Object[s.size()];
    	 }
    		 
    	while(itr.hasNext()){
    		//
    		Object value = itr.next();
    		a[index] = (T) value;
    		
    		System.out.println(" adding c value " + value);
    		index++;
    	}    	
    	  	
    	return a;
    }



    
    
    
    
    
    /**
    * This function converts the binary search tree ... to a string
    *
    * @param it return a string with all the keys of the tree.  
    *
    */
    public String toString(){
	// YOUR CODE GOES HERE
    	return this.s.toString();
    }
    
    
    
    
    
    

    /**
     * Displays a message followed by success or failure indicating
     * whether or not a particular test was successful.
     *
     * @param message The String form of the message.
     *
     * @param       b    A boolean indicating whether the test was successful 
     *			 or not.    
     *
     */

    private static void resultTest(String message, boolean b){
    	if (b){
    		System.out.println(message + " success");
    	} else {
    		System.out.println(message + " failure");
    	}
    }
    
    
    
    
    
    /**
     * Runs a suite of tests to validate the implemenation of BinSet
     * for Integer elements.
     *
     */

    public static void main(String[] args){
	Set<Integer> set = new BinSet<Integer>(Arrays.asList(1,3));
	
	resultTest("constructor 1",
		   Arrays.equals(set.toArray(), Arrays.asList(1,3).toArray()));
	
	set.add(2);
	resultTest("add 1",
		   Arrays.equals(set.toArray(), 
		   Arrays.asList(1,2,3).toArray()));
	
	resultTest("contains 1", set.contains(1));
	resultTest("contains 2", set.contains(2));
	resultTest("contains 3", set.contains(3));
	resultTest("contains 4", !set.contains(4));
	
	resultTest("size 1", set.size() == 3);
	
	set.clear();
	resultTest("clear/size", set.size() == 0);
	resultTest("clear/isEmpty", set.isEmpty());
	
	set.addAll(Arrays.asList(1,2,3));
	resultTest("addAll 1",set.size() == 3);
	
	resultTest("containsAll 1", set.containsAll(Arrays.asList(3,2)));
	resultTest("containsAll 2", !set.containsAll(Arrays.asList(4,3)));
	
	set.remove(2);
	resultTest("remove 1", 
		   Arrays.equals(set.toArray(), Arrays.asList(1,3).toArray()));
	
	Integer[] a = {1,3};
	int j = 0;
	for (Integer i : set){
	    resultTest("iterator " + i, i.equals(a[j]));
	    j++;
	}
	
	set.retainAll(Arrays.asList(3,4));
	resultTest("retainAll 1", 
		   Arrays.equals(set.toArray(), Arrays.asList(3).toArray()));
	
	resultTest("toArray(array) 1", 
		   Arrays.equals(set.toArray(new Integer[0]), 
				 Arrays.asList(3).toArray()));
    }        
}