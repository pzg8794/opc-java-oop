/*
 * Apple.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */


/**
 * This program creates a class Apples that extends Fruit.
 * It implements methods to get total price,color,number
 * weight and generate report for the fruits bought.
 *
 * @author      Sindhu Srinivasan
 * @author      Piter Garcia
 */
import java.io.*;
//import java.util.*;

public class Apples extends Fruit{
  public double weight;
  public double pricePerPound;
  public int numOfApples;
  
 public Apples(String color,double weight,double pricePerPound,int numOfApples) {
   super(color);
   this.weight = weight;
   this.pricePerPound = pricePerPound;   
   this.numOfApples = numOfApples;
 }
 

 public int getNum(int numOfApples){
   return numOfApples;
 } 
 public double getWeight(double weight){
   return weight;
 } 
 public double getPrice(double pricePerPound){
  return pricePerPound;
 }

 public double totalPrice(){
  return weight*pricePerPound;
 }

 public String report(){
  return getColor(color);
 }



/**
* Test class Apples for number of apples greater than 5.
* The rest of the errors are tested in TestFruit class.
*
* @param    appleObj   Object of class Apple
*/
public void testApple(Apples appleObj)throws FruitException{
   appleObj.getWeight(weight);
  if (weight < 0){
     throw new FruitException("Apple weight cannot be negative." +
     		" Please enter positive weight.");
  }

  appleObj.getNum(numOfApples);  
  if (numOfApples > 5){
     throw new FruitException("No. of apples should be less than 5." +
     		" Please re-enter the number of apples.");
  }
  
  appleObj.getPrice(pricePerPound);  
  if (pricePerPound < 0){
     throw new FruitException("Unit price of Apples cannot be negative. " +
     		"Please re-enter the unit price of apples.");
  }

}



/**
* Displays information about the Apple class.
*
* @param    appleObj   Object of class Apple
* @return   color      color of apples got
* @return   totalPrice total cost of apples got.
* @return   report     generates a report for the purchase
* 
*/
public  void displayApplesInfo(Apples appleObj)throws FruitException{ 
  appleObj.getColor(color);
  System.out.println("The Apples color is: "+ color);
  appleObj.getWeight(weight);
  appleObj.getNum(numOfApples);
  System.out.println("The total cost of Apples purchsed is:  $"+ appleObj.totalPrice());
  System.out.println("I bought "+appleObj.report()+" apples.");
}


//Test your program
public static void main(String args[]) throws FruitException,IOException{
  String color1;
  double weight1;
  double pricePerPound1;
  String fruitName1;
  String weightInput1;
  String priceEachInput1;
  int numOfApples1;
  String numOfApplesInp1;
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
  
   
  System.out.println("Enter the number of apples to be purchased");
  numOfApplesInp1 = appleReader.readLine();
  numOfApples1 = Integer.parseInt(numOfApplesInp1);
  
  System.out.println("Enter the price of Apples per unit");
  priceEachInput1 = appleReader.readLine();
  pricePerPound1 = Double.parseDouble(priceEachInput1);
  
  Apples appleObj1 = new Apples(color1,weight1,pricePerPound1,numOfApples1);
  
  appleObj1.displayApplesInfo(appleObj1);
  appleObj1.testApple(appleObj1);
  
   }
 }
}