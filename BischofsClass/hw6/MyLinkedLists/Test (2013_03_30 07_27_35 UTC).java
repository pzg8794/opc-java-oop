import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;


/*
 *  Created:      10/14/2012
 *  Last Changed: 10/14/2012
 *  
 *  HashSet.java 
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */


/**
 * This program creates a class called Test
 * It inheritence the functions of PartialCollectionsImp
 * This has methods to test the sorting functions to sort a linked list.
 * 
 * @author Piter Garcia
 * @author Sindhu Srinivasan
 */
public class Test extends PartialCollectionsImp {

	public static void main( String args[] ){
	
		Test testing = new Test();
		PartialCollectionsImp  test = new PartialCollectionsImp ();
		//String input = null;
		
		System.out.println("Adding Objects");
		testing.testingInput(test);
		
		System.out.println("Displaying Actual List");
		testing.displayingList(test);
		System.out.println(" ");
		
		
		System.out.println("Testing Sorting Functon According to Position");
		System.out.println("Displaying New List");
		test.sort(test);
		System.out.println("\n\n ");
		
		System.out.println("Testing Sorting Shuffle function");
		System.out.println("Displaying New List");
		test.shuffle(test);
		testing.displayingList(test);
	    System.out.println("");   
	    
	    Comparator c = null ;
	    
	    //testing.sort(test, c);
	   
	    System.out.println("Testing Sorting values function");
	    test.shuffle(test);
	    test.shuffle(test);
		System.out.println("Displaying New List");
		testing.displayingList(test);
	    System.out.println("");
	}
	
	
	
	

	
	
		/**
	    *  Getting Input from the user to test sort functions
	    *
	    */		
		public void testingInput(PartialCollectionsImp test){
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
		*  Displaying the objecet in the linked list
		*
		*/
		public void displayingList(LinkedList test) {
			for (Node n = test.head; n != null; n = n.next) {
				System.out.print(" " + n.elem.toString());
			}
			System.out.println("");
		}

}