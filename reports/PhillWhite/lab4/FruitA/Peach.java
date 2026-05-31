/*
 * Peach.java
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
 * This program creates a class Peach that extends Fruit.
 * It implements methods to get total price,color,number
 * weight and generate report for the fruits bought.
 *
 * @author      Sindhu Srinivasan
 * @author      Piter Garcia
 */
class Peach extends Fruit {
	private int number;
	private double priceEach;
  
	
	
	public Peach(String color,int number,double priceEach) {
		super(color);
		this.number = number;
		this.priceEach = priceEach;   
	}
 
	public Peach() {
		// TODO Auto-generated constructor stub
	}


	
	
	public int getNumber(){
		return number;
	}
 
	
	
	
	public double getPrice(double priceEach){
		return priceEach;
	}
 
	
	
	 
	public double totalPrice( ){
		return number * priceEach;
	}

	public String report(){
		return "I bought "+ getColor() + " peaches.";
	}


	
	//Display the Peach information
	public void displayPeachInfo(Peach peachObj){
		
		System.out.println("\nThe peach(es) color is: "+ peachObj.getColor());
		peachObj.getPrice(number);  
		System.out.println("The cost per peach is: $"+priceEach);
		System.out.println("The total cost of the peach(es) purchsed is:  $"+ peachObj.totalPrice());
		System.out.println("\n" + report());
	}


   /**
   * Gets user input 
   * @param    peachObj   Object of class Peach
   */
	public static void main(String args[])throws IOException{

		Peach test = new Peach();
		test.gettingUserInput();
	}
	
	
	
	private void gettingUserInput(){
		int number1;
		double priceEach1;
		String fruitName1, numberInput, priceEachInput, color1;
		
		System.out.println("Enter the fruit to be purchased");
		Scanner peachReader = new Scanner(System.in);
		fruitName1 = peachReader.nextLine();
  
		if (( fruitName1.equals("Peaches")) || ( fruitName1.equals("peaches")) ||
				( fruitName1.equals("Peach")) || ( fruitName1.equals("peach"))) {
  
			System.out.println("Enter the color of the peach(es) to be purchased");
			color1 = peachReader.nextLine();
     
			System.out.println("Enter the number of peach(es) to be purchased");
			numberInput = peachReader.nextLine();
			number1 = Integer.parseInt(numberInput);
     
			System.out.println("Enter the price of each peach per unit");
			priceEachInput = peachReader.nextLine();
			priceEach1 = Double.parseDouble(priceEachInput);
      
			Peach peachObj1 = new Peach(color1,number1,priceEach1);
			peachObj1.displayPeachInfo(peachObj1);
		}
		else{
			System.out.println("Wron name, Please Enter the Peach(es) or peach(es)");
			gettingUserInput();
		}
	}
  }
