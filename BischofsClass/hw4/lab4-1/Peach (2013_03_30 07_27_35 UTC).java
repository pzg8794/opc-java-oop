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
import java.io.IOException;


/**
 * This program creates a class Peach that extends Fruit.
 * It implements methods to get total price,color,number
 * weight and generate report for the fruits bought.
 *
 * @author      Sindhu Srinivasan
 * @author      Piter Garcia
 */
class Peach extends Fruit {
	public static String color;
	public int number;
	public double priceEach;
  
	
	
	public Peach(String color,int number,double priceEach) {
		super(color);
		this.number = number;
		this.priceEach = priceEach;   
	}
 
	public int getNumber(int number){
		return number;
	}
 
	public double getPrice(double priceEach){
		return priceEach;
	}
 
	public double totalPrice( ){
		return number * priceEach;
	}

	public String report(){
		return getColor(color);
	}


	
	//Display the Peach information
	public void displayPeachInfo(Peach peachObj){
		peachObj.getColor(color);
		System.out.println("The peach color is: "+color);
		peachObj.getPrice(number);  
		System.out.println("The cost per peach is: $"+priceEach);
		System.out.println("The total cost of peaches purchsed is:  $"+ peachObj.totalPrice());
		System.out.println("I bought "+peachObj.report()+" peaches.");
	}


   /**
   * Gets user input 
   * @param    peachObj   Object of class Peach
   */
	public static void main(String args[])throws IOException{
		int number1;
		double priceEach1;
		String fruitName1, numberInput, priceEachInput, color1;
  
		System.out.println("Enter the fruit to be purchased");
		BufferedReader bufferreader= new BufferedReader(new InputStreamReader(System.in));
		fruitName1 = bufferreader.readLine();
  
		if (( fruitName1.equals("Peaches")) || ( fruitName1.equals("peaches")) ||
				( fruitName1.equals("Peach")) || ( fruitName1.equals("peach"))) {
  
			BufferedReader peachReader= new BufferedReader(new InputStreamReader(System.in));
   
			System.out.println("Enter the color of peaches to be purchased");
			color1 = peachReader.readLine();
     
			System.out.println("Enter the number of peaches to be purchased");
			numberInput = peachReader.readLine();
			number1 = Integer.parseInt(numberInput);
     
			System.out.println("Enter the price of peach per unit");
			priceEachInput = peachReader.readLine();
			priceEach1 = Double.parseDouble(priceEachInput);
      
			Peach peachObj1 = new Peach(color1,number1,priceEach1);
			peachObj1.displayPeachInfo(peachObj1);
		}
	}
  }
