import java.util.LinkedList;
import java.util.Random;

/*
 * Poker.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */



/**
 * A 2-card poker game played between a human and a computer player
 *
 * @author paw: Phil White
 */

public class PokerHand {

	private static Suits[] suits = { Suits.HEARTS, Suits.SPADES, Suits.DIAMONDS, Suits.CLUBS};
	private static Ranks[] ranks  = { Ranks.ACE, Ranks.DEUCE, Ranks.THREE, Ranks.FOUR, Ranks.FIVE, Ranks.SIX, Ranks.SEVEN, Ranks.EIGHT, Ranks.NINE, Ranks.TEN, Ranks.JACK, Ranks.QUEEN, Ranks.KING };
	
    private LinkedList oldCards = new LinkedList();;
	public Card[] cards;
    private static int i=0;
	
	/**
    * main method -- plays multiple hands of poker, after each hand
    * ask the user if they want to play again.  We also keep trak of
    * the number of games played/won by the user and print the results
    * at the end.
    *
    * @param    args      command line arguments
    */
    public PokerHand(){
    	cards = new Card[4];
		i = 0;

		for (int x=0; x<cards.length; x++){
			cards[x] = null; //empty up cards[] array.
		}

		for (int x=0; x<13; x++){
			
			for (int i=0; i<4; i++){
				oldCards.add(ranks[x].getShortName() + suits[i].getShortName()); 
			}
		}
    }
    public static void main( String args[] ){
    	
    	PokerHand ph = new PokerHand();
    	Card c = new Card(ranks[1], suits[2]);
    	
    	System.out.println("Adding a Card");
    	ph.addCard(c);
    	System.out.println("Adding another Card");
    	ph.addCard(c);
    	
    	System.out.println("Printing Cards");
    	ph.printHand();
    	
    	System.out.println("Card Value");
    	System.out.println(ph.value());
   
    }

    
    public void repeatedCards( int r, int s){
        	
  
		if( oldCards.contains((ranks[r].getShortName() + suits[s].getShortName()))){
			oldCards.remove((ranks[r].getShortName() + suits[s].getShortName()));
		}
		else{
			Card c = new Card();
    		addCard(c);
		}	
    }
 
 
 
 
    public void addCard(Card c) {
    	
    	Random generator = new Random(); 
    	int r=0,s=0; 
    	
		r = generator.nextInt(13);
		//repeatedCards(r);
		s = generator.nextInt(4);
		//repeatedCards2(r);
		
		if(i == 4)
			i=0;
		
		repeatedCards(r,s);
		cards[i] = c = new Card(ranks[r], suits[s]);
		i++;
	
    }

    public void printHand() {
    	int x = 0;
      		
    		while(x != 2){
    			System.out.print( cards[x].getShortName() + " ");
    			x++;
    		}
    }

    public int value() {
     	int x = 0, value = 0;
  		
  		
		while(x != 2){
			value = cards[0].value();
			
			if ( x > 0)
			value  = value + cards[x].value();
			
			x++;
		}
	return value;
}
} 