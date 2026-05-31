/*
 * FruitException.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */


/**
 * This program creates an exception class FruitException.
 * It takes as input the a string object describing the 
 * exception, and return it.
 * 
 * @author      Sindhu Srinivasan
 * @author      Piter Garcia
 */
public class FruitException extends Exception {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private  String excstr;
   public FruitException(String excstr){
     this.excstr = excstr;
   }
   
   public String toString(){
    return excstr;   
   }
 }
   
