/*
 * Computer.java
 *
 * Version:
 * $Id: Computer.java,v 1.1 2013/04/07 18:45:30 pzg8794 Exp $
 *
 * Revisions:
 * $Log: Computer.java,v $
 * Revision 1.1  2013/04/07 18:45:30  pzg8794
 * Poker Game
 *
 */

/**
 * A computer player for 2-card poker
 *
 * @author pzg: Piter Garcia
 */
 public class Computer extends Player {

 
    private static final int BETTER_THAN_HALF_WIN_VALUE =
		( Ranks.QUEEN.getValue() ) * 14 + 
		( Ranks.JACK.getValue() );
    
    

 /**
  * Computer constructor.
  */
  public Computer() {
		super();
  }

   
  /**
   * determines if the computer should stand (vs fold).  Note the
   * computer will stand if it has >=50% chance of winning (Based on
   * other work, a High Card hand with a Q and J beats 50% of the other
   * possible hands).  For the complete odds of winning see 
   * <a href="../chance.html">chance.html</a> for tables containing the chance 
   * to win for 2-cards of the same suit, and 2 cards of unmatched suits
   * @param myCards 
   *
   * @return	a boolean value specifying if the computer wants to stand
   */
   public boolean stand(PokerHand myCards){
	return ( myCards.value() >= BETTER_THAN_HALF_WIN_VALUE );
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
