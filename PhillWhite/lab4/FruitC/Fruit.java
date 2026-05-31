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
 * This program will get the color, price and weight of a fruit.
 * Also, this program will take more than one fruit and return the total price.
 * Moreover, this is a superclass which will provide other fruit classes with default 
 * abstract functions that will be the important used for any class of a fruit.
 * @ return This class will return details about the fruit(s) as well as the total 
 * price of fruit(s) bought.
 * @ see String color.
 * @author Piter Garcia
 * @author Sidhu Srinivasn
 */
abstract class Fruit {
	String color;
	
	Fruit(java.lang.String color) {
		this.color = color;
	}// Create a new fruit object with the specified color.
	 
	 java.lang.String	getColor() {
		 return this.color;
	 }// Return the color of the fruit.
	 
	abstract java.lang.String	report();
    // Return a string containing the color of the fruit: "I bought ."
	 
	abstract double	totalPrice();
    //Return the total price of fruits purchased.

	
	
	/**
	* @Function: The main program.
	*
	* @Description: Call functions to get total price of the apple(s) purchased and small
	* details about it.
	* 
	* @param    args  command line arguments (ignored)
	*/
    static void main( String args[] ) {

    }  
}
