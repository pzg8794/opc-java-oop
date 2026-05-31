/*
 * InsertionSort.java
 * 
 * Version:
 * $Id: InsertionSort.java,v 1.2 2013/06/04 16:59:31 pzg8794 Exp $
 *
 * Revisions:
 * $Log: InsertionSort.java,v $
 * Revision 1.2  2013/06/04 16:59:31  pzg8794
 * Sorting algorithm
 *
 */

import java.util.ArrayList;

/**
 * A class that sorts numbers, using the insertion sort algorithm.
 * @author piter garcia
 *
 */
public class InsertionSort {

	/**
	 * array list of integers sorted.
	 */
	private static ArrayList<Integer> copy;
	
	
	/**
	 * Default constructor;
	 */
	public InsertionSort(){
		
	}
	
	
	
	/**
	 * Sort the array list a.
	 * @param a
	 */
	public static void	insertionSort(ArrayList<Integer> a) {

		copy = new ArrayList<Integer>(a.size());

		while(!a.isEmpty()){
			int loc = getMin(a);
			copy.add(a.get(loc));
			a.remove(loc);
		}
		//        System.out.println(copy);	
	}
    
	
	/**
	 * Obtaining the smallest element in the array.
	 * @param a, array of elements
	 * @return loc, the location of the smallest element.
	 */
	public static int getMin(ArrayList<Integer> a){
		int min = a.get(0);
		int loc = 0;
		
		for( int i = 0; i< a.size(); i++){
			
			if( min < a.get(i)){
				
			}else{
				min = a.get(i);
				loc = i;
			}
		}
		return loc;
	}
	
	
	
	
	/**
	 * The main method.
	 * @param args
	 */
	public static void	main(String[] args) {
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for( String l: args)
			list.add(Integer.parseInt(l));
		
		insertionSort(list);
		System.out.println(StringFromArrayList(copy));
		
	}
    
	
	/**
	 * Convert the array list a into a string that represents the array list.
	 * @param a, arrayList of integers.
	 * @return a string representation of the arrayList.
	 */
	public static String	StringFromArrayList(ArrayList<Integer> a){
	
		return a.toString();
	}
}
