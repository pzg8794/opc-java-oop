
/*
 *  Created:      10/26/2012
 *  Last Changed: 10/28/2012
 *  
 * Deadlock.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

// Import statements are placed here



/**
 * This program simulates the concept of Deadlock.
 * It has a kidnapper and a Father.The kidnapper has the kid,
 * and father has the cash.Both father and Kidnapper do not 
 * wish to be in loss.Father needs to save his kid and money.
 * Kidnapper needs money as well as not let go of kid.So both
 * fight for cash and kid. but due to deadlock none of them are 
 * able to get both.
 *
 * @author      Sindhu Srinivasan
 * @author      Piter Garcia
 */

public class Deadlock extends Thread{ 
	//Initializing the variables
	private static String kid  = new String();  
	private static String cash  = new String();
	private String threadName;

  
  
  
	//Creates the constructor for the threads
	public Deadlock(String threadName){
		this.threadName = threadName;
	}
  
  
  
  
	/**
	 * This method demonstrates the concept of deadlock.
	 * The kidnapper thread holds on to the kid, and waits for 
	 * the cash. on the other hand, the father thread has
	 * the cash, and waits for the kid.Both threads compete
	 * to get both the resources.As both threads have
	 * resources that the other thread has, to complete, it
	 * results in a state of deadlock.
	 */
	public void run(){
		//The kidnapper threads synchronizes on the kid
		if(threadName.equals("Kidnapper")){
			synchronized (kid) {
				System.out.println(threadName+ " Holding kid...");
				try { Thread.sleep(10); }
				catch (InterruptedException e) {}
				System.out.println(threadName+" Waiting for cash...");
				/** It tries to get hold of the cash, but Father kid has to release key for cash.
				 * That shall happen only when father gets both resources and completes.
				 * so we get a circular wait and hence a deadlock
				 */
				synchronized (cash) {
					System.out.println(threadName+" Holding kid and cash...");
				}
			}
		}
		else{
			//The father thread synchronizes on the cash
			synchronized (cash) {
				System.out.println(threadName+" Holding cash..");
				try { Thread.sleep(10); }
				catch (InterruptedException e) {}
				System.out.println(threadName+" Waiting for kid..");
				/**Father tries to get the kid.But the kidnapper synchronizes on kid
				 * Kidnapper shall give key only when it gets both resources,so we 
				 * get a circular wait and hence a deadlock
				 */
    	  
				synchronized (kid) {
					System.out.println(threadName+" Holding cash and kid...");
				}
			}
		}
  	}
  
  
  
   
	/**
	 * The main program.
	 *
	 * @param    args    command line arguments (ignored)
	 */ 
	public static void main(String args[]){
		//Creating the Kidnapper and Father threads and starting them
		Deadlock obj1 = new Deadlock("Kidnapper");
		Deadlock obj2 = new Deadlock("Father");
		obj2.start();
		obj1.start();
    
	}
}