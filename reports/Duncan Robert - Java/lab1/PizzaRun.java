/*
 * PizzaRun.java
 *
 * Version:
 *     $Id: PizzaRun.java,v 1.4 2013/03/13 07:03:41 pzg8794 Exp $
 *
 *
 * Revisions:
 *      $Log: PizzaRun.java,v $
 *      Revision 1.4  2013/03/13 07:03:41  pzg8794
 *      updating
 *
 *      Revision 1.3  2013/03/11 23:58:52  pzg8794
 *      PizzaRun.java
 *
 */


/** 
 * PizzaRun.java is a simple program that calculates the number of pizzas to buy
 * based on the sum of the slices entered on the command line arguments after 
 * the first argument, the price per pizza.
 *
 * @author      Piter Garcia
 */
public class PizzaRun {
   
   /*
    * amount of slices per pizza pie
    */
   private final int SLICE_PER_PIE = 8;



   /**
    * This program takes the price and slides of pizzas as arguments, and it 
    * calculates amount of slices and price needed to for each person as well
    * as calculating  the amount of slices left.
    *
    * @param args command line arguments are being transfered to this method
    * @return calWholepies the number of pizzas needed.
    */
   public int calcWholePies( String[] args) {

          //variables for the price, total price and number of slices needed
	  //and left per person.
          double pricePerPizza = Double.parseDouble(args[0]), totalCost = 0;
          int slicesPerPerson = 0, slicesNeeded = 0, extraSlices = 0;
          int totalAmountOfPizzas = 0;

           // adding the slices of pizza needed per person.
   	   for (int i=1; i< args.length ; i++)  {
                slicesPerPerson = Integer.parseInt(args[i]);
   	        slicesNeeded = slicesNeeded + slicesPerPerson;
   	   }
   	  
	   // total number of pies.
   	   totalAmountOfPizzas = slicesNeeded/SLICE_PER_PIE;


	   extraSlices = slicesNeeded%SLICE_PER_PIE;
           // if no slice is needed then the extra slices are 0.
   	   if( extraSlices != 0) {
   	       totalAmountOfPizzas = totalAmountOfPizzas + 1;
               extraSlices = SLICE_PER_PIE - extraSlices;
	   }

           // total cost for all  pies
   	   totalCost = pricePerPizza * totalAmountOfPizzas;
           
	   // printing the total prize needed for the slices of pizza needed along with
	   // the amount of slice(s) that will be left.
   	   System.out.println("Buy " + totalAmountOfPizzas + " pizzas for " + totalCost);
   	   System.out.println("There will be " + extraSlices + " extra slices.");

    return totalAmountOfPizzas;
   }
   
   
   
   
   /**
    * The main method that calls the function caclWholePies(args) to calculate
    * the number of pizzas to buy based on the sum of the slices.
    *
    * @param args command line arguments, used to obtain the price and the 
    * slices needed.
    */
   public static void main(String[] args) {
         
    	PizzaRun test = new PizzaRun();
	int numberOfPizzas = 0;

    	numberOfPizzas = test.calcWholePies(args); 
	//calculating price, slices left and returning the slices needed .
   }
}
