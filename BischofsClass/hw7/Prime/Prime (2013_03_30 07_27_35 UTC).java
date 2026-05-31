/*
 *  Created:      10/19/2012
 *  Last Changed: 10/19/2012
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
 * This program creates a class called prime
 * This class takes a numbers from the command line and tells you
 * whether they are prime or not.
 * 
 * @author Piter Garcia
 * @author Sindhu Srinivasan
 */

public class Prime  extends Thread   {
	
	static long milliSeconds = 0;
	

        private static long number;
        private static long location;
        
        
        
        /**
         * @Function: Prime (temp_number)
         * it sets the number to the given number by the user to be later analyzed
         * 
         * @param Designates the number which has to be analyzed.
         */
        public Prime (long prime_Number) {
                number = prime_Number;
        }
        
        
        
        /**
         * @Function: init()
         * Initiates the system counting
         * 
         * @param designates the time that the process is taking
         */
        public static void init()  {
            milliSeconds = System.currentTimeMillis();
        }

        
        
        
        /**
    	  * @Function: run()
    	  * runs function to be handle by each thread and executed by the scheduler.
    	  * 
    	  * @param Designates the number which has to be analyzed.
    	  */
		public void run () {
			
			isPrime(number); 
        	
        }

		
		
		
		/**
         * @Function:main (args [])
         * call functions for read the numbers input by the user.
         * 
         * @param Designates whether the input number is a prime number or not.
         */
        public static void main (String args []) throws InterruptedException {
        	
        	
        	int i = 0;
        	
        	System.out.println("Testing Prime Numbers Recognition Program");
        	System.out.println("----------------------------------------- \n");
        	System.out.println(" Analyzing numbers ... \n");
        	
        	// displaying input numbers.
        	displaying(args);
        	
        	// tracking the time that threads will take through the process.
        	init();
        	
        	
        	// Calling threads to analyze whether the input number is prime or not.
        	while( i != args.length){  
        		
        			long prime_Number = 0;
        			location = i;
        			
        	    	prime_Number = Integer.parseInt(args[i]); 
        	    	
                    Prime aT1  = new Prime(prime_Number);
                   
                    aT1.start();
                    aT1.join();
                    
        	    	i++;
        	}
                	

        }
	
	
        
        /**
         * @Function: isPrime( n)
         * This function analyze whether a number is prime or not
         * 
         * @param outputs whether the number analyzed is prime or not, int.
         */
        boolean isPrime(long number2) {
        	for(int i=2;i<number2;i++) {
	    	
        		if(number2%i==0){
        			System.out.println(" i = " + location + " is not a  prime number " + 
        								   ( (milliSeconds/1000000000)- 1000  + " msec") );
	        	
        			return false;
        		}
        	}
	    
        	System.out.println(" i = " + location + " is  a \t prime number "+ 
        									(  (milliSeconds/1000000000)- 1000 + " msec"));
        	return true;
        }
        
        
        
        
        /**
         * @Function: displaying([ ] args)
         * Displays the numbers input by the user
         * 
         * @param inputs given by the user, int.
         */
        public static void displaying(String [ ] args){
        	int i = 0;
        	
        	System.out.println(" List of Numbers Input: ");
        	while( i != args.length){  
        		
    	    	System.out.print(" " + args[i] + " ");
                
    	    	i++;
    	   }
        	
        	System.out.println("\n");
        	
        }
	
}