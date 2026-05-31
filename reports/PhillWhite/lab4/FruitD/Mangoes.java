/*
 * Mangoes.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */


// Import statements are placed here
import java.io.IOException;
import java.text.DecimalFormat;


/**
 * This program creates a class Mangoes that extends Fruit.
 * It implements methods to get total price,color,number
 * weight and generate report for the fruits bought.
 *
 * @author      Piter Garcia
 */

class Mangoes extends Fruit{
  public  int number;
  public  double priceEach;
  
  public Mangoes(String color,double priceEach, int number) {
   super(color);
   
   this.number = number;
   this.priceEach = priceEach; 
  }
 
  
	/**
	* @Function: getColor(), getNumber(), getPrice(), String report(),totalPrice().
	*
	* @Description:	these are functions built to get already assigned to each one
	* of the Mango parameters.
	*
	* @param String, double, double, String, double.
	*/

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
   * Test class Mangoes for number of mangoes greater than 15.
   * The rest of the errors are tested in TestFruit class.
   *
   * @param    mangoObj   Object of class Mangoes
   */

   public void testMango(Mangoes mangoObj)throws FruitException,IOException{
  
	   mangoObj.getNumber(); 
   //The below block handles exception where no. of mangoes entered is more than 15.
   //An object of FruitException class is thrown
	   if (number > 15)
		   try{
			   {
				   throw new FruitException("The number of mangoes cannot be greater than 15.");
			   }
		   }
	   	   catch(FruitException exc){
	   		   System.out.println(exc.toString());
	   	   }
   }
 
   /**
   * Displays information about the Mangoes class.
   *
   * @param    mangoObj   Object of class Mangoes
   * @return   color      color of Mangoes got
   * @return   totalPrice total cost of Mangoes got.
   * @return   report     generates a report for the purchase
   * 
   */
   public void displayMangoInfo(Mangoes mangoObj, int numOfFruits){

    DecimalFormat df = new DecimalFormat("#.##");
        
    //Displaying total price spent in red apple(s).
	//Displaying total price spent in red apple(s).
        System.out.println("\t MANGO(ES) BOUGHT ");
	System.out.println("Total Amount Spent in " + numOfFruits + " " + mangoObj.getColor() + " Mango(es) is $" 
	+ df.format(mangoObj.getNumber()*mangoObj.getPrice()));
 
	System.out.println("Total Number of Mango(es) is $" + df.format(mangoObj.getNumber()));
	//Displaying total price spent in green apple(s).
	System.out.println("\nPrice of Mango per Unit is $" + df.format(mangoObj.getPrice())); 
	System.out.println("Total Price Spent is $" + df.format(mangoObj.getNumber()*mangoObj.getPrice())); 
	  
   }

}
 
