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



/**
 * This program creates a class Apples that extends Fruit.
 * It implements methods to get total price,color,number
 * weight and generate report for the fruits bought.
 *
 * @author      Sindhu Srinivasan
 * @author      Piter Garcia
 */

public class Apples extends Fruit{
	public String color;
	public double weight;
	public double pricePerPound;
  
	
	
	public Apples(String color,double weight,double pricePerPound) {
		super(color);
		this.weight = weight;
		this.pricePerPound = pricePerPound;   
	}
  
	public double getWeight(double weight){
		return weight;
	} 
  
	public double getPrice(double pricePerPound){
		return pricePerPound;
	}

	public double totalPrice(){
		return weight * pricePerPound;
	}
  
	public String report(){
		return getColor(color);
	}

	
	
	//Method to display the Apple Information
	public  void displayApplesInfo(Apples appleObj){ 
		appleObj.getColor(color);
		System.out.println("The Apples color is: "+ color);
		appleObj.getPrice(weight);  
		System.out.println("The weight of the Apples is: "+ weight+" pound.");
		System.out.println("The total cost of Apples purchsed is:  $"+ appleObj.totalPrice());
		System.out.println("I bought "+appleObj.report()+" apples.");
  }

	
	
   /**
   * Gets user input 
   * @param    appleObj   Object of class Apple
   */
  
  public static void main(String args[]) throws IOException {
	  String color1;
	  double weight1;
	  double pricePerPound1;
	  String fruitName1;
	  String weightInput1;
	  String priceEachInput1;
	  System.out.println("Enter the fruit to be purchased");
	  BufferedReader bufferreader= new BufferedReader(new InputStreamReader(System.in));
	  fruitName1 = bufferreader.readLine();
    
	  if (( fruitName1.equals("Apples")) || ( fruitName1.equals("Apple")) ||
    		( fruitName1.equals("apples")) || ( fruitName1.equals("apple"))) {
		  BufferedReader appleReader= new BufferedReader(new InputStreamReader(System.in));
      
		  System.out.println("Enter the color of apples to be purchased");
		  color1 = appleReader.readLine();
      
		  System.out.println("Enter the weight of apples to be purchased");
		  weightInput1 = appleReader.readLine();
		  weight1 = Double.parseDouble(weightInput1);
      
		  System.out.println("Enter the price of Apples per unit");
		  priceEachInput1 = appleReader.readLine();
		  pricePerPound1 = Double.parseDouble(priceEachInput1);
      
		  Apples appleObj1 = new Apples(color1,weight1,pricePerPound1);
		  appleObj1.displayApplesInfo(appleObj1);      
	  }
  	}  
}