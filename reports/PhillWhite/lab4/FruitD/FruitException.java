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
 * @author      Piter Garcia
 */
 public class FruitException extends Exception {
   private  String excstr;
   
   public FruitException(String excstr){
     this.excstr = excstr;
   }
   
   public String toString(){
    return excstr;   
   }
 }
   

