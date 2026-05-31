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

public class Apples extends Fruit
{
  public String color;
  public double weight;
  public double pricePerPound;
  public int numOfApples;
  
  public Apples(String color,double weight,double pricePerPound,int numOfApples) 
  {
    super(color);
    this.color = color;
    this.weight = weight;
    this.pricePerPound = pricePerPound;   
    this.numOfApples = numOfApples;
  }
 

  public int getNum(int numOfApples)
  {
    return numOfApples;
  } 
  public double getWeight(double weight)
  {
    return weight;
  } 
 
  public double getPrice(double pricePerPound)
  {
    return pricePerPound;
  }

  public double totalPrice()
  {
    return weight*pricePerPound;
  }


  public String report()
  {
    return getColor(color);
  }

 
   /**
   * Test class Apples for number of apples greater than 5.
   * The rest of the errors are tested in TestFruit class.
   *
   * @param    appleObj   Object of class Apple
   */
  
  public void testApple(Apples appleObj)throws FruitException
  {
   appleObj.getNum(numOfApples);    
  //The below block handles exception where no. of apples entered is more than 5.
  //An object of FruitException class is thrown
    try
    {
      if (numOfApples > 5)
      {
        throw new FruitException("No. of apples should be less than 5.");
      }
    }
    
    catch(FruitException exc)
    {
      System.out.println(exc.toString());
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
  public  void displayApplesInfo(Apples appleObj)throws FruitException
  { 
    appleObj.getColor(color);
    System.out.println("The Apples color is: "+ color);
    appleObj.getWeight(weight);
    appleObj.getNum(numOfApples);
    System.out.println("The total cost of Apples purchsed is:  $"+ appleObj.totalPrice());
    System.out.println("I bought "+appleObj.report()+" apples.");
    
  }

}

   
  
