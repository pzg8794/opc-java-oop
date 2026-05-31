/*
 * Created:      11/30/2012
 * Last Changed: 12/04/2012
 *  
 * PowerSet.java 
 * 
 * Version: 
 *     $Id$ 
 * 
 * Revisions: 
 *     $Log$ 
 */


import java.util.HashSet;
import java.util.Iterator;

/** 
 * Display the combinations of the letters imput by the user in binary sequence. 
 * 
 * @author      Piter Garcia
 */
public class PowerSet {
	
	
	
	 
	 /**
	 *Takes an Array of String from User, assigns Array of String to a Conversion Function,
	 * and displays Array Strings Combinations.

     * The main program.
     *
     * @param    args    command line arguments {a, b, c}
     */
	 public static void main(String[] args) {
	     
	       HashSet<?>  Combinations= conversion(args); 				//Converting from string to binary, then 
	       System.out.println(Combinations);						//Display Current and Next State Recursively
	     
	   }  
	
	public boolean hasNext() {										// TODO Auto-generated method stub
		return false;
	}
	public Object next() {											// TODO Auto-generated method stub
		return null;
	}
	public void remove() {											// TODO Auto-generated method stub
		
	} 
	 

	/**
	* Gives the combinations of the given set by using a binary counter
	* Example of Combinations -> {a, b, c} = [[], [b], [c], [b, c, a], [a], [b, c], [c, a], [b, a]]
	* @param   String[] set
	* @return HashSet
	*/
	private static HashSet conversion(String[] set) {
	     
	       
	       HashSet CurrentCombination = new HashSet();	//Building a New Set of Combinations [ ].
	       int LengthOfArray = set.length, NextState=0;	//Obtaining the length of the Array of String Assgined.
	       
	       //Obtaining Maximum Number of Combinations Possible
	       int MaximumNumberOfCombinations = (int) Math.pow(2, LengthOfArray); 	
	       	     	       
	       //Loop to Convert elements of the array into Binary, increment and return next combination
	       while( NextState != MaximumNumberOfCombinations) {
	         
	    	     			
	           	    String CurrentString = Integer.toBinaryString(NextState);	
	           	    String CombinationResult= null,  returner = CurrentString;       	    
	           	    int Bits = CurrentString.length();
	           	    
	           	   	    //Converts binary numbers into a string.     	    
	           	    	for (int i = LengthOfArray-1; i >= Bits; i--) {
	           	    		returner = "0" + returner;
	           	    	}CombinationResult = returner;  		   		
	           		         		          		
	           		
	       HashSet<String> innerSet = new HashSet<String>();	//Creating a new hash set of String [].
	       
	       //Checking for 1's to Assign is Respected Character
	       for (int NextBit = CombinationResult.length()-1; NextBit >= 0; NextBit--) {
	           			
	           		if (CombinationResult.charAt(NextBit) == '1'){
	           				innerSet.add(set[NextBit]);			//Finding Current State
	           			}
	        }
	           
	           CurrentCombination.add(innerSet);   				//Saving current state 
	           NextState++;										//Moving to Next State
	       }
	     
	       return CurrentCombination;
	   }
	   
}