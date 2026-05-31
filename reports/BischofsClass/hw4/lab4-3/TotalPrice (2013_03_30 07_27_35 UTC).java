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
 * This program will call the both classes Mango and Apple and it will add them in a vector.
 * Then, it will calculate the amount of fruit(s) bought as well as the total price purchased.
 * @author Piter Garcia
 * @author Sidhu Srinivasn
 */
import java.util.Vector;


public abstract class TotalPrice  extends Fruit {
 
	//default constructor for the TotalPrice class.
	public TotalPrice() {
		super(" ");
	}
	

	
	/**
	* @Function: addingFruits(purchasedFruit)
	*
	* @Description:	these are functions built to get already assigned to each one
	* of the Apple parameters.
	*
	* @param String, double, double, String, double.
	*/
	public static int addingFruits(Vector<Fruit>purchasedFruit){
		
		int totalApples=0;
		
		 // First part: create and add fruit objects to a Vector.
        for (int i = 0; i < 10; i++) {
           	purchasedFruit.addElement( new Apples( "red", Math.random() * 10, Math.random() + 1.0 ));
           	totalApples++;
        }
        for (int i = 0; i < 5; i++) {
            purchasedFruit.addElement( new Apples("green", Math.random() * 10, Math.random() + 1.0 ));
            totalApples++;
        }
        for (int i = 0; i < 10; i++) {
            purchasedFruit.addElement( new Mangos("redandgreen", (int) (Math.random() * 10) , Math.random() + 0.5 ));
        }
        
        return totalApples; // returning the amount of apples inputed.
	} //Adding different type of fruits into a vector.
	
	
	
	/**
	* @Function: gettingTotalPriceVector<Fruit>purchasedFruit)
	*
	* @Description:	first calls a function to add mango(s) and apple(s). Then, it counts the amount of
	* mango(s) and apple(s) purchased along with its total price. Finally, it gives the total price 
	* spent in fruits and calls a functio to display it.
	*
	* @param Vector, int, double.
	* 
	*/
	//Getting the total prices of all purchased fruits.
	public static void gettingTotalPrice(Vector<Fruit>purchasedFruit){
		int x = 0;
		int totalApples= addingFruits(purchasedFruit);
		double  totalPriceOfApples=0, totalPriceOfMangos=0, totalPrice= 0;
		 
		//Looping until the vector is empty.
	     while(x != purchasedFruit.size()){
         	
	        	if(x < totalApples){
	        		totalPriceOfApples = totalPriceOfApples + purchasedFruit.get(x).totalPrice();
	        	}
	        	else {
	        		totalPriceOfMangos = totalPriceOfMangos + purchasedFruit.get(x).totalPrice();
	        	}
	           	x++;
	      }
	      totalPrice= totalPriceOfApples + totalPriceOfMangos;
	      
	      //Calling functions to display prices.
	      displayingFruitPrices(totalPriceOfApples, totalPriceOfMangos, totalPrice);
	 }
	
	
	
	//Displaying the total price for the apples, for the mangos, and overall.
	public static void displayingFruitPrices(double totalPriceOfApples, double totalPriceOfMangos, double totalPrice){
	
		System.out.println("\t\t<<Price Table>> \t");
        System.out.printf("    Total Amount Spent in Apples = $" + "%.2f" , totalPriceOfApples);
        System.out.println();
        System.out.printf("    Total Amount Spent in Mangos = $" + "%.2f" , totalPriceOfMangos);
        System.out.println();
        System.out.printf("    Total Amoung Spent in Fruits = $" + "%.2f" , totalPrice);
	}
	
 
	
	/**
	* @Function: The main program.
	*
	* @Description: Creates a vector of type Fruit to hold any type of fruit class. Then 
	* calls a function to get the total price spent in fruits.
	* 
	* @param    args  command line arguments (ignored)
	*/
	public static void main(String args[]) {
		
	    Vector< Fruit > purchasedFruit = new Vector< Fruit >();
        gettingTotalPrice(purchasedFruit);
	}
	
}
