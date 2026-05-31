/*
 *  Created:      11/30/2012
 *  Last Changed: 12/04/2012
 *  
 *  TestBinSet.java 
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;




/**
* This program creates a Binary Search Tree Class (BinSet)
* This is used to add the keys of elements added from the user to the Array.
* This keys are added and sorted in a binary search tree.
* 
* Piter Garcia ID = 296009929, PZG8794
* Wander Bravo ID = 110006833, WMB1306

* @author      Piter Garcia
* @author 	   Wander Bravo
*/ 
public class TestBinSet {
	static String [] suits = { "CLUBS", "DIMONDS", "HEART", "SPADES"};
	static Ranks[] ranks  = { Ranks.ACE, Ranks.DEUCE, Ranks.THREE, Ranks.FOUR, Ranks.FIVE,
	Ranks.SIX, Ranks.SEVEN, Ranks.EIGHT, Ranks.NINE, Ranks.TEN, Ranks.JACK, Ranks.QUEEN, Ranks.KING };
	static Scanner select = new Scanner(System.in);
	
	   /* unit test
	    * @param	arguments, ignored
	    */
	    @SuppressWarnings({ "unchecked", "rawtypes", "unused" })
		public static void main(String[] arguments) {
	    	
	    	System.out.println("Testing BinarySearchTreeSet Class -- \nConstructor");
	    	Set<Integer> set = new BinSet<Integer>(Arrays.asList(1,3));
	    	System.out.println(" -- \nDisplaying");
			System.out.println(set.toString());
		
			System.out.println("\nTesting BinarySearchTreeSet Class -- \nAdding");
			set.add(2);
			System.out.println(" -- \nDisplaying");
			System.out.println(set.toString());
			
			System.out.println("\nTesting BinarySearchTreeSet Class -- \nClear");
			set.clear();
			System.out.println(" -- \nDisplaying");
			System.out.println(set.toString());
			
			System.out.println("\nTesting BinarySearchTreeSet Class -- \nAdding All");
			set.addAll(Arrays.asList(1,2,3));
			System.out.println(" -- \nDisplaying");
			System.out.println(set.toString());
			
			System.out.println("\nTesting BinarySearchTreeSet Class -- \nRemoving");
			set.remove(2);
			System.out.println(" -- \nDisplaying");
			System.out.println(set.toString());
			
			System.out.println("\nTesting BinarySearchTreeSet Class -- \nIterator");
			Integer[] a = {1,3};
			int j = 0;
			for (Integer i : set){
			    j++;
			}
			System.out.println(" -- Displaying");
			System.out.println(set.toString());
			
			System.out.println("Testing BinarySearchTreeSet Class -- RetailAll");
			set.retainAll(Arrays.asList(3,4));
			System.out.println(" -- Displaying");
			System.out.println(set.toString());
			
			
			
	    	Card c = new Card(ranks[1], suits[2]);
	    	
	       	Random generator = new Random(); 
	    	int r=0,s=0, cards = 0; 
	    	
			
	    	
	    	ArrayList<Card> binSet = new ArrayList<Card>();
			TestBinSet test = new TestBinSet();
			
			
			System.out.println("Please Enter The Amount of Cards You Wish to Enter");
			cards = select.nextInt();
			
			System.out.println("Testing Card Class -- Adding");
	    	for (int i = 0; i <= cards; i++){
	    		
	    		r = generator.nextInt(13);
				s = generator.nextInt(4);
				
	    		binSet.add( new Card(TestBinSet.ranks[r], TestBinSet.suits[s]));
	    	
	    	}
	    	
	    	System.out.println("Testing Card Class -- Iterating");
	    	Iterator itr = binSet.iterator();
	    	while (itr.hasNext()){	
	    	
	    		System.out.println(itr.next().toString());
	    	}
	    	
	    	System.out.println();
	    	System.out.println("Testing BSTS Class -- Constructor");
	    	BinSet binset = new BinSet(binSet);
	    
	    	System.out.println("Testing BSTS Class -- Displaying");
	    	System.out.println(binset.toString());
	    	
	    	
	    	System.out.println();
	    	System.out.println("Testing Comparing Values -- Constructor");
	    	for (int i = 0; i <= cards; i++){
	    		
	    		r = generator.nextInt(13);
				s = generator.nextInt(4);
				
				
				Card test1 = new Card(TestBinSet.ranks[r], TestBinSet.suits[s]);
				Card test11 = new Card(TestBinSet.ranks[r], TestBinSet.suits[s]);	
				
				if( test11.compareTo(test1) < 0){
					System.out.println(test11.toString() + " is smaller than " + test1.toString());
				}
				else if(test11.compareTo(test1) > 0){
					System.out.println(test11.toString() + " is greater than " + test1.toString());
				}
				else{
					
					System.out.println(test11.toString() + " is equal to " + test1.toString());
				}
					
	    	}
	    	
			
			
		
				
	    }
}
