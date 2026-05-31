/*
 * Poker.java
 *
 * Version:
 * $Id: Computer.java,v 1.1 2013/06/11 02:24:22 pzg8794 Exp $
 *
 * Revisions:
 * $Log: Computer.java,v $
 * Revision 1.1  2013/06/11 02:24:22  pzg8794
 * Lab2 done!
 *
 */
import java.util.Scanner;

/**
 * A computer player for 2-card poker
 * This class is designed to play against a human player.
 * Every decision taken by this class depends on the card values access through
 * the class Card. Actions are taking by the class Poker which tells the winner.
 *
 * @author pg: Piter Garcia
 */
public class Computer{

	/*
	 * Facilitate cards management to the computer player.
	 */
	private PokerHand myCards;

	/*
	 * Default and minimum value to which a computer player will stand by.
	 */
	private static final int MIN_VALUE = (Ranks.QUEEN.getValue())*14 + 
			( Ranks.JACK.getValue() );

	/*
	 * Variable to hold command line input access.
	 */
	private static Scanner in = new Scanner( System.in );



	/**
	 * Initialize a computer player for 2-card poker
	 */
	public Computer() {
		myCards = new PokerHand();
	}



	/**
	 * adds a card to the computer's hand
	 * @param dealCard
	 */
	public void addCard(Card dealCard) {
		myCards.addCard(dealCard);
	} 



	/**
	 * clears out all the cards for the computer
	 */
	public void newHand() {
		myCards = new PokerHand();
	} 



	/**
	 * prints the hand in some "nice" format
	 */
	public void printHand() {
		if( this.stand()){
			System.out.println( "==============  House Cards ========" );
			myCards.printHand();
			System.out.println();
		}
	}

	

	/**
	 * determines if the computer should stand (vs fold). Note the computer
	 * will stand if it has >=50% chance of winning (Based on other work, a
	 * High Card hand with a Q and J beats 50% of the other possible hands).
	 * For the complete odds of winning see chance.html for tables 
	 * containing the chance to win for 2-cards of the same suit, and 2 
	 * cards of unmatched suits
	 *
	 * @param    boolean, true if value > or false if not.
	 */
	public boolean stand() {
		//int i = 0;
		//System.out.print(" asking pc \n");
		return ( myCards.value() >= MIN_VALUE);
	} 



	/**
	 * main method for a test driver that should test all the methods in the
	 * class. In this method I am testing all the functions of the class 
	 * Computer to make sure that it works correctly.
	 *
	 * @param    args  command line arguments are ignored
	 */
	public static void	main(String[] args) {

		System.out.println( "== Dealing Cards\n" );

		Computer person = new Computer();
		do{
			//give initial cards 
			for (int j=0; j<2; j++ )
				person.addCard( new Deck().dealCard() );

			System.out.println( " \n ==============  Your Cards  ========" );
			person.printHand();
			System.out.println( " The value of your cards are ");
			person.myCards.value();
			System.out.println(" Your total value is "+person.value()+ "\n");

			// ask player if they want to stand
			if(person.stand() )
				System.out.println( "You can Win" );
			else 
				System.out.println( "You have lost" );


			// have everyone throw in their cards
			person.newHand();
			System.out.print( "You have no cards \n" );

			System.out.print("Do you wish to play "+"another hand (y/n):");

		}while( getIn().nextLine().charAt( 0 ) != 'n' );

		System.out.println( "Game Over! \n" );
	}



	/**
	 * This function must come up with a single integer that represents the 
	 * value of the hand. You want the value to work such that a higher hand
	 * has a higher value. So the values should fall from highest to lowest as:
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
	 * @return myCards.value(), integer value of the computer player.
	 */
	public int	value() {
		return myCards.value();
	}


	
	/**
	 * Method to get access to the terminal.
	 * @return return input access to the terminal.
	 */
	public static Scanner getIn() {
		return in;
	}

}
