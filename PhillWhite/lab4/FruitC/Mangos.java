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
 * This program will get the color, price and weight of a Mango.
 * Also, this program will take more than one Mango and give you the total price.
 * @ return This class will return details about the mango as well as the total 
 * price of mangos bought.
 * @ see String color, double pricePeach, int number.
 * @author Piter Garcia
 * @author Sidhu Srinivasn
 */
public class Mangos extends Fruit {
	//The Parameters of a Mango.
	private double priceEach;
	private double weight;
	private int number;
	
	
	
	/**
	* @Function: Apple(color, weight, pricePerPound)
	*
	* @Description:	it initialize the parameters of an Apple by 
	* assigning to each one the input data.
	*
	* @param String, double, double.
	* 
	*/    
	Mangos(String color, int number, double priceEach) {
		super(color);

		this.number = number;
		this.priceEach = priceEach;	
	}//Mango constructor to initiate its parameters.
	
	
	
	/**
	* @Function: Mango(color, weight, pricePerPound)
	*
	* @Description:	it initialize the parameters of a Mango by 
	* assigning to each one the input data.
	*
	* @param String, int, double.
	* 
	*/ 
	int	getNumber(){
		return number;
	} //Return the number of mangos.

	double	getPrice() {
		return priceEach;
	}//Return the price per each mango. 
	
	java.lang.String	report() {
		return " I bought " + this.color + " Mangos ";
	}//Return a string containing the color of apples: "I bought mangos."
 
	double	totalPrice(){
		return number*priceEach;
	}// Return the total price of Mangos purchased.
	
	
	
	/**
	* @Function: The main program.
	*
	* @Description: Call functions to get total price of the mango(s) purchased and small
	* details about it.
	* 
	* @param    args  command line arguments (ignored)
	*/
	public static void main( String args[] ) {
	    	  
	}  
}
