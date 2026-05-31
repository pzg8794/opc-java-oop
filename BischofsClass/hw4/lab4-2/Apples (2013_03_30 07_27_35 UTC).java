

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
public class Apples implements Fruit{
	
	java.lang.String color = " ";
	double weight;
    double pricePerPound;
    
    
    
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
		super();
		
		color = string;
		this.weight = weight;
		this.pricePerPound = pricePerPound;
	}//Apples constructor to initiate its parameters.

	
	
	/**
	* @Function: getColor(), getPrice(), getWeight(), String report(),totalPrice().
	*
	* @Description:	these are functions built to get already assigned to each one
	* of the Apples parameters.
	*
	* @param String, double, double, String, double.
	*/
	public String getColor() {
		return color;
	} // Return the color of the fruit.
	
	double getPrice() {
		return pricePerPound;
	}//  Return the price per pound of apples.
	
	double getWeight(){
		return weight;
	}//  Return the weight of apples.
	
	public java.lang.String report(){
		return " I bought " + getColor() + " Apples ";
	}//  Return a string containing the color of apples: "I bought apples."
	
	public double totalPrice(){
		return weight*pricePerPound;
	} //  Return the total price of apple(s) purchased.
	
	
	
	/**
	* @Function: The main program.
	*
	* @Description: Call functions to get total price of the apple(s) purchased and small
	* details about it.
	* 
	* @param    args  command line arguments (ignored)
	*/
   public static void main( String args[] ){
    	
    	//Vector<Apple> purchasedFruit = new Vector<Apple>();  	
    	Apples[] apple  = new Apples[10];
    	
    	for (int i = 0; i < 10; i++){
    		if(i>=0 && i<5)
    			apple[i] = new Apples( "Red", Math.random() * 10, Math.random() + 1.0);
    			//purchasedFruit.add(apple);
    		else
    			apple[i] = new Apples( "Green", Math.random() * 10, Math.random() + 1.0);
				//purchasedFruit.add(apple);
    	}
    	
    	//calling displaying function to get total price and then display it.
    	displayingApplesPurchased(apple);
    }

   
   
	/**
	* @Function: displayingApplesPurchased(Apple[])
	*
	* @Description:	it gets the total price of read & green apple(s) purchased.
	* Then, it displays the total price.
	*
	* @param int, double.
	* 
	*/
    static void displayingApplesPurchased(Apples[] apple){
        int i = 0, readApples = 0, greenApples = 0;
        double totalPrice = 0, totalReadPrice = 0, totalGreenPrice = 0;
        
        //displaying for the amount of red & green apple(s) purchased.
        while(i != apple.length){
        	Apples apple1  = null;
           	apple1= apple[i];
        	apple1.report();
        	
        	if(apple1.getColor().equals("Red")){
        		readApples++;
        		totalReadPrice +=  apple1.totalPrice();
        	}
        	
        	if(apple1.getColor().equals("Green")){
        		greenApples++;
        		totalGreenPrice += apple1.totalPrice();
        	}
    	    i++;
    	 }
        totalPrice = totalReadPrice + totalGreenPrice;
        
        	System.out.printf(" Total Amoun Spent in " + readApples + " read Apple is $" + "%.2f",totalReadPrice);
        	System.out.println(); //Displaying total price spent in red apple(s).
        	System.out.printf(" Total Amoun Spent in " + greenApples + " green Apple is $" + "%.2f",totalGreenPrice);
        	System.out.println(); //Displaying total price spent in green apple(s).
        	System.out.printf(" Total Price Spent is $" +  "%.2f", totalPrice);   
        	//Displaying total price spent in apple(s).
    }
}