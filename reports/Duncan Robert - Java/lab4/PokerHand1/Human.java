/*
 * Human.java
 *
 * Version:
 * $Id: Human.java,v 1.1 2013/04/07 18:45:29 pzg8794 Exp $
 *
 * Revisions:
 * $Log: Human.java,v $
 * Revision 1.1  2013/04/07 18:45:29  pzg8794
 * Poker Game
 *
 */

import java.util.Scanner;

/**
 * A human player for 2-card poker.
 *
 * @author paw: Phil White
 */
 public class Human extends Player {


 /**
  * human player(s) constructor.
  */
  public Human() {
	super();
  }

   
   
 /**
  * Asks the player if they want to stand.  You should prompt the
  * player with a suitable message, and then read the players response
  * from standard input.
  *
  * @return  a boolean value specifying if the human wants to stand
  */
  public boolean stand(Scanner in){
   	boolean res = false;
    	String answer;
    	char c;
	do{
	       System.out.print("Do you want to stand (y/n)? ");
	       answer = in.next();
	       answer = answer.toLowerCase();
	       c = answer.charAt(0);
	}while( c != 'y' && c != 'n' );
	
		if ( c == 'y' ){
		    res = true;
		} else {
		    res = false;
	        }
	return res;
  }
    
    
 /**
  * main method for a test driver that should test all the methods
  * in the class
  *
  * @param    args    command line arguments
  */
  public static void main( String args[] ){
    	
    	
  }
}
