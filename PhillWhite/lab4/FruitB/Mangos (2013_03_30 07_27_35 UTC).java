import java.text.DecimalFormat;

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
		private String color;
		private static double priceEach;
		private int number;
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
			return priceEach;
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
	    	 
			//Vector<Apple> purchasedFruit = new Vector<Apple>();  	
	    	Mangos[] mangoes = new Mangos[10];
	    	double price = Math.random() + 1.0;
	    	
	    	for (int i = 0; i < 10; i++){
	    		if(i>=0 && i<5)
	    			mangoes[i] = new Mangos( "Red", 1, price);
	    			//purchasedFruit.add(apple);
	    		else
	    			mangoes[i] = new Mangos( "Green", 1, price);
					//purchasedFruit.add(apple);
	    	}
	    	
	    	//calling displaying function to get total price and then display it.
	    	displayingMangosPurchased(mangoes);
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
	        int i = 0, readMangoes = 0, greenMangoes = 0;
	        double totalPrice = 0, totalReadPrice = 0, totalGreenPrice = 0;
	        
	        //displaying for the amount of red & green apple(s) purchased.
	        while(i != mango.length){
	        	Mangos mango1  = null;
	        	mango1= mango[i];
	        	mango1.report();
	        	
	        	if(mango1.getColor().equals("Red")){
	        		readMangoes++;
	        		totalReadPrice +=  mango1.totalPrice();
	        	}
	        	
	        	if(mango1.getColor().equals("Green")){
	        		greenMangoes++;
	        		totalGreenPrice += mango1.totalPrice();
	        	}
	    	    i++;
	    	 }
	        totalPrice = totalReadPrice + totalGreenPrice;
	        DecimalFormat df = new DecimalFormat("#.##");
	        
	        	System.out.printf(" Total Amoun Spent in " + readMangoes + " read Mango(es)  is $" + df.format(totalReadPrice));
	        	System.out.println(); //Displaying total price spent in red apple(s).
	        	System.out.printf(" Total Amoun Spent in " + greenMangoes + " green Mango(es) is $" + df.format(totalGreenPrice));
	        	System.out.println();
	        	System.out.printf(" Total Amount of Mango(es) is " + (readMangoes+greenMangoes)); 
	        	System.out.println();
	        	//Displaying total price spent in green apple(s).
	        	System.out.printf("\n Price per each Mango is $" + df.format(priceEach));
	        	System.out.println(); 
	        	
	        	System.out.println(" Total Price Spent in Mango(es) is $" + df.format(totalPrice));   
	        	//Displaying total price spent in apple(s).
	    }
	
}
