/*
 * PrimeSieve.java
 *
 * Version:
 *     $Id: PrimeSieve.java,v 1.4 2013/03/13 07:03:41 pzg8794 Exp $ 
 *     
 *
 * Revisions:
 *      $Log: PrimeSieve.java,v $
 *      Revision 1.4  2013/03/13 07:03:41  pzg8794
 *      updating
 *
 *      Revision 1.3  2013/03/12 05:27:33  pzg8794
 *      Snowflake.java
 *
 *      Revision 1.2  2013/03/12 03:54:16  pzg8794
 *      PrimeSieve.java
 *
 */


import java.util.*;


/** A class that computes prime numbers between 2 and N, using the Sieve of
 *   Eratosthenes algorithm.
 *
 *
 *  @author     Piter Garcia
 */
public class PrimeSieve {

    /*
     * variable to read from the command line.
     */
    private Scanner sc = new Scanner(System.in);


    /*
     * variable that contains the highest number to find a range of prime 
     * numbers from 2 - maximumN.
     */
    private int maximumN; 
    
    
    /*
     * list used to crossed out numbers that are not primes.
     */
    private  LinkedList<Integer> listOfIntegers = new LinkedList<Integer>();


    /*
     * list to save numbers that are primes.
     */
    private  ArrayList<Integer> listOfIntegers1 = new ArrayList<Integer>();

    
    
    
   /**
    * This method is the class constructor, and it is used to initialize
    * the maximum number to find a range of prime numbers.
    */
   public PrimeSieve() {

        maximumN = 0;
   }




   /**
    * This method finds the prime numbes of a given range, then it saves it in
    * a ArrayList list.
    *
    * @param N this is the maximum number given to get the primes  within the 
    * range from 0 to N.
    *
    * @return ArrayList this is a list with all the prime numbers resulted 
    * from the given range.
    */
   public  ArrayList<Integer> findPrimes( int N) {
    	
         int i = 0;
	     while( i != (maximumN + 1)) {
		    
		    // crossing out numbers divisible by 2 greater than 2.
		    if( i!=2 && i%2 == 0) {  
		         listOfIntegers.add(null); 
		     }
		     // crossing out numbers divisible by 3 greater than 3.
		     else if( i!=3 && i%3 == 0) {  
		         listOfIntegers.add(null); 
		     }
		     //crossing out numbers divisible by 5 greater than 5.
		     else if( i!=5 && i%5 == 0) { 
		         listOfIntegers.add(null); 
		     }
		     //crossing out number divisible by 7 greater than 7.
		     else if( i!=7 && i%7 == 0) {  
		         listOfIntegers.add(null); 
		     }
		     // adding prime numbres.
		     else
		         listOfIntegers.add(i);

          i++;
          }
 
   
          // transferring prime numbers to a clean new list to avoid  nulls from
	  // those numbers that are not prime and that were crossed out.
	  for( int x = 0; x < listOfIntegers.size(); x++) {
                
		if( listOfIntegers.get(x) != null)
                     listOfIntegers1.add(listOfIntegers.get(x));
          }

        return listOfIntegers1;
   }


    

   /**
    * This method displays all the prime numbers obtained from the given range.
    */
   public void display() {
        
	int i = 0;
        
	System.out.println("Compute prime numbers from 2 to: " + maximumN);
	System.out.print("Prime numbers: ");

        while( i != listOfIntegers1.size()) {

	   if( listOfIntegers1.get(i) != null)
               System.out.print(listOfIntegers1.get(i) + " ");
          
	       i++;
        }
        System.out.println();
   }



    
   /**
    * This method checks if the input is an integer or not, if it is not an
    * integer then will keep asking the user to enter an interger until the
    * user inputs an integer.
    *
    * @return the maximumN(digit) that determs the prime numbers range. 
    */
   public int checkingInput() {  
          
	  while(true){
                System.out.print("\n\nPlease Enter the Maximum N Value >= 2: ");
		if(sc.hasNextInt()) {
		      maximumN = sc.nextInt();
		      break;
		}else{
	              System.out.println("Input Error!");
	              sc.next();
	       }
          }	   

    return maximumN;
   }




   /**
    * The main method, it calls the findPrimes(maximumN) method to find all 
    * prime numbers withing a given range and displays them by calling the
    * fucntion display().
    *
    * @param args command line arguents (ignored).
    */
   public static void main(String[] args) {

    	PrimeSieve test = new PrimeSieve();
    	
         test.maximumN = test.checkingInput(); // getting a range from 0 - maximumN
	     char answer = 'y';
         
	    // checking if the maximumN is >= 2, if not user must re-enter a right
	    // input or quit the program.
	    while( (test.maximumN < 2) && (answer == 'y') ){
	 
	        answer = ' ';

	        System.out.println("Compute prime numbers from 2 to: " + test.maximumN);
	        System.out.println("N must be greater than or equal to 2.\n\n");
             
	        System.out.println("Do You Want To Try Again? \nType yes or no");
	        answer = test.sc.next().toLowerCase().charAt(0);

            // choice for the user to quit the program.
            while( answer != 'y' && answer != 'n'){
	            System.out.println("Please Enter Yes or No");
	            answer = test.sc.next().toLowerCase().charAt(0);
	        }
	     
	        // checking the input, if the user decided to continue the program.
	        System.out.println("\n");
	        if( answer == 'y') {
	            test.maximumN = test.checkingInput();
	        }
         }

        // if the input number is right, the proceed to display prime numbers.
        if( answer == 'y') {
             test.findPrimes(test.maximumN); // finding primes within the given range.
             test.display();    // displyainf prime found within the given range.
	    }else
	         // if the input is wrong and you user decided to quit, let him go.
	         System.out.print("Sorry You Gave up!\nThis Program Really Works!");
	
        System.out.println("\n\n");
   }
}
