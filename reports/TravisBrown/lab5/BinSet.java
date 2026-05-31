// Comments


import java.util.*;

/**
 * An implementation of the Set collection that uses binary search for fast
 * contains operations.
 * @author piter garcia
 *
 * @param <E>, extends java.util.AbstractSet<E>
 */
public class BinSet<E extends Comparable<? super E>> extends AbstractSet<E> {

	/*
	 * arrayList to simulate tree
	 */
	private ArrayList<E> s;




	/**
	 * Construct an empty BinSet.
	 */
	public BinSet(){
		s = new ArrayList<E>();
	}





	/**
	 * Construct a BinSet from the supplied collection.
	 * 
	 * @param c, The collection of elements used to build the set.
	 */
	public BinSet(Collection<? extends E> c){

		addAll(c);
	}




	/**
	 * Adds the specified element to this set if it is not already present.
	 * 
	 * @param e, element to be added to this set
	 * @return true, if this set did not already contain the specified element
	 */
	private int binarySearch(E e){

		int left = 0;
		int right  = s.size();
	
		while (left < right) {
			  
			// Compute mid point.
			int mid = (left + right) / 2;  
			
			if (e.compareTo(s.get(mid)) < 0) {
				
				// repeat search in bottom half.
				right = mid;       
				
			} else if (e.compareTo(s.get(mid)) > 0) {
				
				// Repeat search in top half.
			    left = mid + 1; 
			    
			} else {
				
				// Found it. return position
			    return mid;       
			}
		}
		
		// Failed to find key
		return - 1; 
	}



	

	/**
	 * Adds the specified element to this set if it is not already present.
	 * 
	 * @param e, element to be added to this set
	 */
	public boolean add(E e) {

		if(!contains(e)){
			
			s.add(e);
			for( int j = 0; j< s.size(); j++){
				if( s.get(j).compareTo(e) > 0)
					swap(j, s.size()-1);
			}

			return true;
			
		}
			
		return false;
	}

	
	
	
	
//	/**
//	 * sorts array list from lowest to highest 
//	 */
//	private void sort(){
//		for( int i = s.size() -1; i>1; i--){
//			for( int j = 0; j<i; j++){
//				if( s.get(j).compareTo(s.get(i)) > 0)
//					swap(j, j+1);
//			}
//		}
//	}


	
	
	/**
	 * Swaps the position of each element to keep a ascending order.
	 * From lowest to highest.
	 * 
	 * @param first, index of the first element, lower
	 * @param second, index of the second element, highest
	 */
	private void swap(int first, int second) {
		E temp = s.get(first);
		s.set(first, s.get(second));
		s.set(second, temp);
	}





	/**
	 * Adds all of the elements in the specified collection to this set if 
	 * they're not already present.
	 * 
	 * @param c, collection containing elements to be added to this set
	 */
	public boolean addAll(Collection<? extends E> c) {
		boolean changed = false;
		s = new ArrayList<E>();
		
		Iterator<? extends E> itr = c.iterator();
		while(itr.hasNext()){
			E temp = itr.next();
		
			if(!contains(temp)){
				add(temp);
				changed = true;
			}
			else
				changed = false;
		}

		return changed;
	}




	/**
	 * Removes all of the elements from this set.
	 * 
	 */
	public void clear() {
		
		s.clear();
	}




	/**
	 * Returns true if this set contains the specified element.
	 * 
	 * @param o, element whose presence in this set is to be tested
	 */
	public boolean contains(Object o) {

		@SuppressWarnings("unchecked")
		int loc = ( binarySearch((E)o));
		
		if(loc < 0)
			return false;
		
		return true;
	}




	/**
	 * Returns true if this set contains all of the elements of the
	 *  specified collection.
	 *  
	 *  @param c, collection to be checked for containment in this set
	 */
	public boolean containsAll(Collection<?> c) {

		Iterator<?> itr2 = c.iterator();

		while(itr2.hasNext()){

			if(!contains(itr2.next()))
				return false;
		}
		return true;
	}




	/**
	 * Returns true if this set contains no elements.
	 * 
	 * @return true if this set contains no elements
	 */
	public boolean isEmpty() {

		
		return s.isEmpty();
	}




	/**
	 * Returns an iterator over the elements in this set.
	 * 
	 * @return an iterator over the elements in this set
	 */
	public Iterator<E> iterator() {

		return s.iterator();
	}




	/**
	 * Removes the specified element from this set if it is present.
	 * 
	 * @param o, object to be removed from this set, if present
	 * @return true if this set contained the specified element
	 */
	@SuppressWarnings("unchecked")
	public boolean remove(Object o) {
		int loc = 0;

		if( !contains(o)){
			return false;
			
		}else{
			
			loc = binarySearch((E) o);
			s.remove(loc);
		}

		return true;
	}




	/**
	 * Retains only the elements in this set that are contained in the
	 * specified collection.
	 * 
	 * @return true if this set changed as a result of the call
	 */
	public boolean retainAll(Collection<?> c) {
		boolean change = false;
			
		int i = -1;
		while( ++i < s.size()){

			if(!c.contains(s.get(i))){
				change = true;
				s.remove(s.get(i));
			}
		}
			
		return change;
	}




