/*
 * Poker.java
 *
 * Version:
 * $Id: Human.java,v 1.1 2013/06/11 02:24:22 pzg8794 Exp $
 *
 * Revisions:
 * $Log: Human.java,v $
 * Revision 1.1  2013/06/11 02:24:22  pzg8794
 * Lab2 done!
 *
 */
import java.util.Scanner;


/**
 * A human player for 2-card poker, to plays against the Computer player.
 * Every decision taken depends on the card values access through the 
 * class Card. Actions are taken by the Poker which tells the winner.
 *
 * @author pg: Piter Garcia
 */
public class Human {
        
    
	/*
	 * Facilitate cards management to the Human player.
	 */
    private PokerHand myCards;
    
	/*
	 * Variable to hold command line input access.
	 */
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
    		System.out.println( "==============  Your Cards  ========" );
    		myCards.printHand();
    		System.out.println();
     }

     
     
    /**
     * clears out all the cards for the human
     */
     public void newHand(){
     	myCards = new PokerHand();
     }

     
     
     /**
      * This function must come up with a single integer that represents the
      * value of the hand. You want the value to work such that a higher hand
      * has a higher value. So the values should fall from highest to lowest 
      * as:
      * pair of Aces
      * pair of Kings 
      * ...
      * pair of Twos
      * Ace/King flush (the same suit)
      * Ace/Queen flush 
      * ...
      * three/two flush
      * Ace/King high card (not the same suit)
      * Ace/Queen high card 
      * ...
      * Three/Two high card
      * 
      * @returns  the integer value of the Human player's cards.
      */
     public int value() {
     	return myCards.value();
     }
     
     
     
	/**
	 * Compares the humans hand with the specified computers hand for order.
	 *  
	 * @param d, is the Computer player variable access.
	 * 
	 * @return negative integer,	player hand < computers hand 	
	 * @return zero, player hand == computers hand 
	 * @return positive integer,	player hand > computers hand
	 */
     public int	compareTo(Computer d) {
		
    	return myCards.value() - d.value();
	}
    
	
	
	
	/**
	 * main method for a test driver that should test all the methods in the class
	 * main method -- In this method I am testing all the functions of
	 * the class Human to make sure that it works correctly.
	 *
	 * @param    args  command line arguments are ignored
	 */
	public static void	main(String[] args) {
		
		
		System.out.println( "== Dealing myCards.cards\n" );
		Deck d = new Deck();
		Human person = new Human();
		Computer player = new Computer();
		do{
			System.out.println( "\n== Shuffling" );
		    d.shuffle();
		    
			//give initial cards 
			for (int j=0; j<2; j++ ){
			    person.addCard( d.dealCard() );
			    player.addCard( d.dealCard() );
			}

			person.printHand();
			player.printHand();


			System.out.println( " \n ==============  Compared Cards  ========" );
			System.out.println( person.compareTo(player));
			System.out.println( " Your total value is " + person.value() + "\n");

			// ask player if they want to stand
			if(person.stand() )
				System.out.println( "You can Win" );
			else 
				System.out.println( "You have lost" );

			// have everyone throw in their cards
			person.newHand();
			player.newHand();
			System.out.print( "You have no cards \n" );
			System.out.print("Do you wish to play "+"another hand (y/n):");
			
		}while( getIn().nextLine().toLowerCase().charAt( 0 ) != 'n' );
		
		System.out.println( "Game Over! \n" );
	}
	
	
		
	/**
	 * stand() -- This method ask the user whether he wants to stand or fold.
	 * If the user decides to fold, then the user lose the game and the 
	 * computer wins. Otherwise, the user's cards are compare with the 
	 * computer's cards and whoever has the best cards wins.
	 *
	 * @param    boolean, true if Human player stands and false if not.
	 */
	public boolean	stand() {
		boolean stands = true;
		
		System.out.print( "Do you wish to stand (y/n):" );
		switch(getIn().nextLine().toLowerCase().charAt(0)){
		
		case 'y':
			return true;
		case 'n':
			return false;
			
		default:
			// Asks the player if they want to stand.
			System.out.println( "Wrong Answer, please try again" );
			stand();
		}
	   
		return stands;
	}



	public static Scanner getIn() {
		return in;
	}
}
