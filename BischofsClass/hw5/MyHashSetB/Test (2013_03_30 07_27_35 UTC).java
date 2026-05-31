/*
 *  Created:      10/07/2012
 *  Last Changed: 10/07/2012
 *  
 *  Test.java 
 * 
 *  Version:
 *     $Id$
 *
 *  Revisions:
 *     $Log$
 */


/**
 * This program creates a class called Test that extends Driver1.
 * This program tests Java's implementation of HashSet and our own implementation.
 * 
 * @author Piter Garcia
 * @author Sindhu Srinivasan
 */



public class Test extends Driver1 {
	

    
	public class Test2 extends Driver2 {


	}
	public static void main(String args[] )      {
			
		//Testing HashSet in the System
		System.out.println("Testing HashSet Functions from Java Collection");
	     	Driver2 aDriver2 = new Driver2();
	        aDriver2.init();
	        aDriver2.testIt();
	        aDriver2.end();
	        
	   
			Driver1 aDriver = new Driver1();
			//Testing My Own HashSet   
		    System.out.println("\n\nTesting My Own HashSet Functions");
	        aDriver.init();
	        aDriver.testIt();
	        aDriver.end();
	        
	        
	        System.exit(0);
	   }
}