	/**
	 * Returns the number of elements in this set (its cardinality).
	 * 
	 * @return the number of elements in this set (its cardinality)
	 */
	public int size() {
		
		return s.size();
	}





	/**
	 * Returns an array containing all of the elements in this set.
	 * 
	 * @return an array containing all the elements in this set
	 */
	public Object[] toArray() {

		Object[] array = new Object[s.size()];

		for( int i=0; i< s.size() ; i++)
			array[i] = s.get(i);

		return array;
	}





	/**
	 * Returns an array containing all of the elements in this set; the
	 * runtime type of the returned array is that of the specified array.
	 * 
	 * @return a, 
	 */
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {

		return (T[]) toArray();
	}





	/**
	 * Returns a string representation of the set.
	 * 
	 * @return a string representation of the set
	 */
	public String toString(){

		String temp = "[";
		for(int i=0; i<s.size(); i++){

			if(i == 0)
				temp += s.get(i);
			else
				temp += ", " + s.get(i);
		}
		temp += "]";

		return temp;
	}



	/**
	 * Displays a message followed by success or failure indicating
	 * whether or not a particular test was successful.
	 *
	 * @param message The String form of the message.
	 *
	 * @param       b    A boolean indicating whether the test was successful or not.    
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
	private static void testInteger(){
		Set<Integer> set = new BinSet<Integer>(Arrays.asList(1,3));

		resultTest("constructor 1",
				Arrays.equals(set.toArray(), Arrays.asList(1,3).toArray()));

		set.add(2);
		resultTest("add 1",
				Arrays.equals(set.toArray(), Arrays.asList(1,2,3).toArray()));

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

	public  void testString(BinSet<E> set, ArrayList<E> allDays){

		
		System.out.println("Set Before Adding to Binary Tree: \n"+ allDays);
		System.out.println("\nSet After Adding to Binary Tree: \n" + set);
		
		System.out.println("\nTesting clear & size & isEmpty Methods: ");
		set.clear();
		System.out.println("Set After Clearing: \n" + set);
		set.clear();
		System.out.println("Size After Clearing: \n" + set.size());
		System.out.println("Is the set empty?  \n" + set.isEmpty());
		
		System.out.println("\nTesting addAll & size & isEmpty Methods: ");
		System.out.println("Set Before Adding to Binary Tree: \n"+ allDays);
		set.addAll(allDays);
		System.out.println("\nSet After Adding All to Binary Tree: \n" + set);
		System.out.println("Size After AddAll: \n" + set.size());
		System.out.println("Is the set empty?  \n" + set.isEmpty());
		
		System.out.println("\nTesting toArray() & toArray(T[]a) Methods");
		System.out.println("Binary Tree: ");
		int i = -1;
		while(++i != set.toArray().length){
			System.out.println(set.toArray()[i]);
		}
		
		
		System.out.println("\nTesting binarySearch & contain & contailAll Methods");
		System.out.println("Binary Tree: \n" + set);
		System.out.println("Possition of " + allDays.get(4) + " After Searching in Binary Tree \n " + set.binarySearch(allDays.get(4)));
		System.out.println("Does Binary Tree Contain "+  allDays.get(4) + "?\n" + set.contains(allDays.get(4)));
		System.out.println("Does Binary Tree Contain All"+  allDays + "?\n" + set.containsAll(allDays));
		
		System.out.println("\nTesting iterator & remove & retain & retainAll Methods");
		System.out.println("Iterator:");
		Iterator itr = set.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
		System.out.println("Does Retain All of "+  allDays + " Change the Binary Tree?\n" + set.retainAll(allDays));
		System.out.println("\nAfter Removing " + allDays.get(2) + ":");
		set.remove(allDays.get(2));
		System.out.println("Binary Tree: \n"+ set);
		allDays.remove(3);
		allDays.remove(1);
		System.out.println("Does Retain All of "+  allDays + " Change the Binary Tree?\n" + set.retainAll(allDays));
		System.out.println("Current Binary Tree: \n"+ set);
		System.out.println("\nAfter Removing All" + allDays + ":");
		set.removeAll(allDays);
		System.out.println("Binary Tree: \n"+ set);
	}



	/**
	 * The main method for BinSet.  It runs any test scaffolding methods such as testInteger.
	 *
	 * @param args Command line arguments are not used.
	 *
	 */
	public static void main(String[] args){
		testInteger();
		
		//TESTING ALL METHODS WITH STRINGS
		BinSet<String> set = new BinSet<String>();
		System.out.println("\n\nSECOND TEST: \n\nTesting Add Method: ");
		String[] days = {"Friday", "Wednesday", "Monday", "Thursday", "Tuesday"};
		ArrayList<String> allDays = new ArrayList<String>();
		
		for( String day: days){
			allDays.add(day);
			set.add(day);
		}
		set.testString(set, allDays);

		//TESTING ALL METHODS WITH INTEGERS
		BinSet<Integer> set1 = new BinSet<Integer>();
		System.out.println("\n\nTHIRD TEST: \n\nTesting Add Method: ");
		Integer[] days1 = {55, 45 , 65, 95, 25, 5, 15};
		ArrayList<Integer> allNums = new ArrayList<Integer>();
		
		for( Integer day: days1){
			allNums.add(day);
			set1.add(day);
		}
		set1.testString(set1, allNums);
	}

}