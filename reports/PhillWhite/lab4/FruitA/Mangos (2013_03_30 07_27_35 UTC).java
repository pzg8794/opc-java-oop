/*
 * Mangos.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

//Import statements are placed here
import java.io.*;
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
  
  
	
	public Mangos(String color,int number,double priceEach) {
		super(color);
		this.number = number;
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
	public void displayMangoInfo(Mangos mangoObj){
		
		System.out.println("\nThe mango(es) color is: " + mangoObj.getColor());	  
		System.out.println("The cost per mango is: $"+ mangoObj.getPrice());
		System.out.println("The total cost of the Mango(es) purchsed is:  $"+ mangoObj.totalPrice());
		System.out.println("\n" + report());
	}



  /**
   * Gets user input 
   * @param    mangoObj   Object of class Mango
   */
	public static void main(String args[]){
		
		Mangos test = new Mangos();
		test.gettingUserInput();
	
	}
		
	
	private void gettingUserInput(){
		
		int number1;
		double priceEach1;
		String fruitName1, numberInput, priceEachInput, color1;
		
		System.out.println("Enter the fruit to be purchased");
		Scanner magoReader = new Scanner(System.in);
		fruitName1 = magoReader.nextLine();

		if (( fruitName1.equals("Mangoes")) || ( fruitName1.equals("mangoes")) || 
				( fruitName1.equals("Mango")) || ( fruitName1.equals("mango"))) {
  
		
			System.out.println("Enter the color of all mango(es) to be purchased");
			color1 = magoReader.nextLine();
       
			System.out.println("Enter the number of mango(es) to be purchased");
			numberInput = magoReader.nextLine();
			number1 = Integer.parseInt(numberInput);
     
			System.out.println("Enter the price of each mango per unit");
			priceEachInput = magoReader.nextLine();
			priceEach1 = Double.parseDouble(priceEachInput);
      
			Mangos mangoObj1 = new Mangos(color1,number1,priceEach1);
			mangoObj1.displayMangoInfo(mangoObj1);
		}
		else{
			System.out.println("Wron name, Please Enter the Mango(es) or mango(es)");
			gettingUserInput();
		}
	}
		
  }