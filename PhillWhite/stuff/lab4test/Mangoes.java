/*
 * Mangoes.java
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
 * This program creates a class mangoes that extends Fruit.
 * It implements methods to get total price,color,number
 * weight and generate report for the fruits bought.
 *
 * @author      Sindhu Srinivasan
 * @author      Piter Garcia
 */
class Mangoes extends Fruit{
  public  int number;
  public  double priceEach;
  public double weight;
  
  
  
 /**
 * Initiates the parameters of the class Mango
 * @param    int, double, double.
 */
 public Mangoes(String color,int number,double priceEach,double weight) {
   super(color);
   this.number = number;
   this.priceEach = priceEach;   
   this.weight = weight;
 }
 
 
  
 /**
  * Gets user input for the class Mangos
  * @param    double
  */
 public double getWeight(double weight){
   return weight;
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

 
 
 /**
  * test for conditions for the Class Mango
  * @param    mangoObj   Object of class Mango
  */
 public void testMango(Mangoes mangoObj)throws FruitException,IOException{
   mangoObj.getWeight(weight);
  if (weight < 0){
     throw new FruitException("Mango weight cannot be negative. Please" +
     		" enter positive weight.");
  }

  mangoObj.getNumber(number);  
  if (number > 15){
     throw new FruitException("No. of mangoes should be less than 15. " +
     		"Please re-enter the number of mangoes.");
  }
  
  mangoObj.getPrice(priceEach);  
  if (priceEach < 0){
     throw new FruitException("Unit price of Manoges cannot be negative. " +
     		"Please re-enter the unit price.");
  }
}

 
 
 /**
  * display user processed input 
  * @param    mangoObj   Object of class Mango
  */
 //Display the fruits info
 public void displayMangoInfo(Mangoes mangoObj){
	 mangoObj.getColor(color);
	 System.out.println("The mango color is: "+color);
	 mangoObj.getPrice(number);  
	 System.out.println("The cost per mango is: $"+priceEach);
	 System.out.println("The total cost of mangoes purchsed is:  $"
	 + mangoObj.totalPrice());
  
	 System.out.println("I bought "+mangoObj.report()+" mangoes.");
 }



/**
 * Gets user input 
 * @param    mangoObj   Object of class Mango
 */
 public static void main(String args[])throws FruitException,IOException{
	int number1;
	double priceEach1;
	double weight1;
	String fruitName1, numberInput, priceEachInput, color1,inputWeight1;
  
	System.out.println("Enter the fruit to be purchased");
	BufferedReader bufferreader= new BufferedReader(new InputStreamReader(System.in));
	fruitName1 = bufferreader.readLine();
  
	if (( fruitName1.equals("Mangoes")) || ( fruitName1.equals("mangoes")) 
		   || ( fruitName1.equals("Mango")) || ( fruitName1.equals("mango"))) {
  
		BufferedReader mangoReader= new BufferedReader(new InputStreamReader(System.in));
   
		System.out.println("Enter the color of mango to be purchased");
		color1 = mangoReader.readLine();
     
  
		System.out.println("Enter the number of mangoes to be purchased");
		numberInput = mangoReader.readLine();
		number1 = Integer.parseInt(numberInput);
     
		System.out.println("Enter the number of weight of mangoes to be purchased");
		inputWeight1 = mangoReader.readLine();
		weight1 = Double.parseDouble(inputWeight1);
     
		System.out.println("Enter the price of mango per unit");
		priceEachInput = mangoReader.readLine();
		priceEach1 = Double.parseDouble(priceEachInput);
      
		Mangoes mangoObj1 = new Mangoes(color1,number1,priceEach1,weight1);
		mangoObj1.testMango(mangoObj1);
		mangoObj1.displayMangoInfo(mangoObj1);
	}
 }
}