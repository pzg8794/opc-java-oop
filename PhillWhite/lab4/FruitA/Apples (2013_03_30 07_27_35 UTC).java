/*
 * Apple.java
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
 * This program creates a class Apples that extends Fruit.
 * It implements methods to get total price,color,number
 * weight and generate report for the fruits bought.
 *
 * @author      Piter Garcia
 */

public class Apples extends Fruit{

	private double weight;
	private double pricePerPound;
  
	
	
	public Apples(String color,double weight,double pricePerPound) {
		super(color);
		this.weight = weight;
		this.pricePerPound = pricePerPound;   
	}
  
	public Apples() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	public double getWeight(){
		return weight;
	} 
		
	
	public double getPrice(){
		return pricePerPound;
	}
		
	
	
	public double totalPrice(){
		return weight * pricePerPound;
	}
  
	public String report(){
		return "I bought "+ getColor() + " apples.";
	}

	
	
	//Method to display the Apple Information
	public  void displayApplesInfo(Apples appleObj){ 
		
		System.out.println("\nThe apple(s) color is: "+ appleObj.getColor());
		appleObj.getPrice();  
		System.out.println("The weight of all apples is: "+ weight +" pound.");
		System.out.println("The total cost of the apple(s) purchsed is:  $"+ appleObj.totalPrice());
		System.out.println("\n" + report());
  }

	
	
   /**
   * Gets user input 
   * @param    appleObj   Object of class Apple
   */
  
  public static void main(String args[]) throws IOException {

	  	Apples test = new Apples();
	  	test.gettingUserInput();
  } 
  
  private void gettingUserInput(){
	  
	  String color1;
	  double weight1;
	  double pricePerPound1;
	  String fruitName1;
	  String weightInput1;
	  String priceEachInput1;
	  System.out.println("Enter the fruit to be purchased");
	  Scanner appleReader = new Scanner(System.in);
	  fruitName1 = appleReader.nextLine();
    
	  if (( fruitName1.equals("Apples")) || ( fruitName1.equals("Apple")) ||
    		( fruitName1.equals("apples")) || ( fruitName1.equals("apple"))) {
		 
		  System.out.println("Enter the color of the apple(s) to be purchased");
		  color1 = appleReader.nextLine();
      
		  System.out.println("Enter the weight(in pounds) of all apples to be purchased");
		  weightInput1 = appleReader.nextLine();
		  weight1 = Double.parseDouble(weightInput1);
      
		  System.out.println("Enter the price of each apple per pound");
		  priceEachInput1 = appleReader.nextLine();
		  pricePerPound1 = Double.parseDouble(priceEachInput1);
      
		  Apples appleObj1 = new Apples(color1,weight1,pricePerPound1);
		  appleObj1.displayApplesInfo(appleObj1); 
	  }
	  else{
			System.out.println("Wron name, Please Enter the Apple(s) or apple(s)");
			gettingUserInput();
	  }
  }
}