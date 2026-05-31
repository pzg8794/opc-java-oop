/*
 * PokerHand.java
 *
 * Version:
 * $Id: PokerHand.java,v 1.1 2013/06/11 02:24:23 pzg8794 Exp $
 *
 * Revisions:
 * $Log: PokerHand.java,v $
 * Revision 1.1  2013/06/11 02:24:23  pzg8794
 * Lab2 done!
 *
 */

/**
 * A class to encapsulate a hand of cards for a 2-card poker game
 *
 * @author pzg: Piter Garcia
 * 
 */

public class PokerHand{

	/*
	 * Players Cards 
	 */
	private Card theCards[];

	/*
	 * Players number of Cards
	 */
	private int numCards;



	/**
	 * Initialize an empty poker hand
	 */
	public PokerHand (){
		theCards = new Card[3];
		numCards = 0;
	}



	/**
	 * returns a numerical value to represent the hand, this value 
	 * will make sure that if value a is > value b, then a is a better hand
	 * than b.
	 * 
	 * @return	total, the integer value representation of the hand 
	 */
	public int value(){
		int total = 0;
		int mod = 0;

		if( theCards[0].getRank() == theCards[1].getRank() ){
			mod = 1000;
		} else if( theCards[0].getSuit() == theCards[1].getSuit() ){
			mod = 500;
		}

		if( theCards[0].value() > theCards[1].value() ){
			total =  mod + theCards[0].value() * 14 +  theCards[1].value();
		} else {
			total =  mod + theCards[1].value() * 14 +  theCards[0].value();
		}

		return total;
	}    

	
	
	/**
	 * adds a card to the hand
	 *
	 * @param	c the card to add to hand
	 */
	public void addCard( Card c ){
		theCards[numCards] = c;
		numCards++;
	}

	
	
	/**
	 * Pretty print the hand in a nice format.
	 */
	public void printHand(){
	
		for (int i=0; i< numCards-1; i++){
			System.out.print( "     " );
		}
		
		System.out.println( " --------" );
		for (int i=numCards-1; i>= 0; i--){
			
			for (int j=0; j < i-1; j++)
				System.out.print( "     " );
			
			if ( i != 0 )
				System.out.print( " ----" );
			
			System.out.print( "|" + theCards[i].getShortName() );
			for (int j=0; j < numCards-i-1; j++)
				System.out.print( " |   " );
			
			System.out.println( "     |" );
		}
	}

	
	
	/**
	 * prints the name of the cards without any especial format
	 *
	 * @return	a string containing all the cards in the hand
	 */
	public String toString(){
		String res = "";
		int i;
		for (i=0; i<numCards -1; i++ ){
			res = res + theCards[i] + " and ";
		}
		res = res + theCards[i];
	
		return res;
	}

	
	/**
	 * main method for a test driver that should test all the
     * methods in this class.
     * 
	 * @param args ignored.
	 */
    public static void main( String args[] ){
    	
    	PokerHand ph = new PokerHand();
    	Card c = new Card(Ranks.FIVE, Suits.DIAMONDS);
    	
    	System.out.println("Adding a Card");
    	ph.addCard(c);
    	System.out.println("Adding another Card");
    	ph.addCard(c);
    	
    	System.out.println("Printing Cards");
    	ph.printHand();
    	
    	System.out.println("Card Value");
    	System.out.println(ph.value());
    	System.out.println(ph);
   
    }
} //PokerHand