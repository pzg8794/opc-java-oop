/*
 *  Created:      10/14/2012
 *  Last Changed: 10/14/2012
 *  
 *  Test.java 
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;


/**
 * This program creates a class called PartialLinkedListImp
 * This is for testing the linked list implementation
 * 
 * @author Piter Garcia
 * @author Sindhu Srinivasan
 */
public class Test extends PartialLinkedListImp {

 public static void main( String args[] ){
 
  Test testing = new Test();
  PartialLinkedListImp test = new PartialLinkedListImp();
  System.out.println("Testing the add function");
  testing.testingInput(test);
  
  System.out.println("Displaying Actual List");
  testing.displayingList(test);
  System.out.println(" ");


  System.out.println("Testing the Remove Function");
  System.out.println("Please enter object to remove");
  BufferedReader objects= new BufferedReader(new InputStreamReader(System.in));

  String input = null;
    try {
		input = objects.readLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  java.lang.Object v = test.remove(input); 
  System.out.println(" ");
  
  System.out.println("Displaying new List");
  testing.displayingList(test);
  System.out.println(" ");
  
  
  
  System.out.println("Testing the addFirst Function"); 
  System.out.println("Please enter object to add first");
    try {
		input = objects.readLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  test.addFirst(input);
  System.out.println(" ");
  
  System.out.println("Displaying new List");
  testing.displayingList(test);
  System.out.println(" ");
 
  System.out.println("Testing the addLast Function"); 
  System.out.println("Please enter object to add last");
  try {
		input = objects.readLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  test.addLast(input);
  testing.displayingList(test);
  System.out.println(" ");
  
 System.out.println(" ");
  
  System.out.println("Test the getFirst function");
  System.out.println(test.getFirst());
  System.out.println(" ");
  
  System.out.println("Test the getLast function");
  System.out.println(test.getLast());
  System.out.println(" ");
  
  System.out.println("Test the add position function");
  int index = 0;
  System.out.println("Please enter Index follow by an object");
  try {
	  input = objects.readLine();
	  index = Integer.parseInt(input);
	  input = objects.readLine();
	  
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
  test.addElem(index, input);
  System.out.println(" ");
  

  
  System.out.println("Displaying new List");
  testing.displayingList(test);
  System.out.println(" ");
  
  System.out.println("Testing Descending Iterator function");
  test.descendingIterator();
  System.out.println("\n\n ");
 
  System.out.println("Testing Ascending Iterator function");
  test.ascendingIterator();
  System.out.println(" \n\n");
     
    System.out.println("Testing ListIterator Index");
     System.out.println(test.listIterator(2));
     System.out.println("");
     
      System.out.println("Test the to Array");
      testing.displayingList(testing.toArray(test));
      System.out.println(" ");
    
  System.out.println("Testing the Remove first Function");
  System.out.println("Please enter object to remove first occurance");
  try {
		input = objects.readLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  Object vi = test.remove(input); 
  System.out.println(" ");
  
  
  System.out.println("Displaying new List");
  testing.displayingList(test);
  System.out.println(" ");    
  
  PartialLinkedListImp test2 = new PartialLinkedListImp();
  System.out.println("Testing the remove last function");
  testing.testingLastOccurance(test2);
  testing.displayingList(test2);
  System.out.println("Please enter object to remove last occurrance");
  try {
		input = objects.readLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  Object vj = test2.removeLastOccurrence(input); 
  System.out.println("Displaying new List");
  testing.displayingList(test2);
  System.out.println(" ");  
 }
 
 
 
 /**
  * Gets the user input from the console.
  * It adds the objects entered by the user into list
  *
  */  
  public void testingInput(PartialLinkedListImp test){
   String input = null;
   System.out.println("Please enter objects and q to exit");
   BufferedReader objects= new BufferedReader(new InputStreamReader(System.in));
   
   int i = 0;
   while( i != -1){
    
    try {
     input = objects.readLine();
    } catch (IOException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
    }
    
    if(input.equals("q"))
     i = -1;
    else{
     test.add(input);
    }
   }
  }
  
  
  /**
   * Gets the user input from the console.
   * It adds the objects entered by the user into list
   *
   */  
   public void testingLastOccurance(PartialLinkedListImp test){
    String input = null;
    System.out.println("Please enter objects");
    BufferedReader objects= new BufferedReader(new InputStreamReader(System.in));
    
    int i = 0;
    while( i != -1){
     
     try {
      input = objects.readLine();
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }
     
     if(input.equals("q"))
      i = -1;
     else{
      test.addFirst(input);
     }
    }
   }
  
  /**
  *  displys the list
  * @param test the llist object
  * @return returns the list
  */
  public void displayingList(LinkedList test) {
   for (Node n = test.head; n != null; n = n.next) {
    System.out.print(" " + n.elem.toString());
   }
   System.out.println("");
  }
  
}
