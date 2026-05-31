/*
 *  Created:      09/21/2012
 *  Last Changed: 09/24/2012
 *  
 *  Person.java 
 * 
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */



/**
 * This program is an interface that will provide other classes with functions 
 * for them to get the color, price and weight of its fruit. Moreover, these 
 * functions are abstract functions that will be the important used for any 
 * class of a fruit.
 * @author Piter Garcia
 * @author Sidhu Srinivasn
 */
 public interface Fruit {
	
	public	java.lang.String	getColor();
    // Return the color of the fruit.
	 
	abstract java.lang.String	report();
    // Return a string containing the color of the fruit: "I bought ."
	 
	abstract public double	totalPrice(); 
    //Return the total price of fruits purchased.
}
