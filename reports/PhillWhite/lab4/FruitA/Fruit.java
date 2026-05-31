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
 * @author      Piter Garcia
 */
abstract class Fruit{
  private String color;    
  
  
  
  public Fruit(String color){
    
	  this.color = color;
  }
  
  
  
  public Fruit() {
	// TODO Auto-generated constructor stub
 }



public String getColor(){
   
	  return this.color;
  }
  
  
  
  public String setColor(String color){
	   
	  return this.color;
  }
  
  
  public abstract  double totalPrice();
  
  
  
  public abstract  String report();
  
}