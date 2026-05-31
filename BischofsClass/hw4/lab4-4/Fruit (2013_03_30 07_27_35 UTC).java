/*
 * Fruit.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */


/**
 * This program creates an abstract class Fruit.
 * It defines a method to get color, and two 
 * abstract methods to compute the total price
 * and generate report for the fruits bought.
 *
 * @author      Sindhu Srinivasan
 * @author      Piter Garcia
 */

abstract class Fruit {

  public String color;    
  
  public Fruit(String color)
  {
    this.color = color;
  }

  public String getColor(String color)
  {
    return this.color;
  }
  
  public abstract  double totalPrice();
  public abstract  String report();
}