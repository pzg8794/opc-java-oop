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
import java.io.IOException;



/**
 * This program creates a class Mangos that extends Fruit.
 * It implements methods to get total price,color,number
 * weight and generate report for the fruits bought.
 *
 * @author      Sindhu Srinivasan
 * @author      Piter Garcia
 */
class Mangos extends Fruit {
	public int number;
	public double priceEach;
  
  
	public Mangos(String color,int number,double priceEach) {
		super(color);
		this.number = number;
		this.priceEach = priceEach;   
	}

	
	
	public int getNumber(int number) {
		return number;
	}
 
	public double getPrice(double priceEach) {
		return priceEach;
	}
 
	public double totalPrice( ) {
		return number * priceEach;
	}

	public String report() {
		return getColor(color);
	}


	//Method to display the Mango Information
	public void displayMangoInfo(Mangos mangoObj){
		mangoObj.getColor(color);
		System.out.println("The mango color is: "+color);
		mangoObj.getPrice(number);  
		System.out.println("The cost per mango is: $"+priceEach);
		System.out.println("The total cost of Mangos purchsed is:  $"+ mangoObj.totalPrice());
		System.out.println("I bought "+mangoObj.report()+" Mangos.");
	}



  /**
   * Gets user input 
   * @param    mangoObj   Object of class Mango
   */
	public static void main(String args[])throws IOException{
		int number1;
		double priceEach1;
		String fruitName1, numberInput, priceEachInput, color1;
  
		System.out.println("Enter the fruit to be purchased");
		BufferedReader bufferreader= new BufferedReader(new InputStreamReader(System.in));
		fruitName1 = bufferreader.readLine();

		if (( fruitName1.equals("Mangoes")) || ( fruitName1.equals("mangoes")) || 
				( fruitName1.equals("Mango")) || ( fruitName1.equals("mango"))) {
  
			BufferedReader mangoReader= new BufferedReader(new InputStreamReader(System.in));
   
			System.out.println("Enter the color of mango to be purchased");
			color1 = mangoReader.readLine();
       
			System.out.println("Enter the number of mangoes to be purchased");
			numberInput = mangoReader.readLine();
			number1 = Integer.parseInt(numberInput);
     
			System.out.println("Enter the price of mango per unit");
			priceEachInput = mangoReader.readLine();
			priceEach1 = Double.parseDouble(priceEachInput);
      
			Mangos mangoObj1 = new Mangos(color1,number1,priceEach1);
			mangoObj1.displayMangoInfo(mangoObj1);
		}
	}
  }