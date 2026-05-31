/*
 * TestFruit.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

// Import statements are placed here
import java.io.*;

/**
 * This program creates an abstract class TestFruit.
 * It is used to check erroneous conditions in Apples
 * and Mangoes class.
 *
 * @author      Sindhu Srinivasan
 * @author      Piter Garcia
 */


public class TestFruit 
{
  
  public static void main(String args[])throws FruitException,IOException
  {
    TestFruit testFruitObj = new TestFruit();
    testFruitObj.getTestUserInput();
  }
  
   /**
   * This method obtains user input, and checks for 
   * error conditions.
   */

  public void getTestUserInput()throws FruitException,IOException
  {
    String color1;
    double weight1;
    double pricePerPound1;
    String fruitName1;
    String weightInput1;
    String priceEachInput1;
    int numOfFruits1;
    String numOfFruitsInp1;
    BufferedReader fruitReader= new BufferedReader(new InputStreamReader(System.in));
    
    System.out.println("Enter the fruit to be purchased");
    fruitName1 = fruitReader.readLine();   
    System.out.println("Enter the color of fruits to be purchased");
    color1 = fruitReader.readLine();
    
    System.out.println("Enter the weight of fruits to be purchased");
    weightInput1 = fruitReader.readLine();
    weight1 = Double.parseDouble(weightInput1);
    //The below block handles exception where fruit weight entered is negative.
    //An object of FruitException class is thrown
    try
    {  
      while (weight1 < 0)
      {
        throw new FruitException("The weight of the fruits cannot be negative.");     
      }
    }
    catch(FruitException exc)
    {
      System.out.println(exc.toString());
    }
    
    System.out.println("Enter the number of fruits to be purchased");
    numOfFruitsInp1 = fruitReader.readLine();
    numOfFruits1 = Integer.parseInt(numOfFruitsInp1);  
    System.out.println("Enter the price of fruits per unit");
    priceEachInput1 = fruitReader.readLine();
    pricePerPound1 = Double.parseDouble(priceEachInput1);
    //The below block handles exception where weight per unit fruit entered, is negative.
    //An object of FruitException class is thrown
    try
    {
      if (pricePerPound1 < 0)
      {
        throw new FruitException("Unit price of Fruits cannot be negative.");
      }
    }
    catch(FruitException exc)
    {
      System.out.println(exc.toString());
    }
    
    Apples appleObj1 = new Apples(color1,weight1,pricePerPound1,numOfFruits1);
    Mangoes mangoObj1 = new Mangoes(color1,numOfFruits1,pricePerPound1,weight1);
    
    if (( fruitName1.equals("Apples")) || ( fruitName1.equals("Apple")) || ( fruitName1.equals("apples")) || ( fruitName1.equals("apple"))) 
    {
      
      appleObj1.displayApplesInfo(appleObj1);
      appleObj1.testApple(appleObj1); 
      
    }
    
    if (( fruitName1.equals("Mangoes")) || ( fruitName1.equals("mangoes")) || ( fruitName1.equals("Mango")) || ( fruitName1.equals("mango")))
    {
      mangoObj1.testMango(mangoObj1);
      mangoObj1.displayMangoInfo(mangoObj1);
    }
  }

}