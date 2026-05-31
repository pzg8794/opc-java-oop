/* Mangos.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

//Import statements are placed here
import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;



/**
 * This program creates a class Mangos that extends Fruit.
 * It implements methods to get total price,color,number
 * weight and generate report for the fruits bought.
 *
 * @author      Piter Garcia
 */
 class Mangos extends Fruit {
	private int number;
	private double priceEach;
  
  public void setNumber(int num){
	  
  }
  public void getNumber(int num){
	  
  }
  
  public void setPrice(double num){
	  
  }
  public void getPricer(double num){
	  
  }
	
	public Mangos(String color,int number,double priceEach) throws FruitException, FruitException2 {
		super(color);
		
		
		if (number  > 15 || number< 1){
			throw new FruitException(" The number of apple(s) cannot be negative or greater than 5  ");
		}
		else
			this.number = number;
		
		 if ( priceEach < 0){
			 throw new FruitException2(" The Price Cannot be negative ");
		 }
		 else
			 this.priceEach = priceEach;
	}

	public Mangos() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getNumber() {
		return number;
	}
 
	
	
	public double getPrice() {
		return priceEach;
	}
 
	
	
	public double totalPrice( ) {
		return number * priceEach;
	}
	public String report() {
		return "I bought "+ getColor() + " Mango(es).";
	}


	//Method to display the Mango Information
	public void displayMangoInfo(Mangos mangoObj1){
		
		System.out.println("\nThe mango(es) color is: " + mangoObj1.getColor());	  
		System.out.println("The cost per mango is: $"+ mangoObj1.getPrice());
		System.out.println("The total cost of the Mango(es) purchsed is:  $"+ mangoObj1.totalPrice());
		System.out.println("\n" + report());
	}



  /**
   * Gets user input 
   * @param    mangoObj   Object of class Mango
   */
	public static void main(String args[]) {
		
		Mangos test = new Mangos();
		while(true){
			int x = 0;
			try {
				Thread.sleep(100);
				test.gettingUserInput();
			} catch (FruitException2 e) {
				e.printStackTrace();
				x = 1;
			
			} catch (FruitException e) {
				e.printStackTrace();
				x = 2;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(x == 0)
					break;
			
			}
		}
	}
		
	
	private void gettingUserInput() throws FruitException2, FruitException {
		
		String fruitName1, color1;
		System.out.println("\n Enter the fruit to be purchased");
		Scanner magoReader = new Scanner(System.in);
		fruitName1 = magoReader.nextLine();

		if (( fruitName1.equals("Mangoes")) || ( fruitName1.equals("mangoes")) || 
				( fruitName1.equals("Mango")) || ( fruitName1.equals("mango"))) {
  
			System.out.println("Enter the color of all mango(es) to be purchased");
			color1 = magoReader.nextLine();
			
			System.out.println("Enter the number of mango(es) to be purchased");
			this.number = magoReader.nextInt();
			if (this.number  > 15 || this.number < 1){
				
				throw new FruitException(" The number of mango(es) cannot be negative or greater than 15  ");
			}
					
			
			System.out.println("Enter the price of each mango per unit");
			this.priceEach = magoReader.nextDouble();
			 if ( this.priceEach < 0){
				 throw new FruitException2(" The Price Cannot be negative ");
			 }
			 
			
      		Mangos mangoObj1 = new Mangos(color1, this.number, this.priceEach);
			
			mangoObj1.displayMangoInfo(mangoObj1);
		}
		else{
			System.out.println("Wron name, Please Enter the Mango(es) or mango(es)");
			gettingUserInput();
		}
	}
	
	
	
	   /**
	   * Displays information about the Mangoes class.
	   *
	   * @param    mangoObj1   Object of class Mangoes
	   * @return   color      color of Mangoes got
	   * @return   totalPrice total cost of Mangoes got.
	   * @return   report     generates a report for the purchase
	   * 
	   */
	   public void displayMangoInfo(Mangos mangoObj1, int numOfFruits){

	    DecimalFormat df = new DecimalFormat("#.##");
	        
	    //Displaying total price spent in red apple(s).
		//Displaying total price spent in red apple(s).
	    System.out.println("\t MANGO(ES) BOUGHT ");
		System.out.printf("Total Amount Spent in " + numOfFruits + " " + mangoObj1.getColor() + " Mango(es) is $" + df.format(mangoObj1.getNumber()*mangoObj1.getPrice()));
		System.out.println(); 
		System.out.printf("Total Number of Mango(es) is $" + df.format(mangoObj1.getNumber()));
		System.out.println();
		//Displaying total price spent in green apple(s).
		System.out.printf("\nPrice per Unit is $" + df.format(mangoObj1.getPrice()));
		System.out.println(); 
		System.out.printf("Total Price Spent is $" + df.format(mangoObj1.getNumber()*mangoObj1.getPrice())); 
		  
	   }
		
  }
