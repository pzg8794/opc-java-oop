/*
 * Mangoes.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */


// Import statements are placed here
import java.io.IOException;


/**
 * This program creates a class Mangoes that extends Fruit.
 * It implements methods to get total price,color,number
 * weight and generate report for the fruits bought.
 *
 * @author      Sindhu Srinivasan
 * @author      Piter Garcia
 */

class Mangoes extends Fruit
{
  public  String color;
  public  int number;
  public  double priceEach;
  public double weight;
  
 public Mangoes(String color,int number,double priceEach,double weight) 
 {
   super(color);
   this.color = color;
   this.number = number;
   this.priceEach = priceEach;   
   this.weight = weight;
 }
 
  
 public double getWeight(double weight)
 {
   return weight;
 }
 
 public int getNumber(int number)
 {
   return number;
 }
 
 public double getPrice(double priceEach)
 {
  return priceEach;
 }
 
 public double totalPrice( )
 {
  return number * priceEach;
 }


public String report()
{
  return getColor(color);
  
}

   /**
   * Test class Mangoes for number of mangoes greater than 15.
   * The rest of the errors are tested in TestFruit class.
   *
   * @param    mangoObj   Object of class Mangoes
   */

public void testMango(Mangoes mangoObj)throws FruitException,IOException
{
  mangoObj.getWeight(weight);
  mangoObj.getNumber(number); 
  //The below block handles exception where no. of mangoes entered is more than 15.
  //An object of FruitException class is thrown
  if (number > 15)
    try
  {
    {
      throw new FruitException("The number of mangoes cannot be greater than 15.");
    }
  }
  catch(FruitException exc)
  {
    System.out.println(exc.toString());
  }
}
 
   /**
   * Displays information about the Mangoes class.
   *
   * @param    mangoObj   Object of class Mangoes
   * @return   color      color of Mangoes got
   * @return   totalPrice total cost of Mangoes got.
   * @return   report     generates a report for the purchase
   * 
   */
public void displayMangoInfo(Mangoes mangoObj)
{
  mangoObj.getColor(color);
  System.out.println("The mango color is: "+color);
  mangoObj.getPrice(number);  
  mangoObj.getPrice(priceEach);
  System.out.println("The total cost of mangoes purchsed is:  $"+ mangoObj.totalPrice());
  System.out.println("I bought "+mangoObj.report()+" mangoes.");
}

}
 
