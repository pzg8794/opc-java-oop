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
 * This program will get the color, price and weight of a apple.
 * Also, this program will take more than one apple and give you the total price.
 * @ return This class will return details about the apple as well as the total 
 * price of apples bought.
 * @ see double weight, pricePerPound.
 * @author Piter Garcia
 * @author Sidhu Srinivasn
 */
public class Apples extends Fruit{
	double weight;
    double pricePerPound;
    //The Parameters of an Apple.
    
    
    
    /**
	* @Function: Apple(color, weight, pricePerPound)
	*
	* @Description:	it initialize the parameters of an Apple by 
	* assigning to each one the input data.
	*
	* @param String, double, double.
	* 
	*/  
	public Apples(String string, double weight, double pricePerPound) {
		super(string);
		
		this.weight = weight;
		this.pricePerPound = pricePerPound;
	} //Apple constructor to initiate its parameters.
	
	
	
	/**
	* @Function: getColor(), getPrice(), getWeight(), String report(),totalPrice().
	*
	* @Description:	these are functions built to get already assigned to each one
	* of the Apple parameters.
	*
	* @param String, double, double, String, double.
	*/
	double	getPrice() {
		return pricePerPound;
	}//  Return the price per pound of apples.
	
	double	getWeight(){
		return weight;
	}//  Return the weight of apples.
	
	java.lang.String report(){
		return " I bought " + this.color + " Apples ";
	}//  Return a string containing the color of apples: "I bought apples."
	
	double	totalPrice(){
		return weight * pricePerPound;
	} //  Return the total price of apples purchased.

	
	
	/**
	* @Function: displayingApplesPurchased(Apple[])
	*
	* @Description:	it gets the total price of read & green apple(s) purchased.
	* Then, it displays the total price.
	*
	* @param int, double.
	* 
	*/
    static void main( String args[] ){

    }  
}