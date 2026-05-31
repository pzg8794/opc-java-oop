/*
 * PokerHand.java
 *
 * Version:
 * $Id: PokerHand.java,v 1.1 2013/04/07 18:45:29 pzg8794 Exp $
 *
 * Revisions:
 * $Log: PokerHand.java,v $
 * Revision 1.1  2013/04/07 18:45:29  pzg8794
 * Poker Game
 *
 */

/**
 * A class to encapsulate a hand of cards for a 2-card poker game
 *
 * @author paw: Phil White
 */

public class PokerHand{

    private Card theCards[];
    private int numCards;
    
    protected int tie = 0;

   /**
    * Initialize an empty poker hand
    */
    public PokerHand (){
	theCards = new Card[3];
	numCards = 0;
    }

   /**
    * returns a numerical value to represent the hand, this value 
    * will make sure that if a.value>b.value then a is a better hand
    * than b
    * 
    * @return	the int value of the hand 
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
    * @param	c	the card to add to hand
    */
    public void addCard( Card c ){
	theCards[numCards] = c;
	numCards++;
    }

   /**
    * Pretty print the hand
    */
    public void printHand(){
/*
	ArrayList<Card> hand = new ArrayList<Card>( theCards );
	PrintHand.printHand( hand, numCards );
	PrintHand.printHand( theCards, numCards );
*/
	for (int i=0; i< numCards-1; i++){
	    System.out.print( "     " );
	}
	System.out.println( " --------" );
	for (int i=numCards-1; i>= 0; i--){
	    for (int j=0; j < i-1; j++){
		System.out.print( "     " );
	    }
	    if ( i != 0 ){
		System.out.print( " ----" );
	    }
	    System.out.print( "|" + theCards[i].getShortName() );
	    for (int j=0; j < numCards-i-1; j++){
                System.out.print( " |   " );
            }
	    System.out.println( "     |" );
	}
    }

   /**
    * returns an "ugly" look at the cards in the hand
    *
    * @return	a string containing all the cards in the hand
    */
    public String toString(){
	String res = "";
	for (int i=0; i<numCards; i++ ){
	    res = res + theCards[i] + " ";
	}
	return res;
    }

} //PokerHand