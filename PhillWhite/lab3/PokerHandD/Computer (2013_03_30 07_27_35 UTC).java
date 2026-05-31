import java.util.Scanner;

/*
 * Computer.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * A computer player for 2-card poker
 *
 * @author paw: Phil White
 */

public class Computer {

    boolean stands =  true;
    PokerHand myCards;
    private static final int BETTER_THAN_HALF_WIN_VALUE = 
    (Ranks.QUEEN.getValue() ) * 14 + ( Ranks.JACK.getValue() );

   /**
    * Initalize a computer player for 2-card poker
    */
    public Computer (){
    	myCards = new PokerHand();
    }

   /**
    * determines if the computer should stand (vs fold).  Note the
    * computer will stand if it has >=50% chance of winning (Based on
    * other work, a High Card hand with a Q and J beats 50% of the other
    * possible hands).  For the complete odds of winning see 
	* <a href="../chance.html">chance.html</a> for tables containing the chance 
    * to win for 2-cards of the same suit, and 2 cards of unmatched suits
    *
    * @returns	a boolean value specifying if the computer wants to stand
    */
    public boolean stand(){
    	
      	if( this.myCards.cards[0].getRank().getValue() !=
      			this.myCards.cards[1].getRank().getValue() ){
    			
    		if (this.myCards.cards[0].getSuit().ordinal() != 
    				this.myCards.cards[1].getSuit().ordinal() ){
    			
    			if (this.value() < 13){  			
    				stands = false;
    			}
    		}
      	}
      	else{
      		stands = true;
      	}
      //System.out.println("this is the pc cards value" + this.value());
	return stands;
    }

   /**
    * adds a card to the computer's hand
    *
    * @param c  the card to add
    */
    public void addCard(Card c){
    	myCards.addCard(c);
    }

   /**
    * prints the hand in some "nice" format
    */
    public void printHand(){
    	if(stand()){
		myCards.printHand();
    		System.out.println( "\n Total:" + value() + "\n" );
	}
    }

   /**
    * clears out all the cards for the computer
    */
    public void newHand(){
    	myCards = new PokerHand();
    }

   /**
    * This function must come up with a single integer that represents the
    * value of the hand.  You want the value to work such that a higher
    * hand has a higher value. So the values should fall from highest
    * to lowest as:
    * <ul>
    *   <li>pair of Aces
    *   <li>pair of Kings
    *   <br>...
    *   <li>pair of Twos
    *   <li>Ace/King flush (the same suit)
    *   <li>Ace/Queen flush
    *   <br>...
    *   <li>three/two flush
    *   <li>Ace/King high card (not the same suit)
    *   <li>Ace/Queen high card
    *   <br>...
    *   <li>Three/Two high card
    * </ul>
    *
    * @returns	the integer representing the value of the hand
    */
    public int value() {
	return myCards.value();
    }

   /**
    * main method for a test driver that should test all the methods
    * in the class
    *
    * @param    args    command line arguments
    */
    public static void main( String args[] ){
    	
	System.out.println( "== Dealing myCards.cards\n" );
	
	Deck d = new Deck();
	Deck s = new Deck();
		
	Human person = new Human();
	Computer player = new Computer();
	char c;
	do{
	//give initial cards 
		for (int j=0; j<2; j++ ){
			person.addCard( d.dealCard() );
		}
		System.out.println( " \n ==============  Your Cards  ========" );
		person.printHand();
			
		player.newHand();
		for (int j=0; j<2; j++ ){
			player.addCard( s.dealCard() );
		}
		System.out.println( " \n ==============  PC Cards  ========" );
		player.printHand();

		System.out.println( " \n ==============  Compared Cards  ========" );
		System.out.println( person.compareTo(player));
			
		System.out.println( " Your total value is " + person.value() + "\n");

		// ask player if they want to stand
		if(person.stand() ){
			System.out.println( "You can Win" );
			
		}
		else {
			System.out.println( "You have lost" );
		}

		// have everyone throw in their cards
		person.newHand();
		System.out.print( "You have no cards \n" );
			
		System.out.print( "Do you wish to play " + "another hand (y/n):" );
			
		Scanner in = new Scanner( System.in );
	    	String again;
	    		    	
		again = in.nextLine();
		again = again.toLowerCase();
		c = again.charAt( 0 );
			
		}while( c != 'n' );
			System.out.println( "Game Over! \n" );
		}
// main method for a test driver that should test all the methods in the class
	
}
