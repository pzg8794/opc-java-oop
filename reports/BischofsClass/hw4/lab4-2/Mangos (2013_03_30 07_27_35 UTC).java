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
 * price of mango bought.
 * @ see String color, double pricePeach, int number.
 * @author Piter Garcia
 * @author Sidhu Srinivasn
 */
public class Mangos implements Fruit {
		String color;
		double priceEach;
		int number;
		//The parametters of a Mango.
		

		
		/**
		* @Function: Mango(color, weight, pricePerPound)
		*
		* @Description:	it initialize the parameters of a Mango by 
		* assigning to each one the input data.
		*
		* @param String, int, double.
		* 
		*/   
		Mangos(String color, int number, double priceEach) {
		super();	
			this.number = number;
			this.color = color;
			this.priceEach = priceEach;
		} //Mango constructor to initiate its parameters.


		
		/**
		* @Function: getColor(), getNumber(), getPrice(), String report(),totalPrice().
		*
		* @Description:	these are functions built to get already assigned to each one
		* of the Mango parameters.
		*
		* @param String, double, double, String, double.
		*/
		@Override
		public String getColor() {
			return color;
		} // Create a new fruit object with the specified color.
		
		int	getNumber(){
			return number;
		} //Return the number of mangos.

		double getPrice() {
			return (Math.random() + 1.0);
		} //Return the price per each mango.
 
		public java.lang.String	report() {
			return " I bought " + getColor() +  " Mangos ";
		} //Return a string containing the color of mangos: "I bought mangos."
 
		public double totalPrice(){
			return getNumber()*getPrice();
		} // Return the total price of mango(s) purchased.
		
 
		
		/**
		* @Function: The main program.
		*
		* @Description: Call functions to get total price of the mango(s) purchased and small
		* details about it.
		* 
		* @param    args  command line arguments (ignored)
		*/
		public static void main( String args[] ) {
	    	  
	    	//Creating an array of Mangos .	
	    	Mangos[] mango  = new Mangos[10];
	    	
	    	//Adding Mangos to the array.
	    	for (int i = 0; i < 10; i++)
	    		mango[i] = new Mangos( "RedAndGreen", (int) (Math.random() * 10), Math.random() + 1.0);
	    	
	    	//Calling a function to display the mango prices and total price.
	   	   	displayingMangosPurchased(mango);
	    }
		
		
		
		/**
		* @Function: displayingApplesPurchased(Mango[])
		*
		* @Description:	it gets the total price of read & green mango(s) purchased.
		* Then, it displays the total price.
		*
		* @param String inputFirstName, String inputLastName.
		* 
		*/
	    static void displayingMangosPurchased(Mangos[] mango){
	        int i = 0;
	        double totalPrice = 0;
	        
	      //displaying for the amount of red & green apple(s) purchased.
	        while(i != mango.length){
	        	Mangos mango1  = null;
	           	mango1= mango[i];
	        	mango1.report();
	        	
	        	totalPrice = totalPrice + mango1.totalPrice();
	        	
	       	    i++;
	    	 }
	        System.out.printf(" Total Amoun Spent in " + i + " Mangos is $" + "%.2f", totalPrice);
	        System.out.println();
	        System.out.printf(" Total Price is $" + "%.2f", totalPrice);    
	    }	//Displaying the Mango Prices and total price.
	
}