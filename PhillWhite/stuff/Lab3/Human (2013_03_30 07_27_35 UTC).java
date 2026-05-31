/*
 * Poker.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;


/**
 * A Human class that plays against the Computer player.
 * Every decision taken by this class depends on the card values access through
 * the class Card. Actions are taking by the class Poker which tells the winner.
 *
 * @author pg: Piter Garcia
 */
public class Human extends Deck{
    
    boolean stands = true;
    private PokerHand myCards;
    private static Scanner in = new Scanner( System.in );

   /**
    * Initialize a human player for 2-card poker
    */
    public Human (){
    	myCards = new PokerHand();
    }

    

    /**
     * adds a card to the human's hand
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
     * clears out all the cards for the human
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
     * @returns  the integer representing the value of the hand
     */
     public int value() {
     	return myCards.value();
     }
     
     
	//Compares the humans hand with the specified computers hand for order.
	int	compareTo(Computer d) {
		
		int hRank=0,cRank=0, hSuit=0, cSuit=0;
		int i= 0, hLowest=0, cLowest=0;
		int winner = 0;
		boolean humansRank = true, computersRank = true, humansSuit = true, computersSuit = true;
		
		if(this.myCards.cards[0].value() < this.myCards.cards[1].value() ){
			hLowest = this.myCards.cards[0].value();
		}
		else{
			hLowest = this.myCards.cards[1].value();
		}
		
		if(d.myCards.cards[0].value() < d.myCards.cards[1].value() ){
			cLowest = d.myCards.cards[0].value();
		}
		else{
			cLowest = d.myCards.cards[1].value();
		}
				
			
		if( this.myCards.cards[0].getRank() != this.myCards.cards[1].getRank()){
				
				humansRank = false;
		}
				
		if( d.myCards.cards[0].getRank() != d.myCards.cards[1].getRank()){
				
				computersRank = false;
		}
		
		if( this.myCards.cards[0].getSuit() != this.myCards.cards[1].getSuit()){
			
			humansSuit = false;
	}
			
	if( d.myCards.cards[0].getSuit() != d.myCards.cards[1].getSuit()){
			
			computersSuit = false;
	}
			
	
	
	
	
	
		if(humansRank && !computersRank){
				winner =  1;
		}
		else if(!humansRank && computersRank){
				winner = -1;
		}
		else if(humansRank && computersRank){
				
			if( this.myCards.cards[0].getRank().getValue() > d.myCards.cards[0].getRank().getValue()){
					winner = 1;
			}
			else if( this.myCards.cards[0].getRank().getValue() < d.myCards.cards[0].getRank().getValue()){
					winner = -1;
			}
			else if( this.myCards.cards[0].getRank().getValue() == d.myCards.cards[0].getRank().getValue()){
				
				if(hLowest > cLowest)
					winner =  1;
				else if(hLowest < cLowest)
					winner =  -1;
				else					
					winner =  0;
			}
		}
		else if(!humansRank && !computersRank){
			
			if(humansSuit && !computersSuit){
				winner =  1;
			}
			else if(!humansSuit && computersSuit){
				winner = -1;
		}
		else if(humansSuit && computersSuit){
				
			if( this.myCards.cards[0].getSuit().ordinal() > d.myCards.cards[0].getSuit().ordinal()){
					winner = 1;
			}
			else if( this.myCards.cards[0].getSuit().ordinal() < d.myCards.cards[0].getSuit().ordinal()){
					winner = -1;
			}
			else if( this.myCards.cards[0].getSuit().ordinal() == d.myCards.cards[0].getSuit().ordinal()){
				
				if(hLowest > cLowest)
					winner =  1;
				else if(hLowest < cLowest)
					winner =  -1;
				else					
					winner =  0;
			}
		}
			
		}
			
		return winner;
	}
    
	
	
	
	/**
	* main method -- In this method I am testing all the functions of
	* the class Human to make sure that it works correctly.
	*
	* @param    args  command line arguments are ignored
	*/
	public static void	main(String[] args) {
		
		
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
			
			System.out.print( "Do you wish to play " +
			    "another hand (y/n):" );
			
			Scanner in = new Scanner( System.in );
	    	String again;
	    	
	    	
			again = in.nextLine();
			again = again.toLowerCase();
			c = again.charAt( 0 );
			
		}while( c != 'n' );
		
		System.out.println( "Game Over! \n" );
	}
	// main method for a test driver that should test all the methods in the class
	
	
	
		
	/**
	* stand() -- This method ask the user whether he wants to stand or fold.
	* If the user decides to fold, then the user lose the game and the computer wins.
	* If the user decides to stand, then the user's cards are compare with the computer's
	* cards and whoever has the best cards wins.
	*
	* @param    boolean
	*/
	public boolean	stand() {
	
		Scanner in = new Scanner( System.in );
    		String again;
    		char c;
    	
			System.out.print( "Do you wish to stand (y/n):" );
			again = in.nextLine();
			again = again.toLowerCase();
			//c = again.charAt(0);
		
		switch(c = again.charAt(0)){
		
		case 'y':
			stands = true;
			break;
			
		case 'n':
			stands = false;
			break;
			
		default:
			if( c != 'y' || c != 'n'){
				System.out.println( "Wrong Answer, please try again" );
				stand();
			}
		}
	   
		return stands;
	}   // Asks the player if they want to stand.


}
