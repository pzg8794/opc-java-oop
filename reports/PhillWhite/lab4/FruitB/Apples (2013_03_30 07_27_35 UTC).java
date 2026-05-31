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
 * This program will get the color, price and weight of a apple.
 * Also, this program will take more than one apple and give you the total price.
 * @ return This class will return details about the apple as well as the total 
 * price of apples bought.
 * @ see double weight, pricePerPound.
 * @author Piter Garcia
 * @author Sidhu Srinivasn
 */
public class Apples implements Fruit{
	
	private String color = " ";
	private double weight;
    private static double pricePerPound;
    
    
    
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
    	double price = Math.random() + 1.0;
    	
    	for (int i = 0; i < 10; i++){
    		if(i>=0 && i<5)
    			apple[i] = new Apples( "Red", Math.random() * 10, price);
    			//purchasedFruit.add(apple);
    		else
    			apple[i] = new Apples( "Green", Math.random() * 10, price);
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
    public static void displayingApplesPurchased(Apples[] apple){
        int i = 0, readApples = 0, greenApples = 0;
        double totalPrice = 0, totalReadPrice = 0, totalGreenPrice = 0;
        double totalRedWeight = 0, totalGreenWeight = 0;
        
        //displaying for the amount of red & green apple(s) purchased.
        while(i != apple.length){
        	Apples apple1  = null;
           	apple1= apple[i];
        	apple1.report();
        	
        	if(apple1.getColor().equals("Red")){
        		readApples++;
        		totalReadPrice +=  apple1.totalPrice();
        		totalRedWeight +=  apple1.weight;
        	}
        	
        	if(apple1.getColor().equals("Green")){
        		greenApples++;
        		totalGreenPrice += apple1.totalPrice();
        		totalGreenWeight +=  apple1.weight;
        	}
    	    i++;
    	 }
        totalPrice = totalReadPrice + totalGreenPrice;
        DecimalFormat df = new DecimalFormat("#.##");
            
        	//Displaying total price spent in red apple(s).
        	System.out.printf(" Total Amoun Spent in " + readApples + " read Apple(s)  is $" + df.format(totalReadPrice));
        	System.out.println(); 
        	System.out.printf(" Total Weight is " + df.format(totalRedWeight)+ " pound(s)");
        	System.out.println();
        	//Displaying total price spent in green apple(s).
        	System.out.printf(" Total Amoun Spent in " + greenApples + " green Apple(s) is $" + df.format(totalGreenPrice));
        	System.out.println();
        	System.out.printf(" Total Weight is " + df.format(totalGreenWeight)+ " pound(s)"); 
        	System.out.println();
        	//Displaying total price spent in green and read apple(s).
		System.out.printf("\n Total Weight is " + df.format(totalRedWeight + totalGreenWeight)+" pound(s)");
        	System.out.printf("\n Price per Pound is $" + df.format(pricePerPound));
        	System.out.println(); 
        	System.out.printf(" Total Price Spent is $" + df.format(totalPrice)+ "\n" );   
    }
}
